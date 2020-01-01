package game.display;

import editor.hud.EnigmaPanel;

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
