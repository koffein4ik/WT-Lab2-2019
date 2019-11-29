package CarRentMigration.Controller;

import CarRentMigration.Service.AdminService;
import CarRentMigration.Service.AdminServiceImpl;
import CarRentMigration.Service.ServiceException;
import org.apache.log4j.Logger;

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
}
