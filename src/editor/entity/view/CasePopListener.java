package editor.entity.view;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import game.entity.map.MapTestScreenCell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CasePopListener implements ActionListener {
    private TiledMapTileLayer.Cell after;

    private CasePopUp popUp;

    public CasePopListener(TiledMapTileLayer.Cell after, CasePopUp popup){
        this.after = after;
        popUp = popup;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        popUp.setCell((MapTestScreenCell) after);
        popUp.clean();
        popUp.display();
    }
}
