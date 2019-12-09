package editor.utils.managers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeLeft extends Resize {

    public ResizeLeft(JPanel resizeComponent, Cursor cursor){
        super(resizeComponent,cursor);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        this.resizeComponent.setCursor(this.cursor);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        this.resizeComponent.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        Point mouseCords = mouseEvent.getPoint();
        if(mouseCords.x > this.resizeComponent.getWidth()) System.out.println("left less");
        if(mouseCords.x < 0) System.out.println("left more");
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
