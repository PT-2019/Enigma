package game.hmi;

import api.ui.CustomPanel;

public abstract class Content {
    protected CustomPanel content;

    public final static int NO_PRECISED_STATE = -1;

    public Content(CustomPanel content){
        this.content = content;
    }

    protected abstract void initContent();

    protected abstract void refresh(int state);

    public CustomPanel getContent() {
        return this.content;
    }
}
