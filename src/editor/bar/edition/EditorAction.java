package editor.bar.edition;

/**
 * Une action
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0
 * @since 5.0 01/02/2020
 */
public interface EditorAction {

	/**
	 * Retourne le type d'action
	 * @return le type d'action
	 * @since 5.0
	 */
	ActionTypes getType();

	/**
	 * La méthode doAction fait l'action normale
	 *
	 * @since 5.0
	 */
	void doAction();

	/**
	 * La méthode undoAction fait l'action inverse de l'action normale
	 *
	 * @since 5.0
	 */
	void undoAction();

	/**
	 * Supprime définitivement l'action
	 * @since 6.0
	 */
	void clear();

}
