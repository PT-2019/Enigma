package editor.map;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Utilisation de la méthode DOM car on veut utilisé de nombreuse données du
 * fichier XML.
 */
public class MapLoaderTest {

    public static void main(String[] args) {
        MapLoader m = new MapLoader();

        m.load("result.tmx");

        //SaveMap save = new SaveMap(m.getTextureproxy().getTextureArea(),m.getMap());

        //save.saveMap("test.tmx");
    }

}
