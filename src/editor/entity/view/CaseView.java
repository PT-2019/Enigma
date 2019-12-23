package editor.entity.view;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import editor.entity.map.Case;

/**
 * TODO: comment CaseView and write Readme.md in editor.entity.view
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 4.0
 */
public class CaseView extends Actor {
    private Case c;

    private TiledMapTileLayer.Cell cell;

    public CaseView(TiledMapTileLayer.Cell cell, Case c){
        this.c = c;
        this.cell = cell;
    }

    public Case getCase() {
        return c;
    }

    public TiledMapTileLayer.Cell getCell(){
        return this.cell;
    }
}
