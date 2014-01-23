package tusk1.Planes.AirportsKinds;

import static java.lang.Math.*;

//список аэропортов, которые использует наша Авиакомпания
public enum Airports {

    Beijing(85, 20), London(35, 35), Kiev(45, 40), NewYork(25, 35);
    private double longiness;//координаты долготы
    private double wideness;//координаты широты

    Airports(double l, double w) {
        longiness = l;
        wideness = w;
    }

    public double getX() {
        return this.wideness;
    }

    public double getY() {
        return this.longiness;
    }

    public static String show() {
        StringBuilder s = new StringBuilder();
        for (Object c : Airports.values()) {
            s.append(c.toString());
            s.append(", ");
        }
        return s.toString();
    }
};
