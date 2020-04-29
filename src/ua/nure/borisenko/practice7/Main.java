package ua.nure.borisenko.practice7;

import org.xml.sax.SAXException;
import ua.nure.borisenko.practice7.controller.DOMController;
import ua.nure.borisenko.practice7.controller.SAXController;
import ua.nure.borisenko.practice7.controller.STAXController;
import ua.nure.borisenko.practice7.entity.Tariffs;
import ua.nure.borisenko.practice7.util.Sorter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Main {
    /**
     * Usage.
     */
    public static void usage() {
        System.out.println("Usage:\njava -jar practice7.jar xmlFileName");
        System.out.println("java ua.nure.borisenko.practice7.Main xmlFileName");
    }

    /**
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        if (args.length != 1) {
            usage();
            return;
        }

        String xmlFileName = args[0];
        System.out.println("Input ==> " + xmlFileName);

        // //////////////////////////////////////////////////////
        // DOM
        // //////////////////////////////////////////////////////

        // get
        DOMController domController = new DOMController(xmlFileName);
        try {
            domController.parse(true);
            Tariffs tariffs = domController.getTariffs();


            // sort (case 1)
            Sorter.sortTariffsByName(tariffs);

            // save
            String outputXmlFile = "output.dom.xml";
            DOMController.saveToXML(tariffs, outputXmlFile);

            // //////////////////////////////////////////////////////
            // SAX
            // //////////////////////////////////////////////////////

            // get
            System.out.println("Output ==> " + outputXmlFile);
            SAXController saxController = new SAXController(xmlFileName);
            saxController.parse(true);
            tariffs = saxController.getTariffs();

            // sort (case 2)
            Sorter.sortTariffsByPayroll(tariffs);

            // save
            outputXmlFile = "output.sax.xml";

            // other way:
            DOMController.saveToXML(tariffs, outputXmlFile);
            System.out.println("Output ==> " + outputXmlFile);

            // //////////////////////////////////////////////////////
            // StAX
            // //////////////////////////////////////////////////////

            // get
            STAXController staxController = new STAXController(xmlFileName);
            staxController.parse();

            tariffs = staxController.getTariffs();

            // sort (case 3)
            Sorter.sortTariffsOperatorName(tariffs);

            // save
            outputXmlFile = "output.stax.xml";
            DOMController.saveToXML(tariffs, outputXmlFile);
            System.out.println("Output ==> " + outputXmlFile);

        } catch (ParserConfigurationException|SAXException|IOException
        		|TransformerException| XMLStreamException e) {
            System.err.println(e.getMessage());
        }  
    }

    /**
     * Constructor.
     */
    protected Main() {

    }
}
