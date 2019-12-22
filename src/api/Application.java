package api;

/**
 * Représentation d'une application
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 1.0
 * @since 1.0
 *
 * @see game.EnigmaGame
 * @see editor.EditorLuncher
 */
@FunctionalInterface
public interface Application {

	/**
	 * Cette méthode lance une application
	 */
	void start();

}