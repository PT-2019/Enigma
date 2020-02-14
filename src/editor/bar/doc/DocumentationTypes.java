package editor.bar.doc;

/**
 * Les types des éléments de la doc
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 11/02/2020
 * @since 6.0 11/02/2020
 */
public enum DocumentationTypes {
	/**
	 * Un titre est le seul élément de contenu
	 */
	TITLE(),
	/**
	 * Un titre suivi d'une icône.
	 * Contient deux éléments de contenu, le texte puis l'icône.
 	 */
	TITLE_SMALL(),
	/**
	 * Un message représente plusieurs éléments de contenus.
	 */
	MESSAGE(),
	/**
	 * Un item représente un seul élément de contenu : son chemin
	 */
	IMAGE()
}
