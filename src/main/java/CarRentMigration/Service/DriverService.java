package CarRentMigration.Service;

import CarRentMigration.Beans.Driver;

import java.util.List;

public interface DriverService {
    List<Driver> getAllDrivers() throws ServiceException;
    void saveAllDrivers(List<Driver> drivers) throws ServiceException;
}
