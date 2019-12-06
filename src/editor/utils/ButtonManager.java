package editor.utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonManager implements MouseListener {

    private Button button;

    public ButtonManager(Button button){
        this.button = button;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        this.button.clicked();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        this.button.pressed();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        this.button.released();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        this.button.hovered();
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        this.button.exited();
    }
}
