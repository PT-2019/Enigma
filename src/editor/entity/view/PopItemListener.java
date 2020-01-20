package editor.entity.view;

import api.enums.Layer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import game.entity.map.MapTestScreenCell;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PopItemListener implements ItemListener {

    private TiledMap map;

    private MapTestScreenCell cell;

    public PopItemListener(TiledMap map, MapTestScreenCell cell){
        this.map = map;
        this.cell = cell;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(Layer.COLLISION.name());

        TiledMapTileLayer.Cell tmp = layer.getCell(cell.getIndex()%layer.getWidth(),cell.getIndex()/layer.getWidth());

        if( e.getStateChange() == ItemEvent.SELECTED){
            tmp.setTile(map.getTileSets().getTile(2041));
        }else{
            tmp.setTile(map.getTileSets().getTile(0));
        }
    }
}