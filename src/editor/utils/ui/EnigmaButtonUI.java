package editor.utils.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.util.Arrays;

public class EnigmaButtonUI extends BasicButtonUI {

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
    private Cursor cursor;
    private int borderSize;
    private int hoveredBorderSize;
    private int pressedBorderSize;
    private boolean[] showedBorders;
    private boolean[] hoveredShowedBorders;
    private boolean[] pressedShowedBorders;

    private Color selectedBackground;
    private Color selectedForeground;
    private Color selectedHoveredBackground;
    private Color selectedHoveredForeground;
    private Color selectedPressedBackground;
    private Color selectedPressedForeground;
    private Color selectedBorder;
    private Color selectedHoveredBorder;
    private Color selectedPressedBorder;
    private boolean selected;
    private int selectedBorderSize;
    private int selectedHoveredBorderSize;
    private int selectedPressedBorderSize;
    private boolean[] selectedShowedBorders;
    private boolean[] selectedHoveredShowedBorders;
    private boolean[] selectedPressedShowedBorders;



    public EnigmaButtonUI(){
        this.background = EnigmaUIValues.ENIGMA_BUTTON_BACKGROUND;
        this.hoveredBackground = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_BACKGROUND;
        this.pressedBackground = EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BACKGROUND;
        this.foreground = EnigmaUIValues.ENIGMA_BUTTON_FOREGROUND;
        this.hoveredForeground = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_FOREGROUND;
        this.pressedForeground = EnigmaUIValues.ENIGMA_BUTTON_PRESSED_FOREGROUND;
        this.border = EnigmaUIValues.ENIGMA_BUTTON_BORDER;
        this.hoveredBorder = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_BORDER;
        this.pressedBorder = EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BORDER;
        this.borderSize = EnigmaUIValues.ENIGMA_BUTTON_BORDER_SIZE;
        this.hoveredBorderSize = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_BORDER_SIZE;
        this.pressedBorderSize = EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BORDER_SIZE;
        this.showedBorders = EnigmaUIValues.ENIGMA_BUTTON_SHOWED_BORDERS;
        this.hoveredShowedBorders = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_SHOWED_BORDERS;
        this.pressedShowedBorders = EnigmaUIValues.ENIGMA_BUTTON_PRESSED_SHOWED_BORDERS;
        this.hovered = false;
        this.cursor = new Cursor(Cursor.HAND_CURSOR);

        this.selectedBackground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_BACKGROUND;
        this.selectedHoveredBackground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_HOVERED_BACKGROUND;
        this.selectedPressedBackground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_PRESSED_BACKGROUND;
        this.selectedForeground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_FOREGROUND;
        this.selectedHoveredForeground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_HOVERED_FOREGROUND;
        this.selectedPressedForeground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_PRESSED_FOREGROUND;
        this.selectedBorder = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_BORDER;
        this.selectedHoveredBorder = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_HOVERED_BORDER;
        this.selectedPressedBorder = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_PRESSED_BORDER;
        this.selectedBorderSize = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_BORDER_SIZE;
        this.selectedHoveredBorderSize = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_HOVERED_BORDER_SIZE;
        this.selectedPressedBorderSize = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_PRESSED_BORDER_SIZE;
        this.selectedShowedBorders = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_SHOWED_BORDERS;
        this.selectedHoveredShowedBorders = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_HOVERED_SHOWED_BORDERS;
        this.selectedPressedShowedBorders = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_PRESSED_SHOWED_BORDERS;
        this.selected = false;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics brush = g.create();
        JButton b = (JButton)c;
        if(this.hovered){
            if(this.selected){
                b.setBackground(this.selectedHoveredBackground);
                b.setForeground(this.selectedHoveredForeground);
                if(this.selectedHoveredBorder != null)
                    this.paintBorder(brush,b,this.selectedHoveredBorder,this.selectedHoveredBorderSize,this.selectedHoveredShowedBorders);
            }else{
                b.setBackground(this.hoveredBackground);
                b.setForeground(this.hoveredForeground);
                if(this.hoveredBorder != null)
                    this.paintBorder(brush,b,this.hoveredBorder,this.hoveredBorderSize,this.hoveredShowedBorders);
            }
        } else {
            if(this.selected){
                b.setBackground(this.selectedBackground);
                b.setForeground(this.selectedForeground);
                if(this.selectedBorder != null)
                    this.paintBorder(brush,b,this.selectedBorder,this.selectedBorderSize,this.selectedShowedBorders);
            }else{
                b.setBackground(this.background);
                b.setForeground(this.foreground);
                if(this.border != null)
                    this.paintBorder(brush,b,this.border,this.borderSize,this.showedBorders);
            }
        }
        super.paint(brush, c);
    }

    @Override
    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {}

