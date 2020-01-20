package game;

import editor.entity.Player;

public class UserConfiguration {
    private Player user;
    private final static UserConfiguration instance = new UserConfiguration();

    private UserConfiguration(){
        this.user = new Player("nom pour l'instant");
    }

    public static UserConfiguration getInstance() {
        return instance;
    }

    public Player getUser() {
        return this.user;
    }
}
