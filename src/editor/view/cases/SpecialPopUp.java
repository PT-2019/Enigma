package editor.view.cases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import editor.view.cases.listeners.SpecialPopListener;
import editor.view.cases.panel.NavigationPanel;
import game.entity.map.MapTestScreenCell;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.GridLayout;

/**
 * PopUp qui permet de se déplacer uniquement entre les entités
 */
public class SpecialPopUp extends AbstractPopUp {
	/**
	 * Panneau de navigation pour passer d'une entité à une autre
	 */
	private NavigationPanel navigation;

	/**
	 * PopUp qui contient la référence à l'objet choisi
	 */
	private CasePopUp popUp;

	private JButton button;

	public SpecialPopUp(JComponent component, TiledMap tiledMap, CasePopUp popUp) {
		super((JFrame) component.getRootPane().getParent(), "", false);

		this.setSize(400, 200);
		this.setLocation(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.tileMap = tiledMap;
		this.setLayout(new GridLayout(2, 1));
		this.popUp = popUp;
	}

	public void display() {
		navigation = new NavigationPanel(this);
		button = new JButton("Utiliser cette entité");
		button.addActionListener(new SpecialPopListener(this, popUp, cell.getEntity()));
		TiledMapTileLayer l = cell.getLayer();
		MapLayers layers = tileMap.getLayers();
		int index = layers.getIndex(l.getName());

		if (cell.getEntity() == null) {
			navigation.setText("Aucune entité");
		} else {
			String className = cell.getEntity().getClass().getName();
			navigation.setClassText(className);
		}
		navigation.displayNavBouton(index, layers);
		this.add(navigation);
		this.add(button);
		this.revalidate();
	}

	public void setCell(MapTestScreenCell cell) {
		this.cell = cell;
	}

	@Override
	public void initComponent() {

	}

	@Override
	public void clean() {
		this.remove(navigation);
		this.remove(button);
	}
}
