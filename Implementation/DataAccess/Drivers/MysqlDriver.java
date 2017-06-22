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
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;

/**
 * Mysql driver class for setting a connection to the mysql instance
 *
 * @TODO Define constants (db statements)
 *
 * @project sd2017
 * @author Dana Negrescu <ng.danna@gmail.com>
 */
public class MysqlDriver extends AbstractAccess {

    private String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private String DATABASE_URL = "jdbc:mysql://172.19.0.4:3306/historicaldata";
//    private String STATEMENT = "SELECT * from data WHERE date BETWEEN ? AND ? AND symbol = ?;";
}
