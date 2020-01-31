package api.ui;

import api.ui.base.CustomComponent;
import api.ui.manager.CustomMenuManager;
import api.ui.skin.CustomMenuUI;

import javax.swing.JMenu;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Un menu customizable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1
 * @since 4.0 29/12/2019
 */
public class CustomMenu extends JMenu implements CustomComponent<CustomMenuUI> {

	private CustomMenuUI ui;

	private ArrayList<Component> items;

	/**
	 * Un menu
	 *
	 * @param title son nom
	 */
	public CustomMenu(String title) {
		super(title);
		this.items = new ArrayList<>();
		this.addMouseListener(new CustomMenuManager(this));
		this.setOpaque(true);
		this.setPopupMenuVisible(false);
		this.getPopupMenu().setBorderPainted(false);
		this.setComponentUI(new CustomMenuUI());
	}

	@Override
	public CustomMenuUI getComponentUI() {
		return this.ui;
	}

	@Override
	public void setComponentUI(CustomMenuUI ui) {
		this.ui = ui.duplicate();
		this.setCursor(this.ui.getCursor());
		this.setFont(this.ui.getFont());
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		super.setUI(this.ui);
	}

	@Override
	public Component add(Component c) {
		items.add(c);
		return super.add(c);
	}

	@Override
	public Component add(Component c, int index) {
		items.add(c);
		return super.add(c, index);
	}

	@Override
	public void remove(Component c) {
		items.remove(c);
		super.remove(c);
	}

	@Override
	public void paintComponent(Graphics g) {
		this.ui.paint(g, this);
	}

	/**
	 * Retourne les items d'un menu
	 *
	 * @return les items d'un menu
	 */
	public Component[] getItems() {
		return (Component[]) items.toArray();
	}
}
