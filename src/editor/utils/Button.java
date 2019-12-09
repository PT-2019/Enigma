package editor.utils;

import editor.utils.managers.ButtonManager;
import editor.utils.ui.ButtonUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton {

    private ButtonUI ui;

    public Button(){
        super();
        this.addMouseListener(new ButtonManager(this));
        this.setOpaque(true);
    }

    public Button(String title){
        super(title);
        this.addMouseListener(new ButtonManager(this));
        this.setOpaque(true);
    }

    public void setButtonUI(ButtonUI ui) {
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        super.setUI(this.ui);
    }

    public ButtonUI getButtonUI(){
        return this.ui;
    }
}
