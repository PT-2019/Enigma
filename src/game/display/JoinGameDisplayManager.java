package game.display;

import editor.hud.EnigmaPanel;

class JoinGameDisplayManager {

    private final static JoinGameDisplayManager instance = new JoinGameDisplayManager();
    private EnigmaPanel panel;

    private JoinGameDisplayManager(){

        this.refresh();
    }

    public void refresh(){
    }

    public static JoinGameDisplayManager getInstance(){
        return instance;
    }

    public EnigmaPanel getPanel(){
        return this.panel;
    }
}
