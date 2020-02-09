package common.map;

import api.utils.annotations.ConvenienceMethod;
import api.utils.annotations.Immutable;
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
 * @version 6.1
 * @since 6.0 01/02/2020
 */
@Immutable
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

	MapObject(Vector2 position, GameObject entity){
		this.position = position;
		this.entity = entity;
	}

	public GameObject getEntity() {
		return entity;
	}

	public Vector2 getPosition() {
		return position;
	}

	/**
	 * Crée un mapObject
	 * @param position position
	 * @param entity entité
	 * @return un mapObject
	 * @since 6.1
	 */
	@ConvenienceMethod
	public static MapObject create(Vector2 position, GameObject entity){
		return new MapObject(position, entity);
	}
}
