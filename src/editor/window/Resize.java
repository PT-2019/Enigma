package editor.window;

import java.awt.Window;
import java.awt.event.MouseMotionListener;

public abstract class Resize implements MouseMotionListener {

    protected ResizeComponent resizeComponent;
    protected java.awt.Window window;
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
