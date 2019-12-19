package editor.utils.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class EnigmaComboBoxUI extends BasicComboBoxUI {

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

    public EnigmaComboBoxUI(){
        this.background = EnigmaUIValues.ENIGMA_COMBOBOX_BACKGROUND;
        this.hoveredBackground = EnigmaUIValues.ENIGMA_COMBOBOX_HOVERED_BACKGROUND;
        this.focusedBackground = EnigmaUIValues.ENIGMA_COMBOBOX_FOCUSED_BACKGROUND;
        this.foreground = EnigmaUIValues.ENIGMA_COMBOBOX_FOREGROUND;
        this.hoveredForeground = EnigmaUIValues.ENIGMA_COMBOBOX_HOVERED_FOREGROUND;
        this.focusedForeground = EnigmaUIValues.ENIGMA_COMBOBOX_FOCUSED_FOREGROUND;
        this.border = EnigmaUIValues.ENIGMA_COMBOBOX_BORDER;
        this.hoveredBorder = EnigmaUIValues.ENIGMA_COMBOBOX_HOVERED_BORDER;
        this.focusedBorder = EnigmaUIValues.ENIGMA_COMBOBOX_FOCUSED_BORDER;
        this.borderSize = EnigmaUIValues.ENIGMA_COMBOBOX_BORDER_SIZE;
        this.hoveredBorderSize = EnigmaUIValues.ENIGMA_COMBOBOX_HOVERED_BORDER_SIZE;
        this.focusedBorderSize = EnigmaUIValues.ENIGMA_COMBOBOX_FOCUSED_BORDER_SIZE;
        this.showedBorders = EnigmaUIValues.ENIGMA_COMBOBOX_SHOWED_BORDERS;
        this.hoveredShowedBorders = EnigmaUIValues.ENIGMA_COMBOBOX_HOVERED_SHOWED_BORDERS;
        this.focusedShowedBorders = EnigmaUIValues.ENIGMA_COMBOBOX_FOCUSED_SHOWED_BORDERS;
        this.hovered = false;
        this.cursor = new Cursor(Cursor.TEXT_CURSOR);
    }



    public void paintComboBox(Graphics g, JComponent c) {
        Graphics brush = g.create();
        JComboBox cb = (JComboBox) c;
        if(cb.hasFocus()){
            cb.setBackground(this.focusedBackground);
            cb.setForeground(this.focusedForeground);
            if(this.focusedBorder != null){
                this.paintBorder(cb,this.focusedBorder,this.focusedBorderSize,this.focusedShowedBorders);
            }
        } else if(this.hovered){
            cb.setBackground(this.hoveredBackground);
            cb.setForeground(this.hoveredForeground);
            if(this.hoveredBorder != null){
                this.paintBorder(cb,this.hoveredBorder,this.hoveredBorderSize,this.hoveredShowedBorders);
            }
        } else {
            cb.setBackground(this.background);
            cb.setForeground(this.foreground);
            if(this.border != null){
                this.paintBorder(cb,this.border,this.borderSize,this.showedBorders);
            }
        }
        super.paint(brush,cb);
    }

    private void paintBorder(JComponent c, Color borderColor, int borderSize, boolean[] showedBorders){
        int[] borders = new int[showedBorders.length];
        for (int i = 0; i < 4; i++) {
            if(showedBorders[i]) borders[i] = borderSize;
        }
        c.setBorder(BorderFactory.createMatteBorder(borders[0],borders[3],borders[2],borders[2],borderColor));
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



    public EnigmaComboBoxUI duplicate() {
        EnigmaComboBoxUI clone = new EnigmaComboBoxUI();

        clone.setCursor(this.getCursor());
        clone.setAllBackgrounds(this.getBackground(), this.getHoveredBackground(), this.getFocusedBackground());
        clone.setAllForegrounds(this.getForeground(), this.getHoveredForeground(), this.getFocusedForeground());
        clone.setAllBorders(this.getBorder(), this.getHoveredBorder(), this.getFocusedBorder());
        clone.setIsHovered(this.isHovered());
        clone.setAllBordersSize(this.getBorderSize(), this.getHoveredBorderSize(), this.getFocusedBorderSize());
        clone.setAllShowedBorders(this.getShowedBorders(), this.getHoveredShowedBorders(), this.getFocusedShowedBorders());

        return clone;
    }
}
