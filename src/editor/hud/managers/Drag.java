package editor.hud.managers;

import editor.hud.Window;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * TODO: comment Drag and write Readme.md in editor.hud.managers
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class Drag extends MouseAdapter {

	private final Window window;
	private Point pressedCords;


	public Drag(Window window) {
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
