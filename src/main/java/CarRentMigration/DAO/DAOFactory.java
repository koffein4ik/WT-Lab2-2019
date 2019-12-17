package CarRentMigration.DAO;

import CarRentMigration.DAO.parsers.*;
import CarRentMigration.DAO.parsers.saxparsers.*;
import CarRentMigration.DAO.parsers.staxparsers.*;

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

    public AdminParser getAdminParser(ParserType parserType) {
        switch (parserType) {
            case DOM: {
                return new CarRentMigration.DAO.parsers.domparser.DOMAdminParser();
            }
            case SAX: {
                return new SAXAdminParser();
            }
            case STAX: {
                return new STAXAdminParser();
            }
        }
        return new CarRentMigration.DAO.parsers.domparser.DOMAdminParser();
    }

    public UserParser getUserParser(ParserType parserType) {
        switch (parserType) {
            case DOM: {
                return new CarRentMigration.DAO.parsers.domparser.DOMUserParser();
            }
            case SAX: {
                return new SAXUserParser();
            }
            case STAX: {
                return new STAXUserParser();
            }
        }
        return new CarRentMigration.DAO.parsers.domparser.DOMUserParser();
    }

    public DriverParser getDriverParser(ParserType parserType) {
        switch (parserType) {
            case DOM: {
                return new CarRentMigration.DAO.parsers.domparser.DOMDriverParser();
            }
            case SAX: {
                return new SAXDriverParser();
            }
            case STAX: {
                return new STAXDriverParser();
            }
        }
        return new CarRentMigration.DAO.parsers.domparser.DOMDriverParser();
    }

    public CarParser getCarParser(ParserType parserType) {
        switch (parserType) {
            case DOM: {
                return new CarRentMigration.DAO.parsers.domparser.DOMCarParser();
            }
            case SAX: {
                return new SAXCarParser();
            }
            case STAX: {
                return new STAXCarParser();
            }
        }
        return new CarRentMigration.DAO.parsers.domparser.DOMCarParser();
    }

    public TruckParser getTruckParser(ParserType parserType) {
        switch (parserType) {
            case DOM: {
                return new CarRentMigration.DAO.parsers.domparser.DOMTruckParser();
            }
            case SAX: {
                return new SAXTruckParser();
            }
            case STAX: {
                return new STAXTruckParser();
            }
        }
        return new CarRentMigration.DAO.parsers.domparser.DOMTruckParser();
    }

    public ParkingParser getParkingParser(ParserType parserType) {
        switch (parserType) {
            case DOM: {
                return new CarRentMigration.DAO.parsers.domparser.DOMParkingParser();
            }
            case SAX: {
                return new SAXParkingParser();
            }
            case STAX: {
                return new STAXParkingParser();
            }
        }
        return new CarRentMigration.DAO.parsers.domparser.DOMParkingParser();
    }
}
