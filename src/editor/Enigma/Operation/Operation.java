package editor.Enigma.Operation;

import editor.Entity.Interface.Entity;
import editor.Entity.Player.Player;

public abstract class Operation {

    protected Entity entity;

    public Operation(Entity e){
        this.entity = e;
    }

    public abstract void doOperation(Player p);
}
