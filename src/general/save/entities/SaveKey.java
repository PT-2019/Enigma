package general.save.entities;

/**
 * Utilisée pour la sauvegarde.
 * On enregistre de la forme "clef" => valeur.
 * On utilise une interface par convenience.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 27/01/2020
 * @since 5.0 27/01/2020
 */
public interface SaveKey {

	/**
	 * Retourne la valeur de la clef
	 *
	 * @return la valeur de la clef
	 */
	String getKey();


}
