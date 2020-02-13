package editor.menus.enimas.create.listeners;

import common.enigmas.condition.Answer;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.map.MapTestScreen;
import editor.menus.enimas.create.ChoicePanel;
import game.EnigmaGame;
import game.screens.TestScreen;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Controlleur qui va afficher le formulaire pour créer la question et réponse
 */
public class AnswerListener implements MouseListener {

    public static final String CANCEL = "Annuler";

    @Override
    public void mouseClicked(MouseEvent e) {
        String answer = "";
        Object tmp;
        answer = EnigmaOptionPane.showInputDialog(new EnigmaWindow(),answer);

        if (!answer.equals(CANCEL)){
            tmp = e.getSource();
            if (tmp instanceof JLabel){
                //pour avoir la factory de id
                MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();
                ChoicePanel panel =(ChoicePanel) ((JLabel) tmp).getParent();
                panel.getParent().update(answer);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
