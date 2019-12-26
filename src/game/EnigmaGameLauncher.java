package game;

import api.Application;
import api.enums.EnigmaScreens;
import api.utils.LoadGameLibgdxApplication;
import editor.enigma.Enigma;
import editor.entity.player.Player;
import editor.hud.*;
import editor.hud.Window;
import editor.hud.managers.RadioButtonManager;
import editor.hud.ui.EnigmaLabelUI;
import editor.hud.ui.EnigmaPanelUI;
import editor.hud.ui.EnigmaUIValues;
import starter.AppClosingManager;

import java.awt.*;

/**
 * Lance le jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 25/12/2019
 * @since 4.0 25/12/2019
 */
public class EnigmaGameLauncher implements Application {

	private static EnigmaGameLauncher launcher;
	private Window window;
	private CardLayout configurationLayout;
	private CardLayout gameLayout;
	
	public final static String SOLO = "solo";
	public final static String MULTI = "multi";
	public final static String WAIT_PLAYERS = "wait players";
	public final static String CREATE_GAME = "create game";
	public final static String CONFIGURATION = "configuration";
	public final static String GAME = "game";

	/**
	 * Crée le lanceur du jeu
	 *
	 * @since 4.0
	 */
	private EnigmaGameLauncher() {
		this.window = new Window();
		this.window.setWindowBackground(Color.DARK_GRAY);
		this.window.addWindowListener(new AppClosingManager());

		EnigmaPanel gamePanel = new EnigmaPanel();
		this.configurationLayout = new CardLayout();
		this.gameLayout = new CardLayout();

		this.window.getContentSpace().setLayout(this.gameLayout);

		LoadGameLibgdxApplication.load(gamePanel, window);

		EnigmaPanel configurationPanel = new EnigmaPanel();
		configurationPanel.setLayout(new GridBagLayout());
		EnigmaPanel navBar = new EnigmaPanel();
		EnigmaPanel configContent = new EnigmaPanel();
		configContent.setLayout(this.configurationLayout);

		configContent.add(SoloDisplayManager.getInstance().getPanel(),SOLO);
		configContent.add(MultiDisplayManager.getInstance().getPanel(),MULTI);
		configContent.add(CreateGameDisplayManager.getInstance().getPanel(), CREATE_GAME);
		configContent.add(WaitPlayersDisplayManager.getInstance().getPanel(),WAIT_PLAYERS);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		configurationPanel.add(navBar,gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 4;
		configurationPanel.add(configContent,gbc);

		this.window.getContentSpace().add(configurationPanel,CONFIGURATION);
		this.window.getContentSpace().add(gamePanel,GAME);

		GameConfiguration.getInstance().setOwner(new Player("utilisateur qui à lancé la partie"));
	}

	/**
	 * Retourne l'instance unique du lanceur du jeu
	 *
	 * @return l'instance unique du lanceur du jeu
	 * @since 4.0
	 */
	public static EnigmaGameLauncher getInstance() {
		if (launcher == null) {
			launcher = new EnigmaGameLauncher();
		}
		return launcher;
	}

	/**
	 * Lance le jeu dans une nouvelle fenêtre
	 *
	 * @since 4.0
	 */
	@Override
	public void start() {
		EnigmaGame.setFirstScreen(EnigmaScreens.GAME.name());
		this.showDisplay(SOLO);
		this.window.setVisible(true);
	}

	/**
	 * Retourne la fenêtre dans laquelle tourne le jeu
	 *
	 * @return la fenêtre dans laquelle tourne le jeu
	 * @since 4.0
	 */
	public Window getWindow() {
		return window;
	}

	/**
	 * Affiche l'écran désigné par son nom
	 * @param displayName Nom de l'écran
	 * @since 4.0
	 */
	public void showDisplay(String displayName){
		switch (displayName){
			case SOLO:
				SoloDisplayManager.getInstance().refresh();
				this.configurationLayout.show(this.window.getContentSpace(),displayName);
				break;
			case MULTI:
				MultiDisplayManager.getInstance().refresh();
				this.configurationLayout.show(this.window.getContentSpace(),displayName);
				break;
			case CREATE_GAME:
				CreateGameDisplayManager.getInstance().refresh();
				this.configurationLayout.show(this.window.getContentSpace(),displayName);
				break;
			case WAIT_PLAYERS:
				WaitPlayersDisplayManager.getInstance().refresh();
				this.configurationLayout.show(this.window.getContentSpace(),displayName);
				break;
			case CONFIGURATION:
				this.gameLayout.show(this.window.getContentSpace(),displayName);
				break;
			case GAME:
				this.gameLayout.show(this.window.getContentSpace(),displayName);
				break;
		}
	}
}

class SoloDisplayManager {

