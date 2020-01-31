package editor.map;

import common.save.MapLoader;

/**
 * Utilisation de la méthode DOM car on veut utilisé de nombreuse données du
 * fichier XML.
 */

public class MapLoaderTestMain {
	public static void main(String[] args) {
		MapLoader m = new MapLoader();

		m.load("assets/map/result.tmx");

		m.getMap().render();

		/*SaveMap save = new SaveMap(m.getTextureproxy().getTextures() ,m.getMap());

		save.saveMap("assets/map/Loadtest.tmx");*/
	}
}
