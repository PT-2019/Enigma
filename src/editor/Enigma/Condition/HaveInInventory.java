package editor.Enigma.Condition;

import editor.Entity.Interface.Item;
import editor.Entity.Player.Player;

public class HaveInInventory extends Condition {

    public HaveInInventory(Item i){
        super(i);
    }

    @Override
    public boolean verify(Player p) {
        Item i = (Item)this.entity;
        //tester si p à i dans son inventaire
        return false;
    }

    @Override
    public String toString(){
        return "[HaveInInventory]";
    }

    public String toLongString(){
        return "[HaveInInventory  : entity = " + this.entity + "]";
    }
}
