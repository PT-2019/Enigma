package game.configure.displayManagers.lowLevel;

import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.hud.ui.EnigmaButtonUI;
import common.hud.ui.EnigmaLabelUI;
import data.config.EnigmaUIValues;
import data.config.GameConfigurationDeprecated;
import game.configure.LaunchGameDisplay;
import game.configure.managers.redirect.Redirect;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

/**
 * Gère l'affichage de l'attente du commencement de la partie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 * @deprecated
 */
@Deprecated
public class WaitPlayersDisplayManager implements DisplayManager {

	/**
	 * Instance
	 */
	private final static WaitPlayersDisplayManager instance = new WaitPlayersDisplayManager();

	/**
	 * Contenu
	 */
	private EnigmaPanel content;

	/**
	 * Barre de menu
	 */
	private EnigmaPanel rightBar;

	/**
	 * Conteneur des caractéristiques de la partie
	 */
	private EnigmaPanel gameInfo;

	/**
	 * Conteneur de la liste des joueurs
	 */
	private EnigmaPanel players;

	private WaitPlayersDisplayManager() {
		this.initContent();
		this.initRightBar();
		this.refreshAll();
	}

	/**
	 * Obtenir l'instance
	 *
	 * @return L'instance
	 */
	public static WaitPlayersDisplayManager getInstance() {
		return instance;
	}

	/**
	 * Initialisation de la barre de menu
	 */
	private void initRightBar() {
		Color darkRed = new Color(150, 0, 0);
		Color lighterDarkRed = new Color(200, 0, 0);
		boolean[] borders = new boolean[4];
		borders[EnigmaUIValues.LEFT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		EnigmaButtonUI bui = new EnigmaButtonUI();
		bui.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		bui.setAllBorders(null, null, null);
		bui.setAllBackgrounds(darkRed, lighterDarkRed, lighterDarkRed);
		EnigmaButtonUI voidBUI = new EnigmaButtonUI();
		voidBUI.setAllBorders(null, null, null);
		voidBUI.setAllBackgrounds(Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY);
		voidBUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		voidBUI.setAllForegrounds(Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY);

		this.rightBar = new EnigmaPanel();
		this.rightBar.setLayout(new GridLayout(2, 1));
		this.rightBar.getComponentUI().setAllBorders(Color.RED, Color.RED, Color.RED);
		this.rightBar.getComponentUI().setAllBordersSize(3, 3, 3);
		this.rightBar.getComponentUI().setAllShowedBorders(borders, borders, borders);

		EnigmaPanel buttonsComponent = new EnigmaPanel();
		EnigmaPanel filtersComponent = new EnigmaPanel();

		GridBagConstraints gbc = new GridBagConstraints();
		int inset = 10;

		buttonsComponent.setLayout(new GridBagLayout());
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(inset, inset, inset, inset);
		EnigmaButton b = new EnigmaButton("Quitter");
		b.setComponentUI(bui);
		b.addActionListener(new Redirect(LaunchGameDisplay.SELECT_GAME));
		buttonsComponent.add(b, gbc);

		gbc.gridy = 2;
		gbc.insets = new Insets(0, inset, inset, inset);
		EnigmaButton voidButton = new EnigmaButton("nothing");
		voidButton.setComponentUI(voidBUI);
		buttonsComponent.add(voidButton, gbc);

		gbc.gridy = 3;
		gbc.insets = new Insets(inset * 2, inset, inset, inset);
		voidButton = new EnigmaButton("nothing");
		voidButton.setComponentUI(voidBUI);
		buttonsComponent.add(voidButton, gbc);

		this.rightBar.add(buttonsComponent);
		this.rightBar.add(filtersComponent);
	}

	/**
	 * Initialisation du contenu
	 */
	private void initContent() {
		int infoCount = 6;

		this.content = new EnigmaPanel();
		EnigmaPanel content = new EnigmaPanel();
		this.gameInfo = new EnigmaPanel();
		this.players = new EnigmaPanel();

		GridBagConstraints gbc = new GridBagConstraints();

		content.setLayout(new GridBagLayout());
		this.content.setLayout(new GridBagLayout());
		this.gameInfo.setLayout(new GridLayout(infoCount, 1));
		this.players.setLayout(new GridLayout(GameConfigurationDeprecated.MAX_PLAYERS, 1));
		int inset = 50;
		int inset2 = 30;

		content.getComponentUI().setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		this.content.getComponentUI().setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		this.gameInfo.getComponentUI().setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);

		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0, 0, 0, inset2);
		content.add(this.gameInfo, gbc);

