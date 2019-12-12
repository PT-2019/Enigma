package editor.utils;

import editor.utils.managers.EnigmaMenuItemManager;
import editor.utils.ui.EnigmaMenuItemUI;

import javax.swing.*;
import java.awt.*;

public class EnigmaMenuItem extends JMenuItem {

    private EnigmaMenuItemUI ui;

    public EnigmaMenuItem(String title){
        super(title);
        this.addMouseListener(new EnigmaMenuItemManager(this));
        this.setOpaque(true);
    }

    public void setMenuItemUI(EnigmaMenuItemUI ui){
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        super.setUI(this.ui);
    }

    public EnigmaMenuItemUI getMenuItemUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        if(this.ui == null) super.paintComponent(g);
        else this.ui.paint(g,this);
    }
}
