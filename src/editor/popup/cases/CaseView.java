package editor.popup.cases;

import com.badlogic.gdx.scenes.scene2d.Actor;
import general.map.MapTestScreenCell;

/**
 * Case qui permet de réalisé des actions sur la cell
 *
 * @see MapTestScreenCell
 * @see editor.popup.listeners.CaseListener
 */
public class CaseView extends Actor {

	private MapTestScreenCell cell;

	public CaseView(MapTestScreenCell cell) {
		this.cell = cell;
	}

	public MapTestScreenCell getCell() {
		return this.cell;
	}
}
