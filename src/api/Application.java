package api;

import api.ui.CustomWindow;

/**
 * Représentation d'une application
 *
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 1.0
 */
public interface Application {

	/**
	 * Affiche aucune info
	 * @since 6.0
	 */
	int LOG_NONE = 0;
	/**
	 * Affiche info debug
	 * @since 6.0
	 */
	int LOG_DEBUG = 3;
	/**
	 * Affiche info
	 * @since 6.0
	 */
	int LOG_INFO = 2;
	/**
	 * Affiche info d'erreurs
	 * @since 6.0
	 */
	int LOG_ERROR = 1;

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