package editor.map.view;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import editor.map.Case;
import editor.map.Map;
import game.entity.MapLibgdxCell;

import javax.swing.*;

public class TileMap extends Stage{

    private TiledMap map;

    private Map gameMap;

    public TileMap(TiledMap m, JComponent component, Map map){
        this.map = m;
        gameMap = map;

        CasePopUp popUp = new CasePopUp(component,this.map);
        CaseListener listenerCase = new CaseListener(popUp);
        MapLayers layers = m.getLayers();
        Case currentCase;

        TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(0);

        for (int y = 0; y < layer.getHeight(); y++) {
            for (int x = 0; x < layer.getWidth(); x++) {
                MapLibgdxCell cell = new MapLibgdxCell(layer,x,y);
                layer.setCell(x,y,cell);

                currentCase = gameMap.getCase(y*layer.getWidth()+x);

                CaseView actor = new CaseView(cell,currentCase);

                actor.setBounds(x * layer.getTileWidth(), y * layer.getTileHeight(), layer.getTileWidth(),
                        layer.getTileHeight());

                addActor(actor);

                actor.addListener(listenerCase);
            }
        }
    }
}
