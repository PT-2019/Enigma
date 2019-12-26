package editor.utils.save.view.cases;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import game.entity.MapLibgdxCell;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CasePopListener implements ActionListener {

    private TiledMapTileLayer.Cell after;

    private CasePopUp popUp;

    public CasePopListener(TiledMapTileLayer.Cell after,CasePopUp popup){
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
