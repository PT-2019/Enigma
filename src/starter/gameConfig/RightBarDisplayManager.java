package starter.gameConfig;

import editor.hud.*;

import java.awt.*;
import java.util.HashMap;

public class RightBarDisplayManager {

    private final static RightBarDisplayManager instance = new RightBarDisplayManager();
    private EnigmaPanel panel;
    private CardLayout layout;
    private HashMap<String,DisplayManager> displays;
    private String currentDisplay;

    private RightBarDisplayManager(){
        this.layout = new CardLayout();
        this.panel = new EnigmaPanel();
        this.panel.setLayout(this.layout);

        this.displays = new HashMap<>();
        this.displays.put(LaunchGameDisplay.SELECT_GAME,SelectGameDisplayManager.getInstance());
        this.displays.put(LaunchGameDisplay.JOIN_GAME,JoinGameDisplayManager.getInstance());
        this.displays.put(LaunchGameDisplay.WAIT_PLAYERS,WaitPlayersDisplayManager.getInstance());
        this.displays.put(LaunchGameDisplay.WAIT_PLAYERS_LEADER,WaitPlayersLeaderDisplayManager.getInstance());
        this.displays.put(LaunchGameDisplay.CREATE_GAME,CreateGameDisplayManager.getInstance());

        this.panel.add(SelectGameDisplayManager.getInstance().getRightBar(),LaunchGameDisplay.SELECT_GAME);
        this.panel.add(JoinGameDisplayManager.getInstance().getRightBar(),LaunchGameDisplay.JOIN_GAME);
        this.panel.add(WaitPlayersDisplayManager.getInstance().getRightBar(),LaunchGameDisplay.WAIT_PLAYERS);
        this.panel.add(WaitPlayersLeaderDisplayManager.getInstance().getRightBar(),LaunchGameDisplay.WAIT_PLAYERS_LEADER);
        this.panel.add(CreateGameDisplayManager.getInstance().getRightBar(),LaunchGameDisplay.CREATE_GAME);

        this.currentDisplay = LaunchGameDisplay.SELECT_GAME;
    }

    public void showDisplay(String displayName){
        this.layout.show(this.panel,displayName);
        this.displays.get(displayName).refreshAll();
        this.currentDisplay = displayName;
    }

    public static RightBarDisplayManager getInstance(){
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
