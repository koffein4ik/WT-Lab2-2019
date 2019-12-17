package CarRentMigration.Service;

import CarRentMigration.Beans.Car;
import CarRentMigration.DAO.CarDAO;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.DAOFactory;
import CarRentMigration.DAO.parsers.ParserType;
import org.apache.log4j.Logger;

import java.util.List;

public class CarServiceImpl implements CarService {
    private final Logger log = Logger.getLogger(CarServiceImpl.class);
    public static CarDAO xmlCarDAO = DAOFactory.getFactory().getXMLCarDAO();
    public static CarDAO sqlCarDAO = DAOFactory.getFactory().getSQLCarDAO();

    @Override
    public List<Car> getAllCars() throws ServiceException {
        try {
            return xmlCarDAO.getAllCars();
        }
        catch (DAOException d) {
            log.error(d.getMessage());
            throw new ServiceException(d);
        }
    }

    @Override
    public void saveAllCars(List<Car> cars) throws ServiceException {
        try {
            sqlCarDAO.saveAllCars(cars);
        }
        catch (DAOException d) {
            log.error(d.getMessage());
            throw new ServiceException(d);
        }
    }

    @Override
    public List<Car> getAllCarsFromPaser(ParserType parserType) throws ServiceException {
        try {
            return DAOFactory.getFactory().getCarParser(parserType).getAllCars();
        } catch (DAOException d) {
            log.error(d.getMessage());
            throw new ServiceException(d);
        }
    }
}
