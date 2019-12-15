package editor.map;

import editor.datas.Layer;
import editor.textures.Texture;
import editor.textures.TextureProxy;

import java.util.HashMap;

public class SaveMapTestMain {
	public static void main(String[] argv) {
		Map map = new Map(30,20);
		Room room1 = new Room();
		Room room2 = new Room();

		TextureProxy p =  new TextureProxy();

		p.addTexture(64,"entities/monsters/019.png",4,1,16);
		p.addTexture(16,"entities/monsters/023s.png",4,17,117);

		p.getImage(5);
		p.getImage(19);

		map.addRoom(1,3, room1);

		map.addRoom(11,2, room2);

		map.render();
		SaveMap save = new SaveMap(p.getTextures(),map);

		/*Case[] c = map.getCases();
		for (int k=0 ; k < map.getRow();k++){
			for (int j=0; j < map.getCol();j++ ) {
				if (c[k*map.getCol()+j] !=null){
					HashMap<Layer, Texture> hash = c[k*map.getCol()+j].getEntities();
					Texture texture = hash.get(Layer.DECORATIONS1);
					System.out.println(texture.getPosition());
				}
				}
			}*/



		save.saveMap("assets/map/old/result.tmx");
	}
}
