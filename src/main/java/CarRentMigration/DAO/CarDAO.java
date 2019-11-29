package CarRentMigration.DAO;

import CarRentMigration.Beans.Car;

import java.util.List;

public interface CarDAO {
    List<Car> getAllCars() throws DAOException;
    void saveAllCars(List<Car> cars) throws DAOException;
}
