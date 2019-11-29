package CarRentMigration.Service;

import CarRentMigration.Beans.Parking;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.DAOFactory;
import CarRentMigration.DAO.ParkingDAO;
import org.apache.log4j.Logger;

import java.util.List;

public class ParkingServiceImpl implements ParkingService {
    private final Logger log = Logger.getLogger(ParkingServiceImpl.class);
    public static ParkingDAO xmlParkingDAO = DAOFactory.getFactory().getXMLParkingDAO();
    public static ParkingDAO sqlParkingDAO = DAOFactory.getFactory().getSQLParkingDAO();

    @Override
    public List<Parking> getAllParkings() throws ServiceException {
        try {
            return xmlParkingDAO.getAllParkings();
        }
        catch (DAOException d) {
            log.error(d.getMessage());
            throw new ServiceException(d);
        }
    }

    @Override
    public void saveAllParkings(List<Parking> parkings) throws ServiceException {
        try {
            sqlParkingDAO.saveAllParkings(parkings);
        }
        catch (DAOException d) {
            log.error(d.getMessage());
            throw new ServiceException(d);
        }
    }
}
