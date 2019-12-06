package editor.utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonMouseManagement implements MouseListener {

    private ButtonUI button;

    public ButtonMouseManagement(ButtonUI button){
        this.button = button;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        button.pressed();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        button.pressed();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        button.on();
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        button.out();
    }
}
