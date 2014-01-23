package tusk1;

import tusk1.Company.AviaCompany;

public class Tusk1 {



    public static void main(String[] args) {

        /*AviaCompany AC=new AviaCompany();
         IdComparator comp=new IdComparator();
         AC.buyPlanes(PassengerPlaneModels.Aerobus, Airports.NewYork, 2);
         AC.buyPlanes(PassengerPlaneModels.Boing, Airports.London, 2);
         AC.buyPlanes(CargoPlaneModels.Aerobus, Airports.NewYork, 2);
         AC.buyPlanes(CargoPlaneModels.Tu, Airports.Kiev, 2);
         System.out.println(AC);
         AC.takeOnFuel(2, 550, comp);
         AC.takeOnFuel(6,1000, comp);
         AC.arrangeDeparture(2, Airports.Beijing, comp);
         AC.arrangeDeparture(6, Airports.Beijing, comp);
         System.out.println(AC);
         //AC.makeFlight(2);
         //System.out.println(AC);
         AC.sortPlanes(new FlightDistanceComparator());
         AC.loadOnBoard(6, 1123.3, comp);
         AC.loadOnBoard(2, 114, comp);
         System.out.println(AC);
         System.out.println(AC.generalCargoCapacity());
         System.out.println(AC.generalPassengerCapacity());
         System.out.println(AC.findPlaneConsideringDiapason(9.0, 11.0 , new FuelPerDistDiapasonChecker()));
         System.out.println(Airports.values()[100]);*/
        AviaCompany.Start();

    }
}
