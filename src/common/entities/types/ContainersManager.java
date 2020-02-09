package common.entities.types;

import com.badlogic.gdx.math.Vector2;
import common.entities.GameObject;
import common.map.MapObject;

import java.util.ArrayList;

/**
 * Une classe pour les entités ou items qui peuvent contenir
 * d'autres containers
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 4.0 23/12/2019
 */
public interface ContainersManager {

	/**
	 * Ajoute un enfant
	 * @param object object
	 * @param start position dans la map
	 * @since 6.0
	 */
	default void addContainer(GameObject object, Vector2 start){}

	/**
	 * Ajoute un enfant
	 * @param object enfant
	 * @since 6.0
	 */
	default void addContainer(MapObject object){}

	/**
	 * Retire un enfant
	 * @param object enfant
	 * @since 6.0
	 */
	default void removeContainer(GameObject object){}

	/**
	 * Retourne tous les enfants contenus
	 * @return tous les enfants contenus
	 * @since 6.0
	 */
	default ArrayList<MapObject> getContainers(){return new ArrayList<>(); }


}
