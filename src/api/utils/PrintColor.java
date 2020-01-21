package api.utils;

import api.enums.AnsiiColor;
import api.utils.annotations.ConvenienceClass;
import api.utils.annotations.ConvenienceMethod;

/**
 * Affiche dans un terminal en couleurs
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 4.0 21/01/2020
 * @since 4.0 21/01/2020
 */
@ConvenienceClass
public class PrintColor {

	private static final String ANSI_RESET = "\u001B[0m";

	@ConvenienceMethod
	public static void println(String str, AnsiiColor color){
		System.out.println(color.code+str+ANSI_RESET);
	}


}
