package editor.map;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import editor.texture.Texture;
import editor.texture.TextureArea;
import editor.texture.TextureProxy;
import org.lwjgl.Sys;
import org.w3c.dom.*;

/**
 * Class en développement à voir si on garde cette organisation.
 * Permet de sauvegarder la map avec la méthode DOM.
 *
 */
public class SaveMap {

    private ArrayList<TextureArea> textures;

    private Map gameMap;

    public SaveMap(ArrayList<TextureArea>textures,Map game){
        this.gameMap = game;
        this.textures = textures;
    }

	public void saveMap(String fichier){
		DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
		
		try {
			
			DocumentBuilder builder = fabrique.newDocumentBuilder();
			
			//on instancie notre document
			Document document = builder.newDocument();

			Case[] tmpCase = gameMap.getCases();

			String tmpstring = "\n";

			String col = Integer.toString(gameMap.getCol());

			String row = Integer.toString(gameMap.getRow());

			Element datas,layers,tileset,rooms;

			//element map in xml file
			Element map = document.createElement("map");
			document.appendChild(map);
			map.setAttribute("version", "M@teo21");
			map.setAttribute("tiledversion", "M@teo21");
			map.setAttribute("orientation", "orthogonal");
			map.setAttribute("renderorder", "M@teo21");
			map.setAttribute("width", String.valueOf(gameMap.getCol()));
			map.setAttribute("height", String.valueOf(gameMap.getRow()));
			map.setAttribute("tilewidth", "M");
			map.setAttribute("tileheight", "M");
			map.setAttribute("infinite", "0");
			map.setAttribute("nextlayerid", "M");
			map.setAttribute("nextobjectid", "M");

			//tileset représente les textures dans le fichier xml
            for (int i = 0; i < textures.size(); i++) {

                tileset = document.createElement("tileset");
                tileset.setAttribute("firstgid", String.valueOf(textures.get(i).getMin()));
                tileset.setAttribute("name", String.valueOf(i));
                tileset.setAttribute("tilewidth",String.valueOf(textures.get(i).getTile()));
                tileset.setAttribute("tileheight",String.valueOf(textures.get(i).getTile()));

                int tmp = textures.get(i).getMax() - textures.get(i).getMin();
                tileset.setAttribute("tilecount", String.valueOf(tmp));
                tileset.setAttribute("columns",String.valueOf(textures.get(i).getNbcol()));

                map.appendChild(tileset);

                Element image = document.createElement("image");
                image.setAttribute("source",textures.get(i).getPath());

                image.setAttribute("width",String.valueOf(textures.get(i).getWidth()));
                image.setAttribute("height",String.valueOf(textures.get(i).getHeight()));

            	tileset.appendChild(image);
            }

			//écriture des différents layers de la map
			int i = 0;
			for (editor.enums.Layer type : editor.enums.Layer.values()) {
				layers = document.createElement("layer");
				layers.setAttribute("id", String.valueOf(i));
				layers.setAttribute("name", String.valueOf(type));
				layers.setAttribute("width",col);
				layers.setAttribute("height",row);
				map.appendChild(layers);

				datas = document.createElement("data");
				datas.setAttribute("encoding","csv");

				tmpstring = "\n";
				for (int k=0 ; k < gameMap.getRow();k++){
					for (int j=0; j < gameMap.getCol();j++ ){
						if (tmpCase[k*gameMap.getCol()+j] == null){

							tmpstring = tmpstring + "0,";
						}else{
							HashMap hash = tmpCase[k*gameMap.getCol()+j].getTextures();

							Texture texture =(Texture) hash.get(type);
							tmpstring += texture.getPosition();
							tmpstring += ",";
						}
					}
					tmpstring += "\n";
				}
				tmpstring = tmpstring.substring(0,tmpstring.length()-2);
				tmpstring += "\n";
				datas.setTextContent(tmpstring);
				layers.appendChild(datas);
				i++;
				tmpstring = "\n";
			}
			
			//création de la colision
			Element colision  = document.createElement("layer");
			colision.setAttribute("id","5");
			colision.setAttribute("name","Colision");
			colision.setAttribute("width",col);
			colision.setAttribute("height",row);
			map.appendChild(colision);
			
			Element data = document.createElement("data");
			data.setAttribute("encoding","csv");
			for ( i=0 ; i < gameMap.getRow();i++){
				for (int j=0; j < gameMap.getCol();j++ ){
					if (tmpCase[i*gameMap.getCol()+j] == null){

						tmpstring = tmpstring + "0,";
					}else{
						if (tmpCase[i*gameMap.getCol()+j].isWalkable()){
							tmpstring = tmpstring + "1,";
						}else {
							tmpstring = tmpstring + "0,";
						}
					}
				}
				tmpstring += "\n";
			}
			tmpstring = tmpstring.substring(0,tmpstring.length()-2) ;
			tmpstring += "\n";
			data.setTextContent(tmpstring);
			colision.appendChild(data);
			
			//représentation des rooms
			for (java.util.Map.Entry<Point,Room> room : gameMap.getRooms().entrySet()) {
				rooms = document.createElement("room");
				rooms.setAttribute("width",String.valueOf(room.getValue().getCol()));
				rooms.setAttribute("heigth",String.valueOf(room.getValue().getRow()));
				rooms.setAttribute("x", String.valueOf(room.getKey().getX()));
				rooms.setAttribute("y", String.valueOf(room.getKey().getY()));

				map.appendChild(rooms);
			}
			
			TransformerFactory factoryTrans = TransformerFactory.newInstance();
			Transformer transformer = factoryTrans.newTransformer();

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(document);

			StreamResult resultat = new StreamResult(new File(fichier));
			transformer.transform(source, resultat);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
 
	public static void main(String argv[]) {
		Map map = new Map(30,20);
		Room room1 = new Room();
		Room room2 = new Room();

        TextureProxy p =  new TextureProxy();

        TextureArea a = new TextureArea(16,"assets/monsters/019.png",4,1,16);
		TextureArea b = new TextureArea(16,"assets/monsters/023s.png",4,17,117);
        p.addTexture(a);
        p.addTexture(b);

		SaveMap save = new SaveMap(p.getTextureArea(),map);

		p.getImage(5);
		p.getImage(19);

		map.addRoom(1,3, room1);

		map.addRoom(11,2, room2);

		map.render();

		save.saveMap("result.tmx");
	}
}