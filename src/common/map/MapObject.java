package common.map;

import com.badlogic.gdx.math.Vector2;
import common.entities.GameObject;

/**
 * Un map object
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 01/02/2020
 * @since 6.0 01/02/2020
 */
public class MapObject {

	/**
	 * Entité
	 * @since 6.0
	 */
	private final GameObject entity;

	/**
	 * Position
	 * @since 6.0
	 */
	private final Vector2 position;

	public MapObject(Vector2 position, GameObject entity){
		this.position = position;
		this.entity = entity;
	}

	public GameObject getEntity() {
		return entity;
	}

	public Vector2 getPosition() {
		return position;
	}
}
