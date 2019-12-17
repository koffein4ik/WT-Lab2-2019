package CarRentMigration.DAO.parsers.domparser;

import CarRentMigration.Beans.User;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.XMLValidator;
import CarRentMigration.DAO.parsers.UserParser;
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

/**
 * Class to work with XML files
 */
public class DOMUserParser implements UserParser {

    private static final Logger log = Logger.getLogger(DOMUserParser.class);

    /**
     * Checks XML for validity and returns list of users if XML is valid
     * @author Shuliak Roman
     * @return list of all users
     * @throws DAOException throws DAOException if xml is not valid or error during opening file
     */
    @Override
    public List<User> getAllUsers() throws DAOException {
        XMLValidator xmlValidator = new XMLValidator();
        String fileToCheck = "D:/CarRent2/src/main/java/CarRentMigration/XMLStorage/Users.xml";
        String xsd = "D:/CarRent2/src/main/java/CarRentMigration/XSDStorage/User.xsd";
        if (!xmlValidator.validate(fileToCheck, xsd)) {

            System.out.println("Not valid");
            log.error("XML is not valid.");
        }
        else {
            System.out.println("Valid");
            log.info("XML is valid");
            try {
                return getAllUsersFromFile(fileToCheck);
            }
            catch (DAOException d) {
                throw d;
            }
        }
        return new ArrayList<>();
    }

    /**
     * Gets all users from the specified file and returns list
     * @param fileName file with users in valid XML format
     * @return lsit of users from the file
     * @throws DAOException throws DAOException if xml is not valid or error during opening file
     */
    List<User> getAllUsersFromFile(String fileName) throws DAOException {
        File file = new File(fileName);
        List<User> users = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("user");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    User user = new User();
                    user.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    user.setSurname(element.getElementsByTagName("surname").item(0).getTextContent());
                    user.setNickname(element.getElementsByTagName("nickname").item(0).getTextContent());
                    user.setPhoneNumber(element.getElementsByTagName("phoneNumber").item(0).getTextContent());
                    user.setPassportNumber(element.getElementsByTagName("passportNumber").item(0).getTextContent());
                    user.setMoneyOnBalance(Integer.parseInt(element.getElementsByTagName("moneyOnBalance").item(0).getTextContent()));
                    user.setTripsCompleted(Integer.parseInt(element.getElementsByTagName("tripsCompleted").item(0).getTextContent()));
                    users.add(user);
                    log.info("User " + user.getNickname() + " loaded successfully");
                }
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            log.error(e.getMessage().toString());
            throw new DAOException(e);
        }
        return users;
    }
}
