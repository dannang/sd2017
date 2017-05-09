package DataAccess.Drivers;

/**
 * Mysql driver class for setting a connection to the mysql instance
 * @TODO Define constants (db statements)
 * 
 * @project sd2017
 * @author Dana Negrescu <ng.danna@gmail.com>
 */
public class SqlServerDriver {
    
    private String DATABASE_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String DATABASE_URL = "jdbc:sqlserver://localhost:3306/historicaldata";
}
