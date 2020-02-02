package common.save;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import common.entities.GameObject;
import common.entities.players.EntityGame;
import common.entities.players.Player;
import common.map.model.Case;
import common.map.model.Map;
import common.save.entities.SaveKey;
import common.utils.textures.TextureArea;
import data.Layer;
import org.lwjgl.Sys;
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
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static data.config.Config.DTD_FOLDER_RELATIVE_TO_MAP;

/**
 * Sauvegarde une map et les textures associés.
 * Permet de sauvegarder la map avec la méthode DOM.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 2.0
 */
public class SaveMap {

	public static final float COLLISION_OPACITY = 0.3f;

	/**
	 * Map à sauvegarder.
	 */
	private final TiledMap gameMap;

	/**
	 * Les entités de la map
	 */
	private final HashMap<Vector2, GameObject> entities;

	/**
	 * Crée un sauvegarde de la map
	 *
	 * @param game     la map
	 * @param entities Les entités de la map et leur position
	 */
	public SaveMap(TiledMap game, HashMap<Vector2, GameObject> entities) {
		this.gameMap = game;
		this.entities = entities;
	}

	/**
	 * Sauvegarde la map et texture.
	 *
	 * @param file     nom du fichier de sauvegarde.
	 * @param map      map a sauvegarder
	 * @param textures les textures de la map
	 * @since 2.0
	 */
	public static void saveMap(String file, Map map, ArrayList<TextureArea> textures) {
		saveMap(file, map, textures, "");
	}

