package api.ui.manager.window;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseEvent;

/**
 * Redimensionnement depuis partie gauche
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class ResizeLeft extends Resize {

	public ResizeLeft(Window window, ResizeComponent resizeComponent) {
		super(window, resizeComponent);
	}

	@Override
	public void mouseDragged(MouseEvent mouseEvent) {
		Point mouseCords = mouseEvent.getPoint();
		Rectangle window = this.window.getBounds();
		if (this.resizable) {
			if (this.window.getMinimumSize().width < window.width) {
				this.window.setSize(window.width - mouseCords.x, window.height);
				this.window.setLocation(window.x + mouseCords.x, window.y);
			} else if (mouseCords.x < this.resizeComponent.getX()) {
				this.window.setSize(window.width - mouseCords.x, window.height);
				this.window.setLocation(window.x + mouseCords.x, window.y);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent) {
	}
}
