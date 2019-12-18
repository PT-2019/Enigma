package editor.map.view;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CasePopListener implements ActionListener {
    TiledMapTileLayer.Cell after;

    CasePopUp popUp;

    public CasePopListener(TiledMapTileLayer.Cell after,CasePopUp popup){
        this.after = after;
        popUp = popup;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        popUp.setCell(after);
        popUp.display();
    }
}
