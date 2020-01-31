package api;

import api.hud.CustomWindow;

/**
 * Représentation d'une application
 *
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 1.0
 */
public interface Application {

	/**
	 * Cette méthode lance une application
	 *
	 * @since 1.0
	 */
	void start();

	/**
	 * Ferme l'application
	 *
	 * @since 4.0
	 */
	void stop();

	/**
	 * Retourne la fenêtre de l'application
	 *
	 * @return la fenêtre de l'application
	 * @since 5.0
	 */
	CustomWindow getWindow();
}