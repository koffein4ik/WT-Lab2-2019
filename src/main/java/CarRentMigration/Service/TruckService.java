package CarRentMigration.Service;

import CarRentMigration.Beans.Truck;
import CarRentMigration.DAO.parsers.ParserType;

import java.util.List;

public interface TruckService {
    List<Truck> getAllTrucks() throws ServiceException;
    void saveAllTrucks(List<Truck> trucks) throws ServiceException;
    List<Truck> getAllTrucksFromPaser(ParserType parserType) throws ServiceException;
}
