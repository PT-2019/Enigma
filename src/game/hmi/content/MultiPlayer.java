package game.hmi.content;

import common.hud.EnigmaPanel;
import game.hmi.Content;

public class MultiPlayer extends Content {
    private final static MultiPlayer instance = new MultiPlayer();

    private MultiPlayer() {
        super(new EnigmaPanel());
    }

    @Override
    public void initContent() {

    }

    @Override
    public void refresh(int state) {

    }

    public static MultiPlayer getInstance(){
        return instance;
    }
}
