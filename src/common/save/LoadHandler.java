package common.save;

import common.map.model.Case;
import common.map.model.Map;
import common.map.model.Room;
import common.utils.textures.Texture;
import common.utils.textures.TextureProxy;
import data.Layer;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Cette classe va activé certaine de ses méthodes lors du parcours d'un
 * fichier xml représentant une map.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.0
 * @see DefaultHandler
 * @since 2.0
 */
public class LoadHandler extends DefaultHandler {

	private String currentName;

	private TextureProxy proxyTexture;

	private Map loadMap;

	private int[] dataTexture = new int[4];

	private String currentLayer;

	private int indice;

	public LoadHandler() {
		proxyTexture = new TextureProxy();
		indice = 0;
	}

	/**
	 * Cette méthode est activé lorsqu'une balise xml est détecté dans le fichier. Elle permet
	 * de récupérer les différents attributs des balises et donc créer les objets de la map.
	 *
	 * @param uri        none
	 * @param localName  none
	 * @param qName      none
	 * @param attributes none
	 * @throws SAXException none
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentName = qName;

		if (qName.equals("map")) {
			int col = Integer.parseInt(attributes.getValue("width"));
			int row = Integer.parseInt(attributes.getValue("height"));

			loadMap = new Map(col, row);
		} else if (qName.equals("tileset")) {
			dataTexture[0] = Integer.parseInt(attributes.getValue("tileheight"));
			dataTexture[1] = Integer.parseInt(attributes.getValue("columns"));
			dataTexture[2] = Integer.parseInt(attributes.getValue("firstgid"));
			dataTexture[3] = dataTexture[2] + Integer.parseInt(attributes.getValue("tilecount"));

		} else if (qName.equals("image")) {
			String source = attributes.getValue("source");
			source = source.substring(6);

			proxyTexture.addTexture(dataTexture[0], source, dataTexture[1], dataTexture[2], dataTexture[3]);
		} else if (qName.equals("layer")) {
			indice = 0;
			currentLayer = attributes.getValue("name");

		} else if (qName.equals("room")) {
			int row = Integer.parseInt(attributes.getValue("heigth"));
			int col = Integer.parseInt(attributes.getValue("width"));
			int pointrow = Integer.parseInt(attributes.getValue("heigthpos"));
			int pointcol = Integer.parseInt(attributes.getValue("widthpos"));
			Case[] mapcase = loadMap.getCases();
			Case[] roomcase = new Case[row * col];

			for (int i = 0, k = pointrow; k < row + pointrow; i++, k++) {
				for (int j = 0, n = pointcol; n < col + pointcol; j++, n++) {

					roomcase[i * col + j] = mapcase[k * loadMap.getCol() + n];
				}
			}

			Room r = new Room(col, row, roomcase);

			loadMap.addRoom(pointcol, pointrow, r);
		}
	}

	/**
	 * Cette méthode permet de récupérer le contenu qu'il y a dans un noeud xml.
	 * Elle est invoqué lorsqu'un noeud avec contenu est détecté.
	 *
	 * @param value  none
	 * @param start  none
	 * @param length none
	 * @throws SAXException none
	 */
	@Override
	public void characters(char[] value, int start, int length) throws SAXException {
		StringBuilder numText = new StringBuilder();
		String str = new String(value, start, length);
		str = str.replaceAll("\\s", "");

		if (currentName.equals("data")) {

			for (int i = 0; i < str.length(); i++) {
				char tmp = str.charAt(i);

				//Le point virgule signifie qu'on change de numéro de texture
				if (tmp == ',') {

					Case tmpCase = loadMap.getCase(indice);
					int num = Integer.parseInt(numText.toString());

					//si la case est null on l'instancie
					if (tmpCase == null) {
						tmpCase = new Case();
						loadMap.setCase(indice, tmpCase);
					}
					//si c'est une collision on applique une méthode différente
					if (currentLayer.equals(Layer.COLLISION.name)) {
						if (num == 1) {
							tmpCase.setWalkable(true);
						}
					} else {
						tmpCase.setEntity(Layer.valueOf(currentLayer), new Texture(num, null));
					}
					indice++;
					numText = new StringBuilder();
				} else {
					//on ajoute le caractère
					numText.append(tmp);
				}
			}
			if (!numText.toString().equals("")) {
				Case tmpCase = loadMap.getCase(indice);
				int num = Integer.parseInt(numText.toString());

				//si la case est null on l'instancie
				if (tmpCase == null) {
					tmpCase = new Case();
					loadMap.setCase(indice, tmpCase);
				}
				//si c'est une collision on applique une méthode différente
				if (currentLayer.equals("Colision")) {
					if (num == 1) {
						tmpCase.setWalkable(true);
					}
				} else {
					tmpCase.setEntity(Layer.valueOf(currentLayer), new Texture(num, null));
				}
				indice++;
			}
		}
	}

	/**
	 * invoqué si le fichier ne respecte pas le dtd.
	 *
	 * @param e none
	 * @throws SAXException none
	 */
	@Override
	public void error(SAXParseException e) throws SAXException {
		System.out.println("ERROR : " + e.getMessage());
		throw e;
	}

	/**
	 * Invoqué lors d'une erreur avec une des règles du xml.
	 *
	 * @param e none
	 * @throws SAXException none
	 */
	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println("FATAL ERROR : " + e.getMessage());
		throw e;
	}

	/**
	 * Warning du parseur.
	 *
	 * @param e none
	 * @throws SAXException none
	 */
	@Override
	public void warning(SAXParseException e) throws SAXException {
	}

	public Map getMap() {
		return loadMap;
	}

	public TextureProxy getProxyTexture() {
		return proxyTexture;
	}
}

