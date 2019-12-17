package CarRentMigration.DAO.parsers.domparser;

import CarRentMigration.Beans.Parking;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.ParkingDAO;
import CarRentMigration.DAO.XMLValidator;
import CarRentMigration.DAO.parsers.ParkingParser;
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

public class DOMParkingParser implements ParkingParser {

    private static final Logger log = Logger.getLogger(DOMParkingParser.class);

    @Override
    public List<Parking> getAllParkings() throws DAOException {
        XMLValidator xmlValidator = new XMLValidator();
        String fileToCheck = "D:/CarRent2/src/main/java/CarRentMigration/XMLStorage/Parkings.xml";
        String xsd = "D:/CarRent2/src/main/java/CarRentMigration/XSDStorage/Parking.xsd";
        if (!xmlValidator.validate(fileToCheck, xsd)) {

            System.out.println("Not valid");
            log.error("XML is not valid.");
        }
        else {
            System.out.println("Valid");
            log.info("XML is valid");
            try {
                return getAllParkingsFromFile(fileToCheck);
            }
            catch (DAOException d) {
                throw d;
            }
        }
        return new ArrayList<>();
    }

    List<Parking> getAllParkingsFromFile(String fileName) throws DAOException {
        File file = new File(fileName);
        List<Parking> parkings = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("parking");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Parking parking = new Parking();
                    parking.setLocation(element.getElementsByTagName("location").item(0).getTextContent());
                    parking.setCarCapacity(Integer.parseInt(element.getElementsByTagName("carCapacity").item(0).getTextContent()));
                    parking.setTruckCapacity(Integer.parseInt(element.getElementsByTagName("truckCapacity").item(0).getTextContent()));
                    parking.setParkingId(Integer.parseInt(element.getAttribute("id")));
                    parkings.add(parking);
                    log.info("Parking at " + parking.getLocation() + " loaded successfully");
                }
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            log.error(e.getMessage().toString());
            throw new DAOException(e);
            //System.out.println(e.toString());
        }
        return parkings;
    }
}
