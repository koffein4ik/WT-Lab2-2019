package CarRentMigration.Service;

import CarRentMigration.Beans.Truck;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.DAOFactory;
import CarRentMigration.DAO.TruckDAO;
import org.apache.log4j.Logger;

import java.util.List;

public class TruckServiceImpl implements TruckService {
    private final Logger log = Logger.getLogger(TruckServiceImpl.class);
    public static TruckDAO xmlTruckDAO = DAOFactory.getFactory().getXMLTruckDAO();
    public static TruckDAO sqlTruckDAO = DAOFactory.getFactory().getSQLTruckDAO();

    @Override
    public List<Truck> getAllTrucks() throws ServiceException {
        try {
            return xmlTruckDAO.getAllTrucks();
        }
        catch (DAOException d) {
            log.error(d.getMessage());
            throw new ServiceException(d);
        }
    }

    @Override
    public void saveAllTrucks(List<Truck> trucks) throws ServiceException {
        try {
            sqlTruckDAO.saveAllTrucks(trucks);
        }
        catch (DAOException d) {
            log.error(d.getMessage());
            throw new ServiceException(d);
        }
    }
}
