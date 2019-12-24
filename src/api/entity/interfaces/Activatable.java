package api.entity.interfaces;

/**
 * Désigne des objets pouvant être activés ou désactivés
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.2
 * @see api.entity.interfaces.Item
 * @since 1.0
 *
 * @apiNote on peux entends une seule classe donc autant que ce soit un actor (possible)
 */
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
	public abstract boolean isActivated();
}
