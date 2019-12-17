package CarRentMigration.DAO;

import CarRentMigration.Beans.Admin;
import CarRentMigration.DAO.parsers.domparser.DOMAdminParser;
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

public class XMLAdminDAOImpl implements AdminDAO {

    private static final Logger log = Logger.getLogger(DOMAdminParser.class);

    @Override
    public void saveAllAdmins(List<Admin> Admins) throws DAOException {

    }

    @Override
    public List<Admin> getAllAdmins() throws DAOException {
        XMLValidator xmlValidator = new XMLValidator();
        String fileToCheck = "D:/CarRent2/src/main/java/CarRentMigration/XMLStorage/Admins.xml";
        String xsd = "D:/CarRent2/src/main/java/CarRentMigration/XSDStorage/Admin.xsd";
        if (!xmlValidator.validate(fileToCheck, xsd)) {

            System.out.println("Not valid");
            log.error("XML is not valid.");
        }
        else {
            System.out.println("Valid");
            log.info("XML is valid");
            try {
                return getAllAdminsFromFile(fileToCheck);
            }
            catch (DAOException d) {
                throw d;
            }
        }
        return new ArrayList<>();
    }

    List<Admin> getAllAdminsFromFile(String fileName) throws DAOException {
        File file = new File(fileName);
        List<Admin> admins = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("admin");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Admin admin = new Admin();
                    admin.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    admin.setSurname(element.getElementsByTagName("surname").item(0).getTextContent());
                    admin.setNickname(element.getElementsByTagName("nickname").item(0).getTextContent());
                    admin.setPhoneNumber(element.getElementsByTagName("phoneNumber").item(0).getTextContent());
                    admin.setPassportNumber(element.getElementsByTagName("passportNumber").item(0).getTextContent());
                    admin.setAccessLevel(Integer.parseInt(element.getElementsByTagName("accessLevel").item(0).getTextContent()));
                    admin.setUserRequestsAnswered(Integer.parseInt(element.getElementsByTagName("userRequestsAnswered").item(0).getTextContent()));
                    admin.setSalary(Integer.parseInt(element.getElementsByTagName("salary").item(0).getTextContent()));
                    admins.add(admin);
                    log.info("Admin " + admin.getNickname() + " loaded successfully");
                }
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            log.error(e.getMessage().toString());
            throw new DAOException(e);
            //System.out.println(e.toString());
        }
        return admins;
    }
}
