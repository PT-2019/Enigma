package editor.utils.lang;

import java.util.HashMap;

public class EnigmaLanguageJson {

	private HashMap<String, String> values;

	public EnigmaLanguageJson() {
		this.values = new HashMap<>();
	}

	public String get(Field field) {
		return this.values.get(field.toString());
	}

}
