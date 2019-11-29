package CarRentMigration.Service;

import CarRentMigration.Beans.Driver;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.DAOFactory;
import CarRentMigration.DAO.DriverDAO;
import org.apache.log4j.Logger;

import java.util.List;

public class DriverServiceImpl implements DriverService {
    private  final Logger log = Logger.getLogger(DriverServiceImpl.class);
    public static DriverDAO xmlDriverDAO = DAOFactory.getFactory().getXMLDriverDAO();
    public static DriverDAO sqlDriverDAO = DAOFactory.getFactory().getSQLDriverDAO();

    @Override
    public List<Driver> getAllDrivers() throws ServiceException {
        try {
            return xmlDriverDAO.getAllDrivers();
        }
        catch (DAOException d) {
            log.error(d.getMessage());
            throw new ServiceException(d);
        }
    }

    @Override
    public void saveAllDrivers(List<Driver> drivers) throws ServiceException {
        try {
            sqlDriverDAO.saveAllDrivers(drivers);
        }
        catch (DAOException d) {
            log.error(d.getMessage());
            throw new ServiceException(d);
        }
    }
}
