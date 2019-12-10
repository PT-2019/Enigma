package editor.utils.managers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeBottom extends Resize {

    public ResizeBottom(Window window, JPanel resizeComponent, Cursor cursor){
        super(window,resizeComponent,cursor);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        Point mouseCords = mouseEvent.getPoint();
        this.window.setSize(this.window.getWidth(), this.window.getHeight() + mouseCords.y);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        this.resizeComponent.setCursor(this.cursor);
    }
}
