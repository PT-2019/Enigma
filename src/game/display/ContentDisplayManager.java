package game.display;

import editor.entity.Player;
import editor.hud.*;
import game.GameConfiguration;

import java.awt.*;

public class ContentDisplayManager {

    private final static ContentDisplayManager instance = new ContentDisplayManager();
    private EnigmaPanel panel;
    private CardLayout layout;

    private ContentDisplayManager(){
        GameConfiguration.getInstance().setMultiPlayer(true);
        GameConfiguration.getInstance().setMaxGamePlayers(GameConfiguration.MAX_PLAYERS);
        GameConfiguration.getInstance().playerJoined(new Player("aszdf"));
        GameConfiguration.getInstance().setMaxGamePlayers(3);
        this.layout = new CardLayout();
        this.panel = new EnigmaPanel();
        this.panel.setLayout(this.layout);
        this.panel.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);

        this.panel.add(SelectGameDisplayManager.getInstance().getContent(),LaunchGameDisplay.SELECT_GAME);
        this.panel.add(JoinGameDisplayManager.getInstance().getContent(),LaunchGameDisplay.JOIN_GAME);
        this.panel.add(WaitPlayersDisplayManager.getInstance().getContent(),LaunchGameDisplay.WAIT_PLAYERS);
        this.panel.add(WaitPlayersLeaderDisplayManager.getInstance().getContent(),LaunchGameDisplay.WAIT_PLAYERS_LEADER);
    }

    public void showDisplay(String displayName){
        this.layout.show(this.panel,displayName);
    }

    public static ContentDisplayManager getInstance(){
        return instance;
    }

    public EnigmaPanel getPanel(){
        return this.panel;
    }
}
