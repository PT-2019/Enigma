package game.hmi.content;

import common.data.GameData;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.hud.EnigmaTextField;
import common.hud.ui.EnigmaButtonUI;
import common.save.DataSave;
import common.utils.EnigmaUtility;
import common.utils.Logger;
import data.NeedToBeTranslated;
import data.config.Config;
import data.config.EnigmaUIValues;
import game.hmi.Content;
import game.hmi.listener.action.DeleteListener;
import game.hmi.listener.action.ExportListener;
import game.hmi.listener.action.JoinListener;
import game.hmi.listener.action.MoreListener;
import game.hmi.listener.action.PlayListener;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Affichage des parties multijoueur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class MultiPlayer extends Content {
	/**
	 * Instance
	 */
	private final static MultiPlayer instance = new MultiPlayer();
	/**
	 * Textes
	 */
	private final static String DELETE = NeedToBeTranslated.DELETE;
	private final static String PLAY = NeedToBeTranslated.PLAY;
	private final static String NAME = NeedToBeTranslated.NAME;
	private final static String AUTHOR = NeedToBeTranslated.AUTHOR;
	private final static String MAP = NeedToBeTranslated.MAP;
	private final static String DESCRIPTION = NeedToBeTranslated.DESCRIPTION;
	private final static String NB_PLAYERS = NeedToBeTranslated.NB_PLAYERS;
	private final static String DURATION = NeedToBeTranslated.DURATION;
	private final static String ENTER_IP = NeedToBeTranslated.ENTER_IP;
	private final static String YOUR_GAMES = NeedToBeTranslated.YOUR_GAMES;
	private final static String FIND = NeedToBeTranslated.FIND;
	private final static String JOIN = NeedToBeTranslated.JOIN;
	private final static String MORE = NeedToBeTranslated.MORE;
	private final static String EXPORT = NeedToBeTranslated.EXPORT;
	/**
	 * Conteneur de l'affichage
	 */
	private EnigmaPanel contentComponent;
	/**
	 * Conteneur de la liste des parties
	 */
	private EnigmaPanel listComponent;

	private MultiPlayer() {
		super(new EnigmaPanel());
		int borderSize = 20;

		this.content.setLayout(new BorderLayout());
		this.listComponent = new EnigmaPanel();
		this.contentComponent = new EnigmaPanel();
		this.contentComponent.setLayout(new GridBagLayout());

		this.contentComponent.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));

		this.initContent();
		this.refresh(NO_PRECISED_STATE);
	}

	/**
	 * Obtenir l'instance
	 *
	 * @return Instance
	 */
	public static MultiPlayer getInstance() {
		return instance;
	}

	/**
	 * Initialise le contenu
	 * Doit être normalement appelé qu'une fois, dans le constructeur
	 */
	@Override
	public void initContent() {
		Color blue = new Color(0, 136, 193);
		int borderSize = 1;
		int borderSize2 = 6;
		int inset = 20;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.fill = GridBagConstraints.BOTH;

		EnigmaLabel find = new EnigmaLabel(FIND);
		EnigmaLabel games = new EnigmaLabel(YOUR_GAMES);
		EnigmaPanel search = new EnigmaPanel();
		EnigmaTextField field = new EnigmaTextField();
		EnigmaPanel fieldPadding = new EnigmaPanel();
		EnigmaLabel ip = new EnigmaLabel(ENTER_IP + ":");
		EnigmaButton join = new EnigmaButton(JOIN);
		JScrollPane scroll = new JScrollPane(this.listComponent);
		scroll.setBorder(BorderFactory.createEmptyBorder());

		fieldPadding.setLayout(new BorderLayout());
		fieldPadding.setBorder(BorderFactory.createMatteBorder(0, borderSize2, 0, borderSize2, Color.GRAY));
		field.getComponentUI().setAllBackgrounds(Color.GRAY, Color.GRAY, Color.GRAY);
		field.setCursor(new Cursor(Cursor.HAND_CURSOR));
		fieldPadding.add(field, BorderLayout.CENTER);

		ip.setHorizontalAlignment(SwingConstants.LEFT);
		search.setLayout(new GridBagLayout());
		search.setPreferredSize(new Dimension(search.getPreferredSize().width, 80));
		find.setBorder(BorderFactory.createMatteBorder(0, 0, borderSize, 0, Color.WHITE));
		games.setBorder(BorderFactory.createMatteBorder(0, 0, borderSize, 0, Color.WHITE));
		join.getComponentUI().setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN, EnigmaUIValues.ALL_BORDER_HIDDEN, EnigmaUIValues.ALL_BORDER_HIDDEN);
		join.getComponentUI().setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		join.getComponentUI().setAllBackgrounds(Color.GRAY, blue, blue);
		join.setBorderPainted(true);
		join.setBorder(BorderFactory.createMatteBorder(0, borderSize * 2, 0, 0, Color.DARK_GRAY));
		join.addActionListener(new JoinListener(field));

		gbc2.gridx = 1;
		gbc2.gridy = 1;
		gbc2.gridwidth = 1;
		gbc2.gridheight = 1;
		gbc2.weightx = 1;
		gbc2.weighty = 1;
		search.add(ip, gbc2);
		gbc2.gridx = 1;
		gbc2.gridy = 2;
		gbc2.gridwidth = 1;
		gbc2.gridheight = 1;
		gbc2.weightx = 4;
		gbc2.weighty = 1;
		search.add(fieldPadding, gbc2);
		gbc2.gridx = 2;
		gbc2.gridy = 2;
		gbc2.gridwidth = 1;
		gbc2.gridheight = 1;
		gbc2.weightx = 1;
		gbc2.weighty = 1;
		search.add(join, gbc2);

		gbc.gridy = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.contentComponent.add(find, gbc);
		gbc.gridy = 2;
		gbc.weighty = 1;
		gbc.insets = new Insets(0, 0, inset, 0);
		this.contentComponent.add(search, gbc);
		gbc.gridy = 3;
		gbc.weighty = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.contentComponent.add(games, gbc);
		gbc.gridy = 4;
		gbc.weighty = 10;
		gbc.insets = new Insets(0, 0, inset, 0);
		this.contentComponent.add(scroll, gbc);

		this.content.add(this.contentComponent, BorderLayout.CENTER);
	}

	/**
	 * Rafraichi l'affichage
	 *
	 * @param state Etat
	 */
	@Override
	public void refresh(int state) {
		Color blue = new Color(0, 136, 193);
		Color green = new Color(90, 191, 17);
		Color red = new Color(193, 7, 0);
		ArrayList<String> games = EnigmaUtility.getAllGameName();
		int size = 4;
		int borderSize = 5;
		int borderSize2 = 2;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1;

		if (games.size() > size)
			size = games.size();

		this.listComponent.removeAll();
		this.listComponent.setLayout(new GridLayout(size, 1));

		EnigmaButtonUI bui = new EnigmaButtonUI();
		bui.setAllBackgrounds(Color.GRAY, Color.GRAY, Color.GRAY);
		bui.setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN, EnigmaUIValues.ALL_BORDER_HIDDEN, EnigmaUIValues.ALL_BORDER_HIDDEN);
		bui.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);

		for (String game : games) {
			GameData data;
			try {
				data = DataSave.readGameData(Config.GAME_DATA_FOLDER + game + Config.DATA_EXTENSION);
			} catch (IOException e) {
				Logger.printError("Solo.java", "read game data error : " + e.getMessage());
				continue;
			}

			if (data.isMultiPlayer()) {

				EnigmaPanel element = new EnigmaPanel();
				EnigmaPanel eTexts = new EnigmaPanel();
				EnigmaPanel eValues = new EnigmaPanel();
				EnigmaPanel eButtons = new EnigmaPanel();
				EnigmaPanel eMainButtons = new EnigmaPanel();
				EnigmaPanel eSecondButtons = new EnigmaPanel();
				EnigmaButton play = new EnigmaButton(PLAY);
				EnigmaButton delete = new EnigmaButton(DELETE);
				EnigmaButton export = new EnigmaButton(EXPORT);
				EnigmaButton more = new EnigmaButton(MORE);
				EnigmaLabel name = new EnigmaLabel(NAME + ": " + data.getName());
				EnigmaLabel description = new EnigmaLabel(DESCRIPTION + ": " + data.getDescription());
				EnigmaLabel author = new EnigmaLabel(AUTHOR + ": " + data.getAuthor());
				EnigmaLabel map = new EnigmaLabel(MAP + ": " + data.getMapName());
				EnigmaLabel nbPlayers = new EnigmaLabel(NB_PLAYERS + ": " + data.getMaxPlayers());
				EnigmaLabel duration = new EnigmaLabel(DURATION + ": " + data.getDuration() + " min");

				element.setPreferredSize(new Dimension(element.getPreferredSize().width, 130));

				play.addActionListener(new PlayListener(data));
				delete.addActionListener(new DeleteListener(data));
				more.addActionListener(new MoreListener(new SeeMore(data)));
				export.addActionListener(new ExportListener(data));

				element.setLayout(new GridLayout(1, 3));
				eTexts.setLayout(new GridLayout(3, 1));
				eValues.setLayout(new GridLayout(3, 1));
				eButtons.setLayout(new GridBagLayout());
				eMainButtons.setLayout(new GridLayout(2, 1));
				eSecondButtons.setLayout(new GridLayout(2, 1));

				eValues.setBorder(BorderFactory.createMatteBorder(0, borderSize2, 0, 0, Color.DARK_GRAY));
				eButtons.setBorder(BorderFactory.createMatteBorder(0, borderSize2, 0, 0, Color.DARK_GRAY));

				eMainButtons.add(play);
				eMainButtons.add(delete);
				eSecondButtons.add(more);
				eSecondButtons.add(export);
				gbc.gridx = 1;
				gbc.weightx = 5;
				eButtons.add(eMainButtons, gbc);
				gbc.gridx = 2;
				gbc.weightx = 1;
				eButtons.add(eSecondButtons, gbc);

				eTexts.add(name);
				eTexts.add(description);
				eTexts.add(author);
				eValues.add(map);
				eValues.add(nbPlayers);
				eValues.add(duration);
				element.add(eTexts);
				element.add(eValues);
				element.add(eButtons);

				name.setHorizontalAlignment(SwingConstants.LEFT);
				description.setHorizontalAlignment(SwingConstants.LEFT);
				author.setHorizontalAlignment(SwingConstants.LEFT);
				map.setHorizontalAlignment(SwingConstants.LEFT);
				duration.setHorizontalAlignment(SwingConstants.LEFT);
				nbPlayers.setHorizontalAlignment(SwingConstants.LEFT);

				element.setBorder(BorderFactory.createMatteBorder(borderSize, borderSize, borderSize, borderSize, Color.DARK_GRAY));
				element.getComponentUI().setAllBackgrounds(Color.GRAY);

				name.getComponentUI().setAllBackgrounds(Color.GRAY);
				description.getComponentUI().setAllBackgrounds(Color.GRAY);
				author.getComponentUI().setAllBackgrounds(Color.GRAY);
				map.getComponentUI().setAllBackgrounds(Color.GRAY);
				duration.getComponentUI().setAllBackgrounds(Color.GRAY);
				nbPlayers.getComponentUI().setAllBackgrounds(Color.GRAY);

				play.setComponentUI(bui);
				play.setBorderPainted(true);
				play.getComponentUI().setHoveredBackground(green);
				play.getComponentUI().setPressedBackground(green);
				delete.setComponentUI(bui);
				delete.setBorderPainted(true);
				delete.getComponentUI().setHoveredBackground(red);
				delete.getComponentUI().setPressedBackground(red);
				more.setComponentUI(bui);
				more.setBorderPainted(true);
				more.getComponentUI().setHoveredBackground(blue);
				more.getComponentUI().setPressedBackground(blue);
				export.setComponentUI(bui);
				export.setBorderPainted(true);
				export.getComponentUI().setHoveredBackground(blue);
				export.getComponentUI().setPressedBackground(blue);

				name.setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.GRAY));
				description.setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.GRAY));
				author.setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.GRAY));
				map.setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.GRAY));
				nbPlayers.setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.GRAY));
				duration.setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.GRAY));
				play.setBorder(BorderFactory.createMatteBorder(0, 0, borderSize2 / 2, 0, Color.DARK_GRAY));
				delete.setBorder(BorderFactory.createMatteBorder(borderSize2 / 2, 0, 0, 0, Color.DARK_GRAY));
				more.setBorder(BorderFactory.createMatteBorder(0, borderSize2, borderSize2 / 2, 0, Color.DARK_GRAY));
				export.setBorder(BorderFactory.createMatteBorder(borderSize2 / 2, borderSize2, 0, 0, Color.DARK_GRAY));

				this.listComponent.add(element);
			}
		}

		this.content.revalidate();
		this.listComponent.repaint();
	}
}
