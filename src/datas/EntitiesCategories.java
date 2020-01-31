package datas;

/**
 * Les catégories d'entités
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public enum EntitiesCategories {
	ROOMS("Salles"),
	DECORS("Décors"),
	ITEMS("Objects"),
	ENTITIES("Personnages"),
	ACTIONS("Actions");

	public final String name;

	EntitiesCategories(String name) {
		this.name = name;
	}

}
