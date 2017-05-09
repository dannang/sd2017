package DataAccess.Drivers;

import DataAccess.AbstractAccess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.sql.*;

/**
 * Mysql driver class for setting a connection to the mysql instance
 * @TODO Define constants (db statements)
 * 
 * @project sd2017
 * @author Dana Negrescu <ng.danna@gmail.com>
 */
public class MysqlDriver extends AbstractAccess{

    private String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private String DATABASE_URL = "jdbc:mysql://localhost:3306/historicaldata";
}
