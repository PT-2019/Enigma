package editor.menus.others;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import data.Layer;
import editor.menus.AbstractPopUpView;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.popup.cases.CasePopUp;
import editor.popup.listeners.PopItemListener;
import general.entities.GameObject;
import general.hud.EnigmaPanel;
import general.hud.ui.EnigmaJCheckBoxUI;
import general.map.MapTestScreenCell;

import javax.swing.JCheckBox;

/**
 * Permet de changer la propriété d'accès (collision)
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class SetAccess implements AvailableOptionRunnable {

	/**
	 * Définit si la case est accessible ou non
	 */
	private static final String VALUE = "Case bloquante";

	private final CasePopUp parent;

	public SetAccess(CasePopUp parent) {
		this.parent = parent;
	}

	@Override
	public AvailablePopUpOption getOption() {
		return AvailablePopUpOption.SET_ACCESS;
	}

	@Override
	public void run() {
		TiledMap tileMap = parent.getTileMap();
		MapTestScreenCell cell = parent.getCell();
		EnigmaPanel panel = parent.getPanel();
		JCheckBox walkable = new JCheckBox(VALUE);
		walkable.setUI(EnigmaJCheckBoxUI.createUI(walkable));

		TiledMapTileLayer collision = (TiledMapTileLayer) tileMap.getLayers().get(Layer.COLLISION.name());
		TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get(cell.getLayer().getName());
		//Récupère la case du niveau collision
		TiledMapTileLayer.Cell cellTmp = collision.getCell(
				cell.getIndex() % layer.getWidth(),
				cell.getIndex() / layer.getWidth());
		//si ya une case, alors on coche "bloquante"
		if (cellTmp.getTile() != null) {
			walkable.setSelected(true);
		}
		walkable.addItemListener(new PopItemListener(tileMap, cell));
		panel.add(walkable);
	}

	@Override
	public void run(AbstractPopUpView view, EnigmaPanel panel, GameObject object) {
		TiledMap tileMap = parent.getTileMap();
		MapTestScreenCell cell = parent.getCell();
		JCheckBox walkable = new JCheckBox(VALUE);
		walkable.setUI(EnigmaJCheckBoxUI.createUI(walkable));

		TiledMapTileLayer collision = (TiledMapTileLayer) tileMap.getLayers().get(Layer.COLLISION.name());
		TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get(cell.getLayer().getName());
		//Récupère la case du niveau collision
		TiledMapTileLayer.Cell cellTmp = collision.getCell(
				cell.getIndex() % layer.getWidth(),
				cell.getIndex() / layer.getWidth());
		//si ya une case, alors on coche "bloquante"
		if (cellTmp.getTile() != null) {
			walkable.setSelected(true);
		}
		walkable.addItemListener(new PopItemListener(tileMap, cell));
		panel.add(walkable);
	}
}
