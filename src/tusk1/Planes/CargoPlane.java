package tusk1.Planes;

import tusk1.MyExceptions.CapacityProblemException;
import tusk1.Planes.PlaneKinds.PlaneModels;
import tusk1.EngineClassification.Loadable;

public class CargoPlane extends Plane implements Loadable {

    private double loadCapacity;
    private double currentFlightCapacity;//колличество груза, который собираются перевезти на 
    //этом самолете в ближайшем рейсе

    public CargoPlane(PlaneModels model) {
        super(model);
        this.loadCapacity = model.getCapacity();
    }

    @Override
    public boolean capacityCheck() {
        if (currentFlightCapacity > loadCapacity) {
            currentFlightCapacity = 0;
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        if (currentFlightCapacity == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String kindOfPlane() {
        StringBuilder s = new StringBuilder();
        s.append("Cargo Plane:   ");
        s.append(currentFlightCapacity);
        s.append("/");
        s.append(loadCapacity);
        s.append(" of weight is taken, ");
        return s.toString();
    }

    @Override
    public void takeLoadOnBoard(double amount){
        currentFlightCapacity = amount;
        try {
            if (!capacityCheck()) {
                throw new CapacityProblemException("Cargo");
            }
        } catch (CapacityProblemException e) {
            throw e;
        }
    }

    @Override
    public double getCapacity(){
        return loadCapacity;
    }
    
    @Override
    public double getCurrentLoad(){
        return currentFlightCapacity;
    }
    
    
};
