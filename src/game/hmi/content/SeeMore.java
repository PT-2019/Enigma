package game.hmi.content;

import api.ui.CustomWindow;
import common.data.GameData;
import common.hud.EnigmaAlert;
import common.hud.EnigmaTextArea;
import data.NeedToBeTranslated;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

/**
 * Affichage pour avoir plus d'infos sur une partie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class SeeMore {
	/**
	 * Textes
	 */
	private final static String NAME = NeedToBeTranslated.NAME;
	private final static String AUTHOR = NeedToBeTranslated.AUTHOR;
	private final static String MAP = NeedToBeTranslated.MAP;
	private final static String DESCRIPTION = NeedToBeTranslated.DESCRIPTION;
	private final static String NB_PLAYERS = NeedToBeTranslated.NB_PLAYERS;
	private final static String DURATION = NeedToBeTranslated.DURATION;
	private final static String MULTI_PLAYERS = NeedToBeTranslated.MULTI_PLAYERS;
	private final static String YES = NeedToBeTranslated.YES;
	private final static String NO = NeedToBeTranslated.NO;
	/**
	 * Fenêtre
	 */
	private EnigmaAlert window;

	/**
	 * @param data Données de la partie
	 */
	public SeeMore(GameData data) {
		this.window = new EnigmaAlert();

		String isMulti = NO;
		if (data.getMaxPlayers() > 1) {
			isMulti = YES;
		}

		EnigmaTextArea area = new EnigmaTextArea();
		area.setEditable(false);
		area.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		area.setText(NAME + ": " + data.getName() + "\n\n"
				+ DESCRIPTION + ": " + data.getDescription() + "\n\n"
				+ AUTHOR + ": " + data.getAuthor() + "\n\n"
				+ MAP + ": " + data.getMapName() + "\n\n"
				+ MULTI_PLAYERS + ": " + isMulti + "\n\n"
				+ NB_PLAYERS + ": " + data.getMaxPlayers() + "\n\n"
				+ DURATION + ": " + data.getDuration() + " min"
		);

		this.window.getContentSpace().setLayout(new BorderLayout());
		this.window.getContentSpace().add(area.setScrollBar());
		this.window.setWindowBackground(Color.DARK_GRAY);
		this.window.setHalfScreenSize();
		this.window.showBorder(Color.WHITE, 1);
		this.window.setLocation(CustomWindow.CENTER);
		this.window.setResizable(false);
		this.window.setModal(true);
	}

	/**
	 * Afficher la fenêtre ou non
	 *
	 * @param show true pour afficher, false sinon
	 */
	public void show(boolean show) {
		this.window.setVisible(show);
	}
}
