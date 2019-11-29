package CarRentMigration.DAO;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class XMLValidator implements Validator {

    private static final Logger log = Logger.getLogger(XMLValidator.class);

    @Override
    public boolean validate(String fileToCheck, String rules) throws DAOException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = factory.newSchema(new File(rules));
            javax.xml.validation.Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(fileToCheck)));
            log.info(fileToCheck + " has passed check through " + rules);
            return true;
        }
        catch (IOException ioException) {
            log.error("Exception during IO operations: " + ioException.getMessage().toString());
            throw new DAOException(ioException);
        }
        catch (SAXException saxException) {
            log.warn(fileToCheck + " has'n passed check through " + rules);
            return false;
        }
    }
}
