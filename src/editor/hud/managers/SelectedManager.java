package editor.hud.managers;

import editor.hud.EnigmaButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectedManager implements MouseListener {

    private MultipleButtonManager manager;

    public SelectedManager(MultipleButtonManager manager){
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
