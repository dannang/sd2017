package Server.Util;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import JavaCorrelation.*;
import Server.Util.Exceptions.NoSuchServiceException;
import Server.Util.Exceptions.CachedResultNotFoundException;

/**
 * Fetches data from computational methods. It also handles the cache.
 *
 * @author sidf
 *
 */
public class ServiceAgregator {

    private static Logger logger = Logger.getLogger(ServiceAgregator.class.getName());
    private static List<CachedResult> cachedResults = new ArrayList<CachedResult>();

    private ServiceAgregator() {

    }

    /**
     * Calls a computational method based on the provided parameters, and
     * returns it's response as a string
     *
     * @param cacheExpiration the amount of time (in seconds) for which cached
     * results are valid
     * @param parsedUrl information about the requested data (service, start and
     * end dates, symbols, etc.)
     * @return a numeric string, representing the result received from one of
     * the computational methods
     * @throws NoSuchServiceException if the requested service does not exist
     * @throws ParseException if the start/end date is ill formatted
     */
    public  String getDataFromUrl(int cacheExpiration, ParsedUrl parsedUrl) throws NoSuchServiceException, ParseException {
        if (cacheExpiration != 0) {
            try {
                CachedResult cachedResult = getCachedResult(parsedUrl);
                logger.info("Found cached result. Won't request data from service");
                return cachedResult.getCompMethodResult();
            } catch (CachedResultNotFoundException e) {
                // simply grab the a new result from the services
                logger.warning("No cached result was found. Will request data from service");
            }
        }

        Double result = null;
        String serviceName = parsedUrl.getServiceName();
        Svc2_TSYMSE svc2 = new Svc2_TSYMSE();

        if (!serviceName.equals("S1") && !serviceName.equals("S2")) {
            throw new NoSuchServiceException(String.format("Cannot provide results for service \"%s\"", serviceName));
        }

        Date startDate = new SimpleDateFormat("MM-dd-yyyy").parse(parsedUrl.getBeginDate());
        Date endDate = new SimpleDateFormat("MM-dd-yyyy").parse(parsedUrl.getEndDate());

        System.out.println("\n\nParameter values as seen by the server:");
        System.out.println("Symbol1:" + parsedUrl.getSymbol1());
        System.out.println("Start date:" + (startDate));
        System.out.println("End date:" + endDate);
        System.out.println("Symbol2 / Exchange2:" + parsedUrl.getExtra());
        System.out.println("Exchange1:" + parsedUrl.getExchange());
        System.out.println("\n\n");

        switch (parsedUrl.getServiceName()) {
            case "S1":
//                result = Svc1_OSYMDE.getSvc1(parsedUrl.getSymbol1(), startDate, endDate, parsedUrl.getExtra(), parsedUrl.getExchange());
                  result = svc2.getSvc2(parsedUrl.getSymbol1(), parsedUrl.getExtra(), startDate, endDate, parsedUrl.getExchange());
                break;
            case "S2":
                result = svc2.getSvc2(parsedUrl.getSymbol1(), parsedUrl.getExtra(), startDate, endDate, parsedUrl.getExchange());
                break;
        }

        String stringResult = result.toString();

        /**
         * add the current result to the cache list, only if caching is enabled
         * and the result is valid
         */
        if (result != null && cacheExpiration != 0) {
            cachedResults.add(new CachedResult(cacheExpiration, parsedUrl, stringResult));
        }

        return stringResult;
    }

    /**
     * Looks for a previously cached result that matches the provided
     * {@link ParsedUrl} instance.
     *
     * @param parsedUrl
     * @return a {@link CachedResult} instance, representing the matched cached
     * result
     * @throws Exception if a matching cached result is not found or if the
     * matched cached result has expired
     */
    private static CachedResult getCachedResult(ParsedUrl parsedUrl) throws CachedResultNotFoundException {
        List<CachedResult> cachedResultsCopy = new ArrayList<>(cachedResults);

        for (CachedResult cachedResult : cachedResultsCopy) {
            if (cachedResult.getParsedUrl().equals(parsedUrl)) {
                if (cachedResult.isExpired()) {
                    cachedResults.remove(cachedResult);
                    break;
                }
                return cachedResult;
            }
        }

        throw new CachedResultNotFoundException("No valid cached result is available");
    }
}
