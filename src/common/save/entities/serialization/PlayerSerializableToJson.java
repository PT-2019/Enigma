package common.save.entities.serialization;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import common.utils.IDFactory;

import java.util.HashMap;

/**
 * Représentation d'une entité du fichier json
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 26/01/2020
 * @since 5.0 26/01/2020
 */
public class PlayerSerializableToJson extends EntitySerializable {

	private String json;
	private String key;

	@SuppressWarnings("unused")
	public PlayerSerializableToJson() {
		//TOUTES LES VARIABLES DOIVENT ETRE INSTANCIEES EN DEHORS A CAUSE DE LA LIBGDX
		//ET DE Json.fromJson qui fait des trucs bizarres avec une classe qui
		//peut mettre un float dans une variable de type int et qui lève une
		//NumberFormatException lorsque l'on passe ce int a une méthode qui attends
		//un int
	}

	/**
	 * Une entité sérializable avec juste assez d'infos pour utiliser la fonction
	 * {@link EntityFactory#createEntity(EntitySerializable, Integer, Vector2, IDFactory)}
	 *
	 * @param width     largeur
	 * @param height    hauteur
	 * @param className chemin classe entité
	 * @param tiles     tiles
	 * @since 5.0
	 */
	@SuppressWarnings("unused")
	public PlayerSerializableToJson(int width, int height, String className, HashMap<String, Array<Float>> tiles) {
		super(width, height, className, tiles);
		this.key = "";
		this.json = "";
	}

	/**
	 * Crée une copie d'une entité sérializable
	 *
	 * @param entity l'entité a copier
	 */
	private PlayerSerializableToJson(PlayerSerializableToJson entity) {
		super(entity);
		this.json = entity.json;
		this.key = entity.key;
	}

	/**
	 * Retourne le chemin du json
	 *
	 * @return le chemin du json
	 */
	public String getJson() {
		return json;
	}

	/**
	 * Retourne le nom de l'entité dans le json
	 *
	 * @return le nom de l'entité dans le json
	 */
	public String getKey() {
		return key;
	}

	@Override
	protected EntitySerializable duplicates() {
		return new PlayerSerializableToJson(this);
	}

	@Override
	public String toString() {
		return "PlayerSerializableToJson{ {" + super.toString() + "} " +
				"json='" + json + '\'' +
				", key='" + key + '\'' +
				'}';
	}
}
