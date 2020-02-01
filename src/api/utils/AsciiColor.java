package api.utils;

/**
 * Couleurs et leur code d'échappement ascii
 *
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 4.0 21/01/2020
 */
public enum AsciiColor {

	BLACK("\u001B[30m"),
	RED("\u001B[31m"),
	GREEN("\u001B[32m"),
	YELLOW("\u001B[33m"),
	BLUE("\u001B[34m"),
	PURPLE("\u001B[35m"),
	CYAN("\u001B[36m"),
	WHITE("\u001B[37m"),
	ORANGE("\u001B[38m");

	public final String code;

	AsciiColor(String code) {
		this.code = code;
	}
}
