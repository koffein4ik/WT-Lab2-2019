package CarRentMigration.DAO;

import CarRentMigration.Beans.Car;
import CarRentMigration.DAO.parsers.domparser.DOMCarParser;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLCarDAOImpl implements CarDAO {

    private static final Logger log = Logger.getLogger(DOMCarParser.class);

    @Override
    public List<Car> getAllCars() throws DAOException {
        XMLValidator xmlValidator = new XMLValidator();
        String fileToCheck = "D:/CarRent2/src/main/java/CarRentMigration/XMLStorage/Cars.xml";
        String xsd = "D:/CarRent2/src/main/java/CarRentMigration/XSDStorage/Car.xsd";
        if (!xmlValidator.validate(fileToCheck, xsd)) {

            System.out.println("Not valid");
            log.error("XML is not valid.");
        }
        else {
            System.out.println("Valid");
            log.info("XML is valid");
            try {
                return getAllCarsFromFile(fileToCheck);
            }
            catch (DAOException d) {
                throw d;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public void saveAllCars(List<Car> cars) throws DAOException {

    }

    List<Car> getAllCarsFromFile(String fileName) throws DAOException {
        File file = new File(fileName);
        List<Car> cars = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("car");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Car car = new Car();
                    car.setModel(element.getElementsByTagName("model").item(0).getTextContent());
                    car.setBrand(element.getElementsByTagName("brand").item(0).getTextContent());
                    car.setNumber(element.getElementsByTagName("number").item(0).getTextContent());
                    car.setColor(element.getElementsByTagName("color").item(0).getTextContent());
                    car.setDriveRange(Integer.parseInt(element.getElementsByTagName("driveRange").item(0).getTextContent()));
                    car.setAccelerationTime(Integer.parseInt(element.getElementsByTagName("accelerationTime").item(0).getTextContent()));
                    car.setPassengerCapacity(Integer.parseInt(element.getElementsByTagName("passengerCapacity").item(0).getTextContent()));
                    car.setWeight(Integer.parseInt(element.getElementsByTagName("weight").item(0).getTextContent()));
                    car.setMaxSpeed(Integer.parseInt(element.getElementsByTagName("maxSpeed").item(0).getTextContent()));
                    car.setPaymentPerMinute(Integer.parseInt(element.getElementsByTagName("paymentPerMinute").item(0).getTextContent()));
                    car.setParkingId(Integer.parseInt(element.getElementsByTagName("parkingId").item(0).getTextContent()));
                    cars.add(car);
                    log.info("Car " + car.getNumber() + " loaded successfully");
                }
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            log.error(e.getMessage().toString());
            throw new DAOException(e);
        }
        return cars;
    }
}
