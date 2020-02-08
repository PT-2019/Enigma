package game.hmi.content;

import common.hud.EnigmaPanel;
import game.hmi.Content;

public class Create extends Content {
    private final static Create instance = new Create();

    private Create() {
        super(new EnigmaPanel());
    }

    @Override
    public void initContent() {

    }

    @Override
    public void refresh(int state) {

    }

    public static Create getInstance(){
        return instance;
    }
}
