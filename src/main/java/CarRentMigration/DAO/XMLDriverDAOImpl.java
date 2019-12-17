package CarRentMigration.DAO;

import CarRentMigration.Beans.Driver;
import CarRentMigration.DAO.parsers.domparser.DOMDriverParser;
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

public class XMLDriverDAOImpl implements DriverDAO {

    private static final Logger log = Logger.getLogger(DOMDriverParser.class);

    @Override
    public void saveAllDrivers(List<Driver> drivers) throws DAOException {

    }

    @Override
    public List<Driver> getAllDrivers() throws DAOException {
        XMLValidator xmlValidator = new XMLValidator();
        String fileToCheck = "D:/CarRent2/src/main/java/CarRentMigration/XMLStorage/Drivers.xml";
        String xsd = "D:/CarRent2/src/main/java/CarRentMigration/XSDStorage/Driver.xsd";
        if (!xmlValidator.validate(fileToCheck, xsd)) {

            System.out.println("Not valid");
            log.error("XML is not valid.");
        }
        else {
            System.out.println("Valid");
            log.info("XML is valid");
            try {
                return getAllDriversFromFile(fileToCheck);
            }
            catch (DAOException d) {
                throw d;
            }
        }
        return new ArrayList<>();
    }

    List<Driver> getAllDriversFromFile(String fileName) throws DAOException {
        File file = new File(fileName);
        List<Driver> drivers = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("driver");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Driver driver = new Driver();
                    driver.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    driver.setSurname(element.getElementsByTagName("surname").item(0).getTextContent());
                    driver.setPhoneNumber(element.getElementsByTagName("phoneNumber").item(0).getTextContent());
                    driver.setPassportNumber(element.getElementsByTagName("passportNumber").item(0).getTextContent());
                    driver.setHoursWorkedThisMonth(Integer.parseInt(element.getElementsByTagName("hoursWorkedThisMonth").item(0).getTextContent()));
                    driver.setSalary(Integer.parseInt(element.getElementsByTagName("salary").item(0).getTextContent()));
                    drivers.add(driver);
                    log.info("Driver " + driver.getName() + " " + driver.getSurname() + " loaded successfully");
                }
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            log.error(e.getMessage().toString());
            throw new DAOException(e);
            //System.out.println(e.toString());
        }
        return drivers;
    }
}
