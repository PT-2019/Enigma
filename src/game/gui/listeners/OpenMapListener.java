package game.gui.listeners;

import api.libgdx.utils.CheckEventType;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import common.hud.EnigmaWindow;
import editor.EditorLauncher;
import editor.bar.listeners.OpenListener;

import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;

/**
 * Ouvre une map. Composant libgdx.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 12/02/2020
 * @since 6.0 12/02/2020
 */
public final class OpenMapListener implements EventListener {

	private final OpenListener open;

	public OpenMapListener() {
		this.open = new OpenListener((EnigmaWindow) EditorLauncher.getInstance().getWindow(), null);
	}

	@Override
	public boolean handle(Event event) {
		if (CheckEventType.isMouseClicked(event)) {
			SwingUtilities.invokeLater(
					() -> this.open.actionPerformed(new ActionEvent(this, 0, "")
					)
			);
			return true;
		}
		return false;
	}
}
