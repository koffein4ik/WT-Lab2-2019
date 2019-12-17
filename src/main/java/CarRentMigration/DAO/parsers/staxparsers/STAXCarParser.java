package CarRentMigration.DAO.parsers.staxparsers;

import CarRentMigration.Beans.Car;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.XMLValidator;
import CarRentMigration.DAO.parsers.CarParser;
import CarRentMigration.DAO.parsers.saxparsers.SAXCarParser;
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

public class STAXCarParser implements CarParser {
    private static final Logger log = Logger.getLogger(STAXCarParser.class);
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
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader(fileName));
            String qName = "";
            String model = "";
            String brand = "";
            String color = "";
            String number = "";
            String driveRange = "";
            String accelerationTime = "";
            String passengerCapacity = "";
            String weight = "";
            String maxSpeed = "";
            String paymentPerMinute = "";
            String parkingId = "";

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
                        if (qName.equals("model"))
                            model = characters.getData();
                        if (qName.equals("number"))
                            number = characters.getData();
                        if (qName.equals("color"))
                            color = characters.getData();
                        if (qName.equals("maxSpeed"))
                            maxSpeed = characters.getData();
                        if (qName.equals("weight"))
                            weight = characters.getData();
                        if (qName.equals("driveRange"))
                            driveRange = characters.getData();
                        if (qName.equals("brand"))
                            brand = characters.getData();
                        if (qName.equals("paymentPerMinute"))
                            paymentPerMinute = characters.getData();
                        if (qName.equals("passengerCapacity"))
                            passengerCapacity = characters.getData();
                        if (qName.equals("accelerationTime"))
                            accelerationTime = characters.getData();
                        if (qName.equals("parkingId"))
                            parkingId = characters.getData();
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equals("car")) {
                            Car car = new Car(Integer.parseInt(maxSpeed), Integer.parseInt(weight), Integer.parseInt(driveRange),
                                    brand, number, model, color, Integer.parseInt(paymentPerMinute),
                                    Integer.parseInt(passengerCapacity), Integer.parseInt(accelerationTime));
                            car.setParkingId(Integer.parseInt(parkingId));
                            cars.add(car);
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
