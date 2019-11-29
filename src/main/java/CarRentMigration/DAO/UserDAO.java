package CarRentMigration.DAO;

import CarRentMigration.Beans.User;

import java.util.List;

public interface UserDAO {
    /**
     * Checks XML validity and returns list of users if XML is valid
     * @return list of Users
     * @throws DAOException throws DAOException if xml is not valid or error during opening file
     */
    List<User> getAllUsers() throws DAOException;

    /**
     * Saves all users from the list to database
     * @param users users to save
     * @throws DAOException throw DAOException if error occured during saving
     */
    void saveAllUsers(List<User> users) throws DAOException;
}
