package common.save.entities.serialization;

/**
 * Permet d'instancier un player et de définir son animation avec un Json
 */
public class PlayerSerializable {

	/**
	 * Nom du personnage
	 */
	private String name;

	/**
	 * Chemin vers le fichier des sprites d'animations
	 */
	private String path;

	/**
	 * Nombre de colonne qui représente tout les sprites de l'animations
	 */
	private int cols;

	/**
	 * Nombre de ligne qui représente tout les sprites de l'animations
	 */
	private int rows;

	/**
	 * Nombre de colonnes totale dans la photo avec les sprites
	 */
	private int colPerImage;

	/**
	 * Nombre de ligne totale dans la photo avec les sprites
	 */
	private int rowPerImage;

	/**
	 * Index des sprites du player que l'on veut
	 */
	private int index;

	/**
	 * Durée de chaque frame de l'animation du player
	 */
	private float duration;

	/**
	 * Renvoi le nom du personnage
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}

	public int getColPerImage() {
		return colPerImage;
	}

	public int getRowPerImage() {
		return rowPerImage;
	}

	public int getIndex() {
		return index;
	}

	public float getDuration() {
		return duration;
	}
}
