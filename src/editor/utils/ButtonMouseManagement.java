package editor.utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonMouseManagement implements MouseListener {

    private Button button;

    public ButtonMouseManagement(Button button){
        this.button = button;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {}

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
