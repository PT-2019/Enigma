package editor.utils.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;

public class EnigmaLabelUI extends BasicLabelUI {

    private Color background;
    private Color foreground;
    private Color hoveredBackground;
    private Color hoveredForeground;
    private Color pressedBackground;
    private Color pressedForeground;
    private Color border;
    private Color hoveredBorder;
    private Color pressedBorder;
    private boolean hovered;
    private boolean pressed;
    private Cursor cursor;
    private int borderSize;
    private int hoveredBorderSize;
    private int pressedBorderSize;
    private boolean[] showedBorders;
    private boolean[] hoveredShowedBorders;
    private boolean[] pressedShowedBorders;

    public EnigmaLabelUI(){
        this.background = EnigmaUIValues.ENIGMA_LABEL_BACKGROUND;
        this.hoveredBackground = EnigmaUIValues.ENIGMA_LABEL_HOVERED_BACKGROUND;
        this.pressedBackground = EnigmaUIValues.ENIGMA_LABEL_PRESSED_BACKGROUND;
        this.foreground = EnigmaUIValues.ENIGMA_LABEL_FOREGROUND;
        this.hoveredForeground = EnigmaUIValues.ENIGMA_LABEL_HOVERED_FOREGROUND;
        this.pressedForeground = EnigmaUIValues.ENIGMA_LABEL_PRESSED_FOREGROUND;
        this.border = EnigmaUIValues.ENIGMA_LABEL_BORDER;
        this.hoveredBorder = EnigmaUIValues.ENIGMA_LABEL_HOVERED_BORDER;
        this.pressedBorder = EnigmaUIValues.ENIGMA_LABEL_PRESSED_BORDER;
        this.borderSize = EnigmaUIValues.ENIGMA_LABEL_BORDER_SIZE;
        this.hoveredBorderSize = EnigmaUIValues.ENIGMA_LABEL_HOVERED_BORDER_SIZE;
        this.pressedBorderSize = EnigmaUIValues.ENIGMA_LABEL_PRESSED_BORDER_SIZE;
        this.showedBorders = EnigmaUIValues.ENIGMA_LABEL_SHOWED_BORDERS;
        this.hoveredShowedBorders = EnigmaUIValues.ENIGMA_LABEL_HOVERED_SHOWED_BORDERS;
        this.pressedShowedBorders = EnigmaUIValues.ENIGMA_LABEL_PRESSED_SHOWED_BORDERS;
        this.hovered = false;
        this.pressed = false;
        this.cursor = new Cursor(Cursor.HAND_CURSOR);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics brush = g.create();
        JLabel l = (JLabel) c;
        if(this.pressed){
            brush.setColor(this.pressedBackground);
            brush.fillRect(0,0,l.getWidth(),l.getHeight());
            l.setForeground(this.pressedForeground);
            if(this.pressedBorder != null){
                brush.setColor(this.pressedBorder);
                for (int i = 0; i < 4; i++) {
                    if(i == EnigmaUIValues.TOP_BORDER && this.pressedShowedBorders[i]) brush.fillRect(0,0,l.getWidth(),this.pressedBorderSize);
                    if(i == EnigmaUIValues.RIGHT_BORDER && this.pressedShowedBorders[i]) brush.fillRect(l.getWidth() - this.pressedBorderSize,0,l.getWidth(),l.getHeight());
                    if(i == EnigmaUIValues.BOTTOM_BORDER && this.pressedShowedBorders[i]) brush.fillRect(0,l.getHeight() - this.pressedBorderSize,l.getWidth(),l.getHeight());
                    if(i == EnigmaUIValues.LEFT_BORDER && this.pressedShowedBorders[i]) brush.fillRect(0,0,this.pressedBorderSize,l.getHeight());
                }
            }
        } else if(this.hovered){
            brush.setColor(this.hoveredBackground);
            brush.fillRect(0,0,l.getWidth(),l.getHeight());
            l.setForeground(this.hoveredForeground);
            if(this.hoveredBorder != null){
                brush.setColor(this.hoveredBorder);
                for (int i = 0; i < 4; i++) {
                    if(i == EnigmaUIValues.TOP_BORDER && this.hoveredShowedBorders[i]) brush.fillRect(0,0,l.getWidth(),this.hoveredBorderSize);
                    if(i == EnigmaUIValues.RIGHT_BORDER && this.hoveredShowedBorders[i]) brush.fillRect(l.getWidth() - this.hoveredBorderSize,0,l.getWidth(),l.getHeight());
                    if(i == EnigmaUIValues.BOTTOM_BORDER && this.hoveredShowedBorders[i]) brush.fillRect(0,l.getHeight() - this.hoveredBorderSize,l.getWidth(),l.getHeight());
                    if(i == EnigmaUIValues.LEFT_BORDER && this.hoveredShowedBorders[i]) brush.fillRect(0,0,this.hoveredBorderSize,l.getHeight());
                }
            }
        } else {
            brush.setColor(this.background);
            brush.fillRect(0,0,l.getWidth(),l.getHeight());
            l.setForeground(this.foreground);
            if(this.border != null){
                brush.setColor(this.border);
                for (int i = 0; i < 4; i++) {
                    if(i == EnigmaUIValues.TOP_BORDER && this.showedBorders[i]) brush.fillRect(0,0,l.getWidth(),this.borderSize);
                    if(i == EnigmaUIValues.RIGHT_BORDER && this.showedBorders[i]) brush.fillRect(l.getWidth() - this.borderSize,0,l.getWidth(),l.getHeight());
                    if(i == EnigmaUIValues.BOTTOM_BORDER && this.showedBorders[i]) brush.fillRect(0,l.getHeight() - this.borderSize,l.getWidth(),l.getHeight());
                    if(i == EnigmaUIValues.LEFT_BORDER && this.showedBorders[i]) brush.fillRect(0,0,this.borderSize,l.getHeight());
                }
            }
        }
        super.paint(brush,c);
    }

