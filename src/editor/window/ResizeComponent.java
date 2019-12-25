package editor.window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ResizeComponent extends JPanel {

    private Cursor cursor;
    private ArrayList<Resize> resizeManagers;

    public ResizeComponent(Cursor cursor){
        super();
        this.cursor = cursor;
        this.setCursor(cursor);
        this.resizeManagers = new ArrayList<>();
    }

    public void addResizer(Resize r) {
        this.resizeManagers.add(r);
        super.addMouseMotionListener(r);
    }

    public void disableResize(){
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        for(Resize r: this.resizeManagers) r.disable();
    }

    public void enableResize(){
        this.setCursor(this.cursor);
        for(Resize r: this.resizeManagers) r.enable();
    }
}
