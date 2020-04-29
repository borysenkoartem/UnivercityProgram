package ua.nure.borisenko.practice7.controller;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import ua.nure.borisenko.practice7.constants.XML;
import ua.nure.borisenko.practice7.entity.CallPrice;
import ua.nure.borisenko.practice7.entity.Charging;
import ua.nure.borisenko.practice7.entity.FavoriteNumber;
import ua.nure.borisenko.practice7.entity.FavoriteNumbers;
import ua.nure.borisenko.practice7.entity.Parameters;
import ua.nure.borisenko.practice7.entity.Tariff;
import ua.nure.borisenko.practice7.entity.Tariffs;


public class STAXController {
    //XML file name
    private String xmlFileName;
    //main container
    private Tariffs tariffs;
    // tariffs
    private Tariff tariff;
    //CallPrice
    private CallPrice callPrice;
    //Parameters
    private Parameters parameters;
    //Favorite Numbers
    private FavoriteNumbers favoriteNumbers;
    //Favorite Number
    private FavoriteNumber favoriteNumber;
    


    public STAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void parse() throws XMLStreamException {
        String currentElement = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
        XMLEventReader reader = factory.createXMLEventReader(new StreamSource(xmlFileName));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            //skip anny empty content
            if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                continue;
            }
            // handler for start tags
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();
                if (XML.TARIFFS.equalsTo(currentElement)) {
                    tariffs = new Tariffs();
                }
                if (XML.TARIFF.equalsTo(currentElement)) {
                    tariff = new Tariff();
                }
                if (XML.CALL_PRICE.equalsTo(currentElement)) {
                    callPrice = new CallPrice();
                }
                if (XML.PARAMETERS.equalsTo(currentElement)) {
                    parameters = new Parameters();
                }
                if (XML.FAVORITE_NUMBERS.equalsTo(currentElement)) {
                    favoriteNumbers = new FavoriteNumbers();
                }
                if (XML.FAVORITE_NUMBER.equalsTo(currentElement)) {
                    favoriteNumber = new FavoriteNumber();
                }
            }

            // handler for contents
            if (event.isCharacters()) {
                Characters characters = event.asCharacters();
                String elementText = characters.getData();
                if (XML.NAME.equalsTo(currentElement)) {
                    tariff.setName(elementText);
                }
                if (XML.OPERATOR_NAME.equalsTo(currentElement)) {
                    tariff.setOperatorName(elementText);
                }
                if (XML.PAYROLL.equalsTo(currentElement)) {
                    tariff.setPayroll(Integer.parseInt(elementText));
                }
                if (XML.IN_NETWORK.equalsTo(currentElement)) {
                    callPrice.setInNetwork(Integer.parseInt(elementText));
                }
                if (XML.OUT_NETWORK.equalsTo(currentElement)) {
                    callPrice.setOutNetwork(Integer.parseInt(elementText));
                }
                if (XML.CITY_NETWORK.equalsTo(currentElement)) {
                    callPrice.setCityNetwork(Integer.parseInt(elementText));
                }
                if (XML.SMS.equalsTo(currentElement)) {
                    tariff.setSMS(Integer.parseInt(elementText));
                }
                if (XML.FAVORITE_NUMBER.equalsTo(currentElement)) {
                    favoriteNumber.setFavoriteNumber(elementText);
                    favoriteNumbers.addFavoriteNumber(favoriteNumber);
                    parameters.setFavoriteNumbers(favoriteNumbers);
                }
                if (XML.CHARGING.equalsTo(currentElement)) {
                    parameters.setCharging(Charging.fromValue(elementText));
                }
                if (XML.PAYMENT_VALUE.equalsTo(currentElement)) {
                    parameters.setPaymentValue(Integer.parseInt(elementText));
                }
            }

            // handler for end tags
            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();
                if (XML.TARIFF.equalsTo(localName)) {
                    tariffs.addTariff(tariff);
                    tariff = null;
                }
                if (XML.CALL_PRICE.equalsTo(localName)) {
                    tariff.setCallPrice(callPrice);
                    callPrice = null;
                }
                if (XML.PARAMETERS.equalsTo(localName)) {
                    tariff.setParameters(parameters);
                    parameters = null;
                }
            }
        }
        reader.close();
    }
    public Tariffs getTariffs(){
        return tariffs;
    }
}
