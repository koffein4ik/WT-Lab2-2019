package CarRentMigration.Service;

import CarRentMigration.Beans.Driver;
import CarRentMigration.DAO.parsers.ParserType;

import java.util.List;

public interface DriverService {
    List<Driver> getAllDrivers() throws ServiceException;
    void saveAllDrivers(List<Driver> drivers) throws ServiceException;
    List<Driver> getAllDriversFromPaser(ParserType parserType) throws ServiceException;
}
