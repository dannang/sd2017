package DataAccess;

import DataAccess.Drivers.MysqlDriver;
import DataAccess.Drivers.PostgressDriver;
import DataAccess.Drivers.SqlServerDriver;
import java.util.Date;
import java.util.Random;

/**
 * The given class will act as a load balancer It will write data to all
 * databases It will read data from random selected databases
 *
 * @project sd2017
 * @author Dana Negrescu <dana.negrescu@evozon.com>
 */
public class Access {

    private  MysqlDriver mysql;
    private  PostgressDriver postgress;
    private  SqlServerDriver sqlserver;
    private  final AbstractAccess[] dbtypes = {mysql, postgress, sqlserver};

    private AbstractAccess driver;

    public  AbstractAccess getDriver() {
        int rnd = new Random().nextInt(dbtypes.length);
        driver = dbtypes[rnd];
        
        return driver;
    }

    public void insertData(Date date, Float open, Float high, Float low, Float close, Float volume, Float adjClose, String symbol) {
        for (AbstractAccess db : dbtypes) {
            try {
                db.insert(date, open, high, low, close, volume, adjClose, symbol);
            } catch (Exception e) {
                System.err.println(db.getClass() + " encountered an error saving data!");
                System.err.println(e.getMessage());
            }
        }
    }
}
