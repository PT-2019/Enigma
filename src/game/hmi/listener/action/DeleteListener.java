package game.hmi.listener.action;

import common.data.GameData;
import common.hud.EnigmaOptionPane;
import common.utils.Logger;
import data.NeedToBeTranslated;
import data.config.Config;
import game.EnigmaGameLauncher;
import game.hmi.ContentManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Gestionnaire du bouton "supprimer"
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class DeleteListener implements ActionListener {
	/**
	 * Textes
	 */
	public final static String DELETE_ERROR = NeedToBeTranslated.DELETE_ERROR;
	public final static String DELETE_CONFIRMATION = NeedToBeTranslated.DELETE_CONFIRMATION;
	/**
	 * Données de la partie
	 */
	private GameData game;

	/**
	 * @param game Données de la partie
	 */
	public DeleteListener(GameData game) {
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		File file = new File(Config.GAME_DATA_FOLDER + this.game.getName() + Config.DATA_EXTENSION);
		if (EnigmaOptionPane.showConfirmDialog(EnigmaGameLauncher.getInstance().getWindow(), DELETE_CONFIRMATION)) {
			if (!file.delete()) {
				EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), DELETE_ERROR);
				Logger.printError("DeleteListener.java", "Erreur de suppression ");
			}

			if (this.game.isMultiPlayer())
				ContentManager.getInstance().refresh(ContentManager.MULTI_STATE);
			else
				ContentManager.getInstance().refresh(ContentManager.SOLO_STATE);
		}
	}
}
