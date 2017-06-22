package JavaCorrelation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import DataAccess.AbstractAccess;
import DataAccess.Access;

/* Serviciul 1 se refera la compararea aceluiasi titlu(simbol) pe doua piete diferite 
 (OSYMDE => O = one, SYM = symbol, D = different, E = exchanges).*/
public class Svc1_OSYMDE {

    /* Parametrii din cadrul metodei getSvc1 sunt introdusi de catre user. Astfel, pentru determinarea 
     corelatiei a unui titlu pe doua piete diferite utilizatorului i se vor solicita urmatoarele info :
     denumirea titlului de analizat (nameSym), limita inferioara si superioara a intervalului de tim de analizat
     (startDate, endDate), denumirea celor doua piete (exc1, exc2). */
    public double getSvc1(String nameSym, Date startDate, Date endDate, String exc1, String exc2) throws ParseException {

        double pearsonCoefficient;
        Access dataAccess = new Access();
        AbstractAccess driver = dataAccess.getDriver();

        ArrayList<Float> closeS1 = new ArrayList<Float>();
        ArrayList<Float> closeS2 = new ArrayList<Float>();

        try {
            closeS1 = driver.getIntervalCloseDataBySymAndExc(startDate, endDate, nameSym, exc1);
            closeS2 = driver.getIntervalCloseDataBySymAndExc(startDate, endDate, nameSym, exc2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("Valorile close ale " + nameSym + " pe piata din " + exc1);
        for (int i = 0; i < closeS1.size(); i++) {
            System.out.print(": " + closeS1.get(i) + ", ");
        }
        
        System.out.print("\nValorile close ale " + nameSym + " pe piata din " + exc2);
        for (int i = 0; i < closeS1.size(); i++) {
            System.out.print(": " + closeS2.get(i) + ", ");
        }
        
        PearsonCorrelation correlation = new PearsonCorrelation();
        pearsonCoefficient = correlation.getCorrelation(closeS1, closeS2);
        
        return pearsonCoefficient;
    }

}
