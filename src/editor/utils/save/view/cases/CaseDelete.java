package editor.utils.save.view.cases;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import game.entity.MapLibgdxCell;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaseDelete implements ActionListener {
    private MapLibgdxCell current;

    private TiledMapTileLayer layer;

    private JLabel label;

    public CaseDelete(MapLibgdxCell current, TiledMapTileLayer layer, JLabel label){
        this.current = current;
        this.layer = layer;
        this.label = label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (current.getEntity() != null) {
            current.setEntity(null);
            current.setTile(null);
            layer.setCell(current.getIndex() % layer.getWidth(), current.getIndex() / layer.getWidth(), current);
            label.setText("Aucune entité");
        }
    }
}
