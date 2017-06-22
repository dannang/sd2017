package DataAccess;

import DataAccess.Drivers.MysqlDriver;
import DataAccess.Drivers.PostgressDriver;
import DataAccess.Drivers.SqlServerDriver;

/**
 * Define the types of databases that exist
 * Will be used by the access class to randomly pick one
 * 
 * @project sd2017
 * @author Dana Negrescu <dana.negrescu@evozon.com>
 */
public class AccessRepository {
    public MysqlDriver mysql;
    public PostgressDriver postgress;
    public SqlServerDriver sqlserver;

    public AbstractAccess drivers[] = {mysql,postgress,sqlserver};
}
