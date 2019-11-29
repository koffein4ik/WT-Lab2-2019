package CarRentMigration.Controller;

import CarRentMigration.Service.DriverService;
import CarRentMigration.Service.DriverServiceImpl;
import CarRentMigration.Service.ServiceException;
import org.apache.log4j.Logger;

public class DriverControllerImpl implements DriverController {

    private final Logger log = Logger.getLogger(DriverControllerImpl.class);

    @Override
    public void migrateDriversToDb() throws ControllerException {
        DriverService driverService = new DriverServiceImpl();
        try {
            driverService.saveAllDrivers(driverService.getAllDrivers());
        }
        catch (ServiceException s) {
            log.error(s.getMessage());
            throw new ControllerException(s);
        }
    }
}
