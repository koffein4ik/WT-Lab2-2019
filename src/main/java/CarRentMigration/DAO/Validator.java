package CarRentMigration.DAO;

public interface Validator {
    boolean validate(String fileToCheck, String rules) throws DAOException;
}
