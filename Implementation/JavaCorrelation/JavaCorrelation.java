package JavaCorrelation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JavaCorrelation {

    public void main(String[] args) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        java.util.Date date1 = format.parse("18/05/2017");
        java.util.Date date2 = format.parse("25/05/2017");

        double res1, res2;
        Svc1_OSYMDE osymde = new Svc1_OSYMDE();
        res1 = osymde.getSvc1("Russell 2000", date1, date2, "New York", "Chicago");
        System.out.print("\nThe Pearson coefficient for first service is = " + res1);

        Svc2_TSYMSE tsymse = new Svc2_TSYMSE();
        res2 = tsymse.getSvc2("Russell 2000", "Nikkei", date1, date2, "Chicago");
        System.out.print("\nThe Pearson coefficient for the second service service is = " + res2);
    }
}
