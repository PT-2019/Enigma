package editor.utils;

import editor.utils.ui.EnigmaMenuBarUI;

import javax.swing.*;
import java.awt.*;

public class EnigmaMenuBar extends JMenuBar {

    private EnigmaMenuBarUI ui;

    public EnigmaMenuBar(){
        super();
        this.ui = null;
        this.setOpaque(true);
        this.setMenuBarUI(new EnigmaMenuBarUI());
    }

    public void setMenuBarUI(EnigmaMenuBarUI ui){
        this.ui = ui.duplicate();
        this.setBorderPainted(false);
        super.setUI(this.ui);
    }

    public EnigmaMenuBarUI getMenuBarUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        this.ui.paint(g,this);
    }
}
