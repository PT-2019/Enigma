package game.event;

import editor.enigma.Enigma;
import editor.entity.Player;

import java.util.ArrayList;

/**
 * Permet de gérer le lancement d'événements via la vérification d'{@link editor.enigma.Enigma énigmes}
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 5.0
 */
public class OperationEvent {

    private ArrayList<Enigma> onEnter;
    private ArrayList<Enigma> onExit;
    private ArrayList<Enigma> onUse;

    public OperationEvent(){
        this.onEnter = new ArrayList<>();
        this.onExit = new ArrayList<>();
        this.onUse = new ArrayList<>();
    }

    /**
     * Ajoute l'énigme à l'entrée sur la tile
     */
    public void addOnEnter(Enigma onEnter){
        this.onEnter.add(onEnter);
    }

    /**
     * Ajoute l'énigme lors de la sortie de la tile
     */
    public void addOnExit(Enigma onExit){
        this.onExit.add(onExit);
    }

    /**
     * Ajoute l'énigme lors d'interaction avec la tile
     */
    public void addOnUse(Enigma onUse){
        this.onUse.add(onUse);
    }

    /**
     * A l'entrée sur la tile
     */
    public void onEnter(Player player){
        for(Enigma e : this.onEnter)
            e.verifyConditions(player);
    }

    /**
     * Lors de la sortie de la tile
     */
    public void onExit(Player player){
        for(Enigma e : this.onExit)
            e.verifyConditions(player);
    }

    /**
     * Lors d'interaction avec la tile
     */
    public void onUse(Player player){
        for(Enigma e : this.onUse)
            e.verifyConditions(player);
    }
}
