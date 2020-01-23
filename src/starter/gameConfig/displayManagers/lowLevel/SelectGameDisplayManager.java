package starter.gameConfig.displayManagers.lowLevel;

import api.hud.manager.choices.CheckBoxManager;
import editor.hud.EnigmaButton;
import editor.hud.EnigmaLabel;
import editor.hud.EnigmaPanel;
import editor.hud.EnigmaTextArea;
import editor.hud.EnigmaUIValues;
import editor.hud.ui.EnigmaButtonUI;
import starter.gameConfig.LaunchGameDisplay;
import starter.gameConfig.managers.redirect.DeleteGameRedirect;
import starter.gameConfig.managers.redirect.Redirect;
import starter.gameConfig.managers.redirect.StartGameRedirect;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

/**
 * Gère l'affichage pour choisir une partie à lancer
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class SelectGameDisplayManager implements DisplayManager {

	/**
	 * Instance
	 */
	private final static SelectGameDisplayManager instance = new SelectGameDisplayManager();

	/**
	 * Contenu
	 */
	private EnigmaPanel content;

	/**
	 * Barre de menu
	 */
	private EnigmaPanel rightBar;
	private EnigmaPanel gameList;

	private SelectGameDisplayManager() {

		this.initContent();
		this.initRightBar();
		this.refreshAll();
	}

	/**
	 * Obtenir l'instance
	 *
	 * @return L'instance
	 */
	public static SelectGameDisplayManager getInstance() {
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
		Color blue = new Color(50, 100, 200);
		Color lighterBlue = new Color(100, 150, 250);
		boolean[] borders = new boolean[4];
		borders[EnigmaUIValues.LEFT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

		EnigmaButtonUI bui = new EnigmaButtonUI();
		bui.setAllBackgrounds(grey, lighterGrey, lighterGrey);
		bui.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		bui.setAllBorders(null, null, null);
		EnigmaButtonUI bui2 = (EnigmaButtonUI) bui.duplicate();
		bui2.setAllBackgrounds(darkRed, lighterDarkRed, lighterDarkRed);

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
		EnigmaButton b = new EnigmaButton("Jouer");
		b.setComponentUI(bui);
		b.addActionListener(new StartGameRedirect());
		buttonsComponent.add(b, gbc);

		gbc.gridy = 2;
		gbc.insets = new Insets(0, inset, inset, inset);
		b = new EnigmaButton("Supprimer");
		b.setComponentUI(bui2);
		b.addActionListener(new DeleteGameRedirect());
		buttonsComponent.add(b, gbc);

		gbc.gridy = 3;
		gbc.insets = new Insets(inset * 2, inset, inset, inset);
		b = new EnigmaButton("Nouvelle partie");
		b.setComponentUI(bui);
		b.addActionListener(new Redirect(LaunchGameDisplay.CREATE_GAME));
		buttonsComponent.add(b, gbc);

		this.rightBar.add(buttonsComponent);

		filtersComponent.setLayout(new GridBagLayout());
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(inset, inset, inset, inset);
		EnigmaTextArea ta = new EnigmaTextArea();
		ta.setPreferredSize(new Dimension());
		ta.getComponentUI().setAllBackgrounds(grey, grey, grey);
		EnigmaPanel search = new EnigmaPanel();
		search.setLayout(new GridLayout(2, 1));
		EnigmaLabel l = new EnigmaLabel("Rechercher :");
		l.setVerticalAlignment(SwingConstants.BOTTOM);
		ta.setRows(1);
		search.add(l);
		search.add(ta);
		filtersComponent.add(search, gbc);

		gbc.gridy = 2;
		gbc.insets = new Insets(inset, inset, inset, inset);
		bui.setAllSelectedBackgrounds(blue, lighterBlue, lighterBlue);
		bui.setAllSelectedBorders(null, null, null);
		bui.setSelectedCursor(new Cursor(Cursor.HAND_CURSOR));
		EnigmaButton filter1 = new EnigmaButton("Solo");
		EnigmaButton filter2 = new EnigmaButton("Multijoueurs");
		filter1.setComponentUI(bui);
		filter2.setComponentUI(bui);
		CheckBoxManager cbm = new CheckBoxManager();
		cbm.add(filter1);
		cbm.add(filter2);
		cbm.setSelected(filter1);
		cbm.setSelected(filter2);
		EnigmaPanel p = new EnigmaPanel();
		p.setLayout(new FlowLayout());
		p.add(filter1);
		p.add(filter2);
		filtersComponent.add(p, gbc);

		this.rightBar.add(filtersComponent);
	}

	/**
	 * Initialisation du contenu
	 */
	private void initContent() {
		this.content = new EnigmaPanel();
		EnigmaPanel navBar = new EnigmaPanel();
		EnigmaPanel content = new EnigmaPanel();
		JScrollPane scrollContent = new JScrollPane(content);
		scrollContent.setBorder(BorderFactory.createEmptyBorder());
		GridBagConstraints gbc = new GridBagConstraints();

		this.content.setLayout(new GridBagLayout());
		int inset = 10;

		navBar.setLayout(new GridLayout(1, 2));
		navBar.getComponentUI().setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		EnigmaButton launchGame = new EnigmaButton("Lancer une partie");
		launchGame.getComponentUI().setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		launchGame.getComponentUI().setAllBorders(null, null, null);
		launchGame.getComponentUI().setAllForegrounds(Color.BLACK, Color.BLACK, Color.BLACK);
		navBar.add(launchGame);
		EnigmaButton joinGame = new EnigmaButton("Rejoindre une partie");
		joinGame.getComponentUI().setAllBackgrounds(Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY);
		joinGame.getComponentUI().setAllBorders(null, null, null);
		joinGame.getComponentUI().setAllForegrounds(Color.BLACK, Color.BLACK, Color.BLACK);
		joinGame.addActionListener(new Redirect(LaunchGameDisplay.JOIN_GAME));
		navBar.add(joinGame);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.content.add(navBar, gbc);

		content.getComponentUI().setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		content.setLayout(new GridBagLayout());
		this.gameList = new EnigmaPanel();
		// TODO: 01/01/2020 afficher dans gameList, une liste des maps en local
		gbc.insets = new Insets(inset, inset, inset, inset);
		content.add(this.gameList, gbc);

		gbc.gridy = 2;
		gbc.weighty = 15;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.content.add(scrollContent, gbc);
	}

	/**
	 * Rafraichir le contenu
	 */
	@Override
	public void refreshContent() {
		// TODO: 01/01/2020 afficher dans gameList, une liste des maps en local
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
