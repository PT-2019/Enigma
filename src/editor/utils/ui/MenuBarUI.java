package editor.utils.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuBarUI;
import java.awt.*;

public class MenuBarUI extends BasicMenuBarUI {

    public final static Color ENIGMA_MENU_BAR_BACKGROUND = Color.DARK_GRAY;
    public final static Color ENIGMA_MENU_BAR_BORDER = null;
    public final static int ENIGMA_MENU_BAR_BORDER_SIZE = 1;
    public final static boolean[] ENIGMA_MENU_BAR_SHOWED_BORDERS = {false,false,false,false};

    public final static boolean[] ALL_BORDERS_SHOWED = {true,true,true,true};
    public final static boolean[] ALL_BORDER_HIDDEN = {false,false,false,false};
    public final static boolean SHOWED_BORDER = true;
    public final static boolean HIDDEN_BORDER = false;
    public final static int TOP_BORDER = 0;
    public final static int RIGHT_BORDER = 1;
    public final static int BOTTOM_BORDER = 2;
    public final static int LEFT_BORDER = 3;

    private Color background;
    private Color border;
    private int borderSize;
    private boolean[] showedBorders;

    public MenuBarUI(){
        this.background = ENIGMA_MENU_BAR_BACKGROUND;
        this.border = ENIGMA_MENU_BAR_BORDER;
        this.borderSize = ENIGMA_MENU_BAR_BORDER_SIZE;
        this.showedBorders = ENIGMA_MENU_BAR_SHOWED_BORDERS;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics brush = g.create();
        editor.utils.MenuBar mb = (editor.utils.MenuBar)c;
        brush.setColor(this.background);
        brush.fillRect(0,0,mb.getWidth(),mb.getHeight());

        if(this.border != null){
            brush.setColor(this.border);
            for (int i = 0; i < 4; i++) {
                if(i == TOP_BORDER && this.showedBorders[i]) brush.fillRect(0,0,mb.getWidth(),this.borderSize);
                if(i == RIGHT_BORDER && this.showedBorders[i]) brush.fillRect(mb.getWidth() - this.borderSize,0,mb.getWidth(),mb.getHeight());
                if(i == BOTTOM_BORDER && this.showedBorders[i]) brush.fillRect(0,mb.getHeight() - this.borderSize,mb.getWidth(),mb.getHeight());
                if(i == LEFT_BORDER && this.showedBorders[i]) brush.fillRect(0,0,this.borderSize,mb.getHeight());
            }
        }
        super.paint(g, c);
    }

    public Color getBackground() {
        return background;
    }

    public Color getBorder() {
        return border;
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

    public void setBackground(Color background) {
        if(background == null) throw  new NullPointerException("L'argument ne peut pas être null");
        this.background = background;
    }

    public void setBorder(Color border) {
        this.border = border;
    }



    public MenuBarUI duplicate() {
        MenuBarUI clone = new MenuBarUI();

        clone.setBackground(this.getBackground());
        clone.setBorder(this.getBorder());
        clone.setBorderSize(this.getBorderSize());
        clone.setShowedBorders(this.getShowedBorders());

        return clone;
    }
}
