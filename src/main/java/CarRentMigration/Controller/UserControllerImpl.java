package CarRentMigration.Controller;

import CarRentMigration.Service.UserService;
import CarRentMigration.Service.UserServiceImpl;
import CarRentMigration.Service.ServiceException;
import org.apache.log4j.Logger;

public class UserControllerImpl implements UserController {

    private final Logger log = Logger.getLogger(UserControllerImpl.class);

    /**
     * Gets all users from the file and saves them to database
     * @throws ControllerException throws ControllerException if exception on lower level occured
     */
    @Override
    public void migrateUsersToDb() throws ControllerException {
        UserService userService = new UserServiceImpl();
        try {
            userService.saveAllUsers(userService.getAllUsers());
        }
        catch (ServiceException s) {
            log.error(s.getMessage());
            throw new ControllerException(s);
        }
    }
}
