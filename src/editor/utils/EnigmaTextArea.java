package editor.utils;

import editor.utils.managers.EnigmaPanelManager;
import editor.utils.managers.EnigmaTextAreaManager;
import editor.utils.ui.EnigmaPanelUI;
import editor.utils.ui.EnigmaTextAreaUI;

import javax.swing.*;
import java.awt.*;

public class EnigmaTextArea extends JTextArea {

    private EnigmaTextAreaUI ui;

    public EnigmaTextArea(){
        super();
        this.addMouseListener(new EnigmaTextAreaManager(this));
        this.setOpaque(true);
        this.setBackground(Color.RED);
        this.setForeground(Color.DARK_GRAY);
        this.setBorder(BorderFactory.createMatteBorder(2,5,3,1,Color.GREEN));
    }

    public void setTextAreaUI(EnigmaTextAreaUI ui){
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        super.setUI(this.ui);
    }

    public EnigmaTextAreaUI getTextAreaUI(){
        return this.ui;
    }

    /*@Override
    public void paintComponent(Graphics g){
        if(this.ui == null) super.paintComponent(g);
        else this.ui.paintTextArea(g,this);
    }*/
}
