package game.entities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public abstract class AbstractMap {

	/**
	 * Return if a position x,y is walkable
	 *
	 * @param x position x of a player updated with movement
	 * @param y position x of a player updated with movement
	 * @param entity an entity
	 *
	 * @return true if a position x,y is walkable
	 */
	public abstract boolean isWalkable(float x, float y, Entity entity);

	/**
	 * Look if there are available actions at posX, posY.
	 * Then entity will performs all actions only if there are the same kind (name) as triggerName.
	 *
	 * @param posX positionX, will be updated if it's a ON_USER trigger on any trigger with check enabled
	 * @param posY positionY, will be updated if it's a ON_USER trigger on any trigger with check enabled
	 * @param entity entity that may perform an action
	 * @param triggerName an enum value for a trigger type
	 *
	 * @return true if there are at least one action that as been done else face.
	 */
	public abstract boolean action(float posX, float posY, Entity entity, Trigger triggerName);

	/**
	 * Return of vectors of item witch match property name
	 *
	 * @param propertyName a property name such as "name"
	 * @return an ArrayList of all matchingItem's position
	 * @see Vector2
	 */
	public abstract ArrayList<Vector2> getPropertyPosition(String propertyName);

	/**
	 * Return unit scale of a map.
	 * Should be 1.0 by default but can be changed.
	 * @return unit scale of a map.
	 * @see OrthogonalTiledMapRenderer#getUnitScale()
	 */
	public abstract float getUnitScale();

	/**
	 * Return a rectangle witch contains map width and height.
	 * @return a rectangle witch contains map width and height.
	 */
	public abstract Rectangle getBounds();

	/**
	 * Return all entities in a layer
	 * @param layer a layer
	 * @return all entities in a layer
	 */
	public abstract Array<Entity> getEntityInLayer(int layer);

	protected static Vector2 getFacedCase(float posX, float posY, AbstractMap map, Entity entity) {
		/*Vector2 playerPos = posToIndex(posX, posY, map);

		//if onUse, we check the faced case
		if (entity.isDirectionFaced(FacedDirection.FRONT)) {
			playerPos.y -= 1;
		} else if (entity.isDirectionFaced(FacedDirection.LEFT)) {
			playerPos.x -= 1;
		} else if (entity.isDirectionFaced(FacedDirection.RIGHT)) {
			playerPos.x += 1;
		} else {//Back
			playerPos.y += 1;
		}

		return playerPos;*/
		throw new UnsupportedOperationException("not supported");
	}

	protected static Vector2 posToIndex(float posX, float posY, AbstractMap map) {
		Vector2 index = new Vector2();

		posX /= map.getUnitScale();
		posY /= map.getUnitScale();

		//convert coordinates to row and column
		//and clamp to the map
		int column = MathUtils.clamp(Math.round(posX / map.getTileWidth()), 0, map.getMapWidth());
		int row = MathUtils.clamp(Math.round(posY / map.getTileHeight()), 0, map.getMapHeight());

		index.x = column;
		index.y = row;

		return index;
	}

	public abstract ArrayList<MapProperties> getProperties(String propertyName);

	public abstract ArrayList<MapObject> getMapObjects(String propertyName);

	protected abstract int getMapWidth();

	protected abstract int getMapHeight();

	protected abstract float getTileHeight();

	protected abstract float getTileWidth();
}
