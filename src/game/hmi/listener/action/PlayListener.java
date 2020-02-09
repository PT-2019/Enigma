package game.hmi.listener.action;

import common.data.GameData;
import data.config.GameConfiguration;
import data.config.UserConfiguration;
import game.hmi.ActionBar;
import game.hmi.ContentManager;
import game.hmi.MHIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayListener implements ActionListener {
    private GameData game;

    public PlayListener(GameData game){
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        GameConfiguration gameConfig = GameConfiguration.getInstance();
        gameConfig.setName(this.game.getName());
        gameConfig.setOwner(UserConfiguration.getInstance().getData().getName());
        gameConfig.setMap(this.game.getMapName());
        gameConfig.setMultiPlayer(this.game.isMultiPlayer());
        gameConfig.setMaxGamePlayers(this.game.getMaxPlayers());
        gameConfig.setDescription(this.game.getDescription());
        gameConfig.setDuration(this.game.getDuration());

        if(this.game.isMultiPlayer() && !(ContentManager.getInstance().getState() == ContentManager.LOBBY_STATE)){
            ContentManager.getInstance().refresh(ContentManager.LOBBY_STATE);
            ActionBar.getInstance().refresh(ActionBar.QUIT_AND_LAUNCH_STATE);
        }

        if(ContentManager.getInstance().getState() == ContentManager.LOBBY_STATE)
            MHIManager.getInstance().refresh(MHIManager.GAME_STATE);
    }
}
