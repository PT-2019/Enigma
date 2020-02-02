package common.utils;

import api.Application;
import api.utils.PrintColor;
import data.config.Config;

/**
 * Affiche les logs de l'application
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 01/02/2020
 * @since 6.0 01/02/2020
 */
public final class Logger {

	/**
	 * Affichage du debug de l'application
	 *
	 * @param className nom de la classe
	 * @param message   message
	 * @since 6.0
	 */
	@SuppressWarnings("ConstantConditions")
	public static void printDebug(String className, String message) {
		if(Config.APPLICATION_LOG >= Application.LOG_DEBUG)
			PrintColor.println(className + ":" + message, Config.DEBUG_COLOR);
	}

	/**
	 * Affichage de message concernant les erreurs
	 *
	 * @param className nom de la classe
	 * @param message   message
	 * @since 6.0
	 */
	@SuppressWarnings("ConstantConditions")
	public static void printError(String className, String message) {
		if(Config.APPLICATION_LOG >= Application.LOG_ERROR)
			PrintColor.println(className + ":" + message, Config.ERROR_COLOR);
	}

	/**
	 * Affichage des messages moins importants de debug de l'application
	 *
	 * @param className nom de la classe
	 * @param message   message
	 * @since 6.0
	 */
	@SuppressWarnings("ConstantConditions")
	public static void printDebugALL(String className, String message) {
		if(Config.APPLICATION_LOG >= Application.DEBUG_ALL)
			PrintColor.println(className + ":" + message, Config.DEBUG_ALL_COLOR);


	}
}
