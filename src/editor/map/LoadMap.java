package editor.map;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Utilisation de la méthode DOM car on veut utilisé de nombreuse données du
 * fichier XML.
 */
public class LoadMap {

    public static Map load(String fichier) throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

    }

}
