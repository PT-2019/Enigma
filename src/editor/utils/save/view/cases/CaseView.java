package editor.utils.save.view.cases;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import editor.entity.map.Case;
import game.entity.MapLibgdx;
import game.entity.MapLibgdxCell;

/**
 * Case qui permet de réalisé des actions sur la cell
 * @see MapLibgdxCell
 * @see editor.utils.save.view.listeners.CaseListener
 */
public class CaseView extends Actor {

    private MapLibgdxCell cell;

    public CaseView(MapLibgdxCell cell){
        this.cell = cell;
    }

    public MapLibgdxCell getCell(){
        return this.cell;
    }
}
