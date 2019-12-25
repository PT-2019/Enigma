package editor.entity.view;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO: comment CasePopListener and write Readme.md in editor.entity.view
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 4.0
 */
public class CasePopListener implements ActionListener {
	private TiledMapTileLayer.Cell after;

	private CasePopUp popUp;

	public CasePopListener(TiledMapTileLayer.Cell after, CasePopUp popup) {
		this.after = after;
		popUp = popup;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(after);
		popUp.setCell(after);
		popUp.display();
	}
}
