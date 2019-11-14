package editor.Enigma.Condition;

import editor.Entity.Interface.Entity;
import editor.Entity.Player.Player;

public abstract class Condition {

    protected Entity entity;

    public Condition(Entity e){
        this.entity = e;
    }

    public abstract boolean verify(Player p);
}
