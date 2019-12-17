package CarRentMigration.DAO.parsers.saxparsers;

import CarRentMigration.Beans.Truck;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.XMLValidator;
import CarRentMigration.DAO.parsers.TruckParser;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXTruckParser implements TruckParser {

    private static final Logger log = Logger.getLogger(SAXTruckParser.class);
    private static ArrayList<Truck> trucks = new ArrayList<>();

    @Override
    public List<Truck> getAllTrucks() throws DAOException {
        trucks.clear();
        XMLValidator xmlValidator = new XMLValidator();
        String fileToCheck = "D:/CarRent2/src/main/java/CarRentMigration/XMLStorage/Trucks.xml";
        String xsd = "D:/CarRent2/src/main/java/CarRentMigration/XSDStorage/Truck.xsd";
        if (!xmlValidator.validate(fileToCheck, xsd)) {

            System.out.println("Not valid");
            log.error("XML is not valid.");
            return new ArrayList<>();
        } else {
            System.out.println("Valid");
            log.info("XML is valid");
            try {
                getAllTrucksFromFile(fileToCheck);
                return trucks;
            } catch (DAOException d) {
                throw d;
            }
        }
    }

    private void getAllTrucksFromFile(String fileName) throws DAOException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            TruckHandler truckHandler = new TruckHandler();
            parser.parse(new File(fileName), truckHandler);

        } catch (SAXException | IOException | ParserConfigurationException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    private class TruckHandler extends DefaultHandler {
        String model;
        String brand;
        String color;
        String number;
        String driveRange;
        String cargoVolume;
        String cargoCapacity;
        String weight;
        String maxSpeed;
        String paymentPerMinute;
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
            if (qName.equalsIgnoreCase("truck")) {
                System.out.println("End Element :" + qName);
                Truck truck = new Truck(Integer.parseInt(maxSpeed), Integer.parseInt(weight), Integer.parseInt(driveRange),
                        brand, number, model, color, Integer.parseInt(paymentPerMinute),
                        Integer.parseInt(cargoCapacity), Integer.parseInt(cargoVolume));
                truck.setParkingId(Integer.parseInt(parkingId));
                trucks.add(truck);
            }
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            String information = new String(ch, start, length);
            information = information.replace("\n", "").trim();
            if (!information.isEmpty()) {
                if (lastElement.equals("model"))
                    model = information;
                if (lastElement.equals("number"))
                    number = information;
                if (lastElement.equals("color"))
                    color = information;
                if (lastElement.equals("maxSpeed"))
                    maxSpeed = information;
                if (lastElement.equals("weight"))
                    weight = information;
                if (lastElement.equals("driveRange"))
                    driveRange = information;
                if (lastElement.equals("brand"))
                    brand = information;
                if (lastElement.equals("paymentPerMinute"))
                    paymentPerMinute = information;
                if (lastElement.equals("cargoCapacity"))
                    cargoCapacity = information;
                if (lastElement.equals("cargoVolume"))
                    cargoVolume = information;
                if (lastElement.equals("parkingId"))
                    parkingId = information;
            }
        }
    }

}
