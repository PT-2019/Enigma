package game.hmi.listener.action;

import api.ui.CustomOptionPane;
import common.hud.EnigmaLabel;
import common.hud.EnigmaOptionPane;
import game.EnigmaGameLauncher;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Gestionnaire de la selection de map
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class MapSelectionListener implements MouseListener {
    /**
     * Conteneur du nom de la map
     */
    private EnigmaLabel label;

    /**
     * @param label Conteneur du nom de la map
     */
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
