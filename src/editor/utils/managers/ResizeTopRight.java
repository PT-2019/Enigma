package editor.utils.managers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeTopRight extends Resize {

    public ResizeTopRight(JPanel resizeComponent, Cursor cursor){
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
        if(mouseCords.y > this.resizeComponent.getHeight()) System.out.println("top less");
        if(mouseCords.y < 0) System.out.println("top more");
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
