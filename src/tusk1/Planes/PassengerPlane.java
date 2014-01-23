package tusk1.Planes;

import tusk1.MyExceptions.CapacityProblemException;
import tusk1.Planes.PlaneKinds.PlaneModels;
import tusk1.EngineClassification.Transport;

public class PassengerPlane extends Plane implements Transport {

    private int passengerCapacity;
    private int currentFlightCapacity;//колличество людей, которые собираются лететь на 
    //этом самолете в ближайшем рейсе

    public PassengerPlane(PlaneModels model) {
        super(model);
        this.passengerCapacity = (int) model.getCapacity();
    }

    @Override
    public boolean capacityCheck() {
        if (currentFlightCapacity > passengerCapacity) {
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
        s.append("Passenger Plane:   ");
        s.append(currentFlightCapacity);
        s.append("/");
        s.append(passengerCapacity);
        s.append(" of seats are taken, ");
        return s.toString();
    }

    @Override
    public int getCapacity() {
        return this.passengerCapacity;
    }

    @Override
    public int getCurrentLoad(){
        return this.currentFlightCapacity;
    }
    
    @Override
    public void takePeopleOnBoard(int amount) {
        currentFlightCapacity = amount;
        try {
            if (!capacityCheck()) {
                throw new CapacityProblemException("People");
            }
        } catch (CapacityProblemException e) {
            throw e;
        }
    }
};
