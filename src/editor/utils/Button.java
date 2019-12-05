package editor.utils;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    private static final Color CLASSIC_BACKGROUND_COLOR = Color.WHITE;
    private static final Color CLASSIC_FOREGROUND_COLOR = Color.BLACK;
    private static final Color CLASSIC_HOVER_BACKGROUND_COLOR = Color.BLACK;
    private static final Color CLASSIC_HOVER_FOREGROUND_COLOR = Color.WHITE;

    private Color hoverBackground;
    private Color hoverForeground;

    public Button(String title){
        super(title);
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setFocusable(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void hover(){
        this.setBackground(this.hover);
    }

    public void setBorder(Color color){
        this.setBorderPainted(true);
        this.setBorder(BorderFactory.createLineBorder(color));
    }

    public void hideBorder(){
        this.setBorderPainted(false);
    }

    public void showBorder(){
        this.setBorderPainted(true);
    }

    public void resetBorder(){
        this.setBorderPainted(false);
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    public void setHoverBackground(Color color){
        this.hoverBackground = color;
    }

    public void setHoverForeground(Color color){
        this.hoverForeground = color;
    }

    public void resetBackground(){
        this.setBackground(CLASSIC_BACKGROUND_COLOR);
    }

    public void resetForeground(){
        this.setBackground(CLASSIC_FOREGROUND_COLOR);
    }
}
