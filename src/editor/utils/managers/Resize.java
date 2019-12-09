package editor.utils.managers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Resize implements MouseListener, MouseMotionListener {

    protected final Cursor cursor;
    protected final JPanel resizeComponent;

    public Resize(JPanel resizeComponent, Cursor cursor){
        this.resizeComponent = resizeComponent;
        this.cursor = cursor;
    }
}
