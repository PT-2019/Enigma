package editor.utils;

import editor.utils.managers.EnigmaTextAreaManager;
import editor.utils.ui.EnigmaTextAreaUI;

import javax.swing.*;
import java.awt.*;

public class EnigmaTextArea extends JTextArea {

    private EnigmaTextAreaUI ui;

    public EnigmaTextArea(){
        super();
        EnigmaTextAreaManager manager = new EnigmaTextAreaManager(this);
        this.addMouseListener(manager);
        this.addFocusListener(manager);
        this.setOpaque(true);
    }

    public void setTextAreaUI(EnigmaTextAreaUI ui){
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        super.setUI(this.ui);
    }

    public EnigmaTextAreaUI getTextAreaUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        if(this.ui == null) super.paintComponent(g);
        else this.ui.paintTextArea(g,this);
    }
}
