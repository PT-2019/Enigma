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
        System.out.println("La porte est dévérouillée");
        return a.isActivated();
    }
}
