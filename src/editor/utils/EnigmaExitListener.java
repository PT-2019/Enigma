package editor.utils;

import api.hud.manager.window.Exit;
import api.utils.CloseWindowLibgdxApplication;
import editor.hud.EnigmaOptionPane;
import editor.hud.EnigmaWindow;
import starter.DesktopLauncher;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;

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
public class EnigmaExitListener extends Exit {

	/**
	 * Ferme la fenêtre.
	 * Si c'est la dernière, arrête l'application.
	 * Ferme correctement la libgdx.
	 *
	 * @param window la fenêtre a fermer
	 */
	public EnigmaExitListener(EnigmaWindow window) {
		super(window);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		boolean close = true;

		if (this.window.willAskBeforeClosing()) {
			close = EnigmaOptionPane.showConfirmDialog(this.window, new Dimension(300, 200),
					"Voulez vous vraiment quittez?");
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
					for (WindowListener l : this.window.getWindowListeners()) {
						if (l instanceof CloseWindowLibgdxApplication) {
							l.windowClosing(null);
							//remove
							this.window.removeWindowListener(l);
						}
					}

					this.window.dispose();
				}
			}
		} else {
			//vider la pile de windowListeners
		}
	}
}
