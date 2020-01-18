package editor.utils.save.view.listeners;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import game.entity.MapLibgdxCell;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlleur qui va supprimer une entité
 */
public class CaseDelete implements ActionListener {
    /**
     * Cellule courrante
     */
    private MapLibgdxCell current;

    /**
     * La couche de cette cellule
     */
    private TiledMapTileLayer layer;

    /**
     * Le widget qui affiche le nom de l'entité qu'on va supprimer
     */
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
