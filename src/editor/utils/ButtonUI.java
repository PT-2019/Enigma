package editor.utils;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class ButtonUI extends BasicButtonUI {

    private Button b;

    public ButtonUI(Button b){
        super();
        this.b = b;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        g.setColor(Color.RED);
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }

    public void on(){
    }

    public void out(){
    }

    public void pressed(){
    }
}
