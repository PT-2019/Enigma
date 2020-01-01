package game.display;

import editor.hud.EnigmaPanel;

class SelectGameDisplayManager {

    private final static SelectGameDisplayManager instance = new SelectGameDisplayManager();
    private EnigmaPanel panel;

    private SelectGameDisplayManager(){

        this.refresh();
    }

    public void refresh(){
    }

    public static SelectGameDisplayManager getInstance(){
        return instance;
    }

    public EnigmaPanel getPanel(){
        return this.panel;
    }
}
