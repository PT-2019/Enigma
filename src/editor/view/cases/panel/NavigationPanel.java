package editor.view.cases.panel;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import editor.view.cases.AbstractPopUp;
import editor.view.cases.listeners.CasePopListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panneau qui permet de naviguer à travers les différentes entités et cellule d'une case
 */
public class NavigationPanel extends JPanel {
	private JButton next;

	private JButton preview;

	private JLabel info;

	private AbstractPopUp popUp;

	public NavigationPanel(AbstractPopUp popUp) {
		next = new JButton();
		preview = new JButton();
		this.popUp = popUp;
		info = new JLabel();
	}

	/**
	 * Méthode qui dispose les différents bouttons de naviguation
	 */
	public void displayNavBouton(int index, MapLayers layers) {
		int x, y;
		TiledMapTileLayer layer;

		if (index < 3) {
			next.setText(layers.get(index + 1).getName());
			this.add(next);

			layer = (TiledMapTileLayer) layers.get(index + 1);

			y = popUp.getCell().getIndex() / layer.getWidth();
			x = popUp.getCell().getIndex() % layer.getWidth();
			next.addActionListener(new CasePopListener(layer.getCell(x, y), popUp));
		}
		this.add(info);
		if (index > 0) {
			preview.setText(layers.get(index - 1).getName());
			this.add(preview);

			layer = (TiledMapTileLayer) layers.get(index - 1);

			y = popUp.getCell().getIndex() / layer.getWidth();
			x = popUp.getCell().getIndex() % layer.getWidth();
			preview.addActionListener(new CasePopListener(layer.getCell(x, y), popUp));
		}
	}

	public void clean() {
		this.remove(preview);
		this.remove(next);
		this.remove(info);
		info = new JLabel();
		next = new JButton();
		preview = new JButton();
	}

	public void setText(String Text) {
		info.setText(Text);
	}

	public JLabel getInfo() {
		return info;
	}

	public void setClassText(String name) {
		this.setText(name);
	}
}
