package common.enigmas.operation;

import common.entities.players.Player;
import common.entities.special.MusicEditor;

public class ChangeMainMusic extends Operation {

    public ChangeMainMusic(MusicEditor object){
        super(object);
    }

    @Override
    public void doOperation(Player p) {
    }

    @Override
    public void run(Player p) {

    }

    @Override
    public String toLongString() {
        return "[MainMusic  : entity = " + this.entity + "]";
    }

    @Override
    public String getEnigmaElementReadablePrint() {
        return null;
    }
}
