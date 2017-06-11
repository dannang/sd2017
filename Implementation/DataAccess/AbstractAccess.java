package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Abstract driver class for re-using common behavior for the drivers
 *
 * @TODO add statement strings to be redefined in the other classes
 *
 * @project sd2017
 * @author Dana Negrescu <ng.danna@gmail.com>
 */
public abstract class AbstractAccess implements AccessInterface {

    private String DATABASE_DRIVER;
    private String DATABASE_URL;
    public Connection connection;
    private String DATABASE_NAME = "historicaldata";
    private String DATABASE_USERNAME = "sd2017";
    private String DATABASE_PASSWORD = "sd2017";
    private String STATEMENT;

    /**
     * Disconnects from the DB
     */
    public void disconnectFromDb() {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
            connection = null;
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connects to DB
     * @return 
     */
    public Connection connectToDb() {
        if (connection == null) {
            try {
                Class.forName(getDatabaseDriver());
                connection = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * Accessing the DB to get the close data for specific symbol on a given market
     * during a given period
     * 
     * @param startDate 
     * @param endDate
     * @param nameSym
     * @param exc
     * @return
     * @throws SQLException 
     */
    public ArrayList<Float> getIntervalCloseDataBySymAndExc(Date startDate, Date endDate, String nameSym, String exc) throws SQLException {
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
        
        ArrayList<Float> list = new ArrayList<Float>();
        try {
            connectToDb();
            try (PreparedStatement st = connection.prepareStatement(getStatement())) {
                st.setDate(1, sqlStartDate);
                st.setDate(2, sqlEndDate);
                st.setString(3, nameSym);
                st.setString(4, exc);
                try (ResultSet rsp = st.executeQuery()) {
                    while (rsp.next()) {
                        list.add(rsp.getFloat(7));
                    }
                }
            }
            disconnectFromDb();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }

    public String getDatabaseDriver() {
        return DATABASE_DRIVER;
    }

    public String getDatabaseUrl() {
        return DATABASE_URL;
    }

    public String getDatabaseUsername() {
        return DATABASE_USERNAME;
    }

    public String getDatabasePassword() {
        return DATABASE_PASSWORD;
    }

    public String getStatement() 
    {
        return STATEMENT;
    }
    
    public String getDatabaseName()
    {
        return DATABASE_NAME;
    }
}
