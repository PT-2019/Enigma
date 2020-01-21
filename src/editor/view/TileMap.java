package editor.view;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import editor.utils.map.Case;
import editor.utils.map.Map;
import editor.view.cases.CasePopUp;
import editor.view.cases.CaseView;
import editor.view.listeners.CaseListener;
import game.entity.map.MapTestScreenCell;

import javax.swing.*;

@Deprecated
public class TileMap extends Stage{

    private TiledMap map;

    private Map gameMap;

    public TileMap(TiledMap m, JComponent component, Map map){
        this.map = m;
        gameMap = map;
        createCell(component);
    }

    private void createCell(JComponent component){
        CasePopUp popUp = new CasePopUp(component,this.map);
        CaseListener listenerCase = new CaseListener(popUp);
        MapLayers layers = map.getLayers();
        Case currentCase;
        boolean isfloor = false;

        for (int i =0; i < 4; i++){
            TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(i);

            //on veut mettre le listener que sur 1 seule couche de cellule
            if (layer.getName().equals("FLOOR1")){
                isfloor = true;
            }
            //TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(0);

            for (int y = 0; y < layer.getHeight(); y++) {
                for (int x = 0; x < layer.getWidth(); x++) {

                    MapTestScreenCell cell = new MapTestScreenCell(layer, y * layer.getWidth() + x);

                    currentCase = gameMap.getCase(y * layer.getWidth() + x);

                    CaseView actor = new CaseView(cell);

                    actor.setBounds(x * layer.getTileWidth(), y * layer.getTileHeight(),
                            layer.getTileWidth(), layer.getTileHeight());

                    addActor(actor);

                    if (isfloor){
                        actor.addListener(listenerCase);
                    }
                }
            }
        }
    }
}