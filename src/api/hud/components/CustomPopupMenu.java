package api.hud.components;

import api.hud.ui.CustomPopupMenuUI;

import javax.swing.JPopupMenu;
import java.awt.Graphics;

/**
 * Un popup de menus
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 29/12/2019
 * @since 4.0 29/12/2019
 */
public class CustomPopupMenu extends JPopupMenu implements CustomComponent<CustomPopupMenuUI> {

	private CustomPopupMenuUI ui;

	/**
	 * Un popup de menus
	 */
	public CustomPopupMenu() {
		super();
		this.setOpaque(true);
		this.setComponentUI(new CustomPopupMenuUI());
	}

	@Override
	public CustomPopupMenuUI getComponentUI() {
		return this.ui;
	}

	@Override
	public void setComponentUI(CustomPopupMenuUI ui) {
		this.ui = ui.duplicate();
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		this.ui.paint(g, this);
	}
}
