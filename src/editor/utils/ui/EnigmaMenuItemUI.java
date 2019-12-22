package editor.utils.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuItemUI;
import java.awt.*;

public class EnigmaMenuItemUI extends BasicMenuItemUI {

    private Color background;
    private Color foreground;
    private Cursor cursor;
    private Font font;

    public EnigmaMenuItemUI(){
        this.background = EnigmaUIValues.ENIGMA_MENU_ITEM_BACKGROUND;
        this.foreground = EnigmaUIValues.ENIGMA_MENU_ITEM_FOREGROUND;
        this.cursor = new Cursor(Cursor.HAND_CURSOR);
        this.font = EnigmaUIValues.ENIGMA_FONT;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics brush = g.create();
        JMenuItem mi = (JMenuItem)c;
        mi.setBackground(this.background);
        mi.setForeground(this.foreground);
        super.paint(brush,c);
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setCursor(Cursor cursor){
        this.cursor = cursor;
    }

    public Cursor getCursor(){
        return this.cursor;
    }

    public Color getBackground() {
        return background;
    }

    public Color getForeground() {
        return foreground;
    }

    public void setBackground(Color background) {
        if(background == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.background = background;
    }

    public void setForeground(Color foreground) {
        if(foreground == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.foreground = foreground;
    }

    public EnigmaMenuItemUI duplicate() {
        EnigmaMenuItemUI clone = new EnigmaMenuItemUI();

        clone.setCursor(this.getCursor());
        clone.setBackground(this.getBackground());
        clone.setForeground(this.getForeground());
        clone.setFont(this.getFont());

        return clone;
    }
}
