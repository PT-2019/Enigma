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

	private EntitySerializable entity;

	public MapLibgdxCell(){
		this.entity = null;
	}

	public void setEntity(EntitySerializable entity) {
		this.entity = entity;
		//this.entity.getClassName renvoi par exemple editor.entity.item.Chest
	}

	public EntitySerializable getEntity() { return entity; }
}
