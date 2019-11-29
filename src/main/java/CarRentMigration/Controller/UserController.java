package CarRentMigration.Controller;

public interface UserController {

    /**
     * Gets all users from the file and saves them to database
     * @throws ControllerException throws ControllerException if exception on lower level occured
     */
    void migrateUsersToDb() throws ControllerException;
}
