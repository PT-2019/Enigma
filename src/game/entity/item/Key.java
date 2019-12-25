package game.entity.item;

import api.entity.AbstractItem;
import api.entity.types.NeedContainer;

/**
 * Une clef
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 24/12/2019
 * @since 4.0 24/12/2019
 */
public class Key extends AbstractItem implements NeedContainer {

	/**
	 * Crée un clef
	 *
	 * @since 4.0
	 */
	public Key() {
		this(-1);
	}

	/**
	 * Crée un clef avec un id unique pour identifier ses énigmes
	 *
	 * @param id ID
	 * @since 4.0
	 */
	public Key(int id) {
		super(id);
	}

	//toString

	@Override
	public String toString() {
		return "Key{" + "enigmas=" + enigmas + ", id=" + id + '}';
	}
}
