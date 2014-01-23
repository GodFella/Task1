package tusk1.Company;

import static java.lang.Math.sqrt;
import java.util.Comparator;
import tusk1.DataStructures.MyArrayList;
import tusk1.Planes.*;
import tusk1.Planes.PlaneKinds.PlaneModels;
import tusk1.PlaneFactory.*;
import tusk1.Planes.AirportsKinds.Airports;
import tusk1.MyExceptions.*;
import java.util.Scanner;
import tusk1.Planes.PlaneKinds.CargoPlaneModels;
import tusk1.Planes.PlaneKinds.PassengerPlaneModels;
import tusk1.EngineClassification.Transport;
import tusk1.EngineClassification.Loadable;
/* В этом классе происходит управление самолетами и организация 
 их полетов
 */

public class AviaCompany {

    private MyArrayList flightInformations = new MyArrayList();
    private PlaneFactoryInterface fabric = new PlaneFactory();

    private void arrangeDeparture(Airports a, int id, Comparator comp) {
        try {
            Plane value = ((FlightInformation) flightInformations.get(flightInformations.indexOf(id, comp))).currentPlane;
            Airports port = ((FlightInformation) flightInformations.get(flightInformations.indexOf(id, comp))).currentAirport;
            if (value.getCurrentFuel() - Controller.getDistance(port, a) * value.getFuelPerDist() <= 0) {
                throw new NotEnoughFuel();
            }
        } catch (NotEnoughFuel e) {
            throw e;
        }
        ((FlightInformation) flightInformations.get(flightInformations.indexOf(id, comp))).destinationAirport = a;
        ((FlightInformation) flightInformations.get(flightInformations.indexOf(id, comp))).initDistance(a);
    }

    static class FlightInformation {

        public double distance;
        public Plane currentPlane;
        public Airports currentAirport;
        public Airports destinationAirport;

        public FlightInformation(Plane pl, Airports a) {
            currentAirport = a;
            destinationAirport = a;
            currentPlane = pl;
        }

        void initDistance(Airports destination) {
            distance = Controller.getDistance(currentAirport, destination);
        }

        void nullDistance() {
            distance = 0;
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder();
            s.append(currentPlane.toString());
            s.append(" now in ");
            s.append(currentAirport);
            if (currentAirport != destinationAirport) {
                s.append(" ,is flying to ");
                s.append(destinationAirport);
                s.append(" .The distance of the current flight is ");
                s.append(distance);
            }
            return s.toString();
        }
    }

    private static class Controller {

        private static Comparator c;

        private static void quickSort(Object[] Mass, int l, int r) {
            int i = l;
            int j = r;
            Object help;
            Object m = Mass[(i + j) / 2];
            while (i <= j) {
                while (c.compare(Mass[i], m) < 0) {
                    i++;
                }
                while (c.compare(Mass[j], m) > 0) {
                    j--;
                }
                if (i <= j) {
                    help = Mass[i];
                    Mass[i] = Mass[j];
                    Mass[j] = help;
                    i++;
                    j--;
                }
            }
            if (j > l) {
                quickSort(Mass, l, j);
            }
            if (i < r) {
                quickSort(Mass, i, r);
            }
        }

        public static void initComparator(Comparator comp) {
            c = comp;
        }

        public static void sort(MyArrayList list) {
            Object[] Help = list.toArray();
            quickSort(Help, 0, Help.length - 1);
            for (int i = 0; i < Help.length; i++) {
                list.set(i, Help[i]);
            }
        }

        public static double getDistance(Airports a, Airports b) {
            return sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
        }
    }

    public static class MainMenu {

        private Scanner sc = new Scanner(System.in);
        private int current;
        private AviaCompany AC = new AviaCompany();
        private IdComparator idComp = new IdComparator();
        private FlightDistanceComparator fComp = new FlightDistanceComparator();
        private FuelPerDistDiapasonChecker fuelPerCheck = new FuelPerDistDiapasonChecker();

        void mainPart() {
            System.out.println("        WELCOME to the menu of My Air Company");
            System.out.println("What do you want to do");
            System.out.println("1-show info, 2-buy planes, 3-take on fuel, 4-arrange the flight, ");
            System.out.println("5-make the flight, 6-sort the planes, 7-print general capacity, 8-find the proper plane, ");
            System.out.println("9-load plane with people or cargo, 10-exit");
            current = sc.nextInt();
            switch (current) {
                case 1:
                    showInfo();
                    break;
                case 2:
                    buyPlanes();
                    break;
                case 3:
                    takeOnFuel();
                    break;
                case 4:
                    arrangeFlight();
                    break;
                case 5:
                    fly();
                    break;
                case 6:
                    sortPlanes();
                    break;
                case 7:
                    generalCapacity();
                    break;
                case 8:
                    properPlane();
                    break;
                case 9:
                    loadPlane();
                    break;
                case 10:
                    break;
                default:
                    System.out.println("try again!");
                    mainPart();
                    break;
            }
        }

