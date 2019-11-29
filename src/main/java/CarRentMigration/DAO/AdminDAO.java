package CarRentMigration.DAO;

import CarRentMigration.Beans.Admin;

import java.util.List;

public interface AdminDAO {
    void saveAllAdmins(List<Admin> Admins) throws DAOException;
    List<Admin> getAllAdmins() throws DAOException;
}
