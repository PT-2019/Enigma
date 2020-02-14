package game.hmi.listener.redirect;

import common.hud.EnigmaOptionPane;
import data.NeedToBeTranslated;
import game.EnigmaGameLauncher;
import game.hmi.ContentManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Redirige vers l'affichage des parties solo
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class SoloRedirectListener implements ActionListener {
	/**
	 * Textes
	 */
	public final static String QUIT_CONFIRMATION = NeedToBeTranslated.QUIT_CONFIRMATION;

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (ContentManager.getInstance().getState() != ContentManager.LOBBY_STATE)
			ContentManager.getInstance().refresh(ContentManager.SOLO_STATE);
		else {
			if (EnigmaOptionPane.showConfirmDialog(EnigmaGameLauncher.getInstance().getWindow(), QUIT_CONFIRMATION))
				ContentManager.getInstance().refresh(ContentManager.SOLO_STATE);
		}
	}
}
