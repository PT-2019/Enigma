package editor.utils;

import editor.utils.ui.MenuBarUI;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {

    private MenuBarUI ui;

    public MenuBar(){
        super();
        this.ui = null;
        this.setOpaque(true);
    }

    public void setMenuBarUI(MenuBarUI ui){
        this.ui = ui.duplicate();
        this.setBorderPainted(false);
        super.setUI(this.ui);
    }

    public MenuBarUI getMenuBarUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        if(this.ui == null) super.paintComponent(g);
        else this.ui.paint(g,this);
    }
}
