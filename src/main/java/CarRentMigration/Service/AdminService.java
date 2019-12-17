package CarRentMigration.Service;

import CarRentMigration.Beans.Admin;
import CarRentMigration.DAO.parsers.ParserType;

import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmins() throws ServiceException;
    List<Admin> getAllAdminsFromPaser(ParserType parserType) throws ServiceException;
    void saveAllAdmins(List<Admin> admins) throws ServiceException;
}
