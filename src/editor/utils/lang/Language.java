package editor.utils.lang;

/**
 * Les languages et leur json.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 4.0 22/01/2020
 * @since 4.0 22/01/2020
 */
public enum Language {
	FRENCH("Français", "assets/lang/french.json"),
	ENGLISH("English", "assets/lang/english.json");

	/**
	 * Nom du language
	 */
	public final String name;
	/**
	 * Chemin du json
	 */
	public final String json;

	Language(String name, String json) {
		this.name = name;
		this.json = json;
	}
}
