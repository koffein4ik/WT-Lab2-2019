package CarRentMigration.DAO.parsers.staxparsers;

import CarRentMigration.Beans.Admin;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.XMLValidator;
import CarRentMigration.DAO.parsers.AdminParser;
import CarRentMigration.DAO.parsers.saxparsers.SAXAdminParser;
import org.apache.log4j.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class STAXAdminParser implements AdminParser {
    private static final Logger log = Logger.getLogger(STAXAdminParser.class);
    private static ArrayList<Admin> admins = new ArrayList<>();

    @Override
    public List<Admin> getAllAdmins() throws DAOException {
        admins.clear();
        XMLValidator xmlValidator = new XMLValidator();
        String fileToCheck = "D:/CarRent2/src/main/java/CarRentMigration/XMLStorage/Admins.xml";
        String xsd = "D:/CarRent2/src/main/java/CarRentMigration/XSDStorage/Admin.xsd";
        if (!xmlValidator.validate(fileToCheck, xsd)) {

            System.out.println("Not valid");
            log.error("XML is not valid.");
            return new ArrayList<>();
        } else {
            System.out.println("Valid");
            log.info("XML is valid");
            try {
                getAllAdminsFromFile(fileToCheck);
                return admins;
            } catch (DAOException d) {
                throw d;
            }
        }
    }

    private void getAllAdminsFromFile(String fileName) throws DAOException {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader(fileName));
            String qName = "";
            String name = "";
            String surname = "";
            String nickname = "";
            String phoneNumber = "";
            String passportNumber = "";
            String accessLevel = "";
            String userRequestsAnswered = "";
            String salary = "";

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {
                    case '\n': {
                        event = eventReader.nextEvent();
                        break;
                    }
                    case XMLStreamConstants.START_ELEMENT: {
                        StartElement startElement = event.asStartElement();
                        qName = startElement.getName().getLocalPart();
                        break;
                    }
                    case XMLStreamConstants.CHARACTERS: {
                        Characters characters = event.asCharacters();
                        if (characters.getData().startsWith("\n")) break;
                        if (qName.equals("name"))
                            name = characters.getData();
                        if (qName.equals("surname"))
                            surname = characters.getData();
                        if (qName.equals("nickname"))
                            nickname = characters.getData();
                        if (qName.equals("salary"))
                            salary = characters.getData();
                        if (qName.equals("accessLevel"))
                            accessLevel = characters.getData();
                        if (qName.equals("userRequestsAnswered"))
                            userRequestsAnswered = characters.getData();
                        if (qName.equals("phoneNumber"))
                            phoneNumber = characters.getData();
                        if (qName.equals("passportNumber"))
                            passportNumber = characters.getData();
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equals("admin")) {
                            Admin admin = new Admin(name, surname, phoneNumber, passportNumber, nickname,
                                    Integer.parseInt(userRequestsAnswered), Integer.parseInt(salary), Integer.parseInt(accessLevel));
                            admins.add(admin);
                        }
                    }
                }

            }
        } catch (XMLStreamException | IOException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
    }
}
