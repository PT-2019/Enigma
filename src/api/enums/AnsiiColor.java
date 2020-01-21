package api.enums;

/**
 * Desc
 *
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 4.0 21/01/2020
 * @since 4.0 21/01/2020
 */
public enum AnsiiColor {

	BLACK("\u001B[30m"),
	RED("\u001B[31m"),
	GREEN("\u001B[32m"),
	YELLOW("\u001B[33m"),
	BLUE("\u001B[34m"),
	PURPLE("\u001B[35m"),
	CYAN("\u001B[36m"),
	WHITE("\u001B[37m");

	public final String code;

	AnsiiColor(String code) {
		this.code = code;
	}
}
