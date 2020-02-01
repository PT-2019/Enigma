package api.ui.manager.window;

import java.awt.Window;
import java.awt.event.MouseMotionListener;

/**
 * Redimensionnement de la fenêtre
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public abstract class Resize implements MouseMotionListener {

	protected ResizeComponent resizeComponent;
	protected Window window;
	protected boolean resizable;

	public Resize(Window window, ResizeComponent resizeComponent) {
		super();
		this.window = window;
		this.resizeComponent = resizeComponent;
		this.resizeComponent.addMouseMotionListener(this);
		this.resizable = true;
	}

	public void disable() {
		this.resizable = false;
	}

	public void enable() {
		this.resizable = true;
	}
}
