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
        Rectangle window = this.window.getBounds();
        if(this.window.getMaximumSize().height > window.height){
            this.window.setSize(window.width, window.height + mouseCords.y);
        }else if(mouseCords.x < this.resizeComponent.getHeight()){
            this.window.setSize(window.width, window.height + mouseCords.y);
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {}
}
