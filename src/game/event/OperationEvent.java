package game.event;

import editor.enigma.Enigma;
import editor.entity.Player;

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

    private final Enigma[] enigmas = new Enigma[3];

    public final static short ON_ENTER = 1;
    public final static short ON_EXIT = 2;
    public final static short ON_USE = 3;

    public OperationEvent(){}

    public OperationEvent(Enigma onEnter, Enigma onExit, Enigma onUse){
        this.enigmas[ON_ENTER] = onEnter;
        this.enigmas[ON_EXIT] = onExit;
        this.enigmas[ON_USE] = onUse;
    }

    /**
     * A l'entrée sur la tile
     */
    public void onEnter(Player player){
        this.enigmas[ON_ENTER].verifyConditions(player);
    }

    /**
     * Lors de la sortie de la tile
     */
    public void onExit(Player player){
        this.enigmas[ON_EXIT].verifyConditions(player);
    }

    /**
     * Lors d'interaction avec la tile
     */
    public void onUse(Player player){
        this.enigmas[ON_USE].verifyConditions(player);
    }
}
