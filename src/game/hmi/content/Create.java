package game.hmi.content;

import api.ui.manager.RadioButtonManager;
import api.utils.Utility;
import common.data.GameData;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaPanel;
import common.hud.EnigmaTextArea;
import common.hud.EnigmaTextField;
import common.hud.ui.EnigmaButtonUI;
import data.NeedToBeTranslated;
import data.config.Config;
import data.config.EnigmaUIValues;
import data.config.UserConfiguration;
import game.EnigmaGameLauncher;
import game.hmi.Content;
import game.hmi.listener.action.MapSelectionListener;
import game.hmi.listener.action.MultiOrSoloListener;

import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.io.File;

/**
 * Affichage pour la création de partie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class Create extends Content {
	/**
	 * Etats
	 */
	public final static int SOLO_STATE = 0;
	public final static int MULTI_STATE = 1;
	/**
	 * Instance
	 */
	private final static Create instance = new Create();
	/**
	 * Textes
	 */
	private final static String NAME = NeedToBeTranslated.NAME;
	private final static String MAP = NeedToBeTranslated.MAP;
	private final static String DESCRIPTION = NeedToBeTranslated.DESCRIPTION;
	private final static String NB_PLAYERS = NeedToBeTranslated.NB_PLAYERS;
	private final static String DURATION = NeedToBeTranslated.DURATION;
	private final static String MULTI_PLAYERS = NeedToBeTranslated.MULTI_PLAYERS;
	private final static String YES = NeedToBeTranslated.YES;
	private final static String NO = NeedToBeTranslated.NO;
	private final static String NO_NAME = NeedToBeTranslated.NO_NAME;
	private final static String NAME_ALREADY_EXIST = NeedToBeTranslated.NAME_ALREADY_EXIST;
	private final static String NO_DURATION = NeedToBeTranslated.NO_DURATION;
	private final static String NO_MAP = NeedToBeTranslated.NO_MAP;
	private final static String WRONG_DURATION = NeedToBeTranslated.WRONG_DURATION;
	/**
	 * Pour entrer le nom de la partie
	 */
	private EnigmaTextField name;
	/**
	 * Pour entrer la description
	 */
	private EnigmaTextArea description;
	/**
	 * Gestionnaire du choix si multijoueur ou non
	 */
	private RadioButtonManager multi;
	/**
	 * Gestionnaire du choix du nombre de joueurs
	 */
	private RadioButtonManager players;
	/**
	 * Pour entrer la durée
	 */
	private EnigmaTextField duration;
	/**
	 * Pour selectionner la map
	 */
	private EnigmaLabel map;
	/**
	 * Conteneur des boutons pour le nombre de joueurs
	 */
	private EnigmaPanel playersComponent;
	/**
	 * Style des boutons
	 */
	private EnigmaButtonUI bui;
	/**
	 * Bouton 1 joueur
	 */
	private EnigmaButton p1;
	/**
	 * Bouton 2 joueur
	 */
	private EnigmaButton p2;
	/**
	 * Bouton 3 joueur
	 */
	private EnigmaButton p3;
	/**
	 * Bouton 4 joueur
	 */
	private EnigmaButton p4;
	/**
	 * Bouton oui
	 */
	private EnigmaButton yes;
	/**
	 * Bouton non
	 */
	private EnigmaButton no;
	/**
	 * Etat actuel
	 */
	private int state;

	private Create() {
		super(new EnigmaPanel());
		this.content.setLayout(new GridLayout(6, 2));
		Color red = new Color(200, 0, 0);
		Color blue = new Color(0, 136, 193);

		this.name = new EnigmaTextField();
		this.description = new EnigmaTextArea();
		this.multi = new RadioButtonManager();
		this.players = new RadioButtonManager();
		this.duration = new EnigmaTextField();
		this.map = new EnigmaLabel();
		this.playersComponent = new EnigmaPanel();
		this.p1 = new EnigmaButton("1");
		this.p2 = new EnigmaButton("2");
		this.p3 = new EnigmaButton("3");
		this.p4 = new EnigmaButton("4");
		this.yes = new EnigmaButton(YES);
		this.no = new EnigmaButton(NO);

		this.bui = new EnigmaButtonUI();
		this.bui.setAllBackgrounds(Color.GRAY, blue, blue);
		this.bui.setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN, EnigmaUIValues.ALL_BORDER_HIDDEN, EnigmaUIValues.ALL_BORDER_HIDDEN);
		this.bui.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		this.bui.setAllSelectedBackgrounds(red, red, red);
		this.bui.setAllSelectedShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN, EnigmaUIValues.ALL_BORDER_HIDDEN, EnigmaUIValues.ALL_BORDER_HIDDEN);
		this.bui.setSelectedCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.bui.setAllSelectedForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);

		this.name.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.description.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.map.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.duration.setCursor(new Cursor(Cursor.HAND_CURSOR));

		this.initContent();
		this.refresh(SOLO_STATE);
	}

	/**
	 * Obtenir l'instance
	 *
	 * @return Instance
	 */
	public static Create getInstance() {
		return instance;
	}

	/**
	 * Initialise le contenu
	 * Doit être normalement appelé qu'une fois, dans le constructeur
	 */
	@Override
	public void initContent() {
		int borderSize = 6;
		EnigmaLabel name = new EnigmaLabel(NAME + ":");
		EnigmaLabel description = new EnigmaLabel(DESCRIPTION + ":");
		EnigmaLabel multi = new EnigmaLabel(MULTI_PLAYERS + ":");
		EnigmaLabel players = new EnigmaLabel(NB_PLAYERS + ":");
		EnigmaLabel duration = new EnigmaLabel(DURATION + "(min) :");
		EnigmaLabel map = new EnigmaLabel(MAP + ":");
		EnigmaPanel multiComponent = new EnigmaPanel();
		EnigmaPanel nameComponent = new EnigmaPanel();
		EnigmaPanel descriptionComponent = new EnigmaPanel();
		EnigmaPanel durationComponent = new EnigmaPanel();
		EnigmaPanel namePadding = new EnigmaPanel();
		EnigmaPanel descriptionPadding = new EnigmaPanel();
		EnigmaPanel durationPadding = new EnigmaPanel();

		MultiOrSoloListener mosl = new MultiOrSoloListener();
		this.yes.addActionListener(mosl);
		this.no.addActionListener(mosl);
		this.map.addMouseListener(new MapSelectionListener(this.map));

		namePadding.setLayout(new BorderLayout());
		descriptionPadding.setLayout(new BorderLayout());
		durationPadding.setLayout(new BorderLayout());
		nameComponent.setLayout(new BorderLayout());
		descriptionComponent.setLayout(new BorderLayout());
		durationComponent.setLayout(new BorderLayout());

		this.yes.setComponentUI(this.bui);
		this.no.setComponentUI(this.bui);
		this.p1.setComponentUI(this.bui);
		this.p2.setComponentUI(this.bui);
		this.p3.setComponentUI(this.bui);
		this.p4.setComponentUI(this.bui);

		name.getComponentUI().setAllBackgrounds(Color.GRAY, Color.GRAY, Color.GRAY);
		description.getComponentUI().setAllBackgrounds(Color.GRAY, Color.GRAY, Color.GRAY);
		multi.getComponentUI().setAllBackgrounds(Color.GRAY, Color.GRAY, Color.GRAY);
		players.getComponentUI().setAllBackgrounds(Color.GRAY, Color.GRAY, Color.GRAY);
		duration.getComponentUI().setAllBackgrounds(Color.GRAY, Color.GRAY, Color.GRAY);
		map.getComponentUI().setAllBackgrounds(Color.GRAY, Color.GRAY, Color.GRAY);
		this.name.getComponentUI().setAllBackgrounds(Color.GRAY, Color.GRAY, Color.GRAY);
		this.description.getComponentUI().setAllBackgrounds(Color.GRAY, Color.GRAY, Color.GRAY);
		this.map.getComponentUI().setAllBackgrounds(Color.GRAY, Color.GRAY, Color.GRAY);
		multiComponent.getComponentUI().setAllBackgrounds(Color.GRAY, Color.GRAY, Color.GRAY);
		this.playersComponent.getComponentUI().setAllBackgrounds(Color.GRAY, Color.GRAY, Color.GRAY);
		this.duration.getComponentUI().setAllBackgrounds(Color.GRAY, Color.GRAY, Color.GRAY);

		name.setBorder(BorderFactory.createMatteBorder(borderSize, borderSize, borderSize / 2, borderSize / 2, Color.DARK_GRAY));
		description.setBorder(BorderFactory.createMatteBorder(borderSize / 2, borderSize, borderSize / 2, borderSize / 2, Color.DARK_GRAY));
		map.setBorder(BorderFactory.createMatteBorder(borderSize / 2, borderSize, borderSize / 2, borderSize / 2, Color.DARK_GRAY));
		multi.setBorder(BorderFactory.createMatteBorder(borderSize / 2, borderSize, borderSize / 2, borderSize / 2, Color.DARK_GRAY));
		players.setBorder(BorderFactory.createMatteBorder(borderSize / 2, borderSize, borderSize / 2, borderSize / 2, Color.DARK_GRAY));
		duration.setBorder(BorderFactory.createMatteBorder(borderSize / 2, borderSize, borderSize, borderSize / 2, Color.DARK_GRAY));
		nameComponent.setBorder(BorderFactory.createMatteBorder(borderSize, borderSize / 2, borderSize / 2, borderSize, Color.DARK_GRAY));
		descriptionComponent.setBorder(BorderFactory.createMatteBorder(borderSize / 2, borderSize / 2, borderSize / 2, borderSize, Color.DARK_GRAY));
		this.map.setBorder(BorderFactory.createMatteBorder(borderSize / 2, borderSize / 2, borderSize / 2, borderSize, Color.DARK_GRAY));
		multiComponent.setBorder(BorderFactory.createMatteBorder(borderSize / 2, borderSize / 2, borderSize / 2, borderSize, Color.DARK_GRAY));
		this.playersComponent.setBorder(BorderFactory.createMatteBorder(borderSize / 2, borderSize / 2, borderSize / 2, borderSize, Color.DARK_GRAY));
		durationComponent.setBorder(BorderFactory.createMatteBorder(borderSize / 2, borderSize / 2, borderSize, borderSize, Color.DARK_GRAY));
		namePadding.setBorder(BorderFactory.createMatteBorder(borderSize, borderSize, borderSize, borderSize, Color.GRAY));
		descriptionPadding.setBorder(BorderFactory.createMatteBorder(borderSize, borderSize, borderSize, borderSize, Color.GRAY));
		durationPadding.setBorder(BorderFactory.createMatteBorder(borderSize, borderSize, borderSize, borderSize, Color.GRAY));

		this.p1.setBorderPainted(true);
		this.p1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.DARK_GRAY));
		this.p3.setBorderPainted(true);
		this.p3.setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, borderSize, Color.DARK_GRAY));
		this.no.setBorderPainted(true);
		this.no.setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderSize, Color.DARK_GRAY));

		multiComponent.setLayout(new GridLayout(1, 2));
		multiComponent.add(this.no);
		multiComponent.add(this.yes);

		this.multi.add(this.yes);
		this.multi.add(this.no);
		this.multi.setSelected(this.no);
		this.players.add(this.p1);
		this.players.add(this.p2);
		this.players.add(this.p3);
		this.players.add(this.p4);
		this.players.setSelected(this.p1);

		namePadding.add(this.name, BorderLayout.CENTER);
		descriptionPadding.add(this.description, BorderLayout.CENTER);
		durationPadding.add(this.duration, BorderLayout.CENTER);

		nameComponent.add(namePadding, BorderLayout.CENTER);
		descriptionComponent.add(descriptionPadding, BorderLayout.CENTER);
		durationComponent.add(durationPadding, BorderLayout.CENTER);

		this.content.add(name);
		this.content.add(nameComponent);
		this.content.add(description);
		this.content.add(descriptionComponent);
		this.content.add(map);
		this.content.add(this.map);
		this.content.add(multi);
		this.content.add(multiComponent);
		this.content.add(players);
		this.content.add(this.playersComponent);
		this.content.add(duration);
		this.content.add(durationComponent);
	}

	/**
	 * Rafraichi l'affichage
	 *
	 * @param state Etat
	 */
	@Override
	public void refresh(int state) {
		this.playersComponent.removeAll();

		switch (state) {
			default:
				state = SOLO_STATE;
			case SOLO_STATE:
				this.playersComponent.setLayout(new GridLayout(1, 1));
				this.playersComponent.add(this.p1);
				this.players.setSelected(this.p1);
				this.multi.setSelected(this.no);
				break;
			case MULTI_STATE:
				this.playersComponent.setLayout(new GridLayout(1, 4));
				this.players.setSelected(this.p2);
				this.multi.setSelected(this.yes);
				this.playersComponent.add(this.p2);
				this.playersComponent.add(this.p3);
				this.playersComponent.add(this.p4);
				break;
		}

		this.state = state;
		this.content.revalidate();
		this.playersComponent.repaint();
	}

	/**
	 * Obtenir l'état actuel
	 *
	 * @return Etat actuel
	 */
	public int getState() {
		return this.state;
	}

	/**
	 * Vérifie les informations entrées
	 * Se charge de prévenir l'utilisateur de ses erreurs
	 *
	 * @return true si les information rentrées sont justes, false sinon
	 */
	public boolean verify() {

		if (Utility.normalize(this.name.getText()).equals("")) {
			EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), NO_NAME);
			return false;
		}

		File file = new File(Config.GAME_DATA_FOLDER + this.name.getText() + Config.DATA_EXTENSION);
		if (file.exists()) {
			EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), NAME_ALREADY_EXIST);
			return false;
		}

		if (this.map.getText().equals("")) {
			EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), NO_MAP);
			return false;
		}

		if (Utility.normalize(this.duration.getText()).equals("")) {
			EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), NO_DURATION);
			return false;
		} else {
			try {
				int i = Integer.parseInt(this.duration.getText().trim());

				if(i < 1 || i > 999){
					EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), WRONG_DURATION);
					return false;
				}

			} catch (NumberFormatException e) {
				EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), WRONG_DURATION);
				return false;
			}
		}

		return true;
	}

	/**
	 * Obtenir les informations rentrées sous forme de données de partie
	 *
	 * @return Données
	 */
	public GameData getData() {
		return new GameData(Utility.normalize(this.name.getText()),
				UserConfiguration.getInstance().getData().getName(),
				this.map.getText(),
				this.description.getText(),
				Integer.parseInt(Utility.normalize(this.duration.getText())),
				Integer.parseInt(this.players.getSelectedButton().getText())
		);
	}
}
