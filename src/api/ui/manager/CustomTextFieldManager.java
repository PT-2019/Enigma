package api.ui.manager;

import api.ui.CustomTextField;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Manager du survol, et des clics sur la zone de saisie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public final class CustomTextFieldManager extends MouseAdapter implements FocusListener {

    private CustomTextField textField;

    public CustomTextFieldManager(CustomTextField textField) {
        this.textField = textField;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        if (this.textField.getComponentUI() != null) {
            this.textField.getComponentUI().setIsHovered(true);
            this.textField.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        if (this.textField.getComponentUI() != null) {
            this.textField.getComponentUI().setIsHovered(false);
            this.textField.repaint();
        }
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        this.textField.repaint();
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        this.textField.repaint();
    }
}
