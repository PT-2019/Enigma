package editor.utils.managers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeRight extends Resize {

    public ResizeRight(Window window, JPanel resizeComponent, Cursor cursor){
        super(window,resizeComponent,cursor);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        Point mouseCords = mouseEvent.getPoint();
        this.window.setSize(this.window.getWidth()  + mouseCords.x, this.window.getHeight());
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        this.resizeComponent.setCursor(this.cursor);
    }
}
