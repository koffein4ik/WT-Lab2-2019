package CarRentMigration.DAO;

import CarRentMigration.Beans.Parking;

import java.util.List;

public interface ParkingDAO {
    void saveAllParkings(List<Parking> parkings) throws DAOException;
    List<Parking> getAllParkings() throws DAOException;
}
