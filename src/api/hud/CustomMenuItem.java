package api.hud;

import api.hud.base.CustomComponent;
import api.hud.ui.CustomMenuItemUI;

import javax.swing.JMenuItem;
import java.awt.Graphics;

/**
 * Un élément d'un menu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 29/12/2019
 * @since 4.0 29/12/2019
 */
public class CustomMenuItem extends JMenuItem implements CustomComponent<CustomMenuItemUI> {

	private CustomMenuItemUI ui;

	/**
	 * Un élément d'un menu
	 *
	 * @param title contenu
	 */
	public CustomMenuItem(String title) {
		super(title);
		this.setOpaque(true);
		this.setComponentUI(new CustomMenuItemUI());
	}

	@Override
	public CustomMenuItemUI getComponentUI() {
		return this.ui;
	}

	@Override
	public void setComponentUI(CustomMenuItemUI ui) {
		this.ui = ui.duplicate();
		this.setCursor(this.ui.getCursor());
		this.setFont(this.ui.getFont());
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		super.setUI(this.ui);
	}

	@Override
	public void paintComponent(Graphics g) {
		this.ui.paint(g, this);
	}
}