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
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setLabelUI(new EnigmaLabelUI());
    }

    public EnigmaLabel(String title){
        super(title);
        this.addMouseListener(new EnigmaLabelManager(this));
        this.setOpaque(true);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setLabelUI(new EnigmaLabelUI());
    }

    public void setLabelUI(EnigmaLabelUI ui){
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        this.setFont(this.ui.getFont());
        super.setUI(this.ui);
    }

    public EnigmaLabelUI getLabelUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        this.ui.paint(g,this);
    }
}
