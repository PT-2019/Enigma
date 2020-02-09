package common.enigmas.operation;

import common.entities.players.Player;
import common.entities.special.GameMusic;
import common.entities.special.MusicEditor;

/**
 * Lance un son suite à la résolution d'une enigme
 */
public class LaunchSound extends Operation {

    private GameMusic musicGame;

    public LaunchSound(MusicEditor object){
        super(object);
    }

    public LaunchSound(MusicEditor object, GameMusic music){
        super(object);
        musicGame = music;
    }

    @Override
    public void doOperation(Player p) {
    }

    @Override
    public void run(Player p) {
        musicGame.playSound((MusicEditor) this.entity);
    }

    @Override
    public String toLongString() {
        return "[Music  : entity = " + this.entity + "]";
    }

    @Override
    public String getEnigmaElementReadablePrint() {
        return null;
    }
}
