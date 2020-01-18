package editor.utils.save.view.cases;

import com.badlogic.gdx.maps.tiled.TiledMap;
import game.entity.MapLibgdxCell;

import javax.swing.*;

/**
 * PopUp abstrait qui permet de formalisé tout les popup utilisés
 * @see MapLibgdxCell
 * @see TiledMap
 */
public abstract class  AbstractPopUp extends JDialog {

    protected MapLibgdxCell cell;

    protected TiledMap tileMap;

    public AbstractPopUp(JFrame frame,String title,boolean modal){
        super(frame,title,modal);
    }

    public MapLibgdxCell getCell() {
        return cell;
    }

    public void setCell(MapLibgdxCell cell) {
        this.cell = cell;
    }

    public abstract void clean();

    public abstract void display();
}
