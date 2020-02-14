package game.hmi.listener.action;

import common.data.GameData;
import data.config.GameConfiguration;
import data.config.UserConfiguration;
import game.hmi.ActionBar;
import game.hmi.ContentManager;
import game.hmi.MHIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Gestionnaire du bouton "jouer"
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class PlayListener implements ActionListener {
	/**
	 * Données de la partie
	 */
	private GameData game;

	/**
	 * @param game Données de la partie
	 */
	public PlayListener(GameData game) {
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		GameConfiguration.getInstance().set(this.game,
				UserConfiguration.getInstance().getData().getName(),
				new ArrayList<>()
		);

		if (this.game.isMultiPlayer()) {
			ContentManager.getInstance().refresh(ContentManager.LOBBY_STATE);
			ActionBar.getInstance().refresh(ActionBar.QUIT_AND_LAUNCH_STATE);
		} else {
			MHIManager.getInstance().refresh(MHIManager.GAME_STATE);
		}
	}
}
