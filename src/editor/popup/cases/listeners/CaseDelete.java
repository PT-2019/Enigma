package editor.popup.cases.listeners;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import editor.popup.cases.CasePopUp;
import general.hud.EnigmaPanel;
import general.map.MapTestScreenCell;

import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlleur qui va supprimer une entité
 */
public class CaseDelete implements ActionListener {
	/**
	 * Parent
	 */
	private final CasePopUp parent;
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

	public CaseDelete(MapTestScreenCell current, TiledMapTileLayer layer, JLabel label, CasePopUp parent) {
		this.current = current;
		this.layer = layer;
		this.label = label;
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.current.getEntity() != null) {
			//System.out.println("remove"+this.current.getEntity());
			this.current.removeEntity();
			this.layer.setCell(
					this.current.getIndex() % this.layer.getWidth(),
					this.current.getIndex() / this.layer.getWidth(), this.current
			);
			if (this.current.getEntity() == null) label.setText("Aucune entité");
			else this.label.setText(this.current.getEntity().getReadableName());
			//supprime les infos de l'ancienne
			EnigmaPanel panel = this.parent.getPanel();
			panel.removeAll();
			panel.revalidate();
			//rebuild le panneau
			this.parent.fillPanel();
		}
	}
}
