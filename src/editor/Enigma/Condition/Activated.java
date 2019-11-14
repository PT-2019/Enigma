package editor.Enigma.Condition;

import editor.Entity.Item.Activatable;
import editor.Entity.Player.Player;

public class Activated extends Condition {

    public Activated(Activatable a){
        super(a);
    }

    @Override
    public boolean verify(Player p) {
        Activatable a = (Activatable)this.entity;
        return a.isActivated();
    }
}
