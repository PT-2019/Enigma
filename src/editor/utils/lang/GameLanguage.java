package editor.utils.lang;

import api.utils.Utility;
import com.badlogic.gdx.utils.Json;
import editor.utils.lang.fields.Field;

import java.util.HashMap;

/**
 * Charger le json du langage par default.
 * Pouvoir changer de langage.
 * <p>
 * Phase de tests.
 */
public class GameLanguage {

	public static final Language DEFAULT = Language.FRENCH;

	public static GameLanguage gl;

	private static HashMap<Language, EnigmaLanguageJson> languages = new HashMap<>();

	private EnigmaLanguageJson l;

	private Language language;

	private GameLanguage() {
		this.setLanguage(DEFAULT);
	}

	public static void init() {
		if (gl == null) {
			gl = new GameLanguage();
		}
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;

		if (!languages.containsKey(language)) {
			//load from json
			Json j = new Json();
			l = j.fromJson(EnigmaLanguageJson.class, Utility.loadFile(language.json));
			languages.put(language, l);
		} else {
			l = languages.get(language);
		}
	}

	public String get(Field field) {
		return l.get(field);
	}


}
