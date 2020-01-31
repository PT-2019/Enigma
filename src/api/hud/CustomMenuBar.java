package api.hud;

import api.hud.base.CustomComponent;
import api.hud.ui.CustomMenuBarUI;
import com.badlogic.gdx.utils.Array;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;

/**
 * Une barre de menus customizable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1 27/12/2019
 * @since 4.0 27/12/2019
 */
public class CustomMenuBar extends JMenuBar implements CustomComponent<CustomMenuBarUI> {

	/**
	 * Le style de la barre de menus
	 */
	protected CustomMenuBarUI ui;
	/**
	 * Les menus qui la compose
	 */
	private Array<Component> menus;
	/**
	 * Sert a afficher la barre dans une sorte de Panel
	 *
	 * @since 4.1
	 */
	private CustomMenuBar layer;

	/**
	 * Indique si c'est la barre originale ou une sous barre créer pour afficher
	 * dans un panel.
	 *
	 * @since 4.1
	 */
	private boolean sub;

	/**
	 * Crée une barre de menus
	 */
	public CustomMenuBar() {
		super();
		this.ui = null;
		this.menus = new Array<>();
		this.setOpaque(true);
		this.setComponentUI(new CustomMenuBarUI());
	}

	/**
	 * Crée une sous-barre de menus
	 *
	 * @param sub sous-barre
	 */
	private CustomMenuBar(boolean sub) {
		super();
		this.ui = null;
		this.menus = new Array<>();
		this.setOpaque(true);
		this.setComponentUI(new CustomMenuBarUI());
		this.sub = sub;
	}

	/**
	 * Affiche une sous-barre et la barre du haut est censée être resizable
	 *
	 * @param resizable true pour resizable sinon false
	 * @since 4.1
	 * @deprecated since 4.1 car c'est moche (^^)
	 */
	@Deprecated
	public void setResizable(boolean resizable) {
		layer = new CustomMenuBar(true);
		for (Component c : new Array.ArrayIterator<>(menus)) {
			layer.add(c);
		}
		this.removeAll();

		this.setLayout(new BorderLayout());
		JLabel label = new JLabel(" ");
		//ce listener aurait permis de check le redimensionnement
		//label.addMouseMotionListener();
		this.add(label, BorderLayout.NORTH);
		this.add(layer, BorderLayout.SOUTH);
	}

	/**
	 * Retourne Le style de la barre de menus
	 *
	 * @return Le style de la barre de menus
	 */
	@Override
	public CustomMenuBarUI getComponentUI() {
		return this.ui;
	}

	/**
	 * Change Le style de la barre de menus
	 *
	 * @param ui Le style de la barre de menus
	 */
	@Override
	public void setComponentUI(CustomMenuBarUI ui) {
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

		if (!this.sub && layer != null) {
			//ajout à l'enfant
			return layer.add(comp);
		}

		return super.add(comp);
	}

	@Override
	public JMenu add(JMenu c) {
		this.menus.add(c);
		if (!this.sub && layer != null) {
			//ajout à l'enfant
			return layer.add(c);
		}
		return super.add(c);
	}

	/**
	 * Retourne les composants de la barre de menu
	 *
	 * @return les composants de la barre de menu
	 */
	public Array<Component> getMenus() {
		return menus;
	}
}
