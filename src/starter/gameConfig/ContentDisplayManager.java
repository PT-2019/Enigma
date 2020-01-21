package starter.gameConfig;

import editor.entity.Player;
import editor.hud.*;
import game.GameConfiguration;

import java.awt.*;
import java.util.HashMap;

public class ContentDisplayManager {

    private final static ContentDisplayManager instance = new ContentDisplayManager();
    private EnigmaPanel panel;
    private CardLayout layout;
    private HashMap<String,DisplayManager> displays;
    private String currentDisplay;

    private ContentDisplayManager(){
        GameConfiguration.getInstance().setMultiPlayer(true);
        GameConfiguration.getInstance().setMaxGamePlayers(GameConfiguration.MAX_PLAYERS);
        GameConfiguration.getInstance().playerJoined(new Player("aszdf"));
        GameConfiguration.getInstance().setMaxGamePlayers(3);
        this.layout = new CardLayout();
        this.panel = new EnigmaPanel();
        this.panel.setLayout(this.layout);
        this.panel.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);

        this.displays = new HashMap<>();
        this.displays.put(LaunchGameDisplay.SELECT_GAME,SelectGameDisplayManager.getInstance());
        this.displays.put(LaunchGameDisplay.JOIN_GAME,JoinGameDisplayManager.getInstance());
        this.displays.put(LaunchGameDisplay.WAIT_PLAYERS,WaitPlayersDisplayManager.getInstance());
        this.displays.put(LaunchGameDisplay.WAIT_PLAYERS_LEADER,WaitPlayersLeaderDisplayManager.getInstance());
        this.displays.put(LaunchGameDisplay.CREATE_GAME,CreateGameDisplayManager.getInstance());

        this.panel.add(SelectGameDisplayManager.getInstance().getContent(),LaunchGameDisplay.SELECT_GAME);
        this.panel.add(JoinGameDisplayManager.getInstance().getContent(),LaunchGameDisplay.JOIN_GAME);
        this.panel.add(WaitPlayersDisplayManager.getInstance().getContent(),LaunchGameDisplay.WAIT_PLAYERS);
        this.panel.add(WaitPlayersLeaderDisplayManager.getInstance().getContent(),LaunchGameDisplay.WAIT_PLAYERS_LEADER);
        this.panel.add(CreateGameDisplayManager.getInstance().getContent(),LaunchGameDisplay.CREATE_GAME);

        this.currentDisplay = LaunchGameDisplay.SELECT_GAME;
    }

    public void showDisplay(String displayName){
        this.layout.show(this.panel,displayName);
        this.displays.get(displayName).refreshAll();
        this.currentDisplay = displayName;
    }

    public static ContentDisplayManager getInstance(){
        return instance;
    }

    public EnigmaPanel getPanel(){
        return this.panel;
    }

    public String getCurrentDisplay(){
        return this.currentDisplay;
    }

    public void refreshCurrentDisplay() {
        this.displays.get(this.currentDisplay).refreshAll();
    }
}
