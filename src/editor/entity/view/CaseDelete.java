package editor.entity.view;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import game.entity.map.MapTestScreenCell;

import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaseDelete implements ActionListener {
	private MapTestScreenCell current;

	private TiledMapTileLayer layer;

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
