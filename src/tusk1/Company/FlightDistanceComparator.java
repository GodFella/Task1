

package tusk1.Company;

import java.util.Comparator;
import tusk1.Company.AviaCompany.FlightInformation;


public class FlightDistanceComparator implements Comparator {
    @Override
    public int compare(Object o1,Object o2){
        if(((FlightInformation)o1).distance<((FlightInformation)o2).distance)
            return -1;
        if(((FlightInformation)o1).distance==((FlightInformation)o2).distance)
            return 0;
        if(((FlightInformation)o1).distance>((FlightInformation)o2).distance)
            return 1; 
       return 0;
    }
}  
