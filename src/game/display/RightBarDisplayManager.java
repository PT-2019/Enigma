package game.display;

import editor.hud.*;

import java.awt.*;

public class RightBarDisplayManager {

    private final static RightBarDisplayManager instance = new RightBarDisplayManager();
    private EnigmaPanel panel;
    private CardLayout layout;

    private RightBarDisplayManager(){
        this.layout = new CardLayout();
        this.panel = new EnigmaPanel();
        this.panel.setLayout(this.layout);

        this.panel.add(SelectGameDisplayManager.getInstance().getRightBar(),LaunchGameDisplay.SELECT_GAME);
        this.panel.add(JoinGameDisplayManager.getInstance().getRightBar(),LaunchGameDisplay.JOIN_GAME);
        this.panel.add(WaitPlayersDisplayManager.getInstance().getRightBar(),LaunchGameDisplay.WAIT_PLAYERS);
        this.panel.add(WaitPlayersLeaderDisplayManager.getInstance().getRightBar(),LaunchGameDisplay.WAIT_PLAYERS_LEADER);
    }

    public void showDisplay(String displayName){
        this.layout.show(this.panel,displayName);
    }

    public static RightBarDisplayManager getInstance(){
        return instance;
    }

    public EnigmaPanel getPanel(){
        return this.panel;
    }
}
