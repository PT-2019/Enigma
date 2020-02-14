package common.language;

/**
 * Les languages et leur json.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 4.0 22/01/2020
 */
public enum Language {
	FRENCH("Français", "assets/lang/fr/"),
	ENGLISH("English", "assets/lang/en/"),
	;

	private static final String JSON = ".json";
	private static final String BASE_NAME = "base";
	private static final String DOCUMENTATION_NAME = "doc";

	/**
	 * Nom du language
	 */
	public final String name;
	/**
	 * Chemin du json
	 */
	public final String json;
	/**
	 * Chemin de la documentation
	 */
	public final String doc;

	Language(String name, String folder) {
		this.name = name;
		this.json = folder + BASE_NAME + JSON;
		this.doc = folder + DOCUMENTATION_NAME + JSON;
	}
}
