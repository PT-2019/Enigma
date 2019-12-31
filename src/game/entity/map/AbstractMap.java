package game.entity.map;

import api.utils.Bounds;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import editor.entity.EntitySerializable;

public abstract class AbstractMap extends Group {

	AbstractMap(String path, float unitScale){}

	/**
	 * Retourne la case (indices) dans la map depuis une positon x,y dans l'espace.
	 * <p>
	 * Attention! La position  x,y est considérée comme étant toujours dans la map.
	 *
	 * @param posX position x
	 * @param posY position y
	 * @param map  la map
	 * @return la case (indices) dans la map depuis une positon x,y dans l'espace.
	 * @since 3.0 14 décembre 2019
	 */
	@SuppressWarnings("WeakerAccess")
	public static Vector2 posToIndex(float posX, float posY, final AbstractMap map) {
		Vector2 index = new Vector2();

		posX /= map.getUnitScale();
		posY /= map.getUnitScale();

		float column = MathUtils.clamp(Math.round(posX / map.getTileWidth()), 0, map.getMapBounds().right);
		float row = MathUtils.clamp(Math.round(posY / map.getTileHeight()), 0, map.getMapBounds().top);

		index.x = column;
		index.y = row;

		return index;
	}

	/**
	 * Charge une entité sur la map a un position si elle est sur la map
	 *
	 * @param entity l'entité à charger
	 * @param pos    la position o&#249; charger
	 * @return true si l'entité a étée chargée
	 * @since 3.0
	 */
	public abstract boolean loadEntity(EntitySerializable entity, Vector2 pos);

	/**
	 * Définit les bounds de la map
	 *
	 * @since 3.0
	 */
	protected abstract void setMapBounds();

	protected abstract Bounds getMapBounds();

	protected abstract Rectangle getMapSize();

	public abstract void showGrid(boolean b);

	public abstract float getMapHeight();

	public abstract float getMapWidth();

	public abstract float getUnitScale();

	public abstract int getTileWidth();

	public abstract int getTileHeight();

	public abstract OrthographicCamera getCamera();

	public abstract TiledMap getTiledMap();

	/**
	 * Met a jour les bounds de la map selon zoom
	 *
	 * @param zoom de combien le zoom est augmenté ou diminué
	 * @since 3.0
	 * @deprecated since 4.0
	 */
	@Deprecated
	protected abstract void updateMapBounds(int zoom);
}
