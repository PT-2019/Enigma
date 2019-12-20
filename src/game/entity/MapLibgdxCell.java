package game.entity;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import editor.entity.EntitySerializable;

/**
 * Une cellule de la map libgdx
 *
 * @version 3.0 14 décembre 2019
 * @since 3.0 14 décembre 2019
 */
public class MapLibgdxCell extends TiledMapTileLayer.Cell {

	private final TiledMapTileLayer layer;
	private EntitySerializable entity;

	public MapLibgdxCell(TiledMapTileLayer layer, int i, int j) {
		this.layer = layer;
		this.entity = null;
	}

	public EntitySerializable getEntity() {
		return entity;
	}

	public void setEntity(EntitySerializable entity) {
		this.entity = entity;
		//this.entity.getClassName renvoi par exemple editor.entity.item.Chest
	}
}
