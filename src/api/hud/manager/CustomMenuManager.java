package api.hud.manager;

import api.hud.CustomMenu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Observateur et manager d'un menu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class CustomMenuManager extends MouseAdapter {

	private CustomMenu menu;

	public CustomMenuManager(CustomMenu menu) {
		this.menu = menu;
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
		if (this.menu.getComponentUI() != null) {
			this.menu.getComponentUI().setIsHovered(true);
			this.menu.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		if (this.menu.getComponentUI() != null) {
			this.menu.getComponentUI().setIsHovered(false);
			this.menu.repaint();
		}
	}
}
