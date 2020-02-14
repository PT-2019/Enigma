package common.save.entities;

import api.utils.annotations.ConvenienceClass;
import common.entities.Item;

import java.util.ArrayList;

/**
 * Sauvegarde et charge l'inventaire.
 * <p>
 * Elle se contente de sauvegarder les ids des entités stockées.
 * <p>
 * Rien n'est instancié à la lecture donc les données sont intraitables.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 04/02/2020
 * @since 6.0 04/02/2020
 */
@ConvenienceClass
@Deprecated
public final class SaveInventory {

	/**
	 * Sauvegarde les ids des objects contenus
	 *
	 * @param items liste d'items
	 * @return string pour sauvegarder
	 */
	public static String save(ArrayList<Item> items) {
		StringBuilder save = new StringBuilder();
		for (Item i : items) {
			save.append(i.getID());
			save.append(',');
		}
		return save.toString();
	}

	/**
	 * Retourne les ids sous forme de liste d'entiers
	 *
	 * @param items liste (string) des ids
	 * @return liste (int) des ids
	 */
	public static ArrayList<Integer> load(String items) {
		ArrayList<Integer> list = new ArrayList<>();
		if (items.contains(",")) {
			String[] split = items.split(",");
			for (String s : split) {
				list.add(Integer.parseInt(s));
			}
		}
		return list;
	}
}
