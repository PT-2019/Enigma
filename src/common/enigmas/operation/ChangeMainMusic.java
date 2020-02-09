package common.enigmas.operation;

import common.entities.players.Player;
import common.entities.special.GameMusic;
import common.entities.special.MusicEditor;

/**
 * Changer la musique de fond suite à la résolution d'une énigme
 */
public class ChangeMainMusic extends Operation {

    private GameMusic musicGame;

    public ChangeMainMusic(MusicEditor object){
        super(object);
    }

    public ChangeMainMusic(MusicEditor object,GameMusic music){
        super(object);
        musicGame = music;
    }

    //deprecated donc on fait rien
    @Override
    public void doOperation(Player p) {
    }

    @Override
    public void run(Player p) {
        musicGame.changeMusic((MusicEditor)this.entity);
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
