package editor.hud;

import api.hud.components.CustomWindow;
import api.utils.CloseWindowLibgdxApplication;
import editor.hud.ui.EnigmaButtonUI;
import editor.hud.ui.EnigmaMenuBarUI;
import editor.utils.EnigmaExitListener;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GraphicsConfiguration;
import java.awt.event.WindowListener;

/**
 * Fenêtre customisée pour le jeu enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 4.0 27/12/2019
 */
public class EnigmaWindow extends CustomWindow {

	public EnigmaWindow() {
		super();
	}

	/**
	 * Crée une fenêtre enigma
	 *
	 * @param width  largeur
	 * @param height hauteur
	 * @since 4.0
	 */
	public EnigmaWindow(int width, int height) {
		super(width, height);
	}

	/**
	 * Crée une fenêtre dans un écran
	 *
	 * @param monitor l'écran
	 * @since 5.0
	 */
	public EnigmaWindow(GraphicsConfiguration monitor) {
		super(monitor);
	}

	@Override
	protected void init() {
		this.setIconImage(new ImageIcon("assets/logo.jpg").getImage());

		//remplacement des panels
		this.windowContent = new EnigmaPanel();
		this.content = new EnigmaPanel();

		this.createMenuBar(new EnigmaMenuBar(), new EnigmaMenuBarUI());

		this.setIfAskBeforeClosing(true);

		if (!EnigmaWindow.DECORATED) {
			EnigmaButtonUI buttonUI = new EnigmaButtonUI();
			buttonUI.setAllBorders(null, null, null);
			buttonUI.setAllBackgrounds(this.windowActionBar.getComponentUI().getBackground(), Color.RED, Color.RED);
			buttonUI.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
			buttonUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			this.close.setText("X");
			this.close.setComponentUI(buttonUI);

			buttonUI.setAllBackgrounds(this.windowActionBar.getComponentUI().getBackground(),
					EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BACKGROUND, EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BACKGROUND);

			this.minimize.setText("-");
			this.minimize.setComponentUI(buttonUI);

			this.smaller.setText("[]");
			this.smaller.setComponentUI(buttonUI);
		}

		this.setExitListener(new EnigmaExitListener(this));

		initContent();
	}

	@Override
	public void close() {
		if (!CustomWindow.DECORATED) {
			super.close();
		} else {
			//check libgdx listeners
			for (WindowListener l : this.getWindowListeners()) {
				if (l instanceof CloseWindowLibgdxApplication) {
					l.windowClosing(null);
				}
			}
			this.dispose();
		}
	}

	@Override
	public EnigmaPanel getContentSpace() {
		return (EnigmaPanel) super.getContentSpace();
	}
}
