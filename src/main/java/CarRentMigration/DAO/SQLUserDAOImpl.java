package CarRentMigration.DAO;

import CarRentMigration.Beans.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class SQLUserDAOImpl implements UserDAO {

    private static final Logger log = Logger.getLogger(SQLUserDAOImpl.class);

    private String url = "jdbc:mysql://localhost:3306/carrent?serverTimezone=UTC";
    private String user = "root";
    private String password = "adminadmin";

    @Override
    public List<User> getAllUsers() throws DAOException {
        return null;
    }

    /**
     * Saves all users from the list to database
     * @param users users to save
     * @throws DAOException throw DAOException if error occured during saving
     */
    @Override
    public void saveAllUsers(List<User> users) throws DAOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            log.info("Successfully connected to " + url);
            String sqlStatement = "insert into users(name, surname, nickname, phone_number, passport_number," +
                    "money_on_balance, trips_completed) values(?, ?, ?, ?, ?, ?, ?)";
            String sqlSearchStatement = "select * from users where name=? and surname=? and nickname=? and" +
                    " phone_number=? and passport_number=?";
            PreparedStatement preparedSelectStatement = connection.prepareStatement(sqlSearchStatement);
            PreparedStatement preparedInsertStatement = connection.prepareStatement(sqlStatement);
            for (User u : users) {
                preparedSelectStatement.setString(1, u.getName());
                preparedSelectStatement.setString(2, u.getSurname());
                preparedSelectStatement.setString(3, u.getNickname());
                preparedSelectStatement.setString(4, u.getPhoneNumber());
                preparedSelectStatement.setString(5, u.getPassportNumber());
                ResultSet rs = preparedSelectStatement.executeQuery();
                if (!rs.next()) {
                    preparedInsertStatement.setString(1, u.getName());
                    preparedInsertStatement.setString(2, u.getSurname());
                    preparedInsertStatement.setString(3, u.getNickname());
                    preparedInsertStatement.setString(4, u.getPhoneNumber());
                    preparedInsertStatement.setString(5, u.getPassportNumber());
                    preparedInsertStatement.setInt(6, u.getMoneyOnBalance());
                    preparedInsertStatement.setInt(7, u.getTripsCompleted());
                    preparedInsertStatement.executeUpdate();
                    log.info("User " + u.getNickname() + " has been successfully added to db");
                }
                else {
                    log.info("User " + u.getNickname() + " already exists in db");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error(e.getMessage().toString());
            throw new DAOException(e);
        }
    }
}
