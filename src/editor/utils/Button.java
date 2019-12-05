package editor.utils;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    private static final Color CLASSIC_BACKGROUND_COLOR = Color.WHITE;
    private static final Color CLASSIC_FOREGROUND_COLOR = Color.BLACK;
    private static final Color CLASSIC_HOVER_BACKGROUND_COLOR = Color.BLACK;
    private static final Color CLASSIC_HOVER_FOREGROUND_COLOR = Color.WHITE;

    private Color background;
    private Color foreground;
    private Color hoverBackground;
    private Color hoverForeground;
    private boolean hovered;

    public Button(String title){
        super(title);
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setFocusable(false);
        this.setContentAreaFilled(false);
        this.setBackground(CLASSIC_BACKGROUND_COLOR);
        this.setForeground(CLASSIC_FOREGROUND_COLOR);
        this.hoverBackground = CLASSIC_HOVER_BACKGROUND_COLOR;
        this.hoverForeground = CLASSIC_HOVER_FOREGROUND_COLOR;
        this.addMouseListener(new ButtonMouseManagement(this));
    }

    @Override
    public void paintComponent(Graphics g) {
        if(hovered) {
            g.setColor(hoverBackground);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.setColor(foreground);
            g.drawString(this.getText(), (this.getWidth() - this.getText().length()) / 2,  this.getHeight() / 2);
        } else {
            g.setColor(background);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.setColor(foreground);
            g.drawString(this.getText(), (this.getWidth() / 2 - this.getText().length() * 2),  this.getHeight() / 2);
        }
    }

    public void on(){
        this.repaint();
        super.setForeground(this.hoverForeground);
    }

    public void out(){
        this.repaint();
        super.setForeground(this.foreground);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void pressed(){
        this.repaint();
        super.setForeground(this.hoverForeground);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void setBackground(Color color){
        this.background = color;
        this.repaint();
    }

    public void setForeground(Color color){
        this.foreground = color;
        super.setForeground(color);
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
