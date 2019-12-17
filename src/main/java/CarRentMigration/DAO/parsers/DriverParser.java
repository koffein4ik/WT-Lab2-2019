package CarRentMigration.DAO.parsers;

import CarRentMigration.Beans.Driver;
import CarRentMigration.Beans.User;
import CarRentMigration.DAO.DAOException;

import java.util.List;

public interface DriverParser {
    List<Driver> getAllDrivers() throws DAOException;
}
