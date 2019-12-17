package CarRentMigration.DAO.parsers.saxparsers;

import CarRentMigration.Beans.Driver;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.XMLValidator;
import CarRentMigration.DAO.parsers.DriverParser;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXDriverParser implements DriverParser {

    private static final Logger log = Logger.getLogger(SAXDriverParser.class);
    private static ArrayList<Driver> drivers = new ArrayList<>();

    @Override
    public List<Driver> getAllDrivers() throws DAOException {
        drivers.clear();
        XMLValidator xmlValidator = new XMLValidator();
        String fileToCheck = "D:/CarRent2/src/main/java/CarRentMigration/XMLStorage/Drivers.xml";
        String xsd = "D:/CarRent2/src/main/java/CarRentMigration/XSDStorage/Driver.xsd";
        if (!xmlValidator.validate(fileToCheck, xsd)) {

            System.out.println("Not valid");
            log.error("XML is not valid.");
            return new ArrayList<>();
        } else {
            System.out.println("Valid");
            log.info("XML is valid");
            try {
                getAllDriversFromFile(fileToCheck);
                return drivers;
            } catch (DAOException d) {
                throw d;
            }
        }
    }

    private void getAllDriversFromFile(String fileName) throws DAOException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            DriverHandler driverHandler = new DriverHandler();
            parser.parse(new File(fileName), driverHandler);

        } catch (SAXException | IOException | ParserConfigurationException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    private class DriverHandler extends DefaultHandler {
        String name;
        String surname;
        String nickname;
        String phoneNumber;
        String passportNumber;
        String hoursWorkedThisMonth;
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
            if (qName.equalsIgnoreCase("driver")) {
                System.out.println("End Element :" + qName);
                Driver driver = new Driver(name, surname, phoneNumber, passportNumber, Integer.parseInt(salary),
                        Integer.parseInt(hoursWorkedThisMonth));
                drivers.add(driver);
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
                if (lastElement.equals("hoursWorkedThisMonth"))
                    hoursWorkedThisMonth = information;
                if (lastElement.equals("phoneNumber"))
                    phoneNumber = information;
                if (lastElement.equals("passportNumber"))
                    passportNumber = information;
            }
        }
    }

}
