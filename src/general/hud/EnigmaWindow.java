package general.hud;

import api.hud.CustomWindow;
import api.hud.manager.window.Exit;
import api.libgdx.utils.CloseWindowLibgdxApplication;
import datas.config.Config;
import datas.config.EnigmaUIValues;
import general.DesktopLauncher;
import general.hud.ui.EnigmaButtonUI;
import general.hud.ui.EnigmaMenuBarUI;
import general.language.GameLanguage;
import general.language.HUDFields;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

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
		this.setIconImage(new ImageIcon(Config.LOGO).getImage());

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

	/**
	 * Ferme la fenêtre.
	 * Si c'est la dernière, arrête l'application.
	 * Ferme correctement la libgdx.
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 4.3 29/12/19
	 * @since 4.0 26/12/2019
	 */
	public static class EnigmaExitListener extends Exit {

		private static String LEAVING = GameLanguage.gl.get(HUDFields.LEAVE);

		/**
		 * Ferme la fenêtre.
		 * Si c'est la dernière, arrête l'application.
		 * Ferme correctement la libgdx.
		 *
		 * @param window la fenêtre a fermer
		 */
		EnigmaExitListener(EnigmaWindow window) {
			super(window);
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			boolean close = true;

			if (this.window.willAskBeforeClosing()) {
				close = EnigmaOptionPane.showConfirmDialog(this.window, new Dimension(300, 200), LEAVING);
			} else {
				//détruire la fenêtre manuellement
			}

			if (close) {
				//check libgdx listeners
				EnigmaWindow w = DesktopLauncher.getInstance().getWindow();
				if (w != null) {
					if (w.equals(this.window)) {
						//Dernière, on quitte
						DesktopLauncher.close();
					} else {
						ArrayList<CloseWindowLibgdxApplication> wl = new ArrayList<>();
						for (WindowListener l : this.window.getWindowListeners()) {
							if (l instanceof CloseWindowLibgdxApplication) {
								//ON NE PEUT PAS LIBERER LA LIBGDX AVANT DE FERMER LA FENETRE ???
								//l.windowClosing(null);//libère libgdx
								//remove
								wl.add((CloseWindowLibgdxApplication) l);
								this.window.removeWindowListener(l);
							}
						}
						this.window.dispose();
						for (CloseWindowLibgdxApplication l : wl) {
							//libération de la libgdx mais lève une erreur
							l.windowClosing(null);
						}
					}
				}
			} else {
				//vider la pile de windowListeners
			}
		}
	}
}
