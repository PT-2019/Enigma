package game.configure.displayManagers.lowLevel;

import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.hud.ui.EnigmaButtonUI;
import common.hud.ui.EnigmaLabelUI;
import data.config.EnigmaUIValues;
import data.config.GameConfiguration;
import game.configure.LaunchGameDisplay;
import game.configure.managers.configurations.ChangeConfiguration;
import game.configure.managers.configurations.ChangeDescription;
import game.configure.managers.configurations.ChangeDuration;
import game.configure.managers.configurations.ChangeMap;
import game.configure.managers.configurations.ChangeMaxPlayers;
import game.configure.managers.configurations.ChangeName;
import game.configure.managers.configurations.ChangeType;
import game.configure.managers.redirect.CreateGameRedirect;
import game.configure.managers.redirect.Redirect;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

/**
 * Gère l'affichage de création de parties
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class CreateGameDisplayManager implements DisplayManager {

	/**
	 * Instance
	 */
	private final static CreateGameDisplayManager instance = new CreateGameDisplayManager();

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
	private EnigmaPanel gameConfig;

	private CreateGameDisplayManager() {

		this.initContent();
		this.initRightBar();
		this.refreshAll();
	}

	/**
	 * Obtenir l'instance
	 *
	 * @return L'instance
	 */
	public static CreateGameDisplayManager getInstance() {
		return instance;
	}

	/**
	 * Initialisation de la barre de menu
	 */
	private void initRightBar() {
		Color grey = new Color(100, 100, 100);
		Color lighterGrey = new Color(150, 150, 150);
		Color darkRed = new Color(150, 0, 0);
		Color lighterDarkRed = new Color(200, 0, 0);
		boolean[] borders = new boolean[4];
		borders[EnigmaUIValues.LEFT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		EnigmaButtonUI bui = new EnigmaButtonUI();
		bui.setAllBackgrounds(grey, lighterGrey, lighterGrey);
		bui.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		bui.setAllBorders(null, null, null);
		EnigmaButtonUI bui2 = (EnigmaButtonUI) bui.duplicate();
		bui2.setAllBackgrounds(darkRed, lighterDarkRed, lighterDarkRed);
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
		EnigmaButton b = new EnigmaButton("Créer");
		b.setComponentUI(bui);
		b.addActionListener(new CreateGameRedirect());
		buttonsComponent.add(b, gbc);

		gbc.gridy = 2;
		gbc.insets = new Insets(0, inset, inset, inset);
		b = new EnigmaButton("Annuler");
		b.setComponentUI(bui2);
		b.addActionListener(new Redirect(LaunchGameDisplay.SELECT_GAME));
		buttonsComponent.add(b, gbc);

		gbc.gridy = 3;
		gbc.insets = new Insets(inset * 2, inset, inset, inset);
		EnigmaButton voidButton = new EnigmaButton("nothing");
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
		int inset = 50;

		this.content = new EnigmaPanel();
		this.gameConfig = new EnigmaPanel();
		EnigmaPanel content = new EnigmaPanel();

		GridBagConstraints gbc = new GridBagConstraints();
		GridBagConstraints gbc2 = new GridBagConstraints();

		this.gameConfig.setLayout(new GridLayout(infoCount, 1));
		this.content.setLayout(new GridBagLayout());
		content.setLayout(new GridBagLayout());

		this.gameConfig.getComponentUI().setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		this.content.getComponentUI().setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		content.getComponentUI().setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);

		gbc2.gridx = 1;
		gbc2.gridy = 1;
		gbc2.gridwidth = 1;
		gbc2.gridheight = 1;
		gbc2.fill = GridBagConstraints.BOTH;
		gbc2.weightx = 1;
		gbc2.weighty = 1;

		EnigmaLabel help = new EnigmaLabel("Cliquez pour modifier quelque chose");
		help.getComponentUI().setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		help.getComponentUI().setAllForegrounds(Color.BLACK, Color.BLACK, Color.BLACK);
		content.add(help, gbc2);

		gbc2.gridy = 2;
		gbc2.weighty = 15;
		content.add(this.gameConfig, gbc2);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(inset, inset, inset, inset);

		this.content.add(content, gbc);
	}

	/**
	 * Rafraichir le contenu
	 */
	@Override
	public void refreshContent() {
		this.gameConfig.removeAll();

		Color grey = new Color(100, 100, 100);
		Color grey2 = new Color(220, 220, 220);
		int infoCount = 6;
		int borderSize = 4;
		GameConfiguration gameConfig = GameConfiguration.getInstance();
		boolean[] bordersB = new boolean[4];
		bordersB[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		ChangeConfiguration[] change = new ChangeConfiguration[infoCount];
		change[0] = new ChangeName();
		change[1] = new ChangeDescription();
		change[2] = new ChangeMap();
		change[3] = new ChangeDuration();
		change[4] = new ChangeType();
		change[5] = new ChangeMaxPlayers();

		String type = "non";
		if (gameConfig.isMultiPlayer())
			type = "oui";

		String[] infos = new String[infoCount];
		infos[0] = "Partie : " + gameConfig.getName();
		infos[1] = "Description : " + gameConfig.getDescription();
		infos[2] = "Map : " + gameConfig.getMap();
		infos[3] = "Durée : " + gameConfig.getDuration() + " min";
		infos[4] = "Multijoueur : " + type;
		infos[5] = "Nombre de joueurs : " + gameConfig.getMaxGamePlayers();

		EnigmaLabelUI lui = new EnigmaLabelUI();
		lui.setAllBackgrounds(Color.WHITE, grey2, grey2);
		lui.setAllForegrounds(Color.BLACK, Color.BLACK, Color.BLACK);
		lui.setFont(lui.getFont().deriveFont((float) 18.0));
		lui.setCursor(new Cursor(Cursor.HAND_CURSOR));

		for (int i = 0; i < infoCount; i++) {
			EnigmaLabel l = new EnigmaLabel(infos[i]);
			l.setComponentUI(lui);
			l.setHorizontalAlignment(SwingConstants.LEFT);
			l.addMouseListener(change[i]);
			this.gameConfig.add(l);

			if (i < infoCount - 1) {
				l.getComponentUI().setAllBorders(grey, grey, grey);
				l.getComponentUI().setAllShowedBorders(bordersB, bordersB, bordersB);
				l.getComponentUI().setAllBordersSize(borderSize, borderSize, borderSize);
			}
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
