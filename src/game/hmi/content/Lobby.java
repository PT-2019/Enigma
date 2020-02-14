package game.hmi.content;

import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.hud.EnigmaTextArea;
import common.hud.ui.EnigmaLabelUI;
import data.NeedToBeTranslated;
import data.config.GameConfiguration;
import data.config.GameConfigurationDeprecated;
import data.config.UserConfiguration;
import game.hmi.Content;

import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

/**
 * Affichage pour l'attente de joueurs
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class Lobby extends Content {
	/**
	 * Instance
	 */
	private final static Lobby instance = new Lobby();
	/**
	 * Textes
	 */
	private final static String NAME = NeedToBeTranslated.NAME;
	private final static String MAP = NeedToBeTranslated.MAP;
	private final static String DESCRIPTION = NeedToBeTranslated.DESCRIPTION;
	private final static String DURATION = NeedToBeTranslated.DURATION;
	private final static String PLAYER_WAIT = NeedToBeTranslated.PLAYER_WAIT;
	private final static String LEADER_WAIT = NeedToBeTranslated.LEADER_WAIT;
	private final static String LEADER = NeedToBeTranslated.LEADER;
	private final static String NB_PLAYERS = NeedToBeTranslated.NB_PLAYERS;
	private final static String AUTHOR = NeedToBeTranslated.AUTHOR;
	/**
	 * Affiche les informations de la partie
	 */
	private EnigmaTextArea game;
	/**
	 * Affiche le nombre de joueurs actuels sur le nombre de joueurs nécessaires
	 */
	private EnigmaLabel players;
	/**
	 * Liste des joueurs ayant rejoin
	 */
	private EnigmaPanel playersList;
	/**
	 * Style de base du conteneur du nom des joueurs
	 */
	private EnigmaLabelUI nameNormalUI;
	/**
	 * Style de base du conteneur qui indique si le joueur est le chef de la partie
	 */
	private EnigmaLabelUI leadNormalUI;

	private Lobby() {
		super(new EnigmaPanel());
		int borderSize = 6;

		this.game = new EnigmaTextArea();
		this.players = new EnigmaLabel();
		this.playersList = new EnigmaPanel();

		this.content.setLayout(new GridLayout(1, 2));
		this.playersList.setLayout(new GridLayout(GameConfigurationDeprecated.MAX_PLAYERS, 1));

		this.nameNormalUI = new EnigmaLabelUI();
		this.nameNormalUI.setAllBackgrounds(Color.GRAY);

		this.leadNormalUI = new EnigmaLabelUI();
		this.leadNormalUI.setAllBackgrounds(Color.GRAY);
		this.leadNormalUI.setAllForegrounds(Color.GRAY);

		this.players.setComponentUI(this.nameNormalUI);
		this.players.setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderSize, Color.DARK_GRAY));

		this.initContent();
		this.refresh(NO_PRECISED_STATE);
	}

	/**
	 * Obtenir l'instance
	 *
	 * @return Instance
	 */
	public static Lobby getInstance() {
		return instance;
	}

	/**
	 * Initialise le contenu
	 * Doit être normalement appelé qu'une fois, dans le constructeur
	 */
	@Override
	public void initContent() {
		EnigmaPanel playersComponent = new EnigmaPanel();
		EnigmaPanel gameComponent = new EnigmaPanel();
		int borderSize = 6;

		gameComponent.setLayout(new BorderLayout());
		gameComponent.setBorder(BorderFactory.createMatteBorder(borderSize, borderSize, borderSize, borderSize, Color.GRAY));
		this.game.setEditable(false);
		this.game.getComponentUI().setAllBackgrounds(Color.GRAY, Color.GRAY, Color.GRAY);
		this.game.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.content.setBorder(BorderFactory.createMatteBorder(borderSize, borderSize, borderSize, borderSize, Color.DARK_GRAY));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;

		playersComponent.setLayout(new GridBagLayout());

		gbc.gridy = 1;
		gbc.weighty = 1;
		playersComponent.add(this.players, gbc);
		gbc.gridy = 2;
		gbc.weighty = 10;
		playersComponent.add(this.playersList, gbc);

		gameComponent.add(this.game, BorderLayout.CENTER);
		this.content.add(playersComponent);
		this.content.add(gameComponent);
	}

	/**
	 * Rafraichi l'affichage
	 *
	 * @param state Etat
	 */
	@Override
	public void refresh(int state) {
		Color red = new Color(200, 0, 0);
		Color blue = new Color(0, 136, 193);
		int borderSize = 6;
		GameConfiguration gameConfig = GameConfiguration.getInstance();
		String waitWhat = PLAYER_WAIT;
		if (gameConfig.getTotalPlayers() == gameConfig.getMaxPlayers())
			waitWhat = LEADER_WAIT;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1;

		this.playersList.removeAll();

		this.game.setText(NAME + ": " + gameConfig.getGameName() + "\n\n"
				+ DESCRIPTION + ": " + gameConfig.getDescription() + "\n\n"
				+ LEADER + ": " + gameConfig.getOwner() + "\n\n"
				+ AUTHOR + ": " + gameConfig.getAuthor() + "\n\n"
				+ MAP + ": " + gameConfig.getMapName() + "\n\n"
				+ NB_PLAYERS + ": " + gameConfig.getMaxPlayers() + "\n\n"
				+ DURATION + ": " + gameConfig.getDuration() + " min"
		);

		this.players.setText(waitWhat + ": " + gameConfig.getTotalPlayers() + "/" + gameConfig.getMaxPlayers());

		ArrayList<String> players = gameConfig.getAllPlayers();
		for (int i = 0; i < GameConfigurationDeprecated.MAX_PLAYERS; i++) {
			EnigmaPanel playerContent = new EnigmaPanel();
			EnigmaLabel lead = new EnigmaLabel(LEADER);
			EnigmaLabel player = new EnigmaLabel();

			player.setComponentUI(this.nameNormalUI);
			lead.setComponentUI(this.leadNormalUI);
			lead.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
			player.setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.DARK_GRAY));
			playerContent.setBorder(BorderFactory.createMatteBorder(borderSize, 0, 0, borderSize, Color.DARK_GRAY));
			playerContent.setLayout(new GridBagLayout());

			if (i < players.size()) {
				String name = players.get(i);
				player.setText(name);

				if (UserConfiguration.getInstance().getData().getName().equals(name)) {
					player.getComponentUI().setAllBackgrounds(blue);
					lead.getComponentUI().setAllBackgrounds(blue);
					lead.getComponentUI().setAllForegrounds(blue);
				}

				if (gameConfig.getOwner().equals(name)) {
					lead.getComponentUI().setAllForegrounds(Color.WHITE);
					lead.getComponentUI().setAllBackgrounds(red);
				}
			}

			gbc.gridx = 1;
			gbc.weightx = 0;
			playerContent.add(lead, gbc);
			gbc.gridx = 2;
			gbc.weightx = 1;
			playerContent.add(player, gbc);
			this.playersList.add(playerContent);
		}

		this.content.revalidate();
	}
}
