package editor.utils.managers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeLeft extends Resize {

    public ResizeLeft(Window window, JPanel resizeComponent, Cursor cursor){
        super(window,resizeComponent,cursor);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        Point mouseCords = mouseEvent.getPoint();
        if(mouseCords.x > this.resizeComponent.getWidth()) System.out.println("left less");
        if(mouseCords.x < 0) System.out.println("left more");
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        this.resizeComponent.setCursor(this.cursor);
    }
}
