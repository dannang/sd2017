package DataAccess;

import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;

/**
 * Driver Interface for setting the ways of using the data
 * Sets the function names that will be used by the model for processing data
 * Each of the behavior will be implemented by isolated classes
 * 
 * @project sd2017
 * @author Dana Negrescu <ng.danna@gmail.com>
 */
public interface AccessInterface {
   
    /**
     * Saving data in the database
     * 
     * @param date
     * @param open
     * @param high
     * @param low
     * @param close
     * @param volume
     * @param adjClose
     * @param symbol 
     */
    public void insert(Date date, Doable open, Doable high, Doable low, Doable close, Doable volume, Doable adjClose, String symbol);
    /**
     * Asks for the comparison of 2 different titles (symbols) on the same market
     * 
     * @param startDate - start of comparison interval 
     * @param endDate - end of comparison interval
     * @param nameSym - title name
     * @param exc - market name
     * @return
     * @throws SQLException 
     */
    public ArrayList<Float> getIntervalCloseDataBySymAndExc(Date startDate, Date endDate, String nameSym, String exc) throws SQLException;
}