        private void showInfo() {
            System.out.println(AC);
            finish();
        }

        private void buySomeKindsOfPlanes(int ch) {
            System.out.println("    Remember that you can choose from such plane models:");
            if (ch == 1) {
                System.out.println(CargoPlaneModels.show());
            }
            if (ch == 2) {
                System.out.println(PassengerPlaneModels.show());
            }
            System.out.println("    Remember that you can choose from such airports:");
            System.out.println(Airports.show());
            System.out.println("    Enter the model ");
            String model = sc.next();
            System.out.println("    Enter the airport ");
            String airport = sc.next();
            System.out.println("    Enter the amount");
            current = sc.nextInt();
            try {
                if (ch == 1) {
                    AC.buyPlanes(CargoPlaneModels.valueOf(model), Airports.valueOf(airport), current);
                }
                if (ch == 2) {
                    AC.buyPlanes(PassengerPlaneModels.valueOf(model), Airports.valueOf(airport), current);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("   No such models or airports");
                System.out.println("   Try again");
                buySomeKindsOfPlanes(ch);
            }
            finish();
        }

        private void buyPlanes() {
            System.out.println("1-buy cargo planes, 2-buy passenger planes");
            current = sc.nextInt();
            switch (current) {
                case 1:
                    buySomeKindsOfPlanes(1);
                    break;
                case 2:
                    buySomeKindsOfPlanes(2);
                    break;
                default:
                    System.out.println("try again!");
                    buyPlanes();
                    break;
            }
        }

        void finish() {
            System.out.println("1-exit, 2-back to the main menu");
            current = sc.nextInt();
            switch (current) {
                case 1:
                    break;
                case 2:
                    mainPart();
                    break;
                default:
                    System.out.println("try again");
                    finish();
                    break;
            }
        }

        private void takeOnFuel() {
            System.out.println("   Enter the id of the plane you want to fill with fuel");
            current = sc.nextInt();
            System.out.println("   Enter the emount of fuel you want to load the plane in");
            double am = sc.nextDouble();
            try {
                AC.takeOnFuel(current, am, idComp);
            } catch (NullPointerException e) {
                System.out.println("   No such id");
                System.out.println("   Try again");
                takeOnFuel();
            } catch (MoreFuelThanCanContain e) {
                System.out.println(e);
                System.out.println("   Try again");
                takeOnFuel();
            }
            finish();
        }

        private void arrangeFlight() {
            System.out.println("   Enter the id of the plane you want to departure");
            current = sc.nextInt();
            System.out.println("    Remember that you can choose from such airports:");
            System.out.println(Airports.show());
            System.out.println("   Enter the airport you want the plane to fly to");
            String airport = sc.next();
            try {
                AC.arrangeDeparture(Airports.valueOf(airport), current, idComp);
            } catch (NotEnoughFuel e) {
                System.out.println(e);
                System.out.println("   Try again");
            } catch (IllegalArgumentException e) {
                System.out.println("   No such models or airports");
                System.out.println("   Try again");
                arrangeFlight();
            } catch (NullPointerException e) {
                System.out.println("   No such id");
                System.out.println("   Try again");
                takeOnFuel();
            }
            finish();
        }

        private void fly() {
            System.out.println("   Enter the id of the plane you want to start the flight");
            current = sc.nextInt();
            try {
                AC.makeFlight(current, idComp);
            } catch (NullPointerException e) {
                System.out.println("   No such id");
                System.out.println("   Try again");
                takeOnFuel();
            }
            finish();
        }

        private void sortPlanes() {
            System.out.println("   Considering what parametre would you like to sort the planes:");
            System.out.println("   1-the distance of the current flight");
            current = sc.nextInt();
            switch (current) {
                case 1:
                    Controller.initComparator(fComp);
                    Controller.sort(AC.flightInformations);
                    break;
                default:
                    System.out.println("try again");
                    sortPlanes();
                    break;
            }
            finish();
        }

        private void generalCapacity() {
            System.out.println("   Which type of planes do you want capacity to be calculated of:");
            System.out.println("1-cargo planes, 2-passenger planes");
            current = sc.nextInt();
            switch (current) {
                case 1:
                    System.out.println("The general capacity is");
                    System.out.println(AC.generalCargoCapacity());
                    break;
                case 2:
                    System.out.println("The general capacity is");
                    System.out.println(AC.generalPassengerCapacity());
                    break;
                default:
                    System.out.println("try again");
                    generalCapacity();
                    break;
            }
            finish();
        }

        private void properPlane() {
            System.out.println("   Considering what parametre would you like to find the plane?:");
            System.out.println("   1-the loose of fuel per distance point");
            current = sc.nextInt();
            switch (current) {
                case 1:
                    System.out.println("   Enter the lower bound");
                    double lb = sc.nextDouble();
                    System.out.println("   Enter the upper bound");
                    double ub = sc.nextDouble();
                    try {
                        System.out.println(AC.findPlaneConsideringDiapason(lb, ub, fuelPerCheck));
                    } catch (NoSuchPlane e) {
                        System.out.println(e);
                        System.out.println("   Try again");
                        properPlane();
                    }
                    break;
                default:
                    System.out.println("try again");
                    properPlane();
                    break;
            }
            finish();
        }

        private void loadPlane() {
            try {
                System.out.println("   Enter the id of the plane you want to load ");
                current = sc.nextInt();
                int checker;
                double amd;
                int amint;
                Plane plane = ((FlightInformation) AC.flightInformations.get(AC.flightInformations.indexOf(current, idComp))).currentPlane;
                if (plane instanceof Loadable) {
                    System.out.println("   Enter the emount of cargo you want to load the plane in ");
                    checker = 1;
                } else {
                    System.out.println("   Enter the emount of people you want to load the plane in ");
                    checker = 2;
                }
                if (checker == 1) {
                    amd = sc.nextDouble();
                    AC.loadOnBoard(current, amd, idComp);
                } else {
                    amint = sc.nextInt();
                    AC.loadOnBoard(current, amint, idComp);
                }
            } catch (CapacityProblemException e) {
                System.out.println(e);
                System.out.println("   Try again");
                loadPlane();
            } catch (NullPointerException e) {
                System.out.println("   No such id");
                System.out.println("   Try again");
                loadPlane();
            }
            finish();
        }
    };

