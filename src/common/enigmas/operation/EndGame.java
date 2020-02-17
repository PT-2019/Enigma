package common.enigmas.operation;

import common.enigmas.reporting.EnigmaReport;
import common.enigmas.reporting.OperationReport;
import common.entities.GameObject;
import common.entities.players.Player;
import common.language.EnigmaField;
import game.screens.GameScreen;

import static common.language.GameLanguage.gl;

/**
 * Lorsqu'on a fini le jeu
 */
public class EndGame extends Operation {

    /**
     * On peut mettre n'importe quel objet
     * @param object
     */
    public EndGame(GameObject object){
        super(object);
    }

    @Override
    public EnigmaReport run(Player p) {
        //on affiche l'écran de fin
        GameScreen.getEndScreen().showVictory();

        return new EnigmaReport(OperationReport.DONE, true);
    }

    @Override
    public String toLongString() {
        return "[End]";
    }

    /**
     * Version texte de l'objet
     *
     * @return Texte représentant l'objet
     */
    @Override
    public String toString() {
        return "[End]";
    }

    @Override
    public String getEnigmaElementReadablePrint() {
        return "[" + gl.get(EnigmaField.END) + ": " +
                this.entity.getReadableName() + " (id=" + this.entity.getID() + ") ]";
    }
}