	/**
	 * Sauvegarde la map et texture.
	 *
	 * @param file         nom du fichier de sauvegarde.
	 * @param map          map a sauvegarder
	 * @param textures     les textures de la map
	 * @param relativePath chemin vers dossier map
	 * @since 5.0
	 */
	public static void saveMap(String file, Map map, ArrayList<TextureArea> textures, String relativePath) {
		DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = fabrique.newDocumentBuilder();

			//on instancie notre document
			Document document = builder.newDocument();

			//element map in xml file
			Element mapHeader = document.createElement("map");
			mapHeader.setAttribute("version", "1.2");
			mapHeader.setAttribute("tiledversion", "2019.11.12");
			mapHeader.setAttribute("orientation", "orthogonal");
			mapHeader.setAttribute("renderorder", "right-down");
			mapHeader.setAttribute("width", String.valueOf(map.getCol()));
			mapHeader.setAttribute("height", String.valueOf(map.getRow()));
			mapHeader.setAttribute("tilewidth", String.valueOf(textures.get(0).getTileWidth()));
			mapHeader.setAttribute("tileheight", String.valueOf(textures.get(0).getTileHeight()));
			mapHeader.setAttribute("infinite", "0");
			mapHeader.setAttribute("nextlayerid", "2");
			mapHeader.setAttribute("nextobjectid", "1");

			document.appendChild(mapHeader);

			//tileset représente les textures dans le fichier xml
			Element tileset, image;
			for (int i = 0; i < textures.size(); i++) {
				textures.get(i).load();

				tileset = document.createElement("tileset");
				tileset.setAttribute("firstgid", String.valueOf(textures.get(i).getMin()));
				tileset.setAttribute("name", String.valueOf(i));
				tileset.setAttribute("tilewidth", String.valueOf(textures.get(i).getTileWidth()));
				tileset.setAttribute("tileheight", String.valueOf(textures.get(i).getTileHeight()));

				int tileCount = textures.get(i).getMax() - textures.get(i).getMin();
				tileset.setAttribute("tilecount", String.valueOf(tileCount));
				tileset.setAttribute("columns", String.valueOf(textures.get(i).getNbcol()));

				mapHeader.appendChild(tileset);

				image = document.createElement("image");
				String src = textures.get(i).getPath();
				if (src.startsWith("assets/map/"))
					src = src.split("assets/map/")[1];
				if (!relativePath.equals(""))
					src = relativePath + src;

				image.setAttribute("source", src);

				image.setAttribute("width", String.valueOf(textures.get(i).getWidth()));
				image.setAttribute("height", String.valueOf(textures.get(i).getHeight()));

				tileset.appendChild(image);
			}

			//écriture des différents layers de la map
			int i = 0;
			Element datas, layers;
			StringBuilder stringBuilder;
			Case[] tmpCase = map.getCases();
			for (Layer type : Layer.values()) {
				layers = document.createElement("layer");
				layers.setAttribute("id", String.valueOf(i));
				layers.setAttribute("name", String.valueOf(type));
				layers.setAttribute("width", String.valueOf(map.getCol()));
				layers.setAttribute("height", String.valueOf(map.getRow()));
				if (type.equals(Layer.COLLISION)) {
					layers.setAttribute("opacity", String.valueOf(COLLISION_OPACITY));
				}
				mapHeader.appendChild(layers);

				datas = document.createElement("data");
				datas.setAttribute("encoding", "csv");

				stringBuilder = new StringBuilder("\n");
				for (int k = 0; k < map.getRow(); k++) {
					for (int j = 0; j < map.getCol(); j++) {
						if (tmpCase[k * map.getCol() + j] == null) {

							stringBuilder.append("0,");
						} else {
							HashMap<Layer, common.utils.textures.Texture> hash;
							hash = tmpCase[k * map.getCol() + j].getEntities();

							common.utils.textures.Texture texture = hash.get(type);
							stringBuilder.append(texture.getPosition());
							stringBuilder.append(",");
						}
					}
					stringBuilder.append("\n");
				}
				stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 2));
				stringBuilder.append("\n");
				datas.setTextContent(stringBuilder.toString());
				layers.appendChild(datas);
				i++;
			}

			TransformerFactory factoryTrans = TransformerFactory.newInstance();
			Transformer transformer = factoryTrans.newTransformer();

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "../../assets/map/mapDtd.dtd");

			DOMSource source = new DOMSource(document);

			StreamResult result = new StreamResult(new File(file));
			transformer.transform(source, result);

		} catch (ParserConfigurationException | TransformerException pce) {
			pce.printStackTrace();
		}
	}

	/**
	 * Sauvegarde la map et texture.
	 *
	 * @param fichier nom du fichier de sauvegarde.
	 * @since 4.0
	 */
	public void saveMap(String fichier) {
		saveMap(fichier, "../../");
	}

	/**
	 * Sauvegarde la map et texture.
	 *
	 * @param fichier      nom du fichier de sauvegarde.
	 * @param relativePath chemin relatif
	 * @since 5.0
	 */
	public void saveMap(String fichier, String relativePath) {
		DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

		try {

			DocumentBuilder builder = fabrique.newDocumentBuilder();

			//on instancie notre document
			Document document = builder.newDocument();

			StringBuilder tmpstring = new StringBuilder("\n");

			MapLayers mapLayers = gameMap.getLayers();
			TiledMapTileLayer l = (TiledMapTileLayer) mapLayers.get(0);

			String col = Integer.toString(l.getWidth());

			String row = Integer.toString(l.getHeight());

			TiledMapTileSets tileSets = gameMap.getTileSets();

			Element datas, layers, tileset, rooms;
			int tilePosition = 1;

			//element map in xml file
			Element map = document.createElement("map");
			document.appendChild(map);
			map.setAttribute("version", "1");
			map.setAttribute("tiledversion", "2019.11.12");
			map.setAttribute("orientation", "orthogonal");
			map.setAttribute("renderorder", "right-down");
			map.setAttribute("width", col);
			map.setAttribute("height", row);
			map.setAttribute("tilewidth", String.valueOf((int) l.getTileWidth()));
			map.setAttribute("tileheight", String.valueOf((int) l.getTileHeight()));
			map.setAttribute("infinite", "0");
			map.setAttribute("nextlayerid", "1");
			map.setAttribute("nextobjectid", "3");

			//tileset représente les textures dans le fichier xml
			for (TiledMapTileSet tileSet : tileSets) {
				MapProperties tileProp = tileSet.getProperties();

				tileset = document.createElement("tileset");
				tileset.setAttribute("firstgid", String.valueOf(tilePosition));
				tileset.setAttribute("name", tileSet.getName());
				tileset.setAttribute("tilewidth", String.valueOf((int) l.getTileWidth()));
				tileset.setAttribute("tileheight", String.valueOf((int) l.getTileHeight()));

				tileset.setAttribute("tilecount", String.valueOf(tileSet.size()));

				Element image = document.createElement("image");
				TiledMapTile img = tileSet.getTile(tilePosition);
				Texture t = img.getTextureRegion().getTexture();

				FileTextureData data = (FileTextureData) t.getTextureData();

				String src = data.getFileHandle().path();
				if (src.contains("assets/map/")) {
					src = src.split("assets/map/")[1];
				}

				image.setAttribute("source", relativePath + src);
				image.setAttribute("width", String.valueOf(t.getWidth()));
				image.setAttribute("height", String.valueOf(t.getHeight()));

				tileset.setAttribute("columns", String.valueOf((int) (t.getWidth() / l.getTileWidth())));

				map.appendChild(tileset);
				tileset.appendChild(image);

				tilePosition += tileSet.size();
			}


			//écriture des différents layers de la map
			for (MapLayer layer : mapLayers) {
				if (!(layer instanceof TiledMapTileLayer))
					continue;

				TiledMapTileLayer tileLayer = (TiledMapTileLayer) layer;

				layers = document.createElement("layer");
				layers.setAttribute("id", "1");
				layers.setAttribute("name", tileLayer.getName());
				layers.setAttribute("width", String.valueOf(tileLayer.getWidth()));
				layers.setAttribute("height", String.valueOf(tileLayer.getHeight()));
				if (layer.getName().equals(Layer.COLLISION.name())) {
					layers.setAttribute("opacity", String.valueOf(COLLISION_OPACITY));
				}
				map.appendChild(layers);

				datas = document.createElement("data");
				datas.setAttribute("encoding", "csv");

				//tmpstring = new StringBuilder("\n");
				for (int i = tileLayer.getHeight() - 1; i >= 0; i--) {
					for (int j = 0; j < tileLayer.getWidth(); j++) {
						TiledMapTileLayer.Cell tmp = tileLayer.getCell(j, i);

						if (tmp == null) {
							tmpstring.append("0");
						} else {
							TiledMapTile tile = tmp.getTile();
							if (tile == null) {//si cellule mais pas de tile
								tmpstring.append("0");
							} else {
								tmpstring.append(tile.getId());
							}
						}

						tmpstring.append(",");
					}
					tmpstring.append("\n");
				}

				tmpstring = new StringBuilder(tmpstring.substring(0, tmpstring.length() - 2));
				tmpstring.append("\n");
				datas.setTextContent(tmpstring.toString());
				layers.appendChild(datas);
				tmpstring = new StringBuilder("\n");
			}

			//écriture des entités
			if (this.entities.size() > 0) {
				Element objetGroup = document.createElement(TmxTags.TMX_OBJECT_GROUP);
				objetGroup.setAttribute(TmxProperties.TMX_ID, "6");
				map.appendChild(objetGroup);

				for (java.util.Map.Entry<Vector2, GameObject> entitySet : entities.entrySet()) {
					GameObject entity = entitySet.getValue();
					Vector2 pos = entitySet.getKey();

					//Sauvegarde de la taille, position de l'entité
					Element object = document.createElement(TmxTags.TMX_OBJECT);
					object.setAttribute(TmxProperties.TMX_ID, String.valueOf(entity.getID()));
					object.setAttribute(TmxProperties.TMX_X, String.valueOf(pos.x));
					object.setAttribute("y", String.valueOf(pos.y));
					object.setAttribute(TmxProperties.TMX_WIDTH, String.valueOf(entity.getGameObjectWidth()));
					object.setAttribute(TmxProperties.TMX_HEIGHT, String.valueOf(entity.getGameObjectHeight()));
					object.setAttribute(TmxProperties.TMX_NAME, TmxProperties.TMX_PROP_ENTITY);

					Element properties = document.createElement(TmxTags.TMX_PROPERTIES);

					//Sauvegarde de la classe de l'entité
					Element className = document.createElement(TmxTags.TMX_PROPERTY);
					className.setAttribute(TmxProperties.TMX_NAME, TmxProperties.TMX_PROP_ENTITY_CLASS);
					className.setAttribute(TmxProperties.TMX_VALUE, entity.getClass().getName());
					properties.appendChild(className);

					//Sauvegarde de sa position y car la lecture donne une valeur bizarre mais celle du fichier est bonne.
					//LIBGDX fait la lecture.
					Element y2 = document.createElement(TmxTags.TMX_PROPERTY);
					y2.setAttribute(TmxProperties.TMX_NAME, TmxProperties.TMX_Y);
					y2.setAttribute(TmxProperties.TMX_VALUE, String.valueOf(pos.y));
					properties.appendChild(y2);

					//Sauvegarde du nombre de tiles par niveau
					for (Layer layer : Layer.values()) {
						Array<Float> tiles = entity.getTiles(layer);
						if (tiles == null || tiles.size == 0) continue;
						Element tilesTag = document.createElement(TmxTags.TMX_PROPERTY);
						tilesTag.setAttribute(TmxProperties.TMX_NAME, layer.name());
						tilesTag.setAttribute(TmxProperties.TMX_VALUE, String.valueOf(tiles.size));
						properties.appendChild(tilesTag);
					}

					//ajout des propriétés propres à l'object
					for (java.util.Map.Entry<SaveKey, String> entry : entity.getSave().entrySet()) {
						Element custom = document.createElement(TmxTags.TMX_PROPERTY);
						custom.setAttribute(TmxProperties.TMX_NAME, entry.getKey().toString());
						custom.setAttribute(TmxProperties.TMX_VALUE, entry.getValue());
						properties.appendChild(custom);
					}

					object.appendChild(properties);
					objetGroup.appendChild(object);
				}
			}

			TransformerFactory factoryTrans = TransformerFactory.newInstance();
			Transformer transformer = factoryTrans.newTransformer();

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, DTD_FOLDER_RELATIVE_TO_MAP);

			DOMSource source = new DOMSource(document);

			StreamResult result = new StreamResult(new File(fichier));
			transformer.transform(source, result);

		} catch (ParserConfigurationException | TransformerException pce) {
			pce.printStackTrace();
		}
	}
}
