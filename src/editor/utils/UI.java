package editor.utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UI {

    public final static boolean DEFAULT_ENIGMA_UI = true;
    public final static boolean PERSONALIZED_UI = false;
    private Color background;
    private Color foreground;
    private Color hoveredBackground;
    private Color hoveredForeground;
    private Color pressedBackground;
    private Color pressedForeground;
    private Border border;
    private Border hoveredBorder;
    private Border pressedBorder;

    public UI(boolean defaultUI){
        if(defaultUI){
            this.background = Color.DARK_GRAY;
            this.foreground = Color.WHITE;
            this.hoveredBackground = Color.DARK_GRAY;
            this.hoveredForeground = Color.CYAN;
            this.pressedBackground = new Color(45, 45 ,45);
            this.pressedForeground = Color.CYAN;
            this.border = BorderFactory.createLineBorder(Color.WHITE);
            this.hoveredBorder = BorderFactory.createLineBorder(Color.CYAN);
            this.pressedBorder = BorderFactory.createLineBorder(Color.CYAN);
        }else{
            this.background = Color.WHITE;
            this.foreground = Color.BLACK;
            this.hoveredBackground = Color.WHITE;
            this.hoveredForeground = Color.BLACK;
            this.pressedBackground = Color.WHITE;
            this.pressedForeground = Color.BLACK;
            this.border = BorderFactory.createEmptyBorder();
            this.hoveredBorder = BorderFactory.createEmptyBorder();
            this.pressedBorder = BorderFactory.createEmptyBorder();
        }
    }

    public Color getBackground() {
        return background;
    }

    public Color getForeground() {
        return foreground;
    }

    public Color getHoveredBackground() {
        return hoveredBackground;
    }

    public Color getHoveredForeground() {
        return hoveredForeground;
    }

    public Color getPressedBackground() {
        return pressedBackground;
    }

    public Color getPressedForeground() {
        return pressedForeground;
    }

    public Border getBorder() {
        return border;
    }

    public Border getHoveredBorder() {
        return hoveredBorder;
    }

    public Border getPressedBorder() {
        return pressedBorder;
    }

    public void setAllBackgrounds(Color background, Color hoveredBackground, Color pressedBackground){
        if(background == null || hoveredBackground == null || pressedBackground == null) throw  new NullPointerException("Les arguments ne peuvent pas être null");
        this.background = background;
        this.hoveredBackground = hoveredBackground;
        this.pressedBackground = pressedBackground;
    }

    public void setAllForegrounds(Color foreground, Color hoveredForeground, Color pressedForeground){
        if(foreground == null || hoveredForeground == null || pressedBackground == null) throw  new NullPointerException("Les arguments ne peuvent pas être null");
        this.foreground = foreground;
        this.hoveredForeground = hoveredForeground;
        this.pressedForeground = pressedForeground;
    }

    public void setAllBorders(Color border, Color hoveredBorder, Color pressedBorder){
        this.border = BorderFactory.createLineBorder(border);
        this.hoveredBorder = BorderFactory.createLineBorder(hoveredBorder);
        this.pressedBorder = BorderFactory.createLineBorder(pressedBorder);
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public void setHoveredBackground(Color hoveredBackground) {
        this.hoveredBackground = hoveredBackground;
    }

    public void setHoveredForeground(Color hoveredForeground) {
        this.hoveredForeground = hoveredForeground;
    }

    public void setPressedBackground(Color pressedBackground) {
        this.pressedBackground = pressedBackground;
    }

    public void setPressedForeground(Color pressedForeground) {
        this.pressedForeground = pressedForeground;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    public void setHoveredBorder(Border hoveredBorder) {
        this.hoveredBorder = hoveredBorder;
    }

    public void setPressedBorder(Border pressedBorder) {
        this.pressedBorder = pressedBorder;
    }
}
