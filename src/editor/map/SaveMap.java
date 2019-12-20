package editor.map;

import editor.datas.Layer;
import editor.textures.Texture;
import editor.textures.TextureArea;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Sauvegarde une map et les textures associés.
 * Permet de sauvegarder la map avec la méthode DOM.
 */
public class SaveMap {
	/**
	 * Texture à sauvegarder dans le fichier
	 */
	private ArrayList<TextureArea> textures;

	/**
	 * Map à sauvegarder.
	 */
	private Map gameMap;

	public SaveMap(ArrayList<TextureArea> textures, Map game) {
		this.gameMap = game;
		this.textures = textures;
	}

	/**
	 * Sauvegarde la map et texture.
	 *
	 * @param fichier nom du fichier de sauvegarde.
	 */
	public void saveMap(String fichier) {
		DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

		try {

			DocumentBuilder builder = fabrique.newDocumentBuilder();

			//on instancie notre document
			Document document = builder.newDocument();

			Case[] tmpCase = gameMap.getCases();

			StringBuilder tmpstring = new StringBuilder("\n");

			String col = Integer.toString(gameMap.getCol());

			String row = Integer.toString(gameMap.getRow());

			Element datas, layers, tileset, rooms;

			//element map in xml file
			Element map = document.createElement("map");
			document.appendChild(map);
			map.setAttribute("version", "1.3.1");
			map.setAttribute("tiledversion", "1.0");
			map.setAttribute("orientation", "orthogonal");
			map.setAttribute("renderorder", "right-down");
			map.setAttribute("width", String.valueOf(gameMap.getCol()));
			map.setAttribute("height", String.valueOf(gameMap.getRow()));
			map.setAttribute("tilewidth", String.valueOf(textures.get(0).getTileWidth()));
			map.setAttribute("tileheight", String.valueOf(textures.get(0).getTileHeight()));
			map.setAttribute("infinite", "0");
			map.setAttribute("nextlayerid", "1");
			map.setAttribute("nextobjectid", "3");

			//tileset représente les textures dans le fichier xml
			for (int i = 0; i < textures.size(); i++) {
				textures.get(i).load();

				tileset = document.createElement("tileset");
				tileset.setAttribute("firstgid", String.valueOf(textures.get(i).getMin()));
				tileset.setAttribute("name", String.valueOf(i));
				tileset.setAttribute("tilewidth", String.valueOf(textures.get(i).getTileWidth()));
				tileset.setAttribute("tileheight", String.valueOf(textures.get(i).getTileHeight()));

				int tmp = textures.get(i).getMax() - textures.get(i).getMin();
				tileset.setAttribute("tilecount", String.valueOf(tmp));
				tileset.setAttribute("columns", String.valueOf(textures.get(i).getNbcol()));

				map.appendChild(tileset);

				Element image = document.createElement("image");
				image.setAttribute("source", "../../" + textures.get(i).getPath());

				image.setAttribute("width", String.valueOf(textures.get(i).getWidth()));
				image.setAttribute("height", String.valueOf(textures.get(i).getHeight()));

				tileset.appendChild(image);
			}

			//écriture des différents layers de la map
			int i = 0;

			for (editor.datas.Layer type : editor.datas.Layer.values()) {
				layers = document.createElement("layer");
				layers.setAttribute("id", String.valueOf(i));
				layers.setAttribute("name", String.valueOf(type));
				layers.setAttribute("width", col);
				layers.setAttribute("height", row);
				map.appendChild(layers);

				datas = document.createElement("data");
				datas.setAttribute("encoding", "csv");

				tmpstring = new StringBuilder("\n");
				for (int k = 0; k < gameMap.getRow(); k++) {
					for (int j = 0; j < gameMap.getCol(); j++) {
						if (tmpCase[k * gameMap.getCol() + j] == null) {

							tmpstring.append("0,");
						} else {
							HashMap<Layer, Texture> hash = tmpCase[k * gameMap.getCol() + j].getEntities();

							Texture texture = hash.get(type);
							tmpstring.append(texture.getPosition());
							tmpstring.append(",");
						}
					}
					tmpstring.append("\n");
				}
				tmpstring = new StringBuilder(tmpstring.substring(0, tmpstring.length() - 2));
				tmpstring.append("\n");
				datas.setTextContent(tmpstring.toString());
				layers.appendChild(datas);
				i++;
				tmpstring = new StringBuilder("\n");
			}

			//création de la colision
			Element colision = document.createElement("layer");
			colision.setAttribute("id", "5");
			colision.setAttribute("name", "Colision");
			colision.setAttribute("width", col);
			colision.setAttribute("height", row);
			map.appendChild(colision);

			Element data = document.createElement("data");
			data.setAttribute("encoding", "csv");
			for (i = 0; i < gameMap.getRow(); i++) {
				for (int j = 0; j < gameMap.getCol(); j++) {
					if (tmpCase[i * gameMap.getCol() + j] == null) {

						tmpstring.append("0,");
					} else {
						if (tmpCase[i * gameMap.getCol() + j].isWalkable()) {
							tmpstring.append("1,");
						} else {
							tmpstring.append("0,");
						}
					}
				}
				tmpstring.append("\n");
			}
			tmpstring = new StringBuilder(tmpstring.substring(0, tmpstring.length() - 2));
			tmpstring.append("\n");
			data.setTextContent(tmpstring.toString());
			colision.appendChild(data);

			//représentation des rooms
			for (java.util.Map.Entry<Point, Room> room : gameMap.getRooms().entrySet()) {
				rooms = document.createElement("room");
				rooms.setAttribute("width", String.valueOf(room.getValue().getCol()));
				rooms.setAttribute("heigth", String.valueOf(room.getValue().getRow()));
				rooms.setAttribute("widthpos", String.valueOf((int) room.getKey().getX()));
				rooms.setAttribute("heigthpos", String.valueOf((int) room.getKey().getY()));

				map.appendChild(rooms);
			}

			TransformerFactory factoryTrans = TransformerFactory.newInstance();
			Transformer transformer = factoryTrans.newTransformer();

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "../../assets/map/mapDtd.dtd");

			DOMSource source = new DOMSource(document);

			StreamResult resultat = new StreamResult(new File(fichier));
			transformer.transform(source, resultat);

		} catch (ParserConfigurationException | TransformerException pce) {
			pce.printStackTrace();
		}
	}
}