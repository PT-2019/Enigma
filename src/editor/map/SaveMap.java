package editor.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.*;
import editor.datas.Layer;
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
	 * Map à sauvegarder.
	 */
	private TiledMap gameMap;

	public SaveMap(TiledMap game) {
		this.gameMap = game;
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

			StringBuilder tmpstring = new StringBuilder("\n");

			MapLayers mapLayers = gameMap.getLayers();
			TiledMapTileLayer l =(TiledMapTileLayer) mapLayers.get(0);

			String col = Integer.toString(l.getWidth());

			String row = Integer.toString(l.getHeight());

			TiledMapTileSets tileSets = gameMap.getTileSets();

			Element datas, layers,tileset, rooms;
			int tilePosition = 1;

			//element map in xml file
			Element map = document.createElement("map");
			document.appendChild(map);
			map.setAttribute("version", "1.3.1");
			map.setAttribute("tiledversion", "1.0");
			map.setAttribute("orientation", "orthogonal");
			map.setAttribute("renderorder", "right-down");
			map.setAttribute("width", col);
			map.setAttribute("height", row);
			map.setAttribute("tilewidth", String.valueOf((int)l.getTileWidth()));
			map.setAttribute("tileheight", String.valueOf((int)l.getTileHeight()));
			map.setAttribute("infinite", "0");
			map.setAttribute("nextlayerid", "1");
			map.setAttribute("nextobjectid", "3");

			//tileset représente les textures dans le fichier xml
			for (TiledMapTileSet tileSet: tileSets) {
				MapProperties tileProp = tileSet.getProperties();

				tileset = document.createElement("tileset");
				tileset.setAttribute("firstgid",String.valueOf(tilePosition));
				tileset.setAttribute("name", tileSet.getName());
				tileset.setAttribute("tilewidth",String.valueOf((int)l.getTileWidth()));
				tileset.setAttribute("tileheight",String.valueOf((int)l.getTileHeight()));

				tileset.setAttribute("tilecount", String.valueOf(tileSet.size()));

				Element image = document.createElement("image");
				TiledMapTile img = tileSet.getTile(tilePosition);
				Texture t = img.getTextureRegion().getTexture();

				FileTextureData data = (FileTextureData ) t.getTextureData();

				image.setAttribute("source", "../../"+data.getFileHandle().path());
				image.setAttribute("width", String.valueOf(t.getWidth()));
				image.setAttribute("height", String.valueOf(t.getHeight()));

				tileset.setAttribute("columns", String.valueOf((int)(t.getWidth()/l.getTileWidth())));

				map.appendChild(tileset);
				tileset.appendChild(image);

				tilePosition += tileSet.size();
			}


			//écriture des différents layers de la map

			for (MapLayer layer: mapLayers) {

				if (! (layer instanceof TiledMapTileLayer))
					continue;

				TiledMapTileLayer tileLayer = (TiledMapTileLayer) layer;

				layers = document.createElement("layer");
				layers.setAttribute("id", "1");
				layers.setAttribute("name", tileLayer.getName());
				layers.setAttribute("width", String.valueOf(tileLayer.getWidth()));
				layers.setAttribute("height", String.valueOf(tileLayer.getHeight()));
				map.appendChild(layers);

				datas = document.createElement("data");
				datas.setAttribute("encoding", "csv");

				//tmpstring = new StringBuilder("\n");
				for (int i = 0; i < tileLayer.getHeight(); i++) {
					for (int j = 0; j < tileLayer.getWidth(); j++) {
						TiledMapTileLayer.Cell tmp = tileLayer.getCell(j,i);
						if (tmp == null){
							tmpstring.append("0");
						}else{
							tmpstring.append(tmp.getTile().getId());
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