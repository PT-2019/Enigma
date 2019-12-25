package editor.hud.managers;

import editor.hud.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO: comment Smaller and write Readme.md in editor.hud.managers
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class Smaller implements ActionListener {

	private Window window;

	public Smaller(Window window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (this.window.isFullScreen()) this.window.setSize(Window.HALF_SCREEN_SIZE);
		else this.window.setSize(Window.FULL_SCREEN_SIZE);
	}
}
