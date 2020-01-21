package editor.view.listeners;

import api.enums.Layer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import game.entity.map.MapTestScreenCell;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Lorsqu'un item est sélectionné ce controlleur rend la case bloquante ou sinon
 * non bloquante
 */
public class PopItemListener implements ItemListener {

    private TiledMap map;

    private MapTestScreenCell cell;
    /**
     * Tile qui représente une case bloquante
     */
    private final int TILEBLOC = 2041;
    /**
     * Tile qui représente une case non bloquante
     */
    private final int TILEVOID = 2041;

    public PopItemListener(TiledMap map, MapTestScreenCell cell){
        this.map = map;
        this.cell = cell;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(Layer.COLLISION.name());

        TiledMapTileLayer.Cell tmp = layer.getCell(cell.getIndex()%layer.getWidth(),cell.getIndex()/layer.getWidth());


        if( e.getStateChange() == ItemEvent.SELECTED){
            tmp.setTile(map.getTileSets().getTile(this.TILEBLOC));
        }else{
            tmp.setTile(map.getTileSets().getTile(this.TILEVOID));
        }
    }
}
