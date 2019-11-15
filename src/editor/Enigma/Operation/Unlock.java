package editor.Enigma.Operation;

import editor.Entity.Interface.Item;
import editor.Entity.Interface.Lockable;
import editor.Entity.Player.Player;

public class Unlock extends Operation {

    public Unlock(Lockable l){
        super((Item)l);
    }

    @Override
    public void doOperation(Player p) {
        Lockable l = (Lockable)this.entity;
        l.unlock();
    }

    @Override
    public String toString(){
        return "[Unlock]";
    }

    public String toLongString(){
        return "[Unlock  : entity = " + this.entity + "]";
    }
}
