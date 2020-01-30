package api.utils;

/**
 * Un object observé
 *
 * @param <T> le type de l'object transmis par update. {@link Subject#updateObserveur(Object)}
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 4.0
 */
public interface Observer<T> {

	/**
	 * Méthode de mise à jour de l'objet
	 *
	 * @param object un object qui contient les données du changement
	 */
	void update(T object);

}
