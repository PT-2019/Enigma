package data.config;

import api.utils.Utility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglFileHandle;
import com.badlogic.gdx.backends.lwjgl.LwjglPreferences;
import common.data.UserData;
import common.data.UserPreferencesFields;
import common.entities.players.Player;
import common.hud.EnigmaOptionPane;

import java.io.File;
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
		Preferences prefs;
		if (Gdx.app == null) {
			//récupération des préférences manuellement
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			prefs = new LwjglPreferences(new LwjglFileHandle(new File(config.preferencesDirectory,
					Config.USER_DATA_FILE_NAME), config.preferencesFileType));

		} else {
			prefs = Gdx.app.getPreferences(Config.USER_DATA_FILE_NAME);
		}

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
			while (name.isEmpty() || Utility.isBlank(name) || name.equals(EnigmaOptionPane.CANCEL)) {
				name = EnigmaOptionPane.showInputUserName();
				System.out.println(name);
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
