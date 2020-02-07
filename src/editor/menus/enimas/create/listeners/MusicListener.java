package editor.menus.enimas.create.listeners;


import common.entities.special.MusicEditor;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.map.MapTestScreen;
import editor.menus.AbstractPopUpView;
import editor.menus.enimas.create.MusicPanel;
import editor.menus.enimas.create.OperationPanel;
import game.EnigmaGame;
import game.screens.TestScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static common.language.GameLanguage.gl;

/**
 * Controlleur pour afficher le choix de musique
 */
public class MusicListener implements MouseListener {

    private static String CANCEL = "Annuler";

    @Override
    public void mouseClicked(MouseEvent e) {
        String chose = EnigmaOptionPane.showMusicChoiceDialog(new EnigmaWindow());
        Object tmp;

        if (!chose.equals(MusicListener.CANCEL)){
            MusicEditor object = new MusicEditor(chose);

            tmp = e.getSource();
            if (tmp instanceof JLabel){
                //pour avoir la factory de id
                MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();
                MusicPanel panel =(MusicPanel) ((JLabel) tmp).getParent();
                map.getIdFactory().newID(object);
                panel.getPanelOperation().update(object);
            }
        }
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
            label.setForeground(new Color(203, 64, 249 ));
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
