package editor.utils;

import editor.utils.ui.EnigmaPopupMenuUI;

import javax.swing.*;
import java.awt.*;

public class EnigmaPopupMenu extends JPopupMenu {

    private EnigmaPopupMenuUI ui;

    public EnigmaPopupMenu(){
        super();
        this.setOpaque(true);
    }

    public void setPopupMenuUI(EnigmaPopupMenuUI ui){
        this.ui = ui;
        this.repaint();
    }

    public EnigmaPopupMenuUI getPopupMenuUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        if(this.ui != null) this.ui.paint(g,this);
        else super.paintComponent(g);
    }
}
