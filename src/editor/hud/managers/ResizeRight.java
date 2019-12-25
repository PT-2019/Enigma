package editor.hud.managers;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseEvent;

public class ResizeRight extends Resize {

	public ResizeRight(Window window, ResizeComponent resizeComponent) {
		super(window, resizeComponent);
	}

	@Override
	public void mouseDragged(MouseEvent mouseEvent) {
		Point mouseCords = mouseEvent.getPoint();
		Rectangle window = this.window.getBounds();
		if (this.resizable) {
			if (this.window.getMaximumSize().width > window.width) {
				this.window.setSize(window.width + mouseCords.x, window.height);
			} else if (mouseCords.x > this.resizeComponent.getWidth()) {
				this.window.setSize(window.width + mouseCords.x, window.height);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent) {
	}
}
