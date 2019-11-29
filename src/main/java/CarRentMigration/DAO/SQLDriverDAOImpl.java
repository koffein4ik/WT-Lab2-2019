package CarRentMigration.DAO;

import CarRentMigration.Beans.Driver;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class SQLDriverDAOImpl implements DriverDAO {

    private static final Logger log = Logger.getLogger(SQLDriverDAOImpl.class);

    private String url = "jdbc:mysql://localhost:3306/carrent?serverTimezone=UTC";
    private String user = "root";
    private String password = "adminadmin";

    @Override
    public void saveAllDrivers(List<Driver> drivers) throws DAOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            log.info("Successfully connected to " + url);
            String sqlStatement = "insert into drivers(name, surname, phone_number, passport_number," +
                    "salary, hours_worked_this_month) values(?, ?, ?, ?, ?, ?)";
            String sqlSearchStatement = "select * from drivers where name=? and surname=? and" +
                    " phone_number=? and passport_number=?";
            PreparedStatement preparedSelectStatement = connection.prepareStatement(sqlSearchStatement);
            PreparedStatement preparedInsertStatement = connection.prepareStatement(sqlStatement);
            for (Driver d : drivers) {
                preparedSelectStatement.setString(1, d.getName());
                preparedSelectStatement.setString(2, d.getSurname());
                preparedSelectStatement.setString(3, d.getPhoneNumber());
                preparedSelectStatement.setString(4, d.getPassportNumber());
                ResultSet rs = preparedSelectStatement.executeQuery();
                if (!rs.next()) {
                    preparedInsertStatement.setString(1, d.getName());
                    preparedInsertStatement.setString(2, d.getSurname());
                    preparedInsertStatement.setString(3, d.getPhoneNumber());
                    preparedInsertStatement.setString(4, d.getPassportNumber());
                    preparedInsertStatement.setInt(5, d.getSalary());
                    preparedInsertStatement.setInt(6, d.getHoursWorkedThisMonth());
                    preparedInsertStatement.executeUpdate();
                    log.info("Driver " + d.getName() + " " + d.getSurname() + " has been successfully added to db");
                }
                else {
                    log.info("Driver " + d.getName() + " " + d.getSurname() + " already exists in db");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error(e.getMessage().toString());
            throw new DAOException(e);
        }
    }

    @Override
    public List<Driver> getAllDrivers() throws DAOException {
        return null;
    }
}
