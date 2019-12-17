package CarRentMigration.DAO.parsers.staxparsers;

import CarRentMigration.Beans.Parking;
import CarRentMigration.DAO.DAOException;
import CarRentMigration.DAO.XMLValidator;
import CarRentMigration.DAO.parsers.ParkingParser;
import CarRentMigration.DAO.parsers.saxparsers.SAXParkingParser;
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

public class STAXParkingParser implements ParkingParser {
    private static final Logger log = Logger.getLogger(STAXParkingParser.class);
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
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader(fileName));
            String qName = "";
            String location = "";
            String carCapacity = "";
            String truckCapacity = "";
            String parkingId = "";
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
                        if (qName.equals("location"))
                            location = characters.getData();
                        if (qName.equals("carCapacity"))
                            carCapacity = characters.getData();
                        if (qName.equals("truckCapacity"))
                            truckCapacity = characters.getData();
                        if (qName.equals("parkingId"))
                            parkingId = characters.getData();
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equals("parking")) {
                            Parking parking = new Parking(location, Integer.parseInt(carCapacity),
                                    Integer.parseInt(truckCapacity), Integer.parseInt(parkingId));
                            parkings.add(parking);
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
