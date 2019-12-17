package CarRentMigration.Service;

import CarRentMigration.Beans.Admin;
import CarRentMigration.DAO.AdminDAO;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.DAOFactory;
import CarRentMigration.DAO.parsers.ParserType;
import org.apache.log4j.Logger;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private final Logger log = Logger.getLogger(AdminServiceImpl.class);
    public static AdminDAO xmlAdminDAO = DAOFactory.getFactory().getXMLAdminDAO();
    public static AdminDAO sqlAdminDAO = DAOFactory.getFactory().getSQLAdminDAO();

    @Override
    public List<Admin> getAllAdmins() throws ServiceException {
        try {
            return xmlAdminDAO.getAllAdmins();
        } catch (DAOException d) {
            log.error(d.getMessage());
            throw new ServiceException(d);
        }
    }

    @Override
    public List<Admin> getAllAdminsFromPaser(ParserType parserType) throws ServiceException {
        try {
            return DAOFactory.getFactory().getAdminParser(parserType).getAllAdmins();
        } catch (DAOException d) {
            log.error(d.getMessage());
            throw new ServiceException(d);
        }
    }

    @Override
    public void saveAllAdmins(List<Admin> admins) throws ServiceException {
        try {
            sqlAdminDAO.saveAllAdmins(admins);
        } catch (DAOException d) {
            log.error(d.getMessage());
            throw new ServiceException(d);
        }
    }
}
