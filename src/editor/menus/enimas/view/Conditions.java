package editor.menus.enimas.view;

import general.entities.Consumable;
import general.entities.GameObject;
import general.entities.types.Activatable;

/**
 * Conditions disponibles
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 26/01/2020
 * @since 5.0 26/01/2020
 */
public enum Conditions {
	ACTIVATED("Un object doit être activé", "Seulement un objet activable.", null),
	ANSWER("Demander au joueur une réponse", "", null),
	HAVE_IN_HANDS("Avoir l'objet dans ses mains", "Objects uniquement (clef, livre, ...)", null),
	HAVE_IN_INVENTORY("Avoir l'objet dans l'inventaire", "Objects uniquement (clef, livre, ...)", null),
	;

	public final String value;
	/**
	 * Le nom d'une classe dont la méthode run crée.
	 * <p>
	 * Non utilisé et non fonctionnel.
	 * <p>
	 * La classe possèderait un constructeur qui prends une EnigmaView et
	 * un OperéationListener.
	 *
	 * @see editor.menus.enimas.listeners.ConditionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public final Class<? extends Runnable> initClass;
	/**
	 * ignore
	 */
	public final String tooltip;
	/**
	 * Message si la condition n'est pas respectée
	 */
	public final String restrict;

	Conditions(String value, String restrict, Class<? extends Runnable> initClass) {
		this.value = value;
		this.initClass = initClass;
		this.tooltip = "";
		this.restrict = restrict;
	}

	/**
	 * Retourne true si le gameObject respecte les conditions de la conditions
	 *
	 * @param object gameObject
	 * @return true si le gameObject respecte les conditions de la conditions
	 */
	public boolean isValid(GameObject object) {
		//cette méthode n'est pas géniale, on a écrit à la main les conditions
		if (this.equals(ACTIVATED)) {
			return object instanceof Activatable;
		} else if (this.equals(ANSWER)) {
			return false;
		} else if (this.equals(HAVE_IN_HANDS)) {
			return object instanceof Consumable;
		} else if (this.equals(HAVE_IN_INVENTORY)) {
			return object instanceof Consumable;
		}
		return false;
	}
}