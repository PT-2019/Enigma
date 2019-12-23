package editor.hud.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTextAreaUI;
import java.awt.*;

public class EnigmaTextAreaUI extends BasicTextAreaUI {

    private Color background;
    private Color foreground;
    private Color hoveredBackground;
    private Color hoveredForeground;
    private Color focusedBackground;
    private Color focusedForeground;
    private Color border;
    private Color hoveredBorder;
    private Color focusedBorder;
    private boolean hovered;
    private Cursor cursor;
    private int borderSize;
    private int hoveredBorderSize;
    private int focusedBorderSize;
    private boolean[] showedBorders;
    private boolean[] hoveredShowedBorders;
    private boolean[] focusedShowedBorders;
    private Font font;

    public EnigmaTextAreaUI(){
        this.background = EnigmaUIValues.ENIGMA_TEXTAREA_BACKGROUND;
        this.hoveredBackground = EnigmaUIValues.ENIGMA_TEXTAREA_HOVERED_BACKGROUND;
        this.focusedBackground = EnigmaUIValues.ENIGMA_TEXTAREA_FOCUSED_BACKGROUND;
        this.foreground = EnigmaUIValues.ENIGMA_TEXTAREA_FOREGROUND;
        this.hoveredForeground = EnigmaUIValues.ENIGMA_TEXTAREA_HOVERED_FOREGROUND;
        this.focusedForeground = EnigmaUIValues.ENIGMA_TEXTAREA_FOCUSED_FOREGROUND;
        this.border = EnigmaUIValues.ENIGMA_TEXTAREA_BORDER;
        this.hoveredBorder = EnigmaUIValues.ENIGMA_TEXTAREA_HOVERED_BORDER;
        this.focusedBorder = EnigmaUIValues.ENIGMA_TEXTAREA_FOCUSED_BORDER;
        this.borderSize = EnigmaUIValues.ENIGMA_TEXTAREA_BORDER_SIZE;
        this.hoveredBorderSize = EnigmaUIValues.ENIGMA_TEXTAREA_HOVERED_BORDER_SIZE;
        this.focusedBorderSize = EnigmaUIValues.ENIGMA_TEXTAREA_FOCUSED_BORDER_SIZE;
        this.showedBorders = EnigmaUIValues.ENIGMA_TEXTAREA_SHOWED_BORDERS;
        this.hoveredShowedBorders = EnigmaUIValues.ENIGMA_TEXTAREA_HOVERED_SHOWED_BORDERS;
        this.focusedShowedBorders = EnigmaUIValues.ENIGMA_TEXTAREA_FOCUSED_SHOWED_BORDERS;
        this.hovered = false;
        this.cursor = new Cursor(Cursor.TEXT_CURSOR);
        this.font = EnigmaUIValues.ENIGMA_FONT;
    }

    public void paintTextArea(Graphics g, JComponent c) {
        Graphics brush = g.create();
        JTextArea ta = (JTextArea) c;
        ta.setBorder(BorderFactory.createEmptyBorder());
        if(ta.hasFocus()){
            ta.setBackground(this.focusedBackground);
            ta.setForeground(this.focusedForeground);
            ta.setCaretColor(this.focusedForeground);
            if(this.focusedBorder != null){
                this.paintBorder(ta,this.focusedBorder,this.focusedBorderSize,this.focusedShowedBorders);
            }
        } else if(this.hovered){
            ta.setBackground(this.hoveredBackground);
            ta.setForeground(this.hoveredForeground);
            ta.setCaretColor(this.hoveredForeground);
            if(this.hoveredBorder != null){
                this.paintBorder(ta,this.hoveredBorder,this.hoveredBorderSize,this.hoveredShowedBorders);
            }
        } else {
            ta.setBackground(this.background);
            ta.setForeground(this.foreground);
            ta.setCaretColor(this.foreground);
            if(this.border != null){
                this.paintBorder(ta,this.border,this.borderSize,this.showedBorders);
            }
        }
        super.paint(brush,c);
    }

