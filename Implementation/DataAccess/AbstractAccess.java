package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Abstract driver class for re-using common behavior for the drivers
 * @TODO add statement strings to be redefined in the other classes
 * 
 * @project sd2017
 * @author Dana Negrescu <ng.danna@gmail.com>
 */
public abstract class AbstractAccess implements AccessInterface {

    private String DATABASE_DRIVER;
    private String DATABASE_URL;
    private Connection connection;
    private String DATABASE_USERNAME = "root";
    private String DATABASE_PASSWORD = "root";

    public void disconnectFromDb() {
        if (connection == null)
        {
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
}