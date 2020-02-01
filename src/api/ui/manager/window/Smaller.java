package api.ui.manager.window;

import api.ui.CustomWindow;
import api.ui.base.WindowSize;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Réduit la taille de la fenêtre
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class Smaller implements ActionListener {

	private CustomWindow window;

	public Smaller(CustomWindow window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (this.window.isFullScreen()) this.window.setSize(WindowSize.HALF_SCREEN_SIZE);
		else this.window.setSize(WindowSize.FULL_SCREEN_SIZE);
	}
}
