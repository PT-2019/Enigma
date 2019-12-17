package editor.utils.managers;

import editor.utils.EnigmaSelectedButtonManager;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectedManager implements MouseListener {

    private EnigmaSelectedButtonManager manager;

    public SelectedManager(EnigmaSelectedButtonManager manager){
        this.manager = manager;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        this.manager.setSelected((JButton)mouseEvent.getSource());
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
