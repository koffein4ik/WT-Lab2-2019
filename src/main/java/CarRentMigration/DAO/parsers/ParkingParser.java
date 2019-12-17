package CarRentMigration.DAO.parsers;

import CarRentMigration.Beans.Parking;
import CarRentMigration.DAO.DAOException;

import java.util.List;

public interface ParkingParser {
    List<Parking> getAllParkings() throws DAOException;
}
