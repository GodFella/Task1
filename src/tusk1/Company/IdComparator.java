

package tusk1.Company;

import java.util.Comparator;
import tusk1.Company.AviaCompany.FlightInformation;


public class IdComparator implements Comparator {
    @Override
    public int compare(Object o1,Object o2){
        if(((FlightInformation)o1).currentPlane.getId()<((Integer)o2).intValue())
            return -1;
        if(((FlightInformation)o1).currentPlane.getId()==((Integer)o2).intValue())
            return 0;
        if(((FlightInformation)o1).currentPlane.getId()>((Integer)o2).intValue())
            return 1; 
       return 0;
    }
}  
