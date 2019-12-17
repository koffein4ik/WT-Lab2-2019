package CarRentMigration.Service;

import CarRentMigration.Beans.Admin;
import CarRentMigration.Beans.User;
import CarRentMigration.DAO.parsers.ParserType;

import java.util.List;

public interface UserService {
    /**
     * Returns list of all users using UserDAO
     * @return list of all users
     * @throws ServiceException throws ServiceException if exception on lower level occured
     */
    List<User> getAllUsers() throws ServiceException;

    /**
     * Saves all users to database
     * @param users users to save
     * @throws ServiceException throws Service exception if exception on lower level occured
     */
    void saveAllUsers(List<User> users) throws ServiceException;

    /**
     *
     * @param parserType parser to use
     * @return list of all users
     * @throws ServiceException throws Service exception if exception on lower level occured
     */
    List<User> getAllUsersFromPaser(ParserType parserType) throws ServiceException;
}
