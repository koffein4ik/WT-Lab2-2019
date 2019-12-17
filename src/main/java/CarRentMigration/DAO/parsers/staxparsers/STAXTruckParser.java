package CarRentMigration.DAO.parsers.staxparsers;

import CarRentMigration.Beans.Truck;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.XMLValidator;
import CarRentMigration.DAO.parsers.TruckParser;
import CarRentMigration.DAO.parsers.saxparsers.SAXTruckParser;
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

public class STAXTruckParser implements TruckParser {
    private static final Logger log = Logger.getLogger(STAXTruckParser.class);
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
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader(fileName));
            String qName = "";
            String model = "";
            String brand = "";
            String color = "";
            String number = "";
            String driveRange = "";
            String cargoVolume = "";
            String cargoCapacity = "";
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
                        if (qName.equals("cargoCapacity"))
                            cargoCapacity = characters.getData();
                        if (qName.equals("cargoVolume"))
                            cargoVolume = characters.getData();
                        if (qName.equals("parkingId"))
                            parkingId = characters.getData();
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equals("truck")) {
                            Truck truck = new Truck(Integer.parseInt(maxSpeed), Integer.parseInt(weight), Integer.parseInt(driveRange),
                                    brand, number, model, color, Integer.parseInt(paymentPerMinute),
                                    Integer.parseInt(cargoCapacity), Integer.parseInt(cargoVolume));
                            truck.setParkingId(Integer.parseInt(parkingId));
                            trucks.add(truck);
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
