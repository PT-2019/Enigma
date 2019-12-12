package editor.utils;

import editor.utils.managers.EnigmaMenuManager;
import editor.utils.ui.EnigmaMenuUI;

import javax.swing.*;
import java.awt.*;

public class EnigmaMenu extends JMenu {

    private EnigmaMenuUI ui;

    public EnigmaMenu(String title){
        super(title);
        this.addMouseListener(new EnigmaMenuManager(this));
        this.setOpaque(true);
        this.setPopupMenuVisible(false);
        this.getPopupMenu().setBorderPainted(false);
    }

    public void setMenuUI(EnigmaMenuUI ui){
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        super.setUI(this.ui);
    }

    public EnigmaMenuUI getMenuUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        if(this.ui == null) super.paintComponent(g);
        else this.ui.paint(g,this);
    }
}
