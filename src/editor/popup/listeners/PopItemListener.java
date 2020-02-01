package editor.popup.listeners;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import common.map.MapTestScreenCell;
import data.Layer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Lorsqu'un item est sélectionné ce controlleur rend la case bloquante ou sinon
 * non bloquante
 */
public class PopItemListener implements ItemListener {

	/**
	 * Tile qui représente une case bloquante
	 */
	private static final int TILE_BLOC = 2041;
	/**
	 * Tile qui représente une case non bloquante
	 */
	private static final int TILE_VOID = 0;
	private TiledMap map;
	private MapTestScreenCell cell;

	public PopItemListener(TiledMap map, MapTestScreenCell cell) {
		this.map = map;
		this.cell = cell;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(Layer.COLLISION.name());

		TiledMapTileLayer.Cell tmp = layer.getCell(cell.getIndex() % layer.getWidth(), cell.getIndex() / layer.getWidth());


		if (e.getStateChange() == ItemEvent.SELECTED) {
			tmp.setTile(map.getTileSets().getTile(TILE_BLOC));
		} else {
			tmp.setTile(map.getTileSets().getTile(TILE_VOID));
		}
	}
}
