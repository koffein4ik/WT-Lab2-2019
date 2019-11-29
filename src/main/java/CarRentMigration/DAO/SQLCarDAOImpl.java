package CarRentMigration.DAO;

import CarRentMigration.Beans.Car;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class SQLCarDAOImpl implements CarDAO {

    private static final Logger log = Logger.getLogger(SQLCarDAOImpl.class);

    private String url = "jdbc:mysql://localhost:3306/carrent?serverTimezone=UTC";
    private String user = "root";
    private String password = "adminadmin";

    @Override
    public List<Car> getAllCars() throws DAOException {
        return null;
    }

    @Override
    public void saveAllCars(List<Car> cars) throws DAOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            log.info("Successfully connected to " + url);
            String sqlStatement = "insert into cars(brand, model, color, " +
                    "number, payment_per_minute, max_speed, drive_range, " +
                    "weight, acceleration_time, passenger_capacity, parking_id) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sqlSearchStatement = "select * from cars where brand=? and model=? and number=?";
            PreparedStatement preparedSelectStatement = connection.prepareStatement(sqlSearchStatement);
            PreparedStatement preparedInsertStatement = connection.prepareStatement(sqlStatement);
            for (Car c : cars) {
                preparedSelectStatement.setString(1, c.getBrand());
                preparedSelectStatement.setString(2, c.getModel());
                preparedSelectStatement.setString(3, c.getNumber());
                ResultSet rs = preparedSelectStatement.executeQuery();
                if (!rs.next()) {
                    String getParking = "select * from parkings where id=?";
                    PreparedStatement preparedParkingStatement = connection.prepareStatement(getParking);
                    preparedParkingStatement.setInt(1, c.getParkingId());
                    ResultSet parking = preparedParkingStatement.executeQuery();
                    if (parking.next()) {
                        preparedInsertStatement.setString(1, c.getBrand());
                        preparedInsertStatement.setString(2, c.getModel());
                        preparedInsertStatement.setString(3, c.getColor());
                        preparedInsertStatement.setString(4, c.getNumber());
                        preparedInsertStatement.setInt(5, c.getPaymentPerMinute());
                        preparedInsertStatement.setInt(6, c.getMaxSpeed());
                        preparedInsertStatement.setInt(7, c.getDriveRange());
                        preparedInsertStatement.setInt(8, c.getWeight());
                        preparedInsertStatement.setInt(9, c.getAccelerationTime());
                        preparedInsertStatement.setInt(10, c.getPassengerCapacity());
                        if (c.getParkingId() != 0) {
                            preparedInsertStatement.setInt(11, c.getParkingId());
                        } else {
                            preparedInsertStatement.setNull(11, 0);
                        }
                        preparedInsertStatement.executeUpdate();
                        log.info("Car " + c.getNumber() + " has been successfully added to db");
                    }
                    else {
                        System.out.println("There's no parking with such id in database");
                        log.error("No parking with such id in database");
                    }
                }
                else {
                    log.info("Car " + c.getNumber() + " already exists in db");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error(e.getMessage().toString());
            throw new DAOException(e);
        }
    }
}
