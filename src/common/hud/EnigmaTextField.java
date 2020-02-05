package common.hud;

import api.ui.CustomTextField;
import common.hud.ui.EnigmaTextFieldUI;

/**
 * Champ de texte
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class EnigmaTextField extends CustomTextField {


    public EnigmaTextField() {
        super();
        this.setComponentUI(new EnigmaTextFieldUI());
    }

    public EnigmaTextField(String text) {
        super(text);
        this.setComponentUI(new EnigmaTextFieldUI());
    }

    @Override
    public EnigmaTextFieldUI getComponentUI() {
        return (EnigmaTextFieldUI) super.getComponentUI();
    }
}
