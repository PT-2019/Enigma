package editor.utils;

import editor.utils.managers.MenuItemManager;
import editor.utils.ui.MenuItemUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuItem extends JMenuItem {

    private MenuItemUI ui;

    public MenuItem(String title){
        super(title);
        this.addMouseListener(new MenuItemManager(this));
        this.setOpaque(true);
    }

    public void setMenuItemUI(MenuItemUI ui){
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        super.setUI(this.ui);
    }

    public MenuItemUI getMenuItemUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        if(this.ui == null) super.paintComponent(g);
        else this.ui.paint(g,this);
    }
}
