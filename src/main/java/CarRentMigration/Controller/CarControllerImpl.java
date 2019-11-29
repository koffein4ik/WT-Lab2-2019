package CarRentMigration.Controller;

import CarRentMigration.Service.CarService;
import CarRentMigration.Service.CarServiceImpl;
import CarRentMigration.Service.ServiceException;
import org.apache.log4j.Logger;

public class CarControllerImpl implements CarController {

    private final Logger log = Logger.getLogger(CarControllerImpl.class);

    @Override
    public void migrateCarsToDb() throws ControllerException {
        CarService adminService = new CarServiceImpl();
        try {
            adminService.saveAllCars(adminService.getAllCars());
        }
        catch (ServiceException s) {
            log.error(s.getMessage());
            throw new ControllerException(s);
        }
    }
}
