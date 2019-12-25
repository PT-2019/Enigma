package editor.hud;

import editor.hud.managers.EnigmaMenuManager;
import editor.hud.ui.EnigmaMenuUI;

import javax.swing.JMenu;
import java.awt.Graphics;

/**
 * TODO: comment EnigmaMenu and write Readme.md in editor.hud
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class EnigmaMenu extends JMenu {

	private EnigmaMenuUI ui;

	public EnigmaMenu(String title) {
		super(title);
		this.addMouseListener(new EnigmaMenuManager(this));
		this.setOpaque(true);
		this.setPopupMenuVisible(false);
		this.getPopupMenu().setBorderPainted(false);
		this.setMenuUI(new EnigmaMenuUI());
	}

	public EnigmaMenuUI getMenuUI() {
		return this.ui;
	}

	public void setMenuUI(EnigmaMenuUI ui) {
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
