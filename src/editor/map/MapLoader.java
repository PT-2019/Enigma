package editor.map;

import editor.texture.TextureProxy;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Utilisation de la méthode DOM car on veut utilisé de nombreuse données du
 * fichier XML.
 */
public class MapLoader{

    private Map map;

    private TextureProxy textureproxy;

    public void load(String fichier){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            LoadHandler handler = new LoadHandler();
            SAXParser parser = factory.newSAXParser();

            parser.parse(fichier, handler);

            map = handler.getMap();

            textureproxy = handler.getProxyTexture();

        }catch (IOException e){
            System.err.println(e.getMessage());
        } catch (SAXException e){
            System.err.println(e.getMessage());
        }catch (ParserConfigurationException e){
            System.err.println(e.getMessage());
        }
    }

    public Map getMap() {
        return map;
    }

    public TextureProxy getTextureproxy() {
        return textureproxy;
    }
}
