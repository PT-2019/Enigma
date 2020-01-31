package api.utils;

import api.utils.annotations.ConvenienceClass;
import api.utils.annotations.ConvenienceMethod;

/**
 * Affiche dans un terminal en couleurs
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 21/01/2020
 * @since 4.0 21/01/2020
 */
@ConvenienceClass
public final class PrintColor {

	private static final String ANSI_RESET = "\u001B[0m";

	/**
	 * Affiche (println) dans la console (standard) dans une couleur. Fait un saut de ligne
	 *
	 * @param str   message
	 * @param color couleur
	 */
	@ConvenienceMethod
	public static void println(String str, AsciiColor color) {
		System.out.println(color.code + str + ANSI_RESET);
	}

	/**
	 * Affiche (print) dans la console (standard) dans une couleur
	 *
	 * @param str   message
	 * @param color couleur
	 */
	@ConvenienceMethod
	public static void print(String str, AsciiColor color) {
		System.out.print(color.code + str + ANSI_RESET);
	}
}
