package common.entities.types;

/**
 * Permet à un objet d'avoir un contenu lisible par les joueurs
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 1.0
 * @since 1.0
 */
public interface Content {

	/**
	 * Obtenir le contenu
	 *
	 * @return le contenu, le contenu peut être vide
	 * @since 1.0
	 */
	String getContent();

	/**
	 * Ajoute un contenu à l'objet
	 *
	 * @param content Contenu à ajouter
	 * @since 1.0
	 */
	void setContent(String content);
}
