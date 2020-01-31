package datas;

/**
 * Etats de l'éditeur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 23/01/2020
 * @since 5.0 23/01/2020
 */
public enum EditorState {
	/**
	 * Glisser déposer entités ok, on peut cliquer sur une entité pour ajouter
	 * une énigme
	 */
	NORMAL,
	/**
	 * Pas de glisser déposer, cliquer sur une entité la supprime, sauf si elle contient
	 * d'autres entités
	 */
	ERASE,
	/**
	 * Permet de déplacer des entités
	 */
	MOVE
}
