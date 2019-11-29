package CarRentMigration.DAO;

import CarRentMigration.Beans.Admin;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class SQLAdminDAOImpl implements AdminDAO {

    private static final Logger log = Logger.getLogger(SQLAdminDAOImpl.class);

    private String url = "jdbc:mysql://localhost:3306/carrent?serverTimezone=UTC";
    private String user = "root";
    private String password = "adminadmin";

    @Override
    public void saveAllAdmins(List<Admin> admins) throws DAOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            log.info("Successfully connected to " + url);
            String sqlStatement = "insert into drivers(name, surname, nickname, phone_number, passport_number," +
                    "access_level, user_requests_answered, salary) values(?, ?, ?, ?, ?, ?, ?, ?)";
            String sqlSearchStatement = "select * from drivers where name=? and surname=? and nickname=? and" +
                    " phone_number=? and passport_number=?";
            PreparedStatement preparedSelectStatement = connection.prepareStatement(sqlSearchStatement);
            PreparedStatement preparedInsertStatement = connection.prepareStatement(sqlStatement);
            for (Admin a : admins) {
                preparedSelectStatement.setString(1, a.getName());
                preparedSelectStatement.setString(2, a.getSurname());
                preparedSelectStatement.setString(3, a.getNickname());
                preparedSelectStatement.setString(4, a.getPhoneNumber());
                preparedSelectStatement.setString(5, a.getPassportNumber());
                ResultSet rs = preparedSelectStatement.executeQuery();
                if (!rs.next()) {
                    preparedInsertStatement.setString(1, a.getName());
                    preparedInsertStatement.setString(2, a.getSurname());
                    preparedInsertStatement.setString(3, a.getNickname());
                    preparedInsertStatement.setString(4, a.getPhoneNumber());
                    preparedInsertStatement.setString(5, a.getPassportNumber());
                    preparedInsertStatement.setInt(6, a.getAccessLevel());
                    preparedInsertStatement.setInt(7, a.getUserRequestsAnswered());
                    preparedInsertStatement.setInt(8, a.getSalary());
                    preparedInsertStatement.executeUpdate();
                    log.info("Admin " + a.getNickname() + " has been successfully added to db");
                }
                else {
                    log.info("Admin " + a.getNickname() + " already exists in db");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error(e.getMessage().toString());
            throw new DAOException(e);
        }
    }

    @Override
    public List<Admin> getAllAdmins() throws DAOException {
        return null;
    }
}
