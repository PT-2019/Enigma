package common.save.entities.serialization;

/**
 * Permet d'instancier un item
 */
public class ItemSerializable {

	/**
	 * Chemin vers la texture
	 */
	private String path;

	/**
	 * Chemin de la classe
	 */
	private String classPath;

	/**
	 * Texte au survol
	 */
	private String hover;

	/**
	 * Nom de la région dans l'atlas
	 */
	private String key;

	/**
	 * Chemin vers l'atlas
	 */
	private String atlas;

	public String getHover() {
		return hover;
	}

	public String getKey() {
		return key;
	}

	public String getAtlas() {
		return atlas;
	}

	public String getPath() {
		return path;
	}

	public String getClassPath() {
		return classPath;
	}
}
