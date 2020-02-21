package data;

import common.language.Field;
import common.language.HUDFields;

import static common.language.GameLanguage.gl;

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
	FLOOR1(HUDFields.FIRST_LAYER, " 0"),

	/**
	 * Seconde couche de sol
	 */
	FLOOR2(HUDFields.LAYER, " 1"),

	/**
	 * Première couche de décoration
	 */
	DECORATIONS1(HUDFields.LAYER, " 2"),

	/**
	 * Seconde couche de décoration
	 */
	DECORATIONS2(HUDFields.LAYER, " 3"),

	/**
	 * Couche de collision
	 */
	COLLISION(null, "Accessible");

	public final String name;

	Layer(Field field, String comp) {
		if (gl != null && field != null) {
			this.name = gl.get(field) + comp;
		} else {
			this.name = this.name();
		}
	}

	/**
	 * Tri du niveau le plus haut au plus bas
	 *
	 * @param layer un niveau
	 * @param other un autre niveau
	 * @return 0 si égaux, -1 si l'autre niveau est plus haut, 1 si l'autre niveau est plus bas
	 */
	public static int compare(Layer layer, Layer other) {
		if (layer.equals(other)) return 0;
		if (layer.ordinal() < other.ordinal()) { //si layer est avant dans la liste
			//alors c'est comme Floor1 avant Decorations1
			//donc layer est après other
			return -1;
		}
		return 1;//sinon avant
	}
}

