package editor.utils.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class ButtonUI extends BasicButtonUI {

    public final static Color ENIGMA_BUTTON_BACKGROUND = Color.DARK_GRAY;
    public final static Color ENIGMA_BUTTON_HOVERED_BACKGROUND = Color.DARK_GRAY;
    public final static Color ENIGMA_BUTTON_PRESSED_BACKGROUND = new Color(100,100,100);
    public final static Color ENIGMA_BUTTON_FOREGROUND = Color.WHITE;
    public final static Color ENIGMA_BUTTON_HOVERED_FOREGROUND = Color.CYAN;
    public final static Color ENIGMA_BUTTON_PRESSED_FOREGROUND = Color.CYAN;
    public final static Color ENIGMA_BUTTON_BORDER = Color.WHITE;
    public final static Color ENIGMA_BUTTON_HOVERED_BORDER = Color.CYAN;
    public final static Color ENIGMA_BUTTON_PRESSED_BORDER = Color.CYAN;
    public final static int ENIGMA_BUTTON_BORDER_SIZE = 1;
    public final static boolean[] ENIGMA_BUTTON_SHOWED_BORDERS = {true,true,true,true};

    public final static boolean[] ALL_BORDERS_SHOWED = {true,true,true,true};
    public final static boolean[] ALL_BORDER_HIDDEN = {false,false,false,false};
    public final static boolean SHOWED_BORDER = true;
    public final static boolean HIDDEN_BORDER = false;
    public final static int TOP_BORDER = 0;
    public final static int RIGHT_BORDER = 1;
    public final static int BOTTOM_BORDER = 2;
    public final static int LEFT_BORDER = 3;

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
    private boolean[] showedBorders;

    public ButtonUI(){
        this.background = ENIGMA_BUTTON_BACKGROUND;
        this.hoveredBackground = ENIGMA_BUTTON_HOVERED_BACKGROUND;
        this.pressedBackground = ENIGMA_BUTTON_PRESSED_BACKGROUND;
        this.foreground = ENIGMA_BUTTON_FOREGROUND;
        this.hoveredForeground = ENIGMA_BUTTON_HOVERED_FOREGROUND;
        this.pressedForeground = ENIGMA_BUTTON_PRESSED_FOREGROUND;
        this.border = ENIGMA_BUTTON_BORDER;
        this.hoveredBorder = ENIGMA_BUTTON_HOVERED_BORDER;
        this.pressedBorder = ENIGMA_BUTTON_PRESSED_BORDER;
        this.borderSize = ENIGMA_BUTTON_BORDER_SIZE;
        this.showedBorders = ENIGMA_BUTTON_SHOWED_BORDERS;
        this.hovered = false;
        this.cursor = new Cursor(Cursor.HAND_CURSOR);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics brush = g.create();
        editor.utils.Button b = (editor.utils.Button)c;
        b.setBackground(Color.CYAN);
        if(this.hovered){
            b.setBackground(this.hoveredBackground);
            b.setForeground(this.hoveredForeground);
            if(this.hoveredBorder != null){
                brush.setColor(this.hoveredBorder);
                for (int i = 0; i < 4; i++) {
                    if(i == TOP_BORDER && this.showedBorders[i]) brush.fillRect(0,0,b.getWidth(),this.borderSize);
                    if(i == RIGHT_BORDER && this.showedBorders[i]) brush.fillRect(b.getWidth() - this.borderSize,0,b.getWidth(),b.getHeight());
                    if(i == BOTTOM_BORDER && this.showedBorders[i]) brush.fillRect(0,b.getHeight() - this.borderSize,b.getWidth(),b.getHeight());
                    if(i == LEFT_BORDER && this.showedBorders[i]) brush.fillRect(0,0,this.borderSize,b.getHeight());
                }
            }
        } else {
            b.setBackground(this.background);
            b.setForeground(this.foreground);
            if(this.border != null){
                for (int i = 0; i < 4; i++) {
                    if(i == TOP_BORDER && this.showedBorders[i]) brush.fillRect(0,0,b.getWidth(),this.borderSize);
                    if(i == RIGHT_BORDER && this.showedBorders[i]) brush.fillRect(b.getWidth() - this.borderSize,0,b.getWidth(),b.getHeight());
                    if(i == BOTTOM_BORDER && this.showedBorders[i]) brush.fillRect(0,b.getHeight() - this.borderSize,b.getWidth(),b.getHeight());
                    if(i == LEFT_BORDER && this.showedBorders[i]) brush.fillRect(0,0,this.borderSize,b.getHeight());
                }
            }
        }
        super.paint(brush, c);
    }

    @Override
    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {}

    @Override
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        Graphics brush = g.create();
        b.setBackground(this.pressedBackground);
        b.setForeground(this.pressedForeground);
        if(this.pressedBorder != null){
            brush.setColor(this.pressedBorder);
            for (int i = 0; i < 4; i++) {
                if(i == TOP_BORDER && this.showedBorders[i]) brush.fillRect(0,0,b.getWidth(),this.borderSize);
                if(i == RIGHT_BORDER && this.showedBorders[i]) brush.fillRect(b.getWidth() - this.borderSize,0,b.getWidth(),b.getHeight());
                if(i == BOTTOM_BORDER && this.showedBorders[i]) brush.fillRect(0,b.getHeight() - this.borderSize,b.getWidth(),b.getHeight());
                if(i == LEFT_BORDER && this.showedBorders[i]) brush.fillRect(0,0,this.borderSize,b.getHeight());
            }
        }
        super.paintButtonPressed(brush, b);
    }

    public boolean[] getShowedBorders(){
        return this.showedBorders;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    public void setShowedBorders(boolean[] showedBorders) {
        if(showedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
        this.showedBorders = showedBorders;
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

    public ButtonUI duplicate() {
        ButtonUI clone = new ButtonUI();

        clone.setCursor(this.getCursor());
        clone.setAllBackgrounds(this.getBackground(), this.getHoveredBackground(), this.getPressedBackground());
        clone.setAllForegrounds(this.getForeground(), this.getHoveredForeground(), this.getPressedForeground());
        clone.setAllBorders(this.getBorder(), this.getHoveredBorder(), this.getPressedBorder());
        clone.setIsHovered(this.isHovered());
        clone.setBorderSize(this.getBorderSize());
        clone.setShowedBorders(this.getShowedBorders());

        return clone;
    }
}
