package editor.view.cases;

import api.enums.Layer;
import api.utils.Utility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import editor.hud.EnigmaButton;
import editor.hud.EnigmaPanel;
import editor.view.cases.listeners.SpecialPopListener;
import editor.view.cases.panel.NavigationPanel;
import game.entity.map.MapTestScreenCell;
import starter.EditorLauncher;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Rectangle;

/**
 * PopUp qui permet de se déplacer uniquement entre les entités
 */
public class SpecialPopUp extends AbstractPopUp {

	/**
	 * Largeur, hauteur de la dialog.
	 * Nombre de lignes et colonnes du contenu.
	 */
	private static final int WIDTH = 400, HEIGHT = 130, COL = 1, ROW = 2;

	/**
	 * Panneau de navigation pour passer d'une entité à une autre
	 */
	private NavigationPanel navigation;

	/**
	 * PopUp qui contient la référence à l'objet choisi
	 */
	private CasePopUp popUp;

	private EnigmaPanel panel;

	private EnigmaButton button;

	public SpecialPopUp(JComponent component, TiledMap tiledMap, CasePopUp popUp) {
		super((JFrame) component.getRootPane().getParent(), "", false);
		this.tileMap = tiledMap;
		this.popUp = popUp;

		Rectangle bounds = Utility.getMonitorOf(EditorLauncher.getInstance().getWindow()).getBounds();
		this.setLocation(bounds.width / 2 - WIDTH/2, bounds.height / 2 - HEIGHT/2);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(ROW, COL));
	}

	public void display() {
		this.initComponent();
		TiledMapTileLayer currentLayer = cell.getLayer();
		this.setTitle(Layer.valueOf(currentLayer.getName()).name);

		if (this.cell.getEntity() == null) {
			this.navigation.setText("Aucune entité");
		} else {
			this.navigation.setText(this.cell.getEntity().getReadableName());
			//fillPanel();
		}
		int index = this.tileMap.getLayers().getIndex(currentLayer.getName());
		this.navigation.displayNavBouton(index, this.tileMap.getLayers());
		this.add(navigation);
		this.add(panel);
		this.revalidate();
	}

	public void setCell(MapTestScreenCell cell) {
		this.cell = cell;
	}

	@Override
	public void initComponent() {
		this.navigation = new NavigationPanel(this);
		this.navigation.setLayout(new GridLayout(1, 3));
		button = new EnigmaButton("Utiliser cette entité");
		button.addActionListener(new SpecialPopListener(this, popUp, cell.getEntity()));
		panel = new EnigmaPanel();
		panel.add(button);
	}

	@Override
	public void clean() {
		this.remove(navigation);
		panel.removeAll();
		this.remove(panel);
	}
}
