package editor.utils.save;

import editor.utils.map.Map;
import editor.utils.textures.TextureProxy;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Classe qui va chargé une map.
 * Utilisation de la méthode Sax.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.0
 * @since 2.0
 */
public class MapLoader {

	/**
	 * map chargée
	 */
	private Map map;

	/**
	 * proxy qui va contenir toutes les textures nécessaire
	 */
	private TextureProxy textureproxy;

	/**
	 * Chargement du fichier map
	 *
	 * @param fichier path
	 */
	public void load(String fichier) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			LoadHandler handler = new LoadHandler();
			factory.setValidating(true);

			SAXParser parser = factory.newSAXParser();

			parser.parse(fichier, handler);

			map = handler.getMap();

			textureproxy = handler.getProxyTexture();

		} catch (IOException | SAXException | ParserConfigurationException e) {
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
