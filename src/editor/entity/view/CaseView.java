package editor.entity.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import game.entity.map.MapTestScreenCell;

public class CaseView extends Actor {

    private MapTestScreenCell cell;

    public CaseView(MapTestScreenCell cell){
        this.cell = cell;
    }

    public MapTestScreenCell getCell(){
        return this.cell;
    }
}
