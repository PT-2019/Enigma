package editor.hud.managers;

import editor.hud.EnigmaOptionPane;
import editor.hud.Window;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO: comment Exit and write Readme.md in editor.hud.managers
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class Exit implements ActionListener {

	private Window window;

	public Exit(Window window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (this.window.willAskBeforeClosing()) {
			if (EnigmaOptionPane.showConfirmDialog(this.window, new Dimension(300, 200), "Voulez vous vraiment quittez?"))
				this.window.dispose();
		} else this.window.dispose();
	}
}
