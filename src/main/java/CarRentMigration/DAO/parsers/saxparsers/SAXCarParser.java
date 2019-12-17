package CarRentMigration.DAO.parsers.saxparsers;

import CarRentMigration.Beans.Car;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.XMLValidator;
import CarRentMigration.DAO.parsers.CarParser;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXCarParser implements CarParser {

    private static final Logger log = Logger.getLogger(SAXCarParser.class);
    private static ArrayList<Car> cars = new ArrayList<>();

    @Override
    public List<Car> getAllCars() throws DAOException {
        cars.clear();
        XMLValidator xmlValidator = new XMLValidator();
        String fileToCheck = "D:/CarRent2/src/main/java/CarRentMigration/XMLStorage/Cars.xml";
        String xsd = "D:/CarRent2/src/main/java/CarRentMigration/XSDStorage/Car.xsd";
        if (!xmlValidator.validate(fileToCheck, xsd)) {

            System.out.println("Not valid");
            log.error("XML is not valid.");
            return new ArrayList<>();
        } else {
            System.out.println("Valid");
            log.info("XML is valid");
            try {
                getAllCarsFromFile(fileToCheck);
                return cars;
            } catch (DAOException d) {
                throw d;
            }
        }
    }

    private void getAllCarsFromFile(String fileName) throws DAOException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            CarHandler carHandler = new CarHandler();
            parser.parse(new File(fileName), carHandler);

        } catch (SAXException | IOException | ParserConfigurationException e) {
            log.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    private class CarHandler extends DefaultHandler {
        String model;
        String brand;
        String color;
        String number;
        String driveRange;
        String accelerationTime;
        String passengerCapacity;
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
            if (qName.equalsIgnoreCase("car")) {
                System.out.println("End Element :" + qName);
                Car car = new Car(Integer.parseInt(maxSpeed), Integer.parseInt(weight), Integer.parseInt(driveRange),
                        brand, number, model, color, Integer.parseInt(paymentPerMinute),
                        Integer.parseInt(passengerCapacity), Integer.parseInt(accelerationTime));
                car.setParkingId(Integer.parseInt(parkingId));
                cars.add(car);
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
                if (lastElement.equals("passengerCapacity"))
                    passengerCapacity = information;
                if (lastElement.equals("accelerationTime"))
                    accelerationTime = information;
                if (lastElement.equals("parkingId"))
                    parkingId = information;
            }
        }
    }

}
