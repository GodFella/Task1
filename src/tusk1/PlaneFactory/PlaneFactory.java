
package tusk1.PlaneFactory;
import tusk1.Planes.Plane;
import tusk1.Planes.PassengerPlane;
import tusk1.Planes.PlaneKinds.PlaneModels;
import tusk1.Planes.PlaneKinds.PassengerPlaneModels;
import tusk1.Planes.PlaneKinds.CargoPlaneModels;
import tusk1.Planes.CargoPlane;
//"конкретная" фабрика для пассажирских самолетов

public class PlaneFactory implements PlaneFactoryInterface{
    @Override
    public Plane buildPlane(PlaneModels model){
        if(model instanceof PassengerPlaneModels)
           return new PassengerPlane(model);
        if(model instanceof CargoPlaneModels) 
           return new CargoPlane(model);
        return new PassengerPlane(model);
    }
 };
