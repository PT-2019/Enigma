package common.entities.types;

import api.utils.annotations.ConvenienceClass;
import common.enigmas.Enigma;
import common.entities.players.Player;

/**
 * Désigne des objets pouvant être activés ou désactivés
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * apiNote : on peux entends une seule classe donc autant que ce soit un actor (possible)
 * @see common.entities.Item
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

	/**
	 * Définit si l'object est activé
	 *
	 * @param activated true si l'object est activé
	 * @since 6.0
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
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
