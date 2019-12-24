package editor.hud;

import editor.hud.managers.EnigmaButtonManager;
import editor.hud.ui.EnigmaButtonUI;

import javax.swing.*;

/**
 * TODO: comment EnigmaButton and write Readme.md in editor.hud
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class EnigmaButton extends JButton {

    private EnigmaButtonUI ui;

    public EnigmaButton(){
        super();
        this.addMouseListener(new EnigmaButtonManager(this));
        this.setOpaque(true);
        this.setButtonUI(new EnigmaButtonUI());
    }

    public EnigmaButton(String title){
        super(title);
        this.addMouseListener(new EnigmaButtonManager(this));
        this.setOpaque(true);
        this.setButtonUI(new EnigmaButtonUI());
    }

    public void setButtonUI(EnigmaButtonUI ui) {
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        this.setFont(this.ui.getFont());
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        super.setUI(this.ui);
    }

    public EnigmaButtonUI getButtonUI(){
        return this.ui;
    }

    public boolean isSelected(){
        return this.ui.isSelected();
    }
}
