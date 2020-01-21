package editor.utils.lang;

import editor.utils.lang.fields.Field;

import java.util.HashMap;

/**
 * Classe pour parser JSon de langues
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 21/01/2020
 * @since 4.0 21/01/2020
 */
public class EnigmaLanguageJson {

	private HashMap<String, String> values;

	public EnigmaLanguageJson() {
		this.values = new HashMap<>();
	}

	public String get(Field field) {
		return this.values.get(field.toString());
	}

}
