package api.entity.utils;

import api.entity.AbstractItem;
import api.entity.Item;
import api.utils.annotations.ConvenienceClass;
import editor.enigma.Enigma;
import editor.entity.Player;

/**
 * Désigne des objets pouvant être activés ou désactivés
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.2
 * apiNote : on peux entends une seule classe donc autant que ce soit un actor (possible)
 * @see Item
 * @since 1.0
 */
@ConvenienceClass
public abstract class Activatable extends AbstractItem {

	/**
	 * Indique si l'objet est activé
	 */
	protected boolean activated;

	/**
	 * @param activated true si l'objet est activé de base, false sinon
	 */
	public Activatable(boolean activated) {
		this(activated, -1);
	}

	/**
	 * @param activated true si l'objet est activé de base, false sinon
	 * @param id        ID
	 */
	public Activatable(boolean activated, int id) {
		super(id);
		this.activated = activated;
	}

	/**
	 * Indique si l'objet est activé
	 *
	 * @return true si l'objet est activé, false sinon
	 */
	public boolean isActivated() {
		return this.activated;
	}

	@Override
	public void interactsWith(Player p) {
		this.activated = !this.activated;

		for (Enigma e : this.enigmas) {
			if (!e.isKnown()) e.discovered();
			else e.verifyConditions(p);
		}
	}
}
