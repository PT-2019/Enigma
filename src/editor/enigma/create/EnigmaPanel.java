package editor.enigma.create;

import api.utils.Observer;

import javax.swing.*;

/**
 * Toute les classes gérant les panneaux de EnigmaView héritent de cette classe.
 * Cette classe sert d'observateur vis à vis du choix d'entité lorsqu'on créer une énigme
 * @see EnigmaView
 * @see editor.view.cases.listeners.EntityChoseListener
 */
public abstract class EnigmaPanel extends JPanel implements Observer {
    protected EnigmaView parent;

    public EnigmaView getFrame(){
        return parent;
    }
}