    private void paintBorder(JComponent c, Color borderColor, int borderSize, boolean[] showedBorders){
        int[] borders = new int[showedBorders.length];
        for (int i = 0; i < 4; i++) {
            if(showedBorders[i]) borders[i] = borderSize;
        }
        c.setBorder(BorderFactory.createMatteBorder(borders[EnigmaUIValues.TOP_BORDER],borders[EnigmaUIValues.LEFT_BORDER],borders[EnigmaUIValues.BOTTOM_BORDER],borders[EnigmaUIValues.RIGHT_BORDER],borderColor));
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public boolean[] getShowedBorders(){
        return this.showedBorders;
    }

    public boolean[] getHoveredShowedBorders(){
        return this.hoveredShowedBorders;
    }

    public boolean[] getFocusedShowedBorders(){
        return this.focusedShowedBorders;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public int getHoveredBorderSize() {
        return hoveredBorderSize;
    }

    public int getFocusedBorderSize() {
        return focusedBorderSize;
    }

    public void setAllBordersSize(int borderSize, int hoveredBorderSize, int focusedBorderSize){
        if(borderSize < 0 || hoveredBorderSize < 0 || focusedBorderSize < 0) throw new IllegalArgumentException("La taille des bordures ne peuvent être négatives");
        this.borderSize = borderSize;
        this.hoveredBorderSize = hoveredBorderSize;
        this.focusedBorderSize = focusedBorderSize;
    }

    public void setBorderSize(int borderSize) {
        if(borderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
        this.borderSize = borderSize;
    }

    public void setHoveredBorderSize(int hoveredBorderSize) {
        if(hoveredBorderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
        this.hoveredBorderSize = hoveredBorderSize;
    }

    public void setFocusedBorderSize(int focusedBorderSize) {
        if(focusedBorderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
        this.focusedBorderSize = focusedBorderSize;
    }

    public void setShowedBorders(boolean[] showedBorders) {
        if(showedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
        this.showedBorders = showedBorders;
    }

    public void setHoveredShowedBorders(boolean[] hoveredShowedBorders) {
        if(showedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
        this.hoveredShowedBorders = hoveredShowedBorders;
    }

    public void setFocusedShowedBorders(boolean[] focusedShowedBorders) {
        if(showedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
        this.focusedShowedBorders = focusedShowedBorders;
    }

    public void setAllShowedBorders(boolean[] showedBorders, boolean[] hoveredShowedBorders, boolean[] focusedShowedBorders){
        if(showedBorders.length != 4 || hoveredShowedBorders.length != 4 || focusedShowedBorders.length != 4) throw new IllegalArgumentException("Les tableaux doivent être de 4 éléments");
        this.showedBorders = showedBorders;
        this.hoveredShowedBorders = hoveredShowedBorders;
        this.focusedShowedBorders = focusedShowedBorders;
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

    public Color getFocusedBackground() {
        return focusedBackground;
    }

    public Color getFocusedForeground() {
        return focusedForeground;
    }

    public Color getBorder() {
        return border;
    }

    public Color getHoveredBorder() {
        return hoveredBorder;
    }

    public Color getFocusedBorder() {
        return focusedBorder;
    }

    public void setAllBackgrounds(Color background, Color hoveredBackground, Color focusedBackground){
        if(background == null || hoveredBackground == null || focusedBackground == null) throw  new NullPointerException("Les arguments ne peuvent pas être null");
        this.background = background;
        this.hoveredBackground = hoveredBackground;
        this.focusedBackground = focusedBackground;
    }

    public void setAllForegrounds(Color foreground, Color hoveredForeground, Color focusedForeground){
        if(foreground == null || hoveredForeground == null || focusedForeground == null) throw  new NullPointerException("Les arguments ne peuvent pas être null");
        this.foreground = foreground;
        this.hoveredForeground = hoveredForeground;
        this.focusedForeground = focusedForeground;
    }

    public void setAllBorders(Color border, Color hoveredBorder, Color focusedBorder){
        this.border = border;
        this.hoveredBorder = hoveredBorder;
        this.focusedBorder = focusedBorder;
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

    public void setFocusedBackground(Color focusedBackground) {
        if(focusedBackground == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.focusedBackground = focusedBackground;
    }

    public void setFocusedForeground(Color focusedForeground) {
        if(focusedForeground == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.focusedForeground = focusedForeground;
    }

    public void setBorder(Color border) {
        this.border = border;
    }

    public void setHoveredBorder(Color hoveredBorder) {
        this.hoveredBorder = hoveredBorder;
    }

    public void setFocusedBorder(Color focusedBorder) {
        this.focusedBorder = focusedBorder;
    }



    public EnigmaTextAreaUI duplicate() {
        EnigmaTextAreaUI clone = new EnigmaTextAreaUI();

        clone.setCursor(this.getCursor());
        clone.setAllBackgrounds(this.getBackground(), this.getHoveredBackground(), this.getFocusedBackground());
        clone.setAllForegrounds(this.getForeground(), this.getHoveredForeground(), this.getFocusedForeground());
        clone.setAllBorders(this.getBorder(), this.getHoveredBorder(), this.getFocusedBorder());
        clone.setIsHovered(this.isHovered());
        clone.setAllBordersSize(this.getBorderSize(), this.getHoveredBorderSize(), this.getFocusedBorderSize());
        clone.setAllShowedBorders(this.getShowedBorders(), this.getHoveredShowedBorders(), this.getFocusedShowedBorders());
        clone.setFont(this.getFont());

        return clone;
    }
}
