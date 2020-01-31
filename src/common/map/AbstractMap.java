package common.map;

import api.libgdx.utils.Bounds;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Représentation d'une map
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public abstract class AbstractMap extends Group {

	/**
	 * objects
	 */
	protected MapObjects objects;
	/**
	 * nom map
	 */
	protected String name;

	/**
	 * Le seul constructeur possible d'une map, ne fait rien
	 *
	 * @param path      chemin d'une map
	 * @param unitScale taux de distortion
	 */
	AbstractMap(String path, float unitScale) {
		//do nothing
		this.objects = new MapObjects();
		this.name = path;
	}

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
	 * <p>
	 * <p>
	 * VERSION SWING TO LIBGDX
	 */
	@SuppressWarnings("WeakerAccess")
	public static Vector2 swingPosToIndex(float posX, float posY, final AbstractMap map) {
		Vector2 index = new Vector2();

		posX /= map.getUnitScale();
		posY /= map.getUnitScale();

		float column = MathUtils.clamp(Math.round(posX / map.getTileWidth()), 0, map.getMapBounds().right);
		float row = MathUtils.clamp(Math.round(posY / map.getTileHeight()), 0, map.getMapBounds().top);

		index.x = column;
		index.y = row;

		return index;
	}

	protected abstract Bounds getMapBounds();

	protected abstract Rectangle getMapSize();

	/**
	 * Affiche la grille de la map
	 *
	 * @param show affiche la grille de la map
	 */
	public abstract void showGrid(boolean show);

	/**
	 * Renvoi la hauteur de la map
	 *
	 * @return la hauteur de la map
	 */
	public abstract float getMapHeight();

	/**
	 * Renvoi la largeur de la map
	 *
	 * @return la largeur de la map
	 */
	public abstract float getMapWidth();

	/**
	 * Retourne le taux de distorsion de la taille d'un tile
	 *
	 * @return le taux de distorsion de la taille d'un tile
	 */
	public abstract float getUnitScale();

	/**
	 * Retourne la largeur d'un tile
	 *
	 * @return la largeur d'un tile
	 */
	public abstract int getTileWidth();

	/**
	 * Retourne la hauteur d'un tile
	 *
	 * @return la hauteur d'un tile
	 */
	public abstract int getTileHeight();

	/**
	 * Retourne la map tiled
	 *
	 * @return la map tiled
	 */
	public abstract TiledMap getTiledMap();

	/**
	 * Retourne les entités de la map
	 *
	 * @return les entités de la map
	 */
	public MapObjects getEntitiesObjects() {
		throw new UnsupportedOperationException("");
	}
}
