package CarRentMigration.DAO.parsers.saxparsers;

import CarRentMigration.Beans.User;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.XMLValidator;
import CarRentMigration.DAO.parsers.UserParser;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXUserParser implements UserParser {

    private static final Logger log = Logger.getLogger(SAXUserParser.class);
    private static ArrayList<User> users = new ArrayList<>();

    @Override
    public List<User> getAllUsers() throws DAOException {
        users.clear();
        XMLValidator xmlValidator = new XMLValidator();
        String fileToCheck = "D:/CarRent2/src/main/java/CarRentMigration/XMLStorage/Users.xml";
        String xsd = "D:/CarRent2/src/main/java/CarRentMigration/XSDStorage/User.xsd";
        if (!xmlValidator.validate(fileToCheck, xsd)) {

            System.out.println("Not valid");
            log.error("XML is not valid.");
            return new ArrayList<>();
        } else {
            System.out.println("Valid");
            log.info("XML is valid");
            try {
                getAllUsersFromFile(fileToCheck);
                return users;
            } catch (DAOException d) {
                throw d;
            }
        }
    }

    private void getAllUsersFromFile(String fileName) throws DAOException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            UserHandler userHandler = new UserHandler();
            parser.parse(new File(fileName), userHandler);

        } catch (SAXException | IOException | ParserConfigurationException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    private class UserHandler extends DefaultHandler {
        String name;
        String surname;
        String nickname;
        String phoneNumber;
        String passportNumber;
        String moneyOnBalance;
        String tripsCompleted;
        String lastElement;

        @Override
        public void startElement(String uri,
                                 String localName, String qName, Attributes attributes) throws SAXException {
            lastElement = qName;
        }

        @Override
        public void endElement(String uri,
                               String localName, String qName) throws SAXException {
            if (qName.equalsIgnoreCase("user")) {
                System.out.println("End Element :" + qName);
                User user = new User(name, surname, phoneNumber, passportNumber, nickname,
                        Integer.parseInt(moneyOnBalance),  Integer.parseInt(tripsCompleted));
                users.add(user);
            }
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            String information = new String(ch, start, length);
            information = information.replace("\n", "").trim();
            if (!information.isEmpty()) {
                if (lastElement.equals("name"))
                    name = information;
                if (lastElement.equals("surname"))
                    surname = information;
                if (lastElement.equals("nickname"))
                    nickname = information;
                if (lastElement.equals("moneyOnBalance"))
                    moneyOnBalance = information;
                if (lastElement.equals("tripsCompleted"))
                    tripsCompleted = information;
                if (lastElement.equals("phoneNumber"))
                    phoneNumber = information;
                if (lastElement.equals("passportNumber"))
                    passportNumber = information;
            }
        }
    }

}
