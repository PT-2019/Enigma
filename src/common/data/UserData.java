package common.data;

import api.utils.annotations.ConvenienceMethod;

import java.util.HashMap;

/**
 * Données de l'utilisateur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.1
 * @since 6.0
 */
public class UserData {

	/**
	 * Map des données du fichier préférences
	 */
	private final HashMap<String, String> data;

	/**
	 * Données de l'utilisateur
	 *
	 * @param data Données
	 * @since 6.0
	 */
	public UserData(HashMap<String, String> data) {
		this.data = data;

		//vérifie que l'on a tout
		for (UserPreferencesFields field : UserPreferencesFields.values()) {
			if (!data.containsKey(field.value)) {
				throw new IllegalArgumentException("Attribut " + field.value + " manquant");
			}
		}
	}

	/**
	 * Obtenir les données
	 *
	 * @return Les données
	 * @since 6.0
	 */
	public HashMap<String, String> getData() {
		return new HashMap<>(this.data);//clone
	}

	/**
	 * Obtenir le nom
	 *
	 * @return Le nom
	 * @since 6.0
	 */
	@ConvenienceMethod
	public String getName() {
		return this.data.get(UserPreferencesFields.NAME.value);
	}

	/**
	 * Obtenir la donnée correspond à la clef
	 *
	 * @param key une clef
	 * @return la donnée correspond à la clef
	 * @see UserPreferencesFields
	 * @since 6.1
	 */
	public String getValue(UserPreferencesFields key) {
		return this.data.get(key.value);
	}
}
