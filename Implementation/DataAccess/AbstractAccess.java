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
    private String STATEMENT = "SELECT * from data WHERE date BETWEEN ? AND ? AND symbol = ?;";
    private String INSERT_QUERY = "INSERT INTO data VALUES (NULL,?,?,?,?,?,?,?,?,0)";

    /**
     * Insert data in DB
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
    @Override
    public void insert(Date date, Float open, Float high, Float low, Float close, Float volume, Float adjClose, String symbol) {
        java.sql.Date dbDate = new java.sql.Date(date.getTime());

        try {
            connectToDb();
            PreparedStatement stmt = connection.prepareStatement(getInsertQuery());
            stmt.setDate(1, dbDate);
            stmt.setFloat(2, open);
            stmt.setFloat(3, high);
            stmt.setFloat(4, low);
            stmt.setFloat(5, close);
            stmt.setFloat(6, volume);
            stmt.setFloat(7, adjClose);
            stmt.setString(8, symbol);

            stmt.execute();

            disconnectFromDb();
        } catch (Exception e) {
            System.err.println("Got an exception during insert!");
            System.err.println(e.getMessage());
        }

    }

    /**
     * Accessing the DB to get the close data for specific symbol on a given
     * market during a given period
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

    public String getStatement() {
        return STATEMENT;
    }

    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    public String getInsertQuery() {
        return INSERT_QUERY;
    }

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
     *
     * @return
     */
    public Connection connectToDb() {
        if (connection == null) {
            try {
                Class.forName(getDatabaseDriver());
                connection = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            } catch (ClassNotFoundException e) {
                System.err.println(getClass() + " class not found!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println(getClass() + " error connecting to the database");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
