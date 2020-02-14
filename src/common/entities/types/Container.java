package common.entities.types;

import common.entities.Item;

import java.util.ArrayList;

/**
 * Une classe pour les entités ou items qui peuvent en contenir d'autres
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 23/12/2019
 * @since 4.0 23/12/2019
 */
@SuppressWarnings("UnusedReturnValue")
public interface Container {

	/**
	 * Ajoute un item
	 *
	 * @param item un item
	 * @return true si ajouté sans problèmes
	 */
	boolean addItem(Item item);

	/**
	 * Retire un item
	 *
	 * @param item un item
	 * @return true si retiré sans problèmes
	 */
	boolean removeItem(Item item);

	/**
	 * Retourne les items contenus
	 *
	 * @return retourne les items contenus
	 */
	ArrayList<Item> getItems();
}
