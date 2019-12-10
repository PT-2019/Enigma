package editor.utils.managers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Resize implements MouseMotionListener {

    protected Cursor cursor;
    protected JPanel resizeComponent;
    protected Window window;

    public Resize(Window window, JPanel resizeComponent, Cursor cursor){
        this.resizeComponent = resizeComponent;
        this.cursor = cursor;
        this.window = window;
    }
}
