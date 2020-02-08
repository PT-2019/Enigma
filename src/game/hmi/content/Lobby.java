package game.hmi.content;

import common.hud.EnigmaPanel;
import game.hmi.Content;

public class Lobby extends Content {
    private final static Lobby instance = new Lobby();

    private Lobby() {
        super(new EnigmaPanel());
    }

    @Override
    public void initContent() {

    }

    @Override
    public void refresh(int state) {

    }

    public static Lobby getInstance(){
        return instance;
    }
}
