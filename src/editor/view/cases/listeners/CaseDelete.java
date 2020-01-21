package editor.view.cases.listeners;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import game.entity.map.MapTestScreenCell;

import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlleur qui va supprimer une entité
 */
public class CaseDelete implements ActionListener {
	/**
	 * Cellule courrante
	 */
	private MapTestScreenCell current;

	/**
	 * La couche de cette cellule
	 */
	private TiledMapTileLayer layer;

	/**
	 * Le widget qui affiche le nom de l'entité qu'on va supprimer
	 */
	private JLabel label;

	public CaseDelete(MapTestScreenCell current, TiledMapTileLayer layer, JLabel label) {
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
