package CarRentMigration.Service;

import CarRentMigration.Beans.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars() throws ServiceException;
    void saveAllCars(List<Car> cars) throws ServiceException;
}
