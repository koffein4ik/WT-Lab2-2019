package CarRentMigration.Controller;

import CarRentMigration.Service.TruckService;
import CarRentMigration.Service.TruckServiceImpl;
import CarRentMigration.Service.ServiceException;
import org.apache.log4j.Logger;

public class TruckControllerImpl implements TruckController {

    private final Logger log = Logger.getLogger(TruckControllerImpl.class);

    @Override
    public void migrateTrucksToDb() throws ControllerException {
        TruckService truckService = new TruckServiceImpl();
        try {
            truckService.saveAllTrucks(truckService.getAllTrucks());
        }
        catch (ServiceException s) {
            log.error(s.getMessage());
            throw new ControllerException(s);
        }
    }
}
