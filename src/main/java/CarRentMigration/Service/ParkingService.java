package CarRentMigration.Service;

import CarRentMigration.Beans.Parking;

import java.util.List;

public interface ParkingService {
    List<Parking> getAllParkings() throws ServiceException;
    void saveAllParkings(List<Parking> parkings) throws ServiceException;
}
