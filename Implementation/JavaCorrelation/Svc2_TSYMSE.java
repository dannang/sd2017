package JavaCorrelation;

import DataAccess.AbstractAccess;
import DataAccess.Drivers.SqlServerDriver;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/* Serviciul 2 se refera la compararea a doua tirtluri(simboluri) diferite pe aceeasi piata
 (OSYMDE => T = two, SYM = symbol, S = same, E = exchange).*/
public class Svc2_TSYMSE {

    /* Parametrii din cadrul metodei getSvc2 sunt introdusi de catre user. Astfel, pentru determinarea 
     corelatiei a doua titluri diferite pe aceeasi piata utilizatorului i se vor solicita urmatoarele info :
     denumirea celor doua titluri de analizat (nameSym1, nameSym2), limita inferioara si superioara a intervalului de tim de analizat
     (startDate, endDate), denumirea pietei (exc). */
    public double getSvc2(String nameSym1, String nameSym2, Date startDate, Date endDate, String exc) throws ParseException {

        double pearsonCoefficient;
        AbstractAccess dataAccess = new SqlServerDriver();

        ArrayList<Float> closeS1 = new ArrayList<Float>();
        ArrayList<Float> closeS2 = new ArrayList<Float>();

        try {
            closeS1 = dataAccess.getIntervalCloseDataBySymAndExc(startDate, endDate, nameSym1, exc);
            closeS2 = dataAccess.getIntervalCloseDataBySymAndExc(startDate, endDate, nameSym2, exc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("\n\nValorile close ale " + nameSym1 + " pe piata din " + exc);
        for (int i = 0; i < closeS1.size(); i++) {
            System.out.print(": " + closeS1.get(i) + ", ");
        }
        
        System.out.print("\nValorile close ale " + nameSym2 + " pe piata din " + exc);
        for (int i = 0; i < closeS1.size(); i++) {
            System.out.print(": " + closeS2.get(i) + ", ");
        }

        PearsonCorrelation correlation = new PearsonCorrelation();
        pearsonCoefficient = correlation.getCorrelation(closeS1, closeS2);
        
        return pearsonCoefficient;
    }
}
