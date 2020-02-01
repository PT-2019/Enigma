package api.utils;

/**
 * Celui qui observe les personnes qui implémentent observateur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 4.0
 */
public interface Subject<T> {

	/**
	 * Ajoute un observateur
	 *
	 * @param obs un observateur
	 */
	void addObserver(Observer<T> obs);

	/**
	 * Retire un observateur
	 *
	 * @param obs un observateur
	 */
	void removeObserver(Observer<T> obs);

	/**
	 * Appel de tous les observateurs {@link Observer#update(Object)}
	 *
	 * @param object object
	 */
	void updateObserver(T object);
}
