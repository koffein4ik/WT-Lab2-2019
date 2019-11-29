package CarRentMigration.Service;

import CarRentMigration.Beans.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmins() throws ServiceException;
    void saveAllAdmins(List<Admin> admins) throws ServiceException;
}
