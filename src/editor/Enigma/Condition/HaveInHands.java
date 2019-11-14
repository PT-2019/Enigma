package editor.Enigma.Condition;

import editor.Entity.Interface.Item;
import editor.Entity.Player.Player;

public class HaveInHands extends Condition {

    public HaveInHands(Item i){
        super(i);
    }

    @Override
    public boolean verify(Player p) {
        Item i = (Item)this.entity;
        //tester si p à i dans ses mains
        return false;
    }
}
