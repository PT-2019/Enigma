package editor.view.cases.listeners;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import editor.view.cases.AbstractPopUp;
import game.entity.map.MapTestScreenCell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Permet de changer de cellule courante dans un AbstractPopUp
 *
 * @see AbstractPopUp
 */
public class CasePopListener implements ActionListener {

	private TiledMapTileLayer.Cell after;

	private AbstractPopUp popUp;

	public CasePopListener(TiledMapTileLayer.Cell after, AbstractPopUp popup) {
		this.after = after;
		popUp = popup;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		popUp.setCell((MapTestScreenCell) after);
		popUp.clean();
		popUp.display();
	}
}
