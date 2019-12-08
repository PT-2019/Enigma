package editor.utils.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.*;

public class MenuUI extends BasicMenuUI {

    public final static Color ENIGMA_MENU_BACKGROUND = Color.DARK_GRAY;
    public final static Color ENIGMA_MENU_HOVERED_BACKGROUND = Color.DARK_GRAY;
    public final static Color ENIGMA_MENU_PRESSED_BACKGROUND = new Color(100,100,100);
    public final static Color ENIGMA_MENU_FOREGROUND = Color.WHITE;
    public final static Color ENIGMA_MENU_HOVERED_FOREGROUND = Color.GREEN;
    public final static Color ENIGMA_MENU_PRESSED_FOREGROUND = Color.ORANGE;
    public final static Color ENIGMA_MENU_BORDER = null;
    public final static Color ENIGMA_MENU_HOVERED_BORDER = null;
    public final static Color ENIGMA_MENU_PRESSED_BORDER = null;
    public final static int ENIGMA_MENU_BORDER_SIZE = 1;
    public final static boolean[] ENIGMA_MENU_SHOWED_BORDERS = {false,false,false,false};

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

    public MenuUI(){
        this.background = ENIGMA_MENU_BACKGROUND;
        this.hoveredBackground = ENIGMA_MENU_HOVERED_BACKGROUND;
        this.pressedBackground = ENIGMA_MENU_PRESSED_BACKGROUND;
        this.foreground = ENIGMA_MENU_FOREGROUND;
        this.hoveredForeground = ENIGMA_MENU_HOVERED_FOREGROUND;
        this.pressedForeground = ENIGMA_MENU_PRESSED_FOREGROUND;
        this.border = ENIGMA_MENU_BORDER;
        this.hoveredBorder = ENIGMA_MENU_HOVERED_BORDER;
        this.pressedBorder = ENIGMA_MENU_PRESSED_BORDER;
        this.borderSize = ENIGMA_MENU_BORDER_SIZE;
        this.showedBorders = ENIGMA_MENU_SHOWED_BORDERS;
        this.hovered = false;
        this.cursor = new Cursor(Cursor.HAND_CURSOR);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics brush = g.create();
        editor.utils.Menu m = (editor.utils.Menu)c;
        if(m.isSelected()){
            m.setBackground(this.pressedBackground);
            m.setForeground(this.pressedForeground);
            if(this.pressedBorder != null){
                brush.setColor(this.pressedBorder);
                for (int i = 0; i < 4; i++) {
                    if(i == TOP_BORDER && this.showedBorders[i]) brush.fillRect(0,0,m.getWidth(),this.borderSize);
                    if(i == RIGHT_BORDER && this.showedBorders[i]) brush.fillRect(m.getWidth() - this.borderSize,0,m.getWidth(),m.getHeight());
                    if(i == BOTTOM_BORDER && this.showedBorders[i]) brush.fillRect(0,m.getHeight() - this.borderSize,m.getWidth(),m.getHeight());
                    if(i == LEFT_BORDER && this.showedBorders[i]) brush.fillRect(0,0,this.borderSize,m.getHeight());
                }
            }
        } else if(this.hovered){
            m.setBackground(this.hoveredBackground);
            m.setForeground(this.hoveredForeground);
            if(this.hoveredBorder != null){
                brush.setColor(this.hoveredBorder);
                for (int i = 0; i < 4; i++) {
                    if(i == TOP_BORDER && this.showedBorders[i]) brush.fillRect(0,0,m.getWidth(),this.borderSize);
                    if(i == RIGHT_BORDER && this.showedBorders[i]) brush.fillRect(m.getWidth() - this.borderSize,0,m.getWidth(),m.getHeight());
                    if(i == BOTTOM_BORDER && this.showedBorders[i]) brush.fillRect(0,m.getHeight() - this.borderSize,m.getWidth(),m.getHeight());
                    if(i == LEFT_BORDER && this.showedBorders[i]) brush.fillRect(0,0,this.borderSize,m.getHeight());
                }
            }
        } else {
            m.setBackground(this.background);
            m.setForeground(this.foreground);
            if(this.border != null){
                brush.setColor(this.border);
                for (int i = 0; i < 4; i++) {
                    if(i == TOP_BORDER && this.showedBorders[i]) brush.fillRect(0,0,m.getWidth(),this.borderSize);
                    if(i == RIGHT_BORDER && this.showedBorders[i]) brush.fillRect(m.getWidth() - this.borderSize,0,m.getWidth(),m.getHeight());
                    if(i == BOTTOM_BORDER && this.showedBorders[i]) brush.fillRect(0,m.getHeight() - this.borderSize,m.getWidth(),m.getHeight());
                    if(i == LEFT_BORDER && this.showedBorders[i]) brush.fillRect(0,0,this.borderSize,m.getHeight());
                }
            }
        }
        super.paint(brush,c);
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



    public MenuUI duplicate() {
        MenuUI clone = new MenuUI();

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