    private void buyPlanes(PlaneModels pl, Airports airport, int amount) {
        for (int i = 0; i < amount; i++) {
            flightInformations.add(new FlightInformation(fabric.buildPlane(pl), airport));
        }
    }

    private void takeOnFuel(int id, double amount, Comparator comp) throws MoreFuelThanCanContain {
        ((FlightInformation) flightInformations.get(flightInformations.indexOf(id, comp))).currentPlane.takeOnFuel(amount);
    }

    private void makeFlight(int id, Comparator comp) {
        Airports current = ((FlightInformation) flightInformations.get(flightInformations.indexOf(id, comp))).currentAirport;
        Airports destination = ((FlightInformation) flightInformations.get(flightInformations.indexOf(id, comp))).destinationAirport;
        double distance = Controller.getDistance(current, destination);
        ((FlightInformation) flightInformations.get(flightInformations.indexOf(id, comp))).currentPlane.fly(distance);
        ((FlightInformation) flightInformations.get(flightInformations.indexOf(id, comp))).currentAirport=destination;
        ((FlightInformation) flightInformations.get(flightInformations.indexOf(id, comp))).nullDistance();
    }

    private void loadOnBoard(int id, int amount, Comparator comp) throws CapacityProblemException {
        Plane plane = ((FlightInformation) flightInformations.get(flightInformations.indexOf(id, comp))).currentPlane;
        ((Transport) plane).takePeopleOnBoard(amount);
    }

    private void loadOnBoard(int id, double amount, Comparator comp) throws CapacityProblemException {
        Plane plane = ((FlightInformation) flightInformations.get(flightInformations.indexOf(id, comp))).currentPlane;
        ((Loadable) plane).takeLoadOnBoard(amount);
    }

    private double generalCargoCapacity() {
        double generalCapacity = 0;
        for (int i = 0; i < flightInformations.size(); i++) {
            if (((FlightInformation) flightInformations.get(i)).currentPlane instanceof Loadable) {
                generalCapacity += ((Loadable) ((FlightInformation) flightInformations.get(i)).currentPlane).getCapacity();
            }
        }
        return generalCapacity;
    }

    private int generalPassengerCapacity() {
        int generalCapacity = 0;
        for (int i = 0; i < flightInformations.size(); i++) {
            if (((FlightInformation) flightInformations.get(i)).currentPlane instanceof PassengerPlane) {
                generalCapacity += ((Transport) ((FlightInformation) flightInformations.get(i)).currentPlane).getCapacity();
            }
        }
        return (int) generalCapacity;
    }

    private Plane findPlaneConsideringDiapason(Object leftBoarder, Object rightBoarder, DiapasonChecker c) {
        try {
            for (int i = 0; i < flightInformations.size(); i++) {
                if (c.ifIn(flightInformations.get(i), leftBoarder, rightBoarder)) {
                    return ((FlightInformation) flightInformations.get(i)).currentPlane;
                }

            }
            throw new NoSuchPlane();
        } catch (NoSuchPlane e) {
            throw e;
        }
    }

    public static void Start() {
        MainMenu menu = new MainMenu();
        menu.mainPart();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < flightInformations.size(); i++) {
            s.append((flightInformations.get(i)).toString());
            s.append("\n");
        }
        return s.toString();
    }
};
