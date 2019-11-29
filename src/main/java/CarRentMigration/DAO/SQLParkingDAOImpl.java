package CarRentMigration.DAO;

import CarRentMigration.Beans.Parking;
import CarRentMigration.Controller.ParkingControllerImpl;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class SQLParkingDAOImpl implements ParkingDAO {

    private static final Logger log = Logger.getLogger(SQLParkingDAOImpl.class);

    private String url = "jdbc:mysql://localhost:3306/carrent?serverTimezone=UTC";
    private String user = "root";
    private String password = "adminadmin";

    @Override
    public void saveAllParkings(List<Parking> parkings) throws DAOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            log.info("Successfully connected to " + url);
            String sqlStatement = "insert into parkings(id, location, car_capacity, truck_capacity) values(?, ?, ?, ?)";
            String sqlSearchStatement = "select * from parkings where location=?";
            PreparedStatement preparedSelectStatement = connection.prepareStatement(sqlSearchStatement);
            PreparedStatement preparedInsertStatement = connection.prepareStatement(sqlStatement);
            for (Parking p : parkings) {
                preparedSelectStatement.setString(1, p.getLocation());
                ResultSet rs = preparedSelectStatement.executeQuery();
                if (!rs.next()) {
                    preparedInsertStatement.setInt(1, p.getParkingId());
                    preparedInsertStatement.setString(2, p.getLocation());
                    preparedInsertStatement.setInt(3, p.getCarCapacity());
                    preparedInsertStatement.setInt(4, p.getTruckCapacity());
                    preparedInsertStatement.executeUpdate();
                    log.info("Parking at " + p.getLocation() + " has been successfully added to db");
                }
                else {
                    log.info("Parking at " + p.getLocation() + " already exists in db");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error(e.getMessage().toString());
            throw new DAOException(e);
        }
    }

    @Override
    public List<Parking> getAllParkings() throws DAOException {
        return null;
    }
}
