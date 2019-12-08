package editor.utils;

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

class MenuItemManager implements MouseListener {

    private MenuItem menu;

    public MenuItemManager(MenuItem menu){
        this.menu = menu;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent){}

    @Override
    public void mousePressed(MouseEvent mouseEvent){}

    @Override
    public void mouseReleased(MouseEvent mouseEvent){}

    @Override
    public void mouseEntered(MouseEvent mouseEvent){
        if(this.menu.getMenuItemUI() != null){
            this.menu.getMenuItemUI().setIsHovered(true);
            this.menu.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent){
        if(this.menu.getMenuItemUI() != null){
            this.menu.getMenuItemUI().setIsHovered(false);
            this.menu.repaint();
        }
    }
}
