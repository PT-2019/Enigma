package game.display;

import editor.hud.EnigmaPanel;

class WaitPlayersLeaderDisplayManager {

    private final static WaitPlayersLeaderDisplayManager instance = new WaitPlayersLeaderDisplayManager();
    private EnigmaPanel panel;

    private WaitPlayersLeaderDisplayManager(){

        this.refresh();
    }

    public void refresh(){
    }

    public static WaitPlayersLeaderDisplayManager getInstance(){
        return instance;
    }

    public EnigmaPanel getPanel(){
        return this.panel;
    }
}
