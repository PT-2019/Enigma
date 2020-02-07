package common.hud;

import api.ui.CustomProgressPopup;

/**
 * Visualisateur de progression
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 06 fevrier 2020
 * @since 6.0
 * @see api.ui.CustomProgressPopup
 */
public class EnigmaProgressPopup extends CustomProgressPopup {

    /**
     * @param message Message
     * @param startingValue Valeur de départ
     * @param maxValue Valeur à atteindre
     * @param type Type
     * @throws IllegalArgumentException Si la valeur de départ est supérieure à la valeur maximale
     */
    public EnigmaProgressPopup(String message, int startingValue, int maxValue, int type){
        super(message,startingValue,maxValue,type,new EnigmaWindow(),new EnigmaLabel(),new EnigmaLabel());
    }
}
