package editor.utils.managers;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Resize implements MouseMotionListener {

    protected ResizeComponent resizeComponent;
    protected Window window;
    protected boolean resizable;

    public Resize(Window window, ResizeComponent resizeComponent){
        super();
        this.window = window;
        this.resizeComponent = resizeComponent;
        this.resizeComponent.addMouseMotionListener(this);
        this.resizable = true;
    }

    public void disable(){
        this.resizable = false;
    }

    public void enable(){
        this.resizable = true;
    }
}
