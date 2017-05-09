package DataAccess.Drivers;

import DataAccess.AbstractAccess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;


/**
 * Driver class for the Postgress database
 * It will define the call and behavior for that database
 * 
 * @project sd2017
 * @author Dana Negrescu <ng.danna@gmail.com>
 */
public class PostgressDriver extends AbstractAccess {
    
    private String DATABASE_DRIVER = "com.postgresql.jdbc.Driver";
    private String DATABASE_URL = "jdbc:postgresql://localhost:3306/historicaldata";
    
}
