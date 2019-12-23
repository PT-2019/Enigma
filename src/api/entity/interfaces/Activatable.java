package api.entity.interfaces;

/**
 * Désigne des objets pouvant être activés ou désactivés
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 2.2
 * @since 1.0
 *
 * @see api.entity.interfaces.Item
 */
public abstract class Activatable implements Item {

	/**
	 * Indique si l'objet est activé
	 */
	protected boolean activated;

	/**
	 * ID
	 */
	protected int id;

	/**
	 * @param activated true si l'objet est activé de base, false sinon
	 */
	public Activatable(boolean activated) {
		this.activated = activated;
		this.id = -1;
	}

	/**
	 * @param activated true si l'objet est activé de base, false sinon
	 * @param id        ID
	 */
	public Activatable(boolean activated, int id) {
		this.activated = activated;
		this.id = id;
	}

	/**
	 * Indique si l'objet est activé
	 *
	 * @return true si l'objet est activé, false sinon
	 */
	public abstract boolean isActivated();

	/**
	 * Obtenir l'ID
	 *
	 * @return L'ID, -1 si pas initialisé
	 */
	@Override
	public int getID() {
		return this.id;
	}

	/**
	 * Définir l'ID
	 *
	 * @param id ID
	 */
	@Override
	public void setID(int id) {
		this.id = id;
	}
}
