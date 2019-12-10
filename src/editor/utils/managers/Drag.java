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
		if(this.window.getLocationOnScreen().y <= 0) {
			this.window.setSize(Window.FULL_SCREEN_SIZE);
			this.window.setPreviousY(0);
			this.window.wontBeResizedFullScreen();
		}
	}

	public void mousePressed(MouseEvent e) {
		this.pressedCords = e.getPoint();
		if(this.window.isFullScreen()) this.window.setPreviousValues();
	}

	public void mouseDragged(MouseEvent e) {
		Point currentCords = e.getLocationOnScreen();
		System.out.println(currentCords+" "+pressedCords);
		this.window.setLocation(currentCords.x - this.pressedCords.x, currentCords.y - this.pressedCords.y);
		/*if(!this.pressedCords.equals(currentCords))
			this.window.setLocation(this.window.getX() + currentCords.x, this.window.getY() + currentCords.y);*/
		this.window.wontBeResizedFullScreen();
		if(this.window.getLocationOnScreen().y <= 0) this.window.willBeResizedFullScreen();
	}
}
