package editor.utils;

import editor.utils.managers.EnigmaPanelManager;
import editor.utils.ui.EnigmaPanelUI;

import javax.swing.*;
import java.awt.*;

public class EnigmaPanel extends JPanel {

    private EnigmaPanelUI ui;

    public EnigmaPanel(){
        super();
        this.addMouseListener(new EnigmaPanelManager(this));
        this.setOpaque(true);
        this.setPanelUI(new EnigmaPanelUI());
    }

    public void setPanelUI(EnigmaPanelUI ui){
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        super.setUI(this.ui);
    }

    public EnigmaPanelUI getPanelUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        this.ui.paint(g,this);
    }
}