package editor.view.cases;

import com.badlogic.gdx.scenes.scene2d.Actor;
import game.entity.map.MapTestScreenCell;

/**
 * Case qui permet de réalisé des actions sur la cell
 * @see MapTestScreenCell
 * @see editor.view.listeners.CaseListener
 */
public class CaseView extends Actor {

    private MapTestScreenCell cell;

    public CaseView(MapTestScreenCell cell){
        this.cell = cell;
    }

    public MapTestScreenCell getCell(){
        return this.cell;
    }
}
