package common.enigmas.operation;

import common.enigmas.reporting.EnigmaReport;
import common.enigmas.reporting.OperationReport;
import common.entities.players.Player;
import common.entities.special.GameMusic;
import common.entities.special.MusicEditor;
import data.NeedToBeTranslated;

/**
 * Changer la musique de fond suite à la résolution d'une énigme
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0
 * @since 6.0
 */
public class ChangeMainMusic extends Operation {

    /**
     * Musique
     */
    private GameMusic musicGame;

    /**
     * Change la musique ambiante
     * @param object une musique
     */
    public ChangeMainMusic(MusicEditor object){
        super(object);
    }

    /**
     * Change la musique ambiante
     * @param object une musique
     * @param music gestionnaire des musiques
     */
    @SuppressWarnings("unused")
    public ChangeMainMusic(MusicEditor object,GameMusic music){
        super(object);
        musicGame = music;
    }

    @Override
    public EnigmaReport run(Player p) {
        this.musicGame.changeMusic((MusicEditor)this.entity);
        return new EnigmaReport(OperationReport.DONE, true);
    }

    @Override
    public String toLongString() {
        return "[MainMusic  : entity = " + this.entity + "]";
    }

    @Override
    public String getEnigmaElementReadablePrint() {
        return "["+ NeedToBeTranslated.CHANGE_MAIN_MUSIC +": "+
                this.entity.getReadableName() + " (id="+this.entity.getID()+") ]";
    }
}
