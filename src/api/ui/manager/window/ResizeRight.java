package api.ui.manager.window;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseEvent;

/**
 * Redimensionnement depuis partie droite
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class ResizeRight extends Resize {

	public static final float NEUTRE = 0;

	private float lastX1 = 0, lastX2 = 0;

	public ResizeRight(Window window, ResizeComponent resizeComponent) {
		super(window, resizeComponent);
	}

	@Override
	public synchronized void mouseDragged(MouseEvent mouseEvent) {
		Point mouseCords = mouseEvent.getPoint();
		Rectangle window = this.window.getBounds();
		if (this.resizable) {
			//System.out.println(mouseCords);

			if (mouseCords.x > 0) {
				lastX1++;
				lastX2--;
				System.out.println("droite");
				this.window.setSize(window.width + mouseCords.x, window.height);
				//impossible que la taille diminue
				if (window.width > this.window.getWidth()) {
					this.window.setSize(window.width, window.height);
				}
			} else if (mouseCords.x < 0) {
				lastX2++;
				lastX1--;
				System.out.println(lastX1 + " " + lastX2);
				/*if(lastX > mouseCords.x && mouseCords.x < -10f ) {
					System.out.println("gauche");
					//this.window.setSize(window.width + mouseCords.x, window.height);
				}
				lastX--;*/
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent) {
	}
}
