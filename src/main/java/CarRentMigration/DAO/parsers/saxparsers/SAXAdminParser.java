package CarRentMigration.DAO.parsers.saxparsers;

import CarRentMigration.Beans.Admin;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.XMLValidator;
import CarRentMigration.DAO.parsers.AdminParser;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXAdminParser implements AdminParser {

    private static final Logger log = Logger.getLogger(SAXAdminParser.class);
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
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            AdminHandler adminHandler = new AdminHandler();
            parser.parse(new File(fileName), adminHandler);

        } catch (SAXException | IOException | ParserConfigurationException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    private class AdminHandler extends DefaultHandler {
        String name;
        String surname;
        String nickname;
        String phoneNumber;
        String passportNumber;
        String accessLevel;
        String userRequestsAnswered;
        String salary;
        String lastElement;

        @Override
        public void startElement(String uri,
                                 String localName, String qName, Attributes attributes) throws SAXException {
            lastElement = qName;
        }

        @Override
        public void endElement(String uri,
                               String localName, String qName) throws SAXException {
            if (qName.equalsIgnoreCase("admin")) {
                System.out.println("End Element :" + qName);
                Admin admin = new Admin(name, surname, phoneNumber, passportNumber, nickname,
                        Integer.parseInt(userRequestsAnswered), Integer.parseInt(salary), Integer.parseInt(accessLevel));
                admins.add(admin);
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
                if (lastElement.equals("salary"))
                    salary = information;
                if (lastElement.equals("accessLevel"))
                    accessLevel = information;
                if (lastElement.equals("userRequestsAnswered"))
                    userRequestsAnswered = information;
                if (lastElement.equals("phoneNumber"))
                    phoneNumber = information;
                if (lastElement.equals("passportNumber"))
                    passportNumber = information;
            }
        }
    }

}
