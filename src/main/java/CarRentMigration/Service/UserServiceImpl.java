package CarRentMigration.Service;

import CarRentMigration.Beans.User;
import CarRentMigration.Beans.User;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.DAOFactory;
import CarRentMigration.DAO.UserDAO;
import CarRentMigration.DAO.parsers.ParserType;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final Logger log = Logger.getLogger(UserServiceImpl.class);
    public static UserDAO xmlUserDAO = DAOFactory.getFactory().getXMLUserDAO();
    public static UserDAO sqlUserDAO = DAOFactory.getFactory().getSQLUserDAO();

    /**
     * Returns list of all users using UserDAO
     * @return list of all users
     * @throws ServiceException throws ServiceException if exception on lower level occured
     */
    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return xmlUserDAO.getAllUsers();
        }
        catch (DAOException d) {
            log.error(d.getMessage());
            throw new ServiceException(d);
        }
    }

    /**
     * Saves all users to database
     * @param users users to save
     * @throws ServiceException throws Service exception if exception on lower level occured
     */
    @Override
    public void saveAllUsers(List<User> users) throws ServiceException {
        try {
            sqlUserDAO.saveAllUsers(users);
        }
        catch (DAOException d) {
            log.error(d.getMessage());
            throw new ServiceException(d);
        }
    }

    /**
     *
     * @param parserType parser to use
     * @return list of all users
     * @throws ServiceException throws Service exception if exception on lower level occured
     */
    @Override
    public List<User> getAllUsersFromPaser(ParserType parserType) throws ServiceException {
        try {
            return DAOFactory.getFactory().getUserParser(parserType).getAllUsers();
        } catch (DAOException d) {
            log.error(d.getMessage());
            throw new ServiceException(d);
        }
    }
}
