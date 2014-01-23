

package tusk1.PlaneFactory;
import tusk1.Planes.Plane;
import tusk1.Planes.PlaneKinds.PlaneModels;
import tusk1.Planes.AirportsKinds.Airports;
//"абстрактная фабрика" для создания самолетов

public interface PlaneFactoryInterface {
    public Plane buildPlane(PlaneModels model);
}
