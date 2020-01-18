package api.entity.interfaces;

/**
 * Permet de gérer in ID
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.0
 * @since 2.0
 */
public interface IDInterface {

	/**
	 * Obtenir l'ID
	 *
	 * @return L'ID, -1 si pas initialisé
	 */
	int getID();

	/**
	 * Définir l'ID
	 *
	 * @param id ID
	 */
	void setID(int id);
}
