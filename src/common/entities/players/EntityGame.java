package common.entities.players;

/**
 * Interface pour toutes les entités du jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 5.0
 */
public interface EntityGame {

	/**
	 * Définit le json et sa clef dedans
	 *
	 * @param json chemin json
	 * @param key  clef (name=?) dans le json
	 * @since 5.0
	 */
	void setJson(String json, String key);

	/**
	 * Retourne le chemin du json
	 *
	 * @return le chemin du json
	 * @since 5.0
	 */
	String getJson();

	/**
	 * Retourne la clef (name=?) dans le json
	 *
	 * @return la clef (name=?) dans le json
	 * @since 5.0
	 */
	String getKey();
}
