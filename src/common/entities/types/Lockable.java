package common.entities.types;

/**
 * Définie une entité comme verrouillable ou déverrouillable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.0
 * @since 2.0
 */
public interface Lockable {

	/**
	 * Verrouille l'objet
	 *
	 * @since 2.0
	 */
	void lock();

	/**
	 * Déverrouille l'objet
	 *
	 * @since 2.0
	 */
	void unlock();

	/**
	 * Indique si l'objet est verrouillé
	 *
	 * @return true si il est verrouillé, false sinon
	 * @since 2.0
	 */
	boolean isLocked();
}
