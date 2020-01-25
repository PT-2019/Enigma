package api.enums;

import editor.utils.lang.GameLanguage;
import editor.utils.lang.fields.HUDFields;

import static editor.utils.lang.GameLanguage.gl;

/**
 * Nom des couches pour l'affichage de la map
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 1.0
 * @since 1.0
 */
public enum Layer {
	//Les couches sont rangées dans l'ordre
	//Les premières sont rangées en bas tandis que les dernières sont en haut
	//Exemple : il y a le sol (FLOOR1), sur le sol il y a une assiette (DECORATION1),
	// dans l'assiette il y a à manger (DECORATION2)
	/**
	 * Première couche de sol
	 */
	FLOOR1(gl.get(HUDFields.FIRST_LAYER)+" 0"),

	/**
	 * Seconde couche de sol
	 */
	FLOOR2(gl.get(HUDFields.LAYER)+" 1"),

	/**
	 * Première couche de décoration
	 */
	DECORATIONS1(gl.get(HUDFields.LAYER)+" 2"),

	/**
	 * Seconde couche de décoration
	 */
	DECORATIONS2(gl.get(HUDFields.LAYER)+" 3"),

	/**
	 * Couche de collision
	 */
	COLLISION("Accessible");

	public final String name;

	Layer(String name) {
		this.name = name;
	}
}

