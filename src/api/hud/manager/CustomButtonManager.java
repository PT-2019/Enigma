package api.hud.manager;

import api.hud.CustomButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Observateur et manager des états des boutons
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.2
 * @since 3.0
 */
public final class CustomButtonManager extends MouseAdapter {

	private CustomButton button;

	public CustomButtonManager(CustomButton button) {
		this.button = button;
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
		if (this.button.getComponentUI() != null) this.button.getComponentUI().setIsHovered(true);
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		if (this.button.getComponentUI() != null) this.button.getComponentUI().setIsHovered(false);
	}
}
