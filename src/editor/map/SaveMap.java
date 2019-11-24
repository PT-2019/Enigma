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
 
public class SaveMap {

    private ArrayList<TextureArea> textures;

    private Map gameMap;

    public SaveMap(ArrayList<TextureArea>textures,Map game){
        this.gameMap = game;
        this.textures = textures;
    }

	public void saveMap(){
		DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder xML_Constructeur = fabrique.newDocumentBuilder();

			Document document = xML_Constructeur.newDocument();

			Case[] tmpCase = gameMap.getCases();

			String tmpstring = "\n";

			String col = Integer.toString(gameMap.getCol());

			String row = Integer.toString(gameMap.getRow());

			Element[] layers = new Element[4];

			Element[] datas = new Element[4];

			Element[] tileset = new Element[textures.size()];

			//element map in xml file
			Element map = document.createElement("map");
			document.appendChild(map);
			map.setAttribute("version", "M@teo21");
			map.setAttribute("tiledversion", "M@teo21");
			map.setAttribute("orientation", "M@teo21");
			map.setAttribute("renderorder", "M@teo21");
			map.setAttribute("width", "M@teo21");
			map.setAttribute("height", "M@teo21");
			map.setAttribute("tilewidth", "M@teo21");
			map.setAttribute("tileheight", "M@teo21");
			map.setAttribute("infinite", "M@teo21");
			map.setAttribute("nextlayerid", "M@teo21");
			map.setAttribute("nextobjectid", "M@teo21");

			//tileset

            for (int i = 0; i < textures.size(); i++) {
                tileset[i] = document.createElement("tileset");
                tileset[i].setAttribute("firstgid", String.valueOf(textures.get(i).getMin()));
                tileset[i].setAttribute("name", String.valueOf(i));
                tileset[i].setAttribute("tilewidth",String.valueOf(textures.get(i).getTile()));
                tileset[i].setAttribute("tileheight",String.valueOf(textures.get(i).getTile()));
                int tmp = textures.get(i).getMax() - textures.get(i).getMin();
                tileset[i].setAttribute("tilecount", String.valueOf(tmp));
                tileset[i].setAttribute("columns",String.valueOf(textures.get(i).getNbcol()));

                map.appendChild(tileset[i]);

                Element image= document.createElement("image");
                image.setAttribute("source",textures.get(i).getPath());

                image.setAttribute("width",String.valueOf(textures.get(i).getWidth()));
                image.setAttribute("height",String.valueOf(textures.get(i).getHeight()));

                tileset[i].appendChild(image);
            }


			int i = 0;
			for (editor.enums.Layer type : editor.enums.Layer.values()) {
				layers[i] = document.createElement("layer");
				layers[i].setAttribute("id", String.valueOf(i));
				layers[i].setAttribute("name", String.valueOf(type));
				layers[i].setAttribute("width",col);
				layers[i].setAttribute("height",row);
				map.appendChild(layers[i]);

				datas[i] = document.createElement("data");
				datas[i].setAttribute("encoding","csv");

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
				datas[i].setTextContent(tmpstring);
				layers[i].appendChild(datas[i]);
				i++;
				tmpstring = "\n";
			}

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


			TransformerFactory factoryTrans = TransformerFactory.newInstance();
			Transformer transformer = factoryTrans.newTransformer();

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(document);

			StreamResult resultat = new StreamResult(new File("result.tmx"));
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

        TextureArea a = new TextureArea(16,"assets/monsters/019.png",4,0,15);

        p.addTexture(a);

		SaveMap save = new SaveMap(p.getTextureArea(),map);

		p.getImage(5);

		map.addRoom(1,3, room1);

		map.addRoom(11,2, room2);

		map.render();

		save.saveMap();
	}
}