		gbc.gridx = 2;
		gbc.insets = new Insets(0, inset2, 0, 0);
		content.add(this.players, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(inset, inset, inset, inset);
		this.content.add(content, gbc);
	}

	/**
	 * Rafraichir le contenu
	 */
	@Override
	public void refreshContent() {
		this.gameInfo.removeAll();
		this.players.removeAll();

		Color grey = new Color(100, 100, 100);
		Color blue = new Color(50, 100, 200);
		int infoCount = 6;
		int borderSize = 4;
		GameConfigurationDeprecated gameConfig = GameConfigurationDeprecated.getInstance();
		boolean[] bordersB = new boolean[4];
		bordersB[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		String[] infos = new String[infoCount];
		infos[0] = "Partie : " + gameConfig.getName();
		infos[1] = "Description : " + gameConfig.getDescription();
		/*Player owner = gameConfig.getOwner();
		//TODO: hum ???????????????, owner est null????
		if (owner == null)
			infos[2] = "Chef de groupe : NULL";
		else
			infos[2] = "Chef de groupe : " + owner.getName();*/
		infos[3] = "Map : " + gameConfig.getMap();
		infos[4] = "Durée : " + gameConfig.getDuration() + " min";
		infos[5] = "Nombre de joueurs : " + gameConfig.getTotalPlayers() + "/" + gameConfig.getMaxGamePlayers();
		EnigmaLabelUI lui = new EnigmaLabelUI();
		lui.setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		lui.setAllForegrounds(Color.BLACK, Color.BLACK, Color.BLACK);
		lui.setFont(lui.getFont().deriveFont((float) 18.0));

		for (int i = 0; i < infoCount; i++) {
			EnigmaLabel l = new EnigmaLabel(infos[i]);
			l.setComponentUI(lui);
			l.setHorizontalAlignment(SwingConstants.LEFT);
			this.gameInfo.add(l);

			if (i < infoCount - 1) {
				l.getComponentUI().setAllBorders(grey, grey, grey);
				l.getComponentUI().setAllShowedBorders(bordersB, bordersB, bordersB);
				l.getComponentUI().setAllBordersSize(borderSize, borderSize, borderSize);
			}
		}

		for (int i = 0; i < GameConfigurationDeprecated.MAX_PLAYERS; i++) {
			EnigmaLabel name = new EnigmaLabel();
			if (i < gameConfig.getTotalPlayers()) {
				/*String pName = gameConfig.getAllPlayers().get(i).getName();
				name.setText(pName);

				if (pName.equals(UserConfiguration.getInstance().getData().getName()))
					name.getComponentUI().setAllBackgrounds(blue, blue, blue);*/
			}

			if (i < GameConfigurationDeprecated.MAX_PLAYERS - 1) {
				name.getComponentUI().setAllBorders(grey, grey, grey);
				name.getComponentUI().setAllShowedBorders(bordersB, bordersB, bordersB);
				name.getComponentUI().setAllBordersSize(borderSize, borderSize, borderSize);
			}

			this.players.add(name);
		}
	}

	/**
	 * Rafraichir la barre de menu
	 */
	@Override
	public void refreshRightBar() {
	}

	/**
	 * Tout rafraichir
	 */
	@Override
	public void refreshAll() {
		this.refreshContent();
		this.refreshRightBar();
		this.content.revalidate();
		this.rightBar.revalidate();
	}

	/**
	 * Obtenir le contenu
	 *
	 * @return Le contenu
	 */
	@Override
	public EnigmaPanel getContent() {
		return this.content;
	}

	/**
	 * Obtenir la barre de menu
	 *
	 * @return La barre de menu
	 */
	@Override
	public EnigmaPanel getRightBar() {
		return this.rightBar;
	}
}
