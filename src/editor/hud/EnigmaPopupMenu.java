package editor.hud;

import editor.hud.ui.EnigmaPopupMenuUI;

import javax.swing.*;
import java.awt.*;

/**
 * TODO: comment EnigmaPopupMenu and write Readme.md in editor.hud
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class EnigmaPopupMenu extends JPopupMenu {

    private EnigmaPopupMenuUI ui;

    public EnigmaPopupMenu(){
        super();
        this.setOpaque(true);
        this.setPopupMenuUI(new EnigmaPopupMenuUI());
    }

    public void setPopupMenuUI(EnigmaPopupMenuUI ui){
        this.ui = ui.duplicate();
        this.repaint();
    }

    public EnigmaPopupMenuUI getPopupMenuUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        this.ui.paint(g,this);
    }
}
