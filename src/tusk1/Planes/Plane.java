package tusk1.Planes;

import tusk1.EngineClassification.FuelConsumable;
import tusk1.Planes.PlaneKinds.PlaneModels;
import tusk1.MyExceptions.*;

public abstract class Plane implements Flyable, FuelConsumable {

    private double fuelPerDist;
    private PlaneModels model;
    private double fuelCapacity;
    private double currentFuel;
    private int planeId;
    private static int amount;

    protected Plane(PlaneModels model) {
        this.fuelPerDist = model.getFuelPerDist();
        this.model = model;
        this.fuelCapacity = model.getFuelCapacity();
        this.planeId = amount++;
    }

    public abstract boolean capacityCheck();

    public abstract boolean isEmpty();

    public abstract String kindOfPlane();

    @Override
    public void takeOnFuel(double value) {
        try {
            if (value + currentFuel > fuelCapacity) {
                throw new MoreFuelThanCanContain();
            } else {
                currentFuel += value;
            }
        } catch (MoreFuelThanCanContain ex) {
            throw ex;
        }
    }

    @Override
    public void useFuel(double value) {
        currentFuel -= value;
    }

    @Override
    public void fly(double distance) {
       useFuel(distance * fuelPerDist);
    }

    public int getId() {
        return this.planeId;
    }

    public PlaneModels getModel() {
        return this.model;
    }
 
    public double getFuelPerDist(){
        return fuelPerDist;
    }
    
    public double getCurrentFuel(){
        return currentFuel;
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("id= ");
        s.append(planeId);
        s.append(", ");
        s.append(kindOfPlane());
        s.append(model);
        s.append(", ");
        s.append(this.currentFuel);
        s.append("/");
        s.append(fuelCapacity);
        s.append(" in the tank and ");
        s.append(fuelPerDist);
        s.append(" is used per distance point, ");
        return s.toString();
    }
};
