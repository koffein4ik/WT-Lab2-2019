package CarRentMigration.DAO.parsers;

import CarRentMigration.Beans.Car;
import CarRentMigration.DAO.DAOException;

import java.util.List;

public interface CarParser {
    List<Car> getAllCars() throws DAOException;
}
