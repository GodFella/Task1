package tusk1.Planes.PlaneKinds;
//пассажирских самолетов

public enum CargoPlaneModels implements PlaneModels {

    Aerobus(10000, 10, 1000), Tu(18000, 17, 1800);
    private double loadCapacity;
    private double fuelPerDist;
    private double fuelCapacity;

    CargoPlaneModels(double capacity, double fuelPerDist, double fuelCapacity) {
        this.loadCapacity = capacity;
        this.fuelPerDist = fuelPerDist;
        this.fuelCapacity = fuelCapacity;
    }

    @Override
    public double getCapacity() {
        return loadCapacity;
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
        for(Object c:CargoPlaneModels.values()){
            s.append(c.toString());
            s.append(", ");
        }
        return s.toString();
    }
 };
