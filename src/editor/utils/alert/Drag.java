package editor.utils.alert;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Drag extends MouseAdapter {

	private final Alert window;
	private Point pressedCords;


	public Drag(Alert window) {
		this.window = window;
	}

	public void mouseReleased(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		this.pressedCords = e.getPoint();
	}

	public void mouseDragged(MouseEvent e) {
		Point currentCords = e.getLocationOnScreen();
		this.window.setLocation(currentCords.x - this.pressedCords.x, currentCords.y - this.pressedCords.y);
	}
}
