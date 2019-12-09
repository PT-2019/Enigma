package editor.utils;

import editor.utils.managers.MenuManager;
import editor.utils.ui.MenuUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu extends JMenu {

    private MenuUI ui;

    public Menu(String title){
        super(title);
        this.addMouseListener(new MenuManager(this));
        this.setOpaque(true);
    }

    public void setMenuUI(MenuUI ui){
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        super.setUI(this.ui);
    }

    public MenuUI getMenuUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        if(this.ui == null) super.paintComponent(g);
        else this.ui.paint(g,this);
    }
}
