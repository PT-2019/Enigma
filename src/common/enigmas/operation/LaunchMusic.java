package common.enigmas.operation;

import common.entities.players.Player;
import common.entities.special.MusicEditor;

public class LaunchMusic extends Operation {

    public LaunchMusic(MusicEditor object){
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
        return "[Give  : entity = " + this.entity + "]";
    }

    @Override
    public String getEnigmaElementReadablePrint() {
        return null;
    }
}
