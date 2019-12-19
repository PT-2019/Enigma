package game.entity;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import editor.entity.EntitySerializable;

/**
 * Une cellule de la map libgdx
 *
 * @version 3.0 14 décembre 2019
 * @since 3.0 14 décembre 2019
 */
public class MapLibgdxCell extends TiledMapTileLayer.Cell {

	private TiledMapTileLayer layer;

	private EntitySerializable entity;

	private int index;


	public MapLibgdxCell(TiledMapTileLayer layer, int index) {
		this.layer = layer;
		this.entity = null;
		this.index = index;
	}

	public EntitySerializable getEntity() {
		return entity;
	}

	public void setEntity(EntitySerializable entity) {
		this.entity = entity;
		//this.entity.getClassName renvoi par exemple editor.entity.item.Chest
	}

	public TiledMapTileLayer getLayer(){
		return this.layer;
	}

	public int getIndex() {
		return index;
	}
}