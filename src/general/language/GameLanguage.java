package general.language;

import api.utils.Utility;
import com.badlogic.gdx.utils.Json;
import datas.config.Config;

import java.util.HashMap;

/**
 * Factory responsable des échanges entre les valeurs du json
 * et le programme.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 22/01/2020
 * @since 4.0 22/01/2020
 */
public class GameLanguage {

	/**
	 * Variable globale a utiliser pour demander la valeur
	 * d'un FIELD
	 *
	 * @see Field
	 * @see general.language.GameFields
	 * @see general.language.HUDFields
	 * <p>
	 * Doit être initialisé ({@link #init()}.
	 * <p>
	 * Globale et public pour éviter de devoir écrire trop de syntaxe
	 * car très très souvent utilisée.
	 */
	public static GameLanguage gl;

	/**
	 * Garde les languages déjà chargés en mémoire
	 */
	private static HashMap<Language, EnigmaLanguageJson> languages = new HashMap<>();

	/**
	 * Le json actuel, on lui demande les valeurs des champs
	 */
	private EnigmaLanguageJson json;

	/**
	 * Langage actuel
	 */
	private Language language;

	/**
	 * Crée un gameLanguage avec le langage par défaut.
	 */
	private GameLanguage() {
		this.setLanguage(Config.DEFAULT);
	}

	/**
	 * Initialise la variable globale a utiliser pour demander la valeur
	 * d'un FIELD
	 */
	public static void init() {
		if (gl == null) {
			gl = new GameLanguage();
		}
	}

	/**
	 * Retourne le langage actuel
	 *
	 * @return le langage actuel
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * Change le language
	 *
	 * @param language un language
	 */
	public void setLanguage(Language language) {
		this.language = language;

		//pas déjà chargé
		if (!GameLanguage.languages.containsKey(language)) {
			//load from json
			Json j = new Json();
			this.json = j.fromJson(EnigmaLanguageJson.class, Utility.loadFile(language.json));
			GameLanguage.languages.put(language, this.json);
		} else {
			this.json = GameLanguage.languages.get(language);
		}
	}

	/**
	 * Retourne la valeur d'un champ dans la langue actuelle
	 *
	 * @param field un champ
	 * @return la valeur d'un champ dans la langue actuelle
	 */
	public String get(Field field) {
		//cache a qui on demande la valeur du field
		return this.json.get(field);
	}


}
