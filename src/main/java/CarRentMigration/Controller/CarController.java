package CarRentMigration.Controller;

public interface CarController {
    void migrateCarsToDb() throws ControllerException;
}
