package editor.utils.managers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Resize implements MouseMotionListener {

    protected JPanel resizeComponent;
    protected Window window;

    public Resize(Window window, JPanel resizeComponent, Cursor cursor){
        this.window = window;
        this.resizeComponent = resizeComponent;
        this.resizeComponent.setCursor(cursor);
        this.resizeComponent.addMouseMotionListener(this);
    }
}
