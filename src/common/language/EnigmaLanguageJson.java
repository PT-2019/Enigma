package common.language;

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

	/**
	 * Dans le json, il y a un attribut values et
	 * des éléments (clef: valeur)
	 * ce qui donne cette hashMap après serialization
	 */
	private HashMap<String, String> values;

	public EnigmaLanguageJson() {
		this.values = new HashMap<>();
	}

	/**
	 * Retourne la valeur dans la HashMap correspond a ce field
	 *
	 * @param field field
	 * @return la valeur dans la HashMap correspond a ce field
	 */
	public String get(Field field) {
		return this.values.get(field.toString());
	}

}
