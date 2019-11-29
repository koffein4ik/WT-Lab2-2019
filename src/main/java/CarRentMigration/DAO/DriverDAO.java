package CarRentMigration.DAO;

import CarRentMigration.Beans.Driver;

import java.util.List;

public interface DriverDAO {
    void saveAllDrivers(List<Driver> drivers) throws DAOException;
    List<Driver> getAllDrivers() throws DAOException;
}
