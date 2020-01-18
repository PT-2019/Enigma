package editor.utils.save.view.listeners;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import editor.utils.save.view.cases.AbstractPopUp;
import editor.utils.save.view.cases.CasePopUp;
import game.entity.MapLibgdxCell;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Permet de changer de cellule courante dans un AbstractPopUp
 * @see AbstractPopUp
 */
public class CasePopListener implements ActionListener {

    private TiledMapTileLayer.Cell after;

    private AbstractPopUp popUp;

    public CasePopListener(TiledMapTileLayer.Cell after, AbstractPopUp popup){
        this.after = after;
        popUp = popup;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        popUp.setCell((MapLibgdxCell)after);
        popUp.clean();
        popUp.display();
    }
}
