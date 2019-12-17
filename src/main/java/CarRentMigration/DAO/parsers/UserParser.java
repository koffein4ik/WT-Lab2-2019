package CarRentMigration.DAO.parsers;

import CarRentMigration.Beans.User;
import CarRentMigration.DAO.DAOException;

import java.util.List;

public interface UserParser {
    List<User> getAllUsers() throws DAOException;
}
