package ua.nure.borisenko.practice7.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.borisenko.practice7.constants.Constants;
import ua.nure.borisenko.practice7.constants.XML;
import ua.nure.borisenko.practice7.entity.CallPrice;
import ua.nure.borisenko.practice7.entity.Charging;
import ua.nure.borisenko.practice7.entity.FavoriteNumber;
import ua.nure.borisenko.practice7.entity.FavoriteNumbers;
import ua.nure.borisenko.practice7.entity.Parameters;
import ua.nure.borisenko.practice7.entity.Tariff;
import ua.nure.borisenko.practice7.entity.Tariffs;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXController extends DefaultHandler {

    //Xml file name.
    private String xmlFileName;

    //Current element.
    private String currentElement;

    //Main container.
    private Tariffs tariffs;

    //Tariff
    private Tariff tariff;

    //CallPrice
    private CallPrice callPrice;

    //Parameters
    private Parameters parameters;

    //Favorite Numbers
    private FavoriteNumbers favoriteNumbers;

    //Favorite Number
    private FavoriteNumber favoriteNumber;

   

    public SAXController(final String xmlFileName2) {
        this.xmlFileName = xmlFileName2;
    }

    public final void parse(final boolean validate)
            throws ParserConfigurationException, SAXException, IOException {

        // obtain sax parser factory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // XML document contains namespaces
        factory.setNamespaceAware(true);

        // set validation
        if (validate) {
            factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON,
                    true);
        }
        SAXParser parser = factory.newSAXParser();
        parser.parse(xmlFileName, this);
    }
    // ///////////////////////////////////////////////////////////
    // ERROR HANDLER IMPLEMENTATION
    // ///////////////////////////////////////////////////////////

    @Override
    public void error(org.xml.sax.SAXParseException e) throws SAXException {
        // if XML document not valid just throw exception
        throw e;
    }

    public final void startElement(final String uri, final String localName,
                                   final String qName, final Attributes attributes)
            throws SAXException {
        currentElement = localName;
        if (XML.TARIFFS.equalsTo(currentElement)) {
            tariffs = new Tariffs();
            return;
        }
        if (XML.TARIFF.equalsTo(currentElement)) {
            tariff = new Tariff();
            return;
        }
        if (XML.CALL_PRICE.equalsTo(currentElement)) {
            callPrice = new CallPrice();
            return;
        }
        if (XML.PARAMETERS.equalsTo(currentElement)) {
            parameters = new Parameters();
            return;
        }
        if (XML.FAVORITE_NUMBERS.equalsTo(currentElement)) {
            favoriteNumbers = new FavoriteNumbers();
            return;
        }
        if (XML.FAVORITE_NUMBER.equalsTo(currentElement)) {
            favoriteNumber = new FavoriteNumber();
            return;
        }

    }

    public final void characters(final char[] ch, final int start,
                                 final int length) throws SAXException {
        String elementText = new String(ch, start, length).trim();

        // return if content is empty
        if (elementText.isEmpty()) {
            return;
        }
        if (XML.NAME.equalsTo(currentElement)) {
            tariff.setName(elementText);
            return;
        }
        if (XML.OPERATOR_NAME.equalsTo(currentElement)) {
            tariff.setOperatorName(elementText);
            return;
        }
        if (XML.PAYROLL.equalsTo(currentElement)) {
            tariff.setPayroll(Integer.parseInt(elementText));
            return;
        }
        if (XML.IN_NETWORK.equalsTo(currentElement)) {
            callPrice.setInNetwork(Integer.parseInt(elementText));
            return;
        }
        if (XML.OUT_NETWORK.equalsTo(currentElement)) {
            callPrice.setOutNetwork(Integer.parseInt(elementText));
            return;
        }
        if (XML.CITY_NETWORK.equalsTo(currentElement)) {
            callPrice.setCityNetwork(Integer.parseInt(elementText));
            return;
        }
        if (XML.SMS.equalsTo(currentElement)) {
            tariff.setSMS(Integer.parseInt(elementText));
            return;
        }
        if (XML.FAVORITE_NUMBER.equalsTo(currentElement)) {
            favoriteNumber.setFavoriteNumber(elementText);
            favoriteNumbers.addFavoriteNumber(favoriteNumber);
            parameters.setFavoriteNumbers(favoriteNumbers);
            return;
        }
        if (XML.CHARGING.equalsTo(currentElement)) {
            parameters.setCharging(Charging.fromValue(elementText));
        }
        if (XML.PAYMENT_VALUE.equalsTo(currentElement)) {
            parameters.setPaymentValue(Integer.parseInt(elementText));
        }
    }

    public final void endElement(final String uri, final String localName,
                                 final String qName) throws SAXException {
        if (XML.TARIFF.equalsTo(localName)) {
            tariffs.addTariff(tariff);
            tariff = null;
            return;
        }
        if (XML.CALL_PRICE.equalsTo(localName)) {
            tariff.setCallPrice(callPrice);
            callPrice = null;
            return;
        }

        if (XML.PARAMETERS.equalsTo(localName)) {
            tariff.setParameters(parameters);
            parameters = null;
            return;
        }
    }

    public Tariffs getTariffs() {
        return tariffs;
    }

    public void setTariffs(Tariffs tariffs2) {
        this.tariffs = tariffs2;
    }

    public static void main(String[] args) throws Exception {
        // try to parse valid XML file (success)
        SAXController saxContr = new SAXController(Constants.VALID_XML_FILE);

        // do parse with validation on (success)
        saxContr.parse(true);

        // obtain container
        Tariffs tariffs = saxContr.getTariffs();

        // we have Test object at this point:
        System.out.println("====================================");
        System.out.print("Here is the test: \n" + tariffs);
        System.out.println("====================================");

        // now try to parse NOT valid XML (failed)
        saxContr = new SAXController(Constants.INVALID_XML_FILE);
        try {
            // do parse with validation on (failed)
            saxContr.parse(true);
        } catch (Exception ex) {
            System.err.println("====================================");
            System.err.println("Validation is failed:\n" + ex.getMessage());
            System.err
                    .println("Try to print tariffs object:" + saxContr.getTariffs());
            System.err.println("====================================");
        }

        // and now try to parse NOT valid XML with validation off (success)
        saxContr.parse(false);

        // we have tariffs object at this point:
        System.out.println("====================================");
        System.out.print("Here is the test: \n" + saxContr.getTariffs());
        System.out.println("====================================");
    }
}
