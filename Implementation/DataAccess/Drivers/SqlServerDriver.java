package DataAccess.Drivers;

import DataAccess.AbstractAccess;

/**
 * Mysql driver class for setting a connection to the mysql instance
 *
 * @TODO Define constants (db statements)
 *
 * @project sd2017
 * @author Dana Negrescu <ng.danna@gmail.com>
 */
public class SqlServerDriver extends AbstractAccess{

    private String DATABASE_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String DATABASE_URL = "jdbc:sqlserver://localhost:3306/historicaldata";
    private String STATEMENT = "SELECT * from public.data WHERE date BETWEEN ? AND ? AND symbol = ? AND idexchange IN (SELECT idexchange FROM public.exchange WHERE marketsymbol = ?)";
}
 