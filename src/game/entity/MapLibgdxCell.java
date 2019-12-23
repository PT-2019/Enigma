package game.entity;

import api.entity.GameObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * Une cellule de la map libgdx
 *
 * @version 3.0 14 décembre 2019
 * @since 3.0 14 décembre 2019
 */
public class MapLibgdxCell extends TiledMapTileLayer.Cell {

	/**
	 * Niveau ou se trouve l'entité
	 */
	private final TiledMapTileLayer layer;

	/**
	 * Index de la case parmi les cases de l'entité
	 */
	private final int index;

	/**
	 * L'entité contenue dans la case
	 *
	 * this.entity.getClassName renvoi par exemple editor.entity.item.Chest
	 */
	private GameObject entity;

	/**
	 * Crée une cellule de la map
	 *
	 * @param layer Niveau ou se trouve l'entité
	 * @param index Index de la case parmi les cases de l'entité
	 */
	public MapLibgdxCell(TiledMapTileLayer layer, int index) {
		this.layer = layer;
		this.index = index;
		this.entity = null;
	}

	public GameObject getEntity() {
		return entity;
	}

	/**
	 * Défini l'entité contenue dans la case
	 *
	 * @param entity l'entité contenue dans la case
	 */
	public void setEntity(GameObject entity) {
		this.entity = entity;
	}

	public int getIndex() {
		return index;
	}

	public TiledMapTileLayer getLayer() {
		return this.layer;
	}
}
