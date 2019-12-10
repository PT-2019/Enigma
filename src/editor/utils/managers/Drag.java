package editor.utils.managers;

import editor.utils.Window;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Drag extends MouseAdapter {

	private final Window window;
	private Point pressedCords;


	public Drag(Window window) {
		this.window = window;
	}

	public void mouseReleased(MouseEvent e) {
		if(this.window.getLocationOnScreen().y <= 0) this.window.setSize(Window.FULL_SCREEN_SIZE);
	}

	public void mousePressed(MouseEvent e) {
		this.pressedCords = e.getPoint();
		/*if(this.window.getLocationOnScreen().y == 0 && this.window.getDimensionOf(Window.LAST_SCREEN_SIZE).equals(this.window.getDimensionOf(Window.FULL_SCREEN_SIZE)))
			this.window.setSize(Window.HALF_SCREEN_SIZE);
		else if(this.window.getLocationOnScreen().y == 0) this.window.setSize(Window.LAST_SCREEN_SIZE);*/
	}

	public void mouseDragged(MouseEvent e) {
		Point currentCords = e.getLocationOnScreen();
		this.window.setLocation(currentCords.x - this.pressedCords.x, currentCords.y - this.pressedCords.y);
	}
}
