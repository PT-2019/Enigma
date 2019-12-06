package editor.utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JLabel {

    private static final Color CLASSIC_BACKGROUND_COLOR = Color.WHITE;
    private static final Color CLASSIC_FOREGROUND_COLOR = Color.BLACK;
    private static final Color CLASSIC_HOVERED_BACKGROUND_COLOR = Color.BLACK;
    private static final Color CLASSIC_HOVERED_FOREGROUND_COLOR = Color.WHITE;
    private static final Color CLASSIC_PRESSED_BACKGROUND_COLOR = Color.BLACK;
    private static final Color CLASSIC_PRESSED_FOREGROUND_COLOR = Color.WHITE;

    private Color background;
    private Color foreground;
    private Color hoveredBackground;
    private Color hoveredForeground;
    private Color pressedBackground;
    private Color pressedForeground;
    private Border border;
    private Border hoveredBorder;
    private Border pressedBorder;
    private boolean onIt;
    private boolean clickable;
    private ActionListener actionListener;

    public Button(String title){
        super(title);
        this.setOpaque(true);
        this.onIt = false;
        this.clickable = true;
        this.setHorizontalAlignment(JLabel.CENTER);
        this.background = CLASSIC_BACKGROUND_COLOR;
        this.foreground = CLASSIC_FOREGROUND_COLOR;
        this.hoveredBackground = CLASSIC_HOVERED_BACKGROUND_COLOR;
        this.hoveredForeground = CLASSIC_HOVERED_FOREGROUND_COLOR;
        this.pressedBackground = CLASSIC_PRESSED_BACKGROUND_COLOR;
        this.pressedForeground = CLASSIC_PRESSED_FOREGROUND_COLOR;
        this.border = BorderFactory.createEmptyBorder();
        this.hoveredBorder = BorderFactory.createEmptyBorder();
        this.pressedBorder = BorderFactory.createEmptyBorder();

        this.addMouseListener(new ButtonManager(this));
        this.setBackground(background);
        this.setForeground(foreground);
    }

    public Button(String title, Color background, Color foreground, Color hoveredBackground, Color hoveredForeground, Color pressedBackground, Color pressedForeground){
        super(title);
        this.setOpaque(true);
        this.onIt = false;
        this.clickable = true;
        this.setHorizontalAlignment(JLabel.CENTER);
        this.background = background;
        this.foreground = foreground;
        this.hoveredBackground = hoveredBackground;
        this.hoveredForeground = hoveredForeground;
        this.pressedBackground = pressedBackground;
        this.pressedForeground = pressedForeground;
        this.border = BorderFactory.createEmptyBorder();
        this.hoveredBorder = BorderFactory.createEmptyBorder();
        this.pressedBorder = BorderFactory.createEmptyBorder();

        this.addMouseListener(new ButtonManager(this));
        this.setBackground(background);
        this.setForeground(foreground);
    }

    public void setClickable(boolean clickable){
        this.clickable = clickable;
    }

    public void setActionListener(ActionListener actionListener){
        this.actionListener = actionListener;
    }

    void clicked(){
        if(this.clickable) {
            this.actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, this.getText()));
        }
    }

     void exited(){
         if(this.clickable) {
             this.onIt = false;
             this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
             this.setBackground(this.background);
             this.setForeground(this.foreground);
             this.setBorder(this.border);
         }
    }

    void hovered(){
        if(this.clickable){
            this.onIt = true;
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.setBackground(this.hoveredBackground);
            this.setForeground(this.hoveredForeground);
            this.setBorder(this.hoveredBorder);
        }
    }

    void pressed(){
        if(this.clickable) {
            this.setBackground(this.pressedBackground);
            this.setForeground(this.pressedForeground);
            this.setBorder(this.pressedBorder);
        }
    }

    void released(){
        if(this.clickable) {
            if (this.onIt) {
                this.setBackground(this.hoveredBackground);
                this.setForeground(this.hoveredForeground);
                this.setBorder(this.hoveredBorder);
            } else {
                this.setBackground(this.background);
                this.setForeground(this.foreground);
                this.setBorder(this.border);
            }
        }
    }

    public void defineBorder(Color color){
        this.border = BorderFactory.createLineBorder(color);
        this.setBorder(this.border);
    }

    public void defineAllColorBorder(Color color, Color hoveredColor, Color pressedColor){
        this.border = BorderFactory.createLineBorder(color);
        this.hoveredBorder = BorderFactory.createLineBorder(hoveredColor);
        this.pressedBorder = BorderFactory.createLineBorder(pressedColor);
        this.setBorder(this.border );
    }

    public void resetBorders(){
        this.border = BorderFactory.createEmptyBorder();
        this.hoveredBorder = BorderFactory.createEmptyBorder();
        this.pressedBorder = BorderFactory.createEmptyBorder();
    }

    public void defineBackground(Color color){
        this.background = color;
        this.setBackground(this.background);
    }

    public void defineForeground(Color color){
        this.foreground = color;
        this.setForeground(this.foreground);
    }

    public void setHoverBackground(Color color){
        this.hoveredBackground = color;
    }

    public void setHoverForeground(Color color){
        this.hoveredForeground = color;
    }

    public void resetBackground(){
        this.setBackground(CLASSIC_BACKGROUND_COLOR);
    }

    public void resetForeground(){
        this.setBackground(CLASSIC_FOREGROUND_COLOR);
    }

    public void resetHoveredBackground(){
        this.hoveredBackground = CLASSIC_HOVERED_BACKGROUND_COLOR;
    }

    public void resetHoveredForeground(){
        this.hoveredForeground = CLASSIC_HOVERED_FOREGROUND_COLOR;
    }

    public void resetPressedBackground(){
        this.pressedBackground = CLASSIC_PRESSED_BACKGROUND_COLOR;
    }

    public void resetPressedForeground(){
        this.pressedForeground = CLASSIC_PRESSED_FOREGROUND_COLOR;
    }
}
