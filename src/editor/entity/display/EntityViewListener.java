package editor.entity.display;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EntityViewListener implements MouseListener {

    private JPopupMenu menu;

    public EntityViewListener(JPopupMenu menu) {
        this.menu = menu;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        JComponent composant = (JComponent) mouseEvent.getSource();
        composant = (JComponent) composant.getParent();
        if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
            menu.show(composant, mouseEvent.getX(), mouseEvent.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
