package editor.hud;

import editor.hud.ui.EnigmaMenuItemUI;

import javax.swing.*;
import java.awt.*;

/**
 * TODO: comment EnigmaMenuItem and write Readme.md in editor.hud
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class EnigmaMenuItem extends JMenuItem {

    private EnigmaMenuItemUI ui;

    public EnigmaMenuItem(String title){
        super(title);
        this.setOpaque(true);
        this.setMenuItemUI(new EnigmaMenuItemUI());
    }

    public void setMenuItemUI(EnigmaMenuItemUI ui){
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        this.setFont(this.ui.getFont());
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        super.setUI(this.ui);
    }

    public EnigmaMenuItemUI getMenuItemUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        this.ui.paint(g,this);
    }
}
