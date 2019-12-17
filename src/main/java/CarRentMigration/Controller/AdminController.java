package CarRentMigration.Controller;

import CarRentMigration.Beans.Admin;
import CarRentMigration.DAO.parsers.ParserType;

import java.util.List;

public interface AdminController {
    void migrateAdminsToDb() throws ControllerException;
    List<Admin> getAllAdminsFromParser(ParserType parserType) throws ControllerException;
}
