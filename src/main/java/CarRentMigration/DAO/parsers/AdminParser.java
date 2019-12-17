package CarRentMigration.DAO.parsers;

import CarRentMigration.Beans.Admin;
import CarRentMigration.DAO.DAOException;

import java.util.List;

public interface AdminParser {
    List<Admin> getAllAdmins() throws DAOException;
}
