package editor.Enigma.Operation;


import editor.Entity.Interface.Entity;
import editor.Entity.Player.Player;

public class Summon extends Operation {

    private Case spawn;

    public Summon(Entity e, Case spawn){
        super(e);
        this.spawn = spawn;
    }

    @Override
    public void doOperation(Player p) {
       //faire apparaitre this.entity sur this.spawn
    }

    @Override
    public String toString(){
        return "[Summon]";
    }

    public String toLongString(){
        return "[Summon  : entity = " + this.entity + "]";
    }
}
