package Server.Util;

import org.json.JSONObject;

/**
 * Builds JSON from parsed computational method results
 * @author sidf
 *
 */
public class OutputBuilder {

	/**
	 * Generates a JSON string from the given parameters
	 * @param parsedUrl data used to build the JSON string
	 * @param serviceResult string that represents the 'coefficient' in the generated JSON
	 * @return a string, representing the JSON serialized parameters
	 */
	public static String getJsonForServiceResult(ParsedUrl parsedUrl, String serviceResult) {
		final JSONObject jsonObject = new JSONObject();

		String coefficient = serviceResult;
		
		String serviceName = parsedUrl.getServiceName();
		
		jsonObject.put("SERVICE_NAME", serviceName);
		jsonObject.put("SERVICE_CODE", serviceName);
		
		jsonObject.put("START_DATE", parsedUrl.getBeginDate());
		jsonObject.put("EXCHANGE", parsedUrl.getExchange());
		jsonObject.put("END_DATE", parsedUrl.getEndDate());
		jsonObject.put("SYM1", parsedUrl.getSymbol1());
		jsonObject.put("EXTRA", parsedUrl.getExtra());
		
		jsonObject.put("CORRELATION_COEFFIECIENT", coefficient);
		
		return jsonObject.toString();
	}
}
