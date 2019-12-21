package editor.map;

import editor.textures.TextureProxy;

public class SaveMapTestMain {
	public static void main(String[] argv) {
		Map map = new Map(30,20);
		Room room1 = new Room();
		Room room2 = new Room();

		TextureProxy p =  new TextureProxy();

		p.addTexture(64,"assets/entities/monsters/019.png",4,1,16);
		p.addTexture(16,"assets/entities/monsters/023s.png",4,17,117);

		//SaveMap save = new SaveMap(p.getTextures(),map);

		p.getImage(5);
		p.getImage(19);

		map.addRoom(1,3, room1);

		map.addRoom(11,2, room2);

		map.render();

		//save.saveMap("assets/map/result.tmx");
	}
}
