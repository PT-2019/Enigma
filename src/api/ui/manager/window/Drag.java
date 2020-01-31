package api.ui.manager.window;

import api.ui.CustomWindow;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Déplace la fenêtre
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class Drag extends MouseAdapter {

	private final CustomWindow window;
	private Point pressedCords;

	/**
	 * Déplace la fenêtre
	 *
	 * @param window la fenêtre a déplacer
	 */
	public Drag(CustomWindow window) {
		this.window = window;
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		this.pressedCords = e.getPoint();
	}

	public void mouseDragged(MouseEvent e) {
		Point currentCords = e.getLocationOnScreen();
		this.window.setLocation(currentCords.x - this.pressedCords.x, currentCords.y - this.pressedCords.y);
	}
}