    public boolean[] getShowedBorders(){
        return this.showedBorders;
    }

    public boolean[] getHoveredShowedBorders(){
        return this.hoveredShowedBorders;
    }

    public boolean[] getPressedShowedBorders(){
        return this.pressedShowedBorders;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public int getHoveredBorderSize() {
        return hoveredBorderSize;
    }

    public int getPressedBorderSize() {
        return pressedBorderSize;
    }

    public void setAllBordersSize(int borderSize, int hoveredBorderSize, int pressedBorderSize){
        if(borderSize < 0 || hoveredBorderSize < 0 || pressedBorderSize < 0) throw new IllegalArgumentException("La taille des bordures ne peuvent être négatives");
        this.borderSize = borderSize;
        this.hoveredBorderSize = hoveredBorderSize;
        this.pressedBorderSize = pressedBorderSize;
    }

    public void setBorderSize(int borderSize) {
        if(borderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
        this.borderSize = borderSize;
    }

    public void setHoveredBorderSize(int hoveredBorderSize) {
        if(hoveredBorderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
        this.hoveredBorderSize = hoveredBorderSize;
    }

    public void setPressedBorderSize(int pressedBorderSize) {
        if(pressedBorderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
        this.pressedBorderSize = pressedBorderSize;
    }

    public void setShowedBorders(boolean[] showedBorders) {
        if(showedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
        this.showedBorders = showedBorders;
    }

    public void setHoveredShowedBorders(boolean[] hoveredShowedBorders) {
        if(showedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
        this.hoveredShowedBorders = hoveredShowedBorders;
    }

    public void setPressedShowedBorders(boolean[] pressedShowedBorders) {
        if(showedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
        this.pressedShowedBorders = pressedShowedBorders;
    }

    public void setAllShowedBorders(boolean[] showedBorders, boolean[] hoveredShowedBorders, boolean[] pressedShowedBorders){
        if(showedBorders.length != 4 || hoveredShowedBorders.length != 4 || pressedShowedBorders.length != 4) throw new IllegalArgumentException("Les tableaux doivent être de 4 éléments");
        this.showedBorders = showedBorders;
        this.hoveredShowedBorders = hoveredShowedBorders;
        this.pressedShowedBorders = pressedShowedBorders;
    }

    public void setCursor(Cursor cursor){
        this.cursor = cursor;
    }

    public Cursor getCursor(){
        return this.cursor;
    }

    public void setIsHovered(boolean isHovered){
        this.hovered = isHovered;
    }

    public boolean isHovered() {
        return hovered;
    }

    public void setIsPressed(boolean isPressed){
        this.pressed = isPressed;
    }

    public boolean isPressed() {
        return pressed;
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

    public Color getBorder() {
        return border;
    }

    public Color getHoveredBorder() {
        return hoveredBorder;
    }

    public Color getPressedBorder() {
        return pressedBorder;
    }

    public void setAllBackgrounds(Color background, Color hoveredBackground, Color pressedBackground){
        if(background == null || hoveredBackground == null || pressedBackground == null) throw  new NullPointerException("Les arguments ne peuvent pas être null");
        this.background = background;
        this.hoveredBackground = hoveredBackground;
        this.pressedBackground = pressedBackground;
    }

    public void setAllForegrounds(Color foreground, Color hoveredForeground, Color pressedForeground){
        if(foreground == null || hoveredForeground == null || pressedForeground == null) throw  new NullPointerException("Les arguments ne peuvent pas être null");
        this.foreground = foreground;
        this.hoveredForeground = hoveredForeground;
        this.pressedForeground = pressedForeground;
    }

    public void setAllBorders(Color border, Color hoveredBorder, Color pressedBorder){
        this.border = border;
        this.hoveredBorder = hoveredBorder;
        this.pressedBorder = pressedBorder;
    }

    public void setBackground(Color background) {
        if(background == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.background = background;
    }

    public void setForeground(Color foreground) {
        if(foreground == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.foreground = foreground;
    }

    public void setHoveredBackground(Color hoveredBackground) {
        if(hoveredBackground == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.hoveredBackground = hoveredBackground;
    }

    public void setHoveredForeground(Color hoveredForeground) {
        if(hoveredForeground == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.hoveredForeground = hoveredForeground;
    }

    public void setPressedBackground(Color pressedBackground) {
        if(pressedBackground == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.pressedBackground = pressedBackground;
    }

    public void setPressedForeground(Color pressedForeground) {
        if(pressedForeground == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.pressedForeground = pressedForeground;
    }

    public void setBorder(Color border) {
        this.border = border;
    }

    public void setHoveredBorder(Color hoveredBorder) {
        this.hoveredBorder = hoveredBorder;
    }

    public void setPressedBorder(Color pressedBorder) {
        this.pressedBorder = pressedBorder;
    }



    public EnigmaLabelUI duplicate() {
        EnigmaLabelUI clone = new EnigmaLabelUI();

        clone.setCursor(this.getCursor());
        clone.setAllBackgrounds(this.getBackground(), this.getHoveredBackground(), this.getPressedBackground());
        clone.setAllForegrounds(this.getForeground(), this.getHoveredForeground(), this.getPressedForeground());
        clone.setAllBorders(this.getBorder(), this.getHoveredBorder(), this.getPressedBorder());
        clone.setIsHovered(this.isHovered());
        clone.setAllBordersSize(this.getBorderSize(), this.getHoveredBorderSize(), this.getPressedBorderSize());
        clone.setAllShowedBorders(this.getShowedBorders(), this.getHoveredShowedBorders(), this.getPressedShowedBorders());

        return clone;
    }
}
