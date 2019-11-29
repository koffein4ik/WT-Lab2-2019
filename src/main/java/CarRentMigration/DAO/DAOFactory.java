package CarRentMigration.DAO;

public class DAOFactory {
    private static final DAOFactory factory = new DAOFactory();

    private final UserDAO userDAOXML = new XMLUserDAOImpl();
    private final AdminDAO adminDAOXML = new XMLAdminDAOImpl();
    private final DriverDAO driverDAOXML = new XMLDriverDAOImpl();
    private final CarDAO carDAOXML = new XMLCarDAOImpl();
    private final TruckDAO truckDAOXML = new XMLTruckDAOImpl();
    private final ParkingDAO parkingDAOXML = new XMLParkingDAOImpl();
    private final UserDAO userDAOSQL = new SQLUserDAOImpl();
    private final AdminDAO adminDAOSQL = new SQLAdminDAOImpl();
    private final DriverDAO driverDAOSQL = new SQLDriverDAOImpl();
    private final CarDAO carDAOSQL = new SQLCarDAOImpl();
    private final TruckDAO truckDAOSQL = new SQLTruckDAOImpl();
    private final ParkingDAO parkingDAOSQL = new SQLParkingDAOImpl();

    public static DAOFactory getFactory() {
        return factory;
    }

    public UserDAO getXMLUserDAO() {
        return userDAOXML;
    }

    public AdminDAO getXMLAdminDAO() {
        return adminDAOXML;
    }

    public DriverDAO getXMLDriverDAO() {
        return driverDAOXML;
    }

    public CarDAO getXMLCarDAO() {
        return carDAOXML;
    }

    public TruckDAO getXMLTruckDAO() {
        return truckDAOXML;
    }

    public ParkingDAO getXMLParkingDAO() {
        return parkingDAOXML;
    }

    public DAOFactory getInstance() {
        return factory;
    }

    public UserDAO getSQLUserDAO() {
        return userDAOSQL;
    }

    public AdminDAO getSQLAdminDAO() {
        return adminDAOSQL;
    }

    public DriverDAO getSQLDriverDAO() {
        return driverDAOSQL;
    }

    public CarDAO getSQLCarDAO() {
        return carDAOSQL;
    }

    public TruckDAO getSQLTruckDAO() {
        return truckDAOSQL;
    }

    public ParkingDAO getSQLParkingDAO() {
        return parkingDAOSQL;
    }
}
