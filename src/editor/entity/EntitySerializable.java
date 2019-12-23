package editor.entity;

import api.enums.EntitiesCategories;
import api.enums.Layer;
import com.badlogic.gdx.utils.Array;
import game.ui.CategoriesMenu;

import java.util.HashMap;

/**
 * Représentation d'une entité du fichier json
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class EntitySerializable {

	/**
	 * chemin de la texture
	 */
	private String path;
	/**
	 * dimension
	 */
	private int width = 1, height = 1;
	/**
	 * les tiles (a convertir en int)
	 */
	private HashMap<String, Array<Float>> tiles = new HashMap<>();
	/**
	 * catégorie de l'entité
	 */
	private EntitiesCategories category;
	/**
	 * sa classe
	 */
	private String className;

	public EntitySerializable() {
	}

	/**
	 * Crée une copie d'une entité sérializable
	 *
	 * @param entity l'entité a copier
	 */
	public EntitySerializable(EntitySerializable entity) {
		super();
		this.className = entity.className;
		this.path = entity.path;
		this.width = entity.width;
		this.height = entity.height;
		this.tiles = entity.tiles;
		this.category = entity.category;
	}

	/**
	 * Retourne la catégorie de l'entité
	 *
	 * @return la catégorie de l'entité
	 * @see CategoriesMenu
	 * @since 3.0
	 */
	public EntitiesCategories getCategory() {
		return this.category;
	}

	/**
	 * Renvoi le chemin vers le .png qui représente l'entité
	 *
	 * @return le chemin vers le .png qui représente l'entité
	 * @since 3.0
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Renvoi la largeur de l'objet
	 *
	 * @return la largeur de l'objet
	 * @since 3.0
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Renvoi la hauteur de l'objet
	 *
	 * @return la hauteur de l'objet
	 * @since 3.0
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Retourne un tableau de tiles TILED représentant l'entité selon niveau
	 *
	 * @param layer le niveau
	 * @return Retourne un tableau de tiles TILED représentant l'entité
	 * @see api.enums.Layer
	 * @since 3.0
	 */
	public Array<Float> getTiles(Layer layer) {
		return this.tiles.get(layer.name());
	}

	/**
	 * Retourne le nom .class de la classe qui correspond a cet entité
	 *
	 * @return le nom .class de la classe qui correspond a cet entité
	 * @since 3.0
	 */
	public String getClassName() {
		return this.className;
	}
}
