package api.hud.manager.window;

import api.hud.components.CustomWindow;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO: comment Minimize and write Readme.md in editor.hud.managers
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class Minimize implements ActionListener {

	private CustomWindow window;

	public Minimize(CustomWindow window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.window.setExtendedState(JFrame.ICONIFIED);
	}
}
