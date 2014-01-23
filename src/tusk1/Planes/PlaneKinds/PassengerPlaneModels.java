package tusk1.Planes.PlaneKinds;
//пассажирских самолетов

public enum PassengerPlaneModels implements PlaneModels {

    Aerobus(200, 10, 1000), Boing(150, 6, 800);
    private int peopleCapacity;
    private double fuelPerDist;
    private double fuelCapacity;
    
    PassengerPlaneModels(int capacity, double fuelPerDist, double fuelCapacity) {
        this.peopleCapacity = capacity;
        this.fuelPerDist = fuelPerDist;
        this.fuelCapacity = fuelCapacity;
    }

    @Override
    public double getCapacity() {
        return (double) peopleCapacity;
    }

    @Override
    public double getFuelCapacity() {
        return fuelCapacity;
    }

    @Override
    public double getFuelPerDist() {
        return fuelPerDist;
    }

    public static String show(){
        StringBuilder s=new StringBuilder();
        for(Object c:PassengerPlaneModels.values()){
            s.append(c.toString());
            s.append(", ");
        }
        return s.toString();
    }

};
