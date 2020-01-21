package editor.enigma.create.enigma;

import javax.swing.*;

/**
 * Toute les classes gérant les panneaux de EnigmaView héritent de cette classe.
 * @see EnigmaView
 */
public abstract class EnigmaPanel extends JPanel {
    protected EnigmaView parent;

    public EnigmaView getFrame(){
        return parent;
    }
}
