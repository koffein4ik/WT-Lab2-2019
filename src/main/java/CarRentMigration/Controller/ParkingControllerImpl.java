package CarRentMigration.Controller;

import CarRentMigration.Service.ParkingService;
import CarRentMigration.Service.ParkingServiceImpl;
import CarRentMigration.Service.ServiceException;
import org.apache.log4j.Logger;

public class ParkingControllerImpl implements ParkingController {

    private final Logger log = Logger.getLogger(ParkingControllerImpl.class);

    @Override
    public void migrateParkingsToDb() throws ControllerException {
        ParkingService parkingService = new ParkingServiceImpl();
        try {
            parkingService.saveAllParkings(parkingService.getAllParkings());
        }
        catch (ServiceException s) {
            log.error(s.getMessage());
            throw new ControllerException(s);
        }
    }
}
