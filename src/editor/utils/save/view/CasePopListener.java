package editor.utils.save.view;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import game.entity.MapLibgdxCell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
