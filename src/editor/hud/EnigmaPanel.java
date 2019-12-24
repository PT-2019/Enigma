package editor.hud;

import editor.hud.managers.EnigmaPanelManager;
import editor.hud.ui.EnigmaPanelUI;

import javax.swing.*;
import java.awt.*;

/**
 * TODO: comment EnigmaPanel and write Readme.md in editor.hud
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
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
