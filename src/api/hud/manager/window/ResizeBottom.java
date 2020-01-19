package api.hud.manager.window;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseEvent;

/**
 * TODO: comment ResizeBottom and write Readme.md in editor.hud.managers
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class ResizeBottom extends Resize {

	public ResizeBottom(Window window, ResizeComponent resizeComponent) {
		super(window, resizeComponent);
	}

	@Override
	public void mouseDragged(MouseEvent mouseEvent) {
		Point mouseCords = mouseEvent.getPoint();
		Rectangle window = this.window.getBounds();
		if (this.resizable) {
			if (this.window.getMaximumSize().height > window.height) {
				this.window.setSize(window.width, window.height + mouseCords.y);
			} else if (mouseCords.x < this.resizeComponent.getHeight()) {
				this.window.setSize(window.width, window.height + mouseCords.y);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent) {
	}
}
