package editor.Enigma.Operation;


import editor.Entity.Interface.Item;
import editor.Entity.Player.Player;

public class Give extends Operation {

    public Give(Item i){
        super(i);
    }

    @Override
    public void doOperation(Player p) {
        Item i = (Item)this.entity;
        //Donner i à p
    }
}
