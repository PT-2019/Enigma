package game.hmi.listener.action;

import api.ui.CustomOptionPane;
import common.hud.EnigmaLabel;
import common.hud.EnigmaOptionPane;
import game.EnigmaGameLauncher;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapSelectionListener implements MouseListener {
    private EnigmaLabel label;

    public MapSelectionListener(EnigmaLabel label){
        this.label = label;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        String answer = EnigmaOptionPane.showMapChoiceDialog(EnigmaGameLauncher.getInstance().getWindow());
        if(!answer.equals(CustomOptionPane.CANCEL))
            this.label.setText(answer);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
