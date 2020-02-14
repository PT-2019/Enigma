package data;

/**
 * états de l'éditeur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.2
 * @since 5.0 23/01/2020
 */
public enum EditorState {
	/**
	 * Glisser déposer entités ok, on peut cliquer sur une entité pour ajouter
	 * une énigme
	 */
	NORMAL(false),

	/**
	 * Pas de glisser déposer, cliquer sur une entité la supprime, sauf si elle contient
	 * d'autres entités
	 */
	ERASE(false),

	/**
	 * Permet de déplacer des entités
	 */
	MOVE(false),

	/**
	 * En cours de simulation
	 *
	 * @since 6.0
	 */
	SIMULATION(false),

	/**
	 * En cours de zoom
	 *
	 * @since 6.1
	 */
	ZOOM(true),

	/**
	 * Second popup désactivé
	 *
	 * @since 6.2
	 */
	SPECIAL_POPUP_DISABLED(true),

	/**
	 * Second popup activé
	 *
	 * @since 6.2
	 */
	SPECIAL_POPUP_ENABLED(true),

	/**
	 * Second popup avec contenu
	 *
	 * @since 6.2
	 */
	SPECIAL_POPUP_CONTENT(true),

	/**
	 * Englobe tous les états persistants
	 *
	 * @since 6.2
	 */
	PERSISTANT(false),
	;

	/**
	 * true si un état est persistant
	 */
	public final boolean persistant;

	EditorState(boolean persistant) {
		this.persistant = persistant;
	}
}
