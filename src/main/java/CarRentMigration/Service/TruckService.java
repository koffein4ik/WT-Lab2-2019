package CarRentMigration.Service;

import CarRentMigration.Beans.Truck;

import java.util.List;

public interface TruckService {
    List<Truck> getAllTrucks() throws ServiceException;
    void saveAllTrucks(List<Truck> trucks) throws ServiceException;
}
