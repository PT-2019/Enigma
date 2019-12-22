package api.enums;

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
	 * Couche de collision
	 */
	COLLISION,
	/**
	 * Seconde couche de décoration
	 */
	DECORATIONS2,
	/**
	 * Première couche de décoration
	 */
	DECORATIONS1,
	/**
	 * Seconde couche de sol
	 */
	FLOOR2,
	/**
	 * Première couche de sol
	 */
	FLOOR1
}

