package editor.utils;

import editor.utils.managers.EnigmaComboBoxManager;
import editor.utils.ui.EnigmaComboBoxUI;

import javax.swing.*;
import java.awt.*;

public class EnigmaComboBox extends JComboBox {

    private EnigmaComboBoxUI ui;

    public EnigmaComboBox(){
        super();
        this.addMouseListener(new EnigmaComboBoxManager(this));
        this.setOpaque(true);
    }

    public void setComboBoxUI(EnigmaComboBoxUI ui){
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        super.setUI(this.ui);
    }

    public EnigmaComboBoxUI getComboBoxUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        if(this.ui == null) super.paintComponent(g);
        else this.ui.paintComboBox(g,this);
    }
}
