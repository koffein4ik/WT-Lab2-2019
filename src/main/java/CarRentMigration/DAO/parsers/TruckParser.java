package CarRentMigration.DAO.parsers;

import CarRentMigration.Beans.Truck;
import CarRentMigration.DAO.DAOException;

import java.util.List;

public interface TruckParser {
    List<Truck> getAllTrucks() throws DAOException;
}
