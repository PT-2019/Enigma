package editor.menus.enimas.create.listeners;


import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import editor.menus.AbstractPopUpView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static common.language.GameLanguage.gl;

/**
 * Controlleur pour afficher le choix de musique
 */
public class MusicListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        EnigmaOptionPane.showMusicChoiceDialog(new EnigmaWindow());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JLabel){
            JLabel label = (JLabel) e.getSource();
            label.setForeground(new Color(  230, 69, 249 ));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JLabel){
            JLabel label = (JLabel) e.getSource();
            label.setForeground(Color.BLACK);
        }
    }
}
