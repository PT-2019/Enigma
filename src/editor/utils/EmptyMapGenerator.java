package editor.utils;

import api.utils.ConvenienceClass;
import api.utils.ConvenienceMethod;
import editor.entity.map.Map;
import editor.utils.save.SaveMap;
import editor.utils.textures.TextureProxy;

/**
 * Une classe qui génère une map vide
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 23/12/2019
 * @since 4.0 23/12/2019
 */
@ConvenienceClass
public class EmptyMapGenerator {

	/**
	 * Génère une map vide
	 *
	 * @param path chemin du fichier a créer
	 * @param col  nombre de colonnes
	 * @param row  nombre de lignes
	 * @since 4.0
	 */
	@ConvenienceMethod
	public static void generate(String path, int col, int row) {
	/*<tileset name="dungeon1"><image width="128" height="96"/></tileset>
	<tileset name="pipo"><image width="128" height="3984"/></tileset>
	<tileset name="collision"><image width="128" height="128"/></tileset>*/
		TextureProxy proxy = new TextureProxy();
		proxy.addTexture(16, "assets/map/tiles/ground/dungeon1.png", 8, 1, 48);
		proxy.addTexture(16, "assets/map/tiles/pipo.png", 8, 49, 1992 + 49);
		proxy.addTexture(16, "assets/map/collision.png", 8, 2041, 2041 + 64);

		SaveMap.saveMap(path, new Map(col, row), proxy.getTextures());
	}

}
