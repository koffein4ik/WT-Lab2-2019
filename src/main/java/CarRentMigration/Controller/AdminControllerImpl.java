package CarRentMigration.Controller;

import CarRentMigration.Beans.Admin;
import CarRentMigration.DAO.parsers.ParserType;
import CarRentMigration.Service.AdminService;
import CarRentMigration.Service.AdminServiceImpl;
import CarRentMigration.Service.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class AdminControllerImpl implements AdminController {

    private final Logger log = Logger.getLogger(AdminServiceImpl.class);

    @Override
    public void migrateAdminsToDb() throws ControllerException {
        AdminService adminService = new AdminServiceImpl();
        try {
            adminService.saveAllAdmins(adminService.getAllAdmins());
        }
        catch (ServiceException s) {
            log.error(s.getMessage());
            throw new ControllerException(s);
        }
    }

    @Override
    public List<Admin> getAllAdminsFromParser(ParserType parserType) throws ControllerException {
        AdminService adminService = new AdminServiceImpl();
        try {
            return adminService.getAllAdminsFromPaser(parserType);
        } catch (ServiceException s) {
            log.error(s.getMessage());
            throw new ControllerException(s);
        }
    }
}
