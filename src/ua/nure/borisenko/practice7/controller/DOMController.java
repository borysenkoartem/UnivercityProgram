package ua.nure.borisenko.practice7.controller;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DOMController {
    private String xmlFileName;

    // main container
    private Tariffs tariffs;

    public Tariffs getTariffs() {
        return tariffs;
    }

    public DOMController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    /**
     * Parses XML document.
     *
     * @param validate If true validate XML document against its XML schema.
     */
    public void parse(boolean validate) throws ParserConfigurationException, IOException, SAXException {
        // obtain DOM parser
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        // set properties for Factory

        // XML document contains namespaces
        documentBuilderFactory.setNamespaceAware(true);

        if (validate) {
            // turn validation on
            documentBuilderFactory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            documentBuilderFactory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        // set error handler
        documentBuilder.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException e) throws SAXException {
                // throw exception if XML is Not valid
                throw e;
            }
        });
        // parse XML document
        Document document = documentBuilder.parse(xmlFileName);

        // get root element
        Element root = document.getDocumentElement();
        // create container
        tariffs = new Tariffs();
        // obtain tariff nodes
        NodeList tariffNodes = root.getElementsByTagName(XML.TARIFF.value());
        //process tariff nodes
        for (int i = 0; i < tariffNodes.getLength(); i++) {
            Tariff tariff = getTariff(tariffNodes.item(i));
            tariffs.addTariff(tariff);
        }
    }

    /**
     * Extracts tariff object from the tariff XML node.
     *
     * @param qNode Tariff node.
     * @return tariff object.
     */
    private Tariff getTariff(Node qNode) {
        Tariff tariff = new Tariff();
        Element qElement = (Element) qNode;
        //set name
        Node qtNode = qElement.getElementsByTagName(XML.NAME.value()).item(0);
        tariff.setName(qtNode.getTextContent());
        //set operator name
        qtNode = qElement.getElementsByTagName(XML.OPERATOR_NAME.value()).item(0);
        tariff.setOperatorName(qtNode.getTextContent());
        //set payroll
        qtNode = qElement.getElementsByTagName(XML.PAYROLL.value()).item(0);
        tariff.setPayroll(Integer.parseInt(qtNode.getTextContent()));
        //set sms
        qtNode = qElement.getElementsByTagName(XML.SMS.value()).item(0);
        tariff.setSMS(Integer.parseInt(qtNode.getTextContent()));
        //set call price
        tariff.setCallPrice(getCallPrice(qElement.getElementsByTagName(XML.CALL_PRICE.value()).item(0)));
        //set parameters
        tariff.setParameters(getParameters(qElement.getElementsByTagName(XML.PARAMETERS.value()).item(0)));

        return tariff;
    }

    private static CallPrice getCallPrice(Node qNode) {
        CallPrice callPrice = new CallPrice();
        Element qElement = (Element) qNode;
        //set inNetwork
        Node qtNode = qElement.getElementsByTagName(XML.IN_NETWORK.value()).item(0);
        callPrice.setInNetwork(Integer.parseInt(qtNode.getTextContent()));
        //set outNetwork
        qtNode = qElement.getElementsByTagName(XML.OUT_NETWORK.value()).item(0);
        callPrice.setOutNetwork(Integer.parseInt(qtNode.getTextContent()));
        //set cityNetwork
        qtNode = qElement.getElementsByTagName(XML.CITY_NETWORK.value()).item(0);
        callPrice.setCityNetwork(Integer.parseInt(qtNode.getTextContent()));
        return callPrice;
    }

    private Parameters getParameters(Node qNode) {
        Parameters parameters = new Parameters();
        Element qElement = (Element) qNode;
        //set payroll
        Node qtNode = qElement.getElementsByTagName(XML.PAYMENT_VALUE.value()).item(0);
        parameters.setPaymentValue(Integer.parseInt(qtNode.getTextContent()));
        // set charging
        qtNode = qElement.getElementsByTagName(XML.CHARGING.value()).item(0);
        parameters.setCharging(Charging.fromValue(qtNode.getTextContent()));
        //set favorite numbers
        parameters.setFavoriteNumbers(getFavoriteNumbers(qElement
        		.getElementsByTagName(XML.FAVORITE_NUMBERS.value())
        		.item(0)));
        return parameters;
    }

    private FavoriteNumbers getFavoriteNumbers(Node qNode) {
        FavoriteNumbers favoriteNumbers = new FavoriteNumbers();
        Element qElement = (Element) qNode;
        NodeList favoriteNumbersNodeList = qElement.getElementsByTagName(XML.FAVORITE_NUMBER.value());
        for (int i = 0; i < favoriteNumbersNodeList.getLength(); i++) {
            FavoriteNumber favoriteNumber = getFavoriteNumber(favoriteNumbersNodeList.item(i));
            favoriteNumbers.addFavoriteNumber(favoriteNumber);
        }
        return favoriteNumbers;
    }

    public FavoriteNumber getFavoriteNumber(Node qNode) {
        FavoriteNumber favoriteNumber = new FavoriteNumber();
        Element qElement = (Element) qNode;
        favoriteNumber.setFavoriteNumber(qElement.getTextContent());
        return favoriteNumber;
    }

    public static Document getDocument(Tariffs tariffs) throws ParserConfigurationException {
        // obtain DOM parser
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        // set properties for Factory

        // XML document contains namespaces
        documentBuilderFactory.setNamespaceAware(true);

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        // create root element
        Element tariffsElement = document.createElement(XML.TARIFFS.value());
        // add root element
        document.appendChild(tariffsElement);
        //add tariff element
        for (Tariff tariff : tariffs.getTariff()) {
            //add tariff
            Element tariffElement = document.createElement(XML.TARIFF.value());
            tariffsElement.appendChild(tariffElement);
            //add name
            Element nameElement = document.createElement(XML.NAME.value());
            nameElement.setTextContent(tariff.getName());
            tariffElement.appendChild(nameElement);
            //add operator name
            Element operatorNameElement = document.createElement(XML.OPERATOR_NAME.value());
            operatorNameElement.setTextContent(tariff.getOperatorName());
            tariffElement.appendChild(operatorNameElement);
            //add Payroll
            Element payrollElement = document.createElement(XML.PAYROLL.value());
            payrollElement.setTextContent(String.valueOf(tariff.getPayroll()));
            tariffElement.appendChild(payrollElement);
            //add Call Price
            Element callPriceElement = document.createElement(XML.CALL_PRICE.value());
            tariffElement.appendChild(callPriceElement);
            //add inNetwork
            Element inNetworkElement = document.createElement(XML.IN_NETWORK.value());
            inNetworkElement.setTextContent(String.valueOf(tariff.getCallPrice().getInNetwork()));
            callPriceElement.appendChild(inNetworkElement);
            //add OutNetwork
            Element outNetworkElement = document.createElement(XML.OUT_NETWORK.value());
            outNetworkElement.setTextContent(String.valueOf(tariff.getCallPrice().getOutNetwork()));
            callPriceElement.appendChild(outNetworkElement);
            //add cityNetwork
            Element cityNetworkElement = document.createElement(XML.CITY_NETWORK.value());
            cityNetworkElement.setTextContent(String.valueOf(tariff.getCallPrice().getCityNetwork()));
            callPriceElement.appendChild(cityNetworkElement);
            //add SMS
            Element smsElement = document.createElement(XML.SMS.value());
            smsElement.setTextContent(String.valueOf(tariff.getSMS()));
            tariffElement.appendChild(smsElement);
            //add parameters
            Element parametersElement = document.createElement(XML.PARAMETERS.value());
            tariffElement.appendChild(parametersElement);
            //add favorite numbers
            Element favoriteNumbers = document.createElement(XML.FAVORITE_NUMBERS.value());
            parametersElement.appendChild(favoriteNumbers);
            //add favorite number
            for (FavoriteNumber favoriteNumber : tariff.getParameters().getFavoriteNumbers().getFavoriteNumber()) {
                Element fnElement = document.createElement(XML.FAVORITE_NUMBER.value());
                fnElement.setTextContent(favoriteNumber.getFavoriteNumber());
                favoriteNumbers.appendChild(fnElement);
            }
            //add charging
            Element chargingElement = document.createElement(XML.CHARGING.value());
            chargingElement.setTextContent(tariff.getParameters().getCharging().value());
            parametersElement.appendChild(chargingElement);
            //add payment
            Element paymentElement = document.createElement(XML.PAYMENT_VALUE.value());
            paymentElement.setTextContent(String.valueOf(tariff.getParameters().getPaymentValue()));
            parametersElement.appendChild(paymentElement);
        }
        return document;
    }

    public static void saveToXML(Tariffs tariffs, String xmlFileName) 
    		throws ParserConfigurationException, TransformerException {
        // Test -> DOM -> XML
        saveToXML(getDocument(tariffs), xmlFileName);
    }

    public static void saveToXML(Document document, String xmlFileName) throws TransformerException {
        StreamResult result = new StreamResult(new File(xmlFileName));
        // set up transformation
        TransformerFactory tf = TransformerFactory.newInstance();
        javax.xml.transform.Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        // run transformation
        t.transform(new DOMSource(document), result);
    }

    public static void main(String[] args) throws Exception {
        // try to parse NOT valid XML document with validation on (failed)
        DOMController domContr = new DOMController(Constants.VALID_XML_FILE);
        try {
            // parse with validation (failed)
            domContr.parse(true);
        } catch (SAXException e) {
            System.err.println("====================================");
            System.err.println("XML not valid");
            System.err.println("Tariffs object --> " + domContr.getTariffs());
            System.err.println("====================================");
        }
        // try to parse NOT valid XML document with validation off (success)
        domContr.parse(false);

        // we have Test object at this point:
        System.out.println("====================================");
        System.out.print("Here is the test: \n" + domContr.getTariffs());
        System.out.println("====================================");

        // save test in XML file
        Tariffs tariffs = domContr.getTariffs();
        DOMController.saveToXML(tariffs, Constants.INVALID_XML_FILE + ".dom-result.xml");
        tariffs.getTariff().forEach(System.out::println);
    }
}