package CarRentMigration.DAO;

import CarRentMigration.Beans.Truck;
import CarRentMigration.DAO.parsers.domparser.DOMTruckParser;
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

public class XMLTruckDAOImpl implements TruckDAO {

    private static final Logger log = Logger.getLogger(DOMTruckParser.class);

    @Override
    public void saveAllTrucks(List<Truck> cars) throws DAOException {

    }

    @Override
    public List<Truck> getAllTrucks() throws DAOException {
        XMLValidator xmlValidator = new XMLValidator();
        String fileToCheck = "D:/CarRent2/src/main/java/CarRentMigration/XMLStorage/Trucks.xml";
        String xsd = "D:/CarRent2/src/main/java/CarRentMigration/XSDStorage/Truck.xsd";
        if (!xmlValidator.validate(fileToCheck, xsd)) {

            System.out.println("Not valid");
            log.error("XML is not valid.");
        }
        else {
            System.out.println("Valid");
            log.info("XML is valid");
            try {
                return getAllTrucksFromFile(fileToCheck);
            }
            catch (DAOException d) {
                throw d;
            }
        }
        return new ArrayList<>();
    }

    List<Truck> getAllTrucksFromFile(String fileName) throws DAOException {
        File file = new File(fileName);
        List<Truck> trucks = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("truck");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Truck truck = new Truck();
                    truck.setModel(element.getElementsByTagName("model").item(0).getTextContent());
                    truck.setBrand(element.getElementsByTagName("brand").item(0).getTextContent());
                    truck.setNumber(element.getElementsByTagName("number").item(0).getTextContent());
                    truck.setColor(element.getElementsByTagName("color").item(0).getTextContent());
                    truck.setDriveRange(Integer.parseInt(element.getElementsByTagName("driveRange").item(0).getTextContent()));
                    truck.setCargoCapacity(Integer.parseInt(element.getElementsByTagName("cargoCapacity").item(0).getTextContent()));
                    truck.setCargoVolume(Integer.parseInt(element.getElementsByTagName("cargoVolume").item(0).getTextContent()));
                    truck.setWeight(Integer.parseInt(element.getElementsByTagName("weight").item(0).getTextContent()));
                    truck.setMaxSpeed(Integer.parseInt(element.getElementsByTagName("maxSpeed").item(0).getTextContent()));
                    truck.setPaymentPerMinute(Integer.parseInt(element.getElementsByTagName("paymentPerMinute").item(0).getTextContent()));
                    truck.setParkingId(Integer.parseInt(element.getElementsByTagName("parkingId").item(0).getTextContent()));
                    trucks.add(truck);
                    log.info("Truck " + truck.getNumber() + " loaded successfully");
                }
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            log.error(e.getMessage().toString());
            throw new DAOException(e);
            //System.out.println(e.toString());
        }
        return trucks;
    }
}
