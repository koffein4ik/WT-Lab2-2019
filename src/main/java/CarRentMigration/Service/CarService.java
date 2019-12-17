package CarRentMigration.Service;

import CarRentMigration.Beans.Car;
import CarRentMigration.DAO.parsers.ParserType;

import java.util.List;

public interface CarService {
    List<Car> getAllCars() throws ServiceException;
    void saveAllCars(List<Car> cars) throws ServiceException;
    List<Car> getAllCarsFromPaser(ParserType parserType) throws ServiceException;
}
