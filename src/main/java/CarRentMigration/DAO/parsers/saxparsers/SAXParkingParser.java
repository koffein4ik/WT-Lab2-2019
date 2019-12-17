package CarRentMigration.DAO.parsers.saxparsers;

import CarRentMigration.Beans.Parking;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.XMLValidator;
import CarRentMigration.DAO.parsers.ParkingParser;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXParkingParser implements ParkingParser {

    private static final Logger log = Logger.getLogger(SAXParkingParser.class);
    private static ArrayList<Parking> parkings = new ArrayList<>();

    @Override
    public List<Parking> getAllParkings() throws DAOException {
        parkings.clear();
        XMLValidator xmlValidator = new XMLValidator();
        String fileToCheck = "D:/CarRent2/src/main/java/CarRentMigration/XMLStorage/Parkings.xml";
        String xsd = "D:/CarRent2/src/main/java/CarRentMigration/XSDStorage/Parking.xsd";

        if (!xmlValidator.validate(fileToCheck, xsd)) {

            System.out.println("Not valid");
            log.error("XML is not valid.");
            return new ArrayList<>();
        } else {
            System.out.println("Valid");
            log.info("XML is valid");
            try {
                getAllParkingsFromFile(fileToCheck);
                return parkings;
            } catch (DAOException d) {
                throw d;
            }
        }
    }

    private void getAllParkingsFromFile(String fileName) throws DAOException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            ParkingHandler parkingHandler = new ParkingHandler();
            parser.parse(new File(fileName), parkingHandler);

        } catch (SAXException | IOException | ParserConfigurationException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    private class ParkingHandler extends DefaultHandler {
        String location;
        String carCapacity;
        String truckCapacity;
        String parkingId;
        String lastElement;

        @Override
        public void startElement(String uri,
                                 String localName, String qName, Attributes attributes) throws SAXException {
            lastElement = qName;
        }

        @Override
        public void endElement(String uri,
                               String localName, String qName) throws SAXException {
            if (qName.equalsIgnoreCase("parking")) {
                System.out.println("End Element :" + qName);
                Parking parking = new Parking(location, Integer.parseInt(carCapacity), Integer.parseInt(truckCapacity),
                        Integer.parseInt(parkingId));
                parkings.add(parking);
            }
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            String information = new String(ch, start, length);
            information = information.replace("\n", "").trim();
            if (!information.isEmpty()) {
                if (lastElement.equals("location"))
                    location = information;
                if (lastElement.equals("carCapacity"))
                    carCapacity = information;
                if (lastElement.equals("truckCapacity"))
                    truckCapacity = information;
                if (lastElement.equals("parkingId"))
                    parkingId = information;
            }
        }
    }

}
