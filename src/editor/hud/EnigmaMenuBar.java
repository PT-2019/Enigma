package editor.hud;

import com.badlogic.gdx.utils.Array;
import editor.hud.ui.EnigmaMenuBarUI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.Component;
import java.awt.Graphics;

/**
 * TODO: comment EnigmaMenuBar and write Readme.md in editor.hud
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class EnigmaMenuBar extends JMenuBar {

	private Array<Component> menus;

	private EnigmaMenuBarUI ui;

	public EnigmaMenuBar() {
		super();
		this.ui = null;
		this.menus = new Array<>();
		this.setOpaque(true);
		this.setMenuBarUI(new EnigmaMenuBarUI());
	}

	public EnigmaMenuBarUI getMenuBarUI() {
		return this.ui;
	}

	public void setMenuBarUI(EnigmaMenuBarUI ui) {
		this.ui = ui.duplicate();
		this.setBorderPainted(false);
		super.setUI(this.ui);
	}

	@Override
	public void paintComponent(Graphics g) {
		this.ui.paint(g, this);
	}

	@Override
	public Component add(Component comp) {
		this.menus.add(comp);
		return super.add(comp);
	}

	@Override
	public JMenu add(JMenu c) {
		this.menus.add(c);
		return super.add(c);
	}

	public Array<Component> getMenus() {
		return menus;
	}
}
