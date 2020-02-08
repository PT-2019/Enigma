package game.hmi;

import common.hud.EnigmaPanel;
import game.hmi.content.Create;
import game.hmi.content.Lobby;
import game.hmi.content.MultiPlayer;
import game.hmi.content.Solo;

import java.awt.*;

public class ContentManager extends Content {
    private final static ContentManager instance = new ContentManager();
    private CardLayout layout;

    /**
     * Etats
     */
    public final static int SOLO_STATE = 0;
    public final static int MULTI_STATE = 1;
    public final static int CREATE_STATE = 2;
    public final static int LOBBY_STATE = 3;

    private ContentManager() {
        super(new EnigmaPanel());

        this.layout = new CardLayout();

        this.initContent();
        this.refresh(NO_PRECISED_STATE);
    }

    @Override
    public void initContent() {
        this.content.setLayout(this.layout);
        this.content.add(Solo.getInstance().getContent(),String.valueOf(SOLO_STATE));
        this.content.add(MultiPlayer.getInstance().getContent(),String.valueOf(MULTI_STATE));
        this.content.add(Create.getInstance().getContent(),String.valueOf(CREATE_STATE));
        this.content.add(Lobby.getInstance().getContent(),String.valueOf(LOBBY_STATE));
    }

    @Override
    public void refresh(int state) {
        if (state > NO_PRECISED_STATE)
            this.layout.show(this.content, String.valueOf(state));

        switch (state){
            case SOLO_STATE:
                Solo.getInstance().refresh(NO_PRECISED_STATE);
                break;
            case MULTI_STATE:
                MultiPlayer.getInstance().refresh(NO_PRECISED_STATE);
                break;
            case CREATE_STATE:
                Create.getInstance().refresh(NO_PRECISED_STATE);
                break;
            case LOBBY_STATE:
                Lobby.getInstance().refresh(NO_PRECISED_STATE);
                break;
        }

        this.content.revalidate();
    }

    public static ContentManager getInstance(){
        return instance;
    }
}
