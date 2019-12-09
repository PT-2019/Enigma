package editor.utils.managers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeBottom extends Resize {

    public ResizeBottom(JPanel resizeComponent, Cursor cursor){
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
        if(mouseCords.y > this.resizeComponent.getHeight()) System.out.println("bottom more");
        if(mouseCords.y < 0) System.out.println("bottom less");
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