	private final static SoloDisplayManager instance = new SoloDisplayManager();
	private EnigmaPanel panel;

	private SoloDisplayManager(){

		this.refresh();
	}

	public void refresh(){
	}

	public static SoloDisplayManager getInstance(){
		return instance;
	}

	public EnigmaPanel getPanel(){
		return this.panel;
	}
}

class MultiDisplayManager {

	private final static MultiDisplayManager instance = new MultiDisplayManager();
	private EnigmaPanel panel;

	private MultiDisplayManager(){

		this.refresh();
	}

	public void refresh(){
	}

	public static MultiDisplayManager getInstance(){
		return instance;
	}

	public EnigmaPanel getPanel(){
		return this.panel;
	}
}

class WaitPlayersDisplayManager {

	private final static WaitPlayersDisplayManager instance = new WaitPlayersDisplayManager();
	private EnigmaPanel panel;

	private WaitPlayersDisplayManager(){

		this.refresh();
	}

	public void refresh(){
	}

	public static WaitPlayersDisplayManager getInstance(){
		return instance;
	}

	public EnigmaPanel getPanel(){
		return this.panel;
	}
}

class CreateGameDisplayManager {

	private final static CreateGameDisplayManager instance = new CreateGameDisplayManager();
	private EnigmaButton[] numberPlayerOptions;
	private EnigmaButton[] multiOptions;
	private EnigmaPanel panel;
	private EnigmaTextArea nameAnswer;
	private EnigmaTextArea durationAnswer;
	private EnigmaLabel mapAnswer;
	private RadioButtonManager numberPlayerRBM;
	private RadioButtonManager multiRBM;

