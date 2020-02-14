package data.config;

import api.utils.Utility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import common.data.UserData;
import common.data.UserPreferencesFields;
import common.entities.players.Player;
import common.hud.EnigmaOptionPane;

import java.util.HashMap;
import java.util.Map;

/**
 * Défini les configurations et informations de l'utilisateur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.1
 * @since 3.0
 */
public class UserConfiguration {

	/**
	 * Instance
	 */
	private static UserConfiguration instance;

	/**
	 * Données de l'utilisateur
	 */
	private UserData data;

	private UserConfiguration() {
		//récupère le fichier des préférences
		Preferences prefs = Gdx.app.getPreferences(Config.USER_DATA_FILE_NAME);
		//map des données
		HashMap<String, String> data = new HashMap<>();

		//save toutes les valeurs
		for (Map.Entry<String, ?> entry : prefs.get().entrySet()) {
			data.put(entry.getKey(), entry.getValue().toString());
		}

		//pas de nom
		if (!data.containsKey(UserPreferencesFields.NAME.value)) {
			//demande son nom
			String name = "";
			while (name.isEmpty() || name.isBlank() || name.equals(EnigmaOptionPane.CANCEL)) {
				name = EnigmaOptionPane.showInputUserName();
			}
			data.put(UserPreferencesFields.NAME.value, Utility.normalize(Utility.escape(name)));
		}

		//met à jour le fichier
		prefs.put(data);
		prefs.flush();

		//charge les données
		this.data = new UserData(data);
	}

	/**
	 * Obtenir l'instance
	 *
	 * @return L'instance
	 */
	public static UserConfiguration getInstance() {
		if (instance == null) {
			instance = new UserConfiguration();
		}
		return instance;
	}

	/**
	 * Obtenir le nom
	 *
	 * @return Le nom
	 */
	public UserData getData() {
		return this.data;
	}

	/**
	 * Obtenir l'utilisateur
	 *
	 * @return Un objet Player de l'utilisateur
	 * @deprecated
	 */
	@Deprecated
	public Player getUser() {
		throw new UnsupportedOperationException("Deprecated. Use UserConfiguration#getData instead.");
	}
}
