package editor.map.view;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import editor.map.Case;

public class CaseView extends Actor {
    private Case c;

    private TiledMapTileLayer.Cell cell;

    public CaseView(TiledMapTileLayer.Cell cell,Case c){
        this.c = c;
        this.cell = cell;
    }

    public Case getCase() {
        return c;
    }
}
