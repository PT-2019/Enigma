package editor.utils;

import editor.utils.managers.EnigmaLabelManager;
import editor.utils.ui.EnigmaLabelUI;

import javax.swing.*;
import java.awt.*;

public class EnigmaLabel extends JLabel {

    private EnigmaLabelUI ui;

    public EnigmaLabel(){
        super();
        this.addMouseListener(new EnigmaLabelManager(this));
        this.setOpaque(true);
    }

    public void setLabelUI(EnigmaLabelUI ui){
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        super.setUI(this.ui);
    }

    public EnigmaLabelUI getLabelUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        if(this.ui == null) super.paintComponent(g);
        else{
            this.ui.paint(g,this);
            System.out.println("-------");
        }
    }
}
