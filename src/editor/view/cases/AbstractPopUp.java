package editor.view.cases;

import api.hud.ResetComponent;
import api.utils.Utility;
import com.badlogic.gdx.maps.tiled.TiledMap;
import game.entity.map.MapTestScreenCell;
import starter.EditorLauncher;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * PopUp abstrait qui permet de formalisé tout les popup utilisés
 *
 * @see MapTestScreenCell
 * @see TiledMap
 */
public abstract class AbstractPopUp extends JDialog implements ResetComponent {

	protected MapTestScreenCell cell;

	protected TiledMap tileMap;

	public AbstractPopUp(JFrame frame, String title, boolean modal) {
		super(frame, title, modal, Utility.getMonitorOf(EditorLauncher.getInstance().getWindow()));
		this.setIconImage(EditorLauncher.getInstance().getWindow().getIconImage());
	}

	public MapTestScreenCell getCell() {
		return cell;
	}

	public void setCell(MapTestScreenCell cell) {
		this.cell = cell;
	}

	@Override
	public abstract void clean();

	public abstract void display();
}
