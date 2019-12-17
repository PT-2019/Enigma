package editor.utils.managers;

import editor.utils.EnigmaButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectedManager implements MouseListener {

    private SelectedButtonManager manager;

    public SelectedManager(SelectedButtonManager manager){
        this.manager = manager;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        this.manager.setSelected((EnigmaButton) mouseEvent.getSource());
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
