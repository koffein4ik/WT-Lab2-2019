package CarRentMigration.DAO;

import CarRentMigration.Beans.Truck;

import java.util.List;

public interface TruckDAO {
    void saveAllTrucks(List<Truck> cars) throws DAOException;
    List<Truck> getAllTrucks() throws DAOException;
}
