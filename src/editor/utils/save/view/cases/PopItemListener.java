package editor.utils.save.view.cases;

import api.enums.Layer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import game.entity.MapLibgdxCell;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PopItemListener implements ItemListener {

    public PopItemListener(){

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if( e.getStateChange() == ItemEvent.SELECTED){

        }else{

        }
    }
}
