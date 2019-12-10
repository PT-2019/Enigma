package editor.utils.managers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeTop extends Resize {

    public ResizeTop(Window window, JPanel resizeComponent, Cursor cursor){
        super(window,resizeComponent,cursor);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        Point mouseCords = mouseEvent.getPoint();
        if(mouseCords.y > this.resizeComponent.getHeight()) System.out.println("top less");
        if(mouseCords.y < 0) System.out.println("top more");
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        this.resizeComponent.setCursor(this.cursor);
    }
}