    @Override
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        Graphics brush = g.create();
        if(this.selected){
            b.setBackground(this.selectedPressedBackground);
            b.setForeground(this.selectedPressedForeground);
            if(this.selectedPressedBorder != null)
                this.paintBorder(brush,b,this.selectedPressedBorder,this.selectedPressedBorderSize,this.selectedPressedShowedBorders);
            super.paintButtonPressed(brush, b);
        }else{
            b.setBackground(this.pressedBackground);
            b.setForeground(this.pressedForeground);
            if(this.pressedBorder != null)
                this.paintBorder(brush,b,this.pressedBorder,this.pressedBorderSize,this.pressedShowedBorders);
            super.paintButtonPressed(brush, b);
        }
    }

    private void paintBorder(Graphics g, AbstractButton b, Color borderColor, int borderSize, boolean[] showedBorders){
        g.setColor(borderColor);
        for (int i = 0; i < 4; i++) {
            if(i == EnigmaUIValues.TOP_BORDER && showedBorders[i]) g.fillRect(0,0,b.getWidth(),borderSize);
            if(i == EnigmaUIValues.RIGHT_BORDER && showedBorders[i]) g.fillRect(b.getWidth() - borderSize,0,b.getWidth(),b.getHeight());
            if(i == EnigmaUIValues.BOTTOM_BORDER && showedBorders[i]) g.fillRect(0,b.getHeight() - borderSize,b.getWidth(),b.getHeight());
            if(i == EnigmaUIValues.LEFT_BORDER && showedBorders[i]) g.fillRect(0,0,borderSize,b.getHeight());
        }
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
        return this.borderSize;
    }

    public int getHoveredBorderSize() {
        return this.hoveredBorderSize;
    }

    public int getPressedBorderSize() {
        return this.pressedBorderSize;
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
        if(hoveredShowedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
        this.hoveredShowedBorders = hoveredShowedBorders;
    }

    public void setPressedShowedBorders(boolean[] pressedShowedBorders) {
        if(pressedShowedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
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

    public boolean[] getSelectedShowedBorders(){
        return this.selectedShowedBorders;
    }

    public boolean[] getSelectedHoveredShowedBorders(){
        return this.selectedHoveredShowedBorders;
    }

    public boolean[] getSelectedPressedShowedBorders(){
        return this.selectedPressedShowedBorders;
    }

    public int getSelectedBorderSize() {
        return this.selectedBorderSize;
    }

    public int getSelectedHoveredBorderSize() {
        return this.selectedHoveredBorderSize;
    }

    public int getSelectedPressedBorderSize() {
        return this.selectedPressedBorderSize;
    }

    public void setAllSelectedBordersSize(int selectedBorderSize, int selectedHoveredBorderSize, int selectedPressedBorderSize){
        if(selectedBorderSize < 0 || selectedHoveredBorderSize < 0 || selectedPressedBorderSize < 0) throw new IllegalArgumentException("La taille des bordures ne peuvent être négatives");
        this.selectedBorderSize = selectedBorderSize;
        this.selectedHoveredBorderSize = selectedHoveredBorderSize;
        this.selectedPressedBorderSize = selectedPressedBorderSize;
    }

    public void setSelectedBorderSize(int selectedBorderSize) {
        if(borderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
        this.selectedBorderSize = selectedBorderSize;
    }

    public void setSelectedHoveredBorderSize(int selectedHoveredBorderSize) {
        if(selectedHoveredBorderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
        this.selectedHoveredBorderSize = selectedHoveredBorderSize;
    }

    public void setSelectedPressedBorderSize(int selectedPressedBorderSize) {
        if(selectedPressedBorderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
        this.selectedPressedBorderSize = selectedPressedBorderSize;
    }

    public void setSelectedShowedBorders(boolean[] selectedShowedBorders) {
        if(selectedShowedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
        this.selectedShowedBorders = selectedShowedBorders;
    }

    public void setSelectedHoveredShowedBorders(boolean[] selectedHoveredShowedBorders) {
        if(selectedHoveredShowedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
        this.selectedHoveredShowedBorders = selectedHoveredShowedBorders;
    }

    public void setSelectedPressedShowedBorders(boolean[] selectedPressedShowedBorders) {
        if(selectedPressedShowedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
        this.selectedPressedShowedBorders = selectedPressedShowedBorders;
    }

    public void setAllSelectedShowedBorders(boolean[] selectedShowedBorders, boolean[] selectedHoveredShowedBorders, boolean[] selectedPressedShowedBorders){
        if(selectedShowedBorders.length != 4 || selectedHoveredShowedBorders.length != 4 || selectedPressedShowedBorders.length != 4) throw new IllegalArgumentException("Les tableaux doivent être de 4 éléments");
        this.selectedShowedBorders = selectedShowedBorders;
        this.selectedHoveredShowedBorders = selectedHoveredShowedBorders;
        this.selectedPressedShowedBorders = selectedPressedShowedBorders;
    }

    public void setIsSelected(boolean isSelected){
        this.selected = isSelected;
    }

    public boolean isSelected() {
        return selected;
    }

    public Color getSelectedBackground() {
        return selectedBackground;
    }

    public Color getSelectedForeground() {
        return selectedForeground;
    }

    public Color getSelectedHoveredBackground() {
        return selectedHoveredBackground;
    }

    public Color getSelectedHoveredForeground() {
        return selectedHoveredForeground;
    }

    public Color getSelectedPressedBackground() {
        return selectedPressedBackground;
    }

    public Color getSelectedPressedForeground() {
        return selectedPressedForeground;
    }

    public Color getSelectedBorder() {
        return selectedBorder;
    }

    public Color getSelectedHoveredBorder() {
        return selectedHoveredBorder;
    }

    public Color getSelectedPressedBorder() {
        return selectedPressedBorder;
    }

    public void setAllSelectedBackgrounds(Color selectedBackground, Color selectedHoveredBackground, Color selectedPressedBackground){
        if(selectedBackground == null || hoveredBackground == null || selectedPressedBackground == null) throw  new NullPointerException("Les arguments ne peuvent pas être null");
        this.selectedBackground = selectedBackground;
        this.selectedHoveredBackground = selectedHoveredBackground;
        this.selectedPressedBackground = selectedPressedBackground;
    }

    public void setAllSelectedForegrounds(Color selectedForeground, Color selectedHoveredForeground, Color selectedPressedForeground){
        if(selectedForeground == null || selectedHoveredForeground == null || selectedPressedForeground == null) throw  new NullPointerException("Les arguments ne peuvent pas être null");
        this.selectedForeground = selectedForeground;
        this.selectedHoveredForeground = selectedHoveredForeground;
        this.selectedPressedForeground = selectedPressedForeground;
    }

    public void setAllSelectedBorders(Color selectedBorder, Color selectedHoveredBorder, Color selectedPressedBorder){
        this.selectedBorder = selectedBorder;
        this.selectedHoveredBorder = selectedHoveredBorder;
        this.selectedPressedBorder = selectedPressedBorder;
    }

    public void setSelectedBackground(Color selectedBackground) {
        if(selectedBackground == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.selectedBackground = selectedBackground;
    }

    public void setSelectedForeground(Color selectedForeground) {
        if(selectedForeground == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.selectedForeground = selectedForeground;
    }

    public void setSelectedHoveredBackground(Color selectedHoveredBackground) {
        if(selectedHoveredBackground == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.selectedHoveredBackground = selectedHoveredBackground;
    }

    public void setSelectedHoveredForeground(Color selectedHoveredForeground) {
        if(selectedHoveredForeground == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.selectedHoveredForeground = selectedHoveredForeground;
    }

    public void setSelectedPressedBackground(Color selectedPressedBackground) {
        if(selectedPressedBackground == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.selectedPressedBackground = selectedPressedBackground;
    }

    public void setSelectedPressedForeground(Color selectedPressedForeground) {
        if(selectedPressedForeground == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.selectedPressedForeground = selectedPressedForeground;
    }

    public void setSelectedBorder(Color selectedBorder) {
        this.selectedBorder = selectedBorder;
    }

    public void setSelectedHoveredBorder(Color selectedHoveredBorder) {
        this.selectedHoveredBorder = selectedHoveredBorder;
    }

    public void setSelectedPressedBorder(Color selectedPressedBorder) {
        this.selectedPressedBorder = selectedPressedBorder;
    }

    public EnigmaButtonUI duplicate() {
        EnigmaButtonUI clone = new EnigmaButtonUI();

        clone.setCursor(this.getCursor());
        clone.setAllBackgrounds(this.getBackground(), this.getHoveredBackground(), this.getPressedBackground());
        clone.setAllForegrounds(this.getForeground(), this.getHoveredForeground(), this.getPressedForeground());
        clone.setAllBorders(this.getBorder(), this.getHoveredBorder(), this.getPressedBorder());
        clone.setAllBordersSize(this.getBorderSize(), this.getHoveredBorderSize(), this.getPressedBorderSize());
        clone.setAllShowedBorders(this.getShowedBorders(), this.getHoveredShowedBorders(), this.getPressedShowedBorders());

        clone.setAllSelectedBackgrounds(this.getBackground(), this.getHoveredBackground(), this.getPressedBackground());
        clone.setAllSelectedForegrounds(this.getForeground(), this.getHoveredForeground(), this.getPressedForeground());
        clone.setAllSelectedBorders(this.getBorder(), this.getHoveredBorder(), this.getPressedBorder());
        clone.setAllSelectedBordersSize(this.getBorderSize(), this.getHoveredBorderSize(), this.getPressedBorderSize());
        clone.setAllSelectedShowedBorders(this.getShowedBorders(), this.getHoveredShowedBorders(), this.getPressedShowedBorders());

        return clone;
    }
}
