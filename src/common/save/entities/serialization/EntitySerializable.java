package common.save.entities.serialization;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import data.EntitiesCategories;
import data.Layer;

import java.util.HashMap;

/**
 * Représentation d'une entité du fichier json
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
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
	/**
	 * Message de survol
	 */
	private String hover = null;

	public EntitySerializable() {
		//TOUTES LES VARIABLES DOIVENT ETRE INSTANCIEES EN DEHORS A CAUSE DE LA LIBGDX
		//ET DE Json.fromJson qui fait des trucs bizarres avec une classe qui
		//peut mettre un float dans une variable de type int et qui lève une
		//NumberFormatException lorsque l'on passe ce int a une méthode qui attends
		//un int
	}

	/**
	 * Une entité sérializable avec juste assez d'infos pour utiliser la fonction
	 *
	 * @param width     largeur
	 * @param height    hauteur
	 * @param className chemin classe entité
	 * @since 5.0
	 */
	public EntitySerializable(int width, int height, String className, HashMap<String, Array<Float>> tiles) {
		this.width = width;
		this.height = height;
		this.className = className;
		this.tiles = tiles;
	}

	/**
	 * Crée une copie d'une entité sérializable
	 *
	 * @param entity l'entité a copier
	 */
	protected EntitySerializable(EntitySerializable entity) {
		super();
		this.className = entity.className;
		this.path = entity.path;
		this.width = entity.width;
		this.height = entity.height;
		this.tiles = entity.tiles;
		this.category = entity.category;
		this.hover = entity.hover;
	}

	/**
	 * Retourne la catégorie de l'entité
	 *
	 * @return la catégorie de l'entité
	 * @see game.gui.CategoriesMenu
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
	 * @see data.Layer
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

	/**
	 * Retourne un texte de survol de l'entité, null si aucun
	 *
	 * @return un texte de survol de l'entité, null si aucun
	 * @since 4.0
	 */
	public String getHover() {
		return hover;
	}

	/**
	 * Crée une copie
	 *
	 * @return une copie
	 * @since 5.0
	 */
	protected EntitySerializable duplicates() {
		return new EntitySerializable(this);
	}

	@Override
	public String toString() {
		return "EntitySerializable{" + "path='" + path + '\'' + ", width=" + width + ", height=" + height +
				", tiles=" + tiles + ", category=" + category + ", className='" + className + '\'' +
				", hover='" + hover + '\'' + '}';
	}
}
