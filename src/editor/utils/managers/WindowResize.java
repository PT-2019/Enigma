package editor.utils.managers;

import editor.utils.Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class WindowResize implements MouseMotionListener {

	private final Window window;
	private boolean resize;

	public WindowResize(Window window){
		this.window = window;
		this.resize = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		/*if(resize){

		}else if(!this.window.isFullScreen()) {
			Point mouseCords = e.getPoint();
			if(mouseCords.x > this.window.getSize().width - 5 && mouseCords.x < this.window.getSize().width + 5) this.window.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
		}*/
	}
}
