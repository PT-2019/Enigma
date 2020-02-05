package common.hud;

import api.ui.CustomTextField;
import common.hud.ui.EnigmaTextFieldUI;

/**
 * Champ de texte de Enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class EnigmaTextField extends CustomTextField {

    /**
     * Champ de texte vide
     */
    public EnigmaTextField() {
        super();
        this.setComponentUI(new EnigmaTextFieldUI());
    }

    /**
     * Champ de texte
     * @param text contenu
     */
    public EnigmaTextField(String text) {
        super(text);
        this.setComponentUI(new EnigmaTextFieldUI());
    }

    @Override
    public EnigmaTextFieldUI getComponentUI() {
        return (EnigmaTextFieldUI) super.getComponentUI();
    }
}