	private CreateGameDisplayManager(){
		this.panel = new EnigmaPanel();
		this.panel.setLayout(new GridLayout(6,1));
		GridLayout optionLayout = new GridLayout(1,2);

		boolean[] optionBorders = new boolean[4];
		optionBorders[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		EnigmaPanelUI optionUI = new EnigmaPanelUI();
		optionUI.setAllBorders(Color.WHITE,Color.WHITE,Color.WHITE);
		optionUI.setAllShowedBorders(optionBorders,optionBorders,optionBorders);

		boolean[] titleBorders = new boolean[4];
		titleBorders[EnigmaUIValues.RIGHT_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		EnigmaLabelUI optionTitleUI = new EnigmaLabelUI();
		optionTitleUI.setAllBorders(Color.WHITE,Color.WHITE,Color.WHITE);
		optionTitleUI.setAllShowedBorders(titleBorders,titleBorders,titleBorders);

		EnigmaPanel gameName = new EnigmaPanel();
		gameName.setPanelUI(optionUI);
		gameName.setLayout(optionLayout);
		EnigmaLabel nameTitle = new EnigmaLabel("Nom de la partie (max 50 charactères) :");
		nameTitle.setLabelUI(optionTitleUI);
		gameName.add(nameTitle);
		this.nameAnswer = new EnigmaTextArea();
		gameName.add(this.nameAnswer.setScrollBar());
		this.panel.add(gameName);

		EnigmaPanel multi = new EnigmaPanel();
		multi.setPanelUI(optionUI);
		multi.setLayout(optionLayout);
		EnigmaLabel multiTitle = new EnigmaLabel("Multijoueurs :");
		multiTitle.setLabelUI(optionTitleUI);
		multi.add(multiTitle);
		EnigmaPanel multiAnswer = new EnigmaPanel();
		multiAnswer.setLayout(new FlowLayout());
		this.multiRBM = new RadioButtonManager();
		this.multiOptions = new EnigmaButton[2];
		this.multiOptions[0] = new EnigmaButton("Oui");
		this.multiOptions[1] = new EnigmaButton("Non");
		for(EnigmaButton b: this.multiOptions){
			this.multiRBM.add(b);
			multiAnswer.add(b);
		}
		multi.add(multiAnswer);
		this.panel.add(multi);

		EnigmaPanel nbPlayers = new EnigmaPanel();
		nbPlayers.setPanelUI(optionUI);
		nbPlayers.setLayout(optionLayout);
		EnigmaLabel nbTitle = new EnigmaLabel("Nombre de joueurs :");
		nbTitle.setLabelUI(optionTitleUI);
		nbPlayers.add(nbTitle);
		EnigmaPanel nbAnswer = new EnigmaPanel();
		nbAnswer.setLayout(new FlowLayout());
		this.numberPlayerOptions = new EnigmaButton[3];
		this.numberPlayerRBM = new RadioButtonManager();
		for(int i = 0; i < this.numberPlayerOptions.length; i++){
			this.numberPlayerOptions[i] = new EnigmaButton(Integer.toString(i + 2));
			this.numberPlayerRBM.add(this.numberPlayerOptions[i]);
			nbAnswer.add(this.numberPlayerOptions[i]);
		}
		nbPlayers.add(nbAnswer);
		this.panel.add(nbPlayers);

		EnigmaPanel duration = new EnigmaPanel();
		duration.setPanelUI(optionUI);
		duration.setLayout(optionLayout);
		EnigmaLabel durationTitle = new EnigmaLabel("Durée de la partie (en minutes) :");
		durationTitle.setLabelUI(optionTitleUI);
		duration.add(durationTitle);
		this.durationAnswer = new EnigmaTextArea();
		duration.add(this.durationAnswer.setScrollBar());
		this.panel.add(duration);

		EnigmaPanel map = new EnigmaPanel();
		map.setPanelUI(optionUI);
		map.setLayout(optionLayout);
		EnigmaLabel mapTitle = new EnigmaLabel("Choisir une map :");
		mapTitle.setLabelUI(optionTitleUI);
		map.add(mapTitle);
		this.mapAnswer = new EnigmaLabel();
		map.add(this.mapAnswer);
		this.panel.add(map);

		EnigmaButton play = new EnigmaButton("Commencer");
		play.getButtonUI().setAllBackgrounds(new Color(0,150,0),new Color(0,150,0),new Color(0,100,0));
		play.getButtonUI().setAllBorders(null,null,null);
		play.getButtonUI().setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);
		this.panel.add(play);

		this.refresh();
	}

	public void refresh(){
		GameConfiguration gameConfig = GameConfiguration.getInstance();
		this.nameAnswer.setText(gameConfig.getGameName());
		this.mapAnswer.setText(gameConfig.getMap());
		this.durationAnswer.setText(Double.toString(gameConfig.getDuration()).replace(".",":"));
		if(gameConfig.getPlayerNumber() > 1) this.numberPlayerRBM.setSelected(this.numberPlayerOptions[gameConfig.getPlayerNumber() - 2]);
		if(gameConfig.isMultiPlayer()) this.multiRBM.setSelected(this.multiOptions[0]);
		else {
			this.multiRBM.setSelected(this.multiOptions[1]);
		}
	}

	public static CreateGameDisplayManager getInstance(){
		return instance;
	}

	public EnigmaPanel getPanel(){
		return this.panel;
	}
}
