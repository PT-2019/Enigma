package common.data;

/**
 * Les champs contenus dans le fichier utilisateur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 14/02/2020
 * @since 6.0 14/02/2020
 */
public enum UserPreferencesFields {
	/**
	 * Donnée sur le nom
	 */
	NAME("name"),
	;

	public final String value;

	UserPreferencesFields(String value) {
		this.value = value;
	}
}
