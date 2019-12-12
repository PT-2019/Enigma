package editor.utils;

import editor.utils.managers.EnigmaButtonManager;
import editor.utils.ui.EnigmaButtonUI;

import javax.swing.*;

public class EnigmaButton extends JButton {

    private EnigmaButtonUI ui;

    public EnigmaButton(){
        super();
        this.addMouseListener(new EnigmaButtonManager(this));
        this.setOpaque(true);
    }

    public EnigmaButton(String title){
        super(title);
        this.addMouseListener(new EnigmaButtonManager(this));
        this.setOpaque(true);
    }

    public void setButtonUI(EnigmaButtonUI ui) {
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        super.setUI(this.ui);
    }

    public EnigmaButtonUI getButtonUI(){
        return this.ui;
    }
}
