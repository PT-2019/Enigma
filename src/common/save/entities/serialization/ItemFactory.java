package common.save.entities.serialization;

import api.utils.Utility;
import com.badlogic.gdx.utils.Json;
import common.entities.Item;
import common.entities.players.PlayerGame;
import data.Layer;

import java.util.ArrayList;

/**
 * Factory qui permet à partir d'un fichier Json et d'un nom de charger un player
 * avec une animation
 */
public class ItemFactory {

	/**
	 * Liste des joueurs qu'on a récupérere dans le Json
	 */
	private static ArrayList<ItemSerializable> items;

	public ItemFactory() {
	}

	/**
	 * Permet de créer un playergame à partir d'un fichier Json
	 *
	 * @param key
	 * @param path
	 * @return un item du Json
	 */
	@SuppressWarnings("unchecked")
	public static Item createItem(String key, String path) {
		Item item;
		Json json = new Json();
		ItemSerializable itemInfo = null;

		//parseur de json de la libgdx
		items = json.fromJson(ArrayList.class, ItemSerializable.class, Utility.loadFile(path));
		for (ItemSerializable i : items) {
			if (i.getKey().equals(key)) {
				itemInfo = i;
				break;
			}
		}

		if (itemInfo == null) throw new IllegalArgumentException("Clef non trouvée:" + key + " dans " + path);


		item.setAtlas(itemInfo.getAtlas(),itemInfo.getKey());

		return item;
	}
}
