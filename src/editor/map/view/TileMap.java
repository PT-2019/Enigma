package editor.map.view;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import editor.entity.dispose.EntityPopMenu;
import editor.map.Map;

import javax.swing.*;

public class TileMap extends Stage {

    private TiledMap map;

    private Map gameMap;

    public TileMap(TiledMap m, JComponent component, Map map, RoomView r, Camera c){
        this.map = m;
        gameMap = map;

        EntityPopMenu menu = new EntityPopMenu(r,c);

        MapLayers layers = m.getLayers();

        for (int i = 0; i < 4; i++) {
            TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(i);
            for (int x = 0; x < layer.getWidth(); x++) {
                for (int y = 0; y < layer.getHeight(); y++) {
                    TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                    CaseView actor = new CaseView( cell);

                    CaseListener eventListener = new CaseListener(gameMap.getCase(y*layer.getWidth()+x),component,menu);

                    actor.setBounds(x * layer.getTileWidth(), y * layer.getTileHeight(), layer.getTileWidth(),
                            layer.getTileHeight());
                    addActor(actor);

                    actor.addListener(eventListener);
                }
            }
        }
    }
}
