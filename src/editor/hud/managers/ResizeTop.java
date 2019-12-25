package editor.hud.managers;

import editor.hud.Resize;
import editor.hud.ResizeComponent;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseEvent;

/**
 * TODO: comment ResizeTop and write Readme.md in editor.hud.managers
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class ResizeTop extends Resize {

	public ResizeTop(Window window, ResizeComponent resizeComponent) {
		super(window, resizeComponent);
	}

	@Override
	public void mouseDragged(MouseEvent mouseEvent) {
		Point mouseCords = mouseEvent.getPoint();
		Rectangle window = this.window.getBounds();
		if (this.resizable) {
			if (this.window.getMinimumSize().height < window.height) {
				this.window.setSize(window.width, window.height - mouseCords.y);
				this.window.setLocation(window.x, window.y + mouseCords.y);
			} else if (mouseCords.y < this.resizeComponent.getY()) {
				this.window.setSize(window.width, window.height - mouseCords.y);
				this.window.setLocation(window.x, window.y + mouseCords.y);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent) {
	}
}
