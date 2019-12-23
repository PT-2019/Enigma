package editor.hud.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuBarUI;
import java.awt.*;

public class EnigmaMenuBarUI extends BasicMenuBarUI {

    private Color background;
    private Color border;
    private int borderSize;
    private boolean[] showedBorders;

    public EnigmaMenuBarUI(){
        this.background = EnigmaUIValues.ENIGMA_MENU_BAR_BACKGROUND;
        this.border = EnigmaUIValues.ENIGMA_MENU_BAR_BORDER;
        this.borderSize = EnigmaUIValues.ENIGMA_MENU_BAR_BORDER_SIZE;
        this.showedBorders = EnigmaUIValues.ENIGMA_MENU_BAR_SHOWED_BORDERS;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics brush = g.create();
        JMenuBar mb = (JMenuBar)c;
        brush.setColor(this.background);
        brush.fillRect(0,0,mb.getWidth(),mb.getHeight());

        if(this.border != null){
            brush.setColor(this.border);
            for (int i = 0; i < 4; i++) {
                if(i == EnigmaUIValues.TOP_BORDER && this.showedBorders[i]) brush.fillRect(0,0,mb.getWidth(),this.borderSize);
                if(i == EnigmaUIValues.RIGHT_BORDER && this.showedBorders[i]) brush.fillRect(mb.getWidth() - this.borderSize,0,mb.getWidth(),mb.getHeight());
                if(i == EnigmaUIValues.BOTTOM_BORDER && this.showedBorders[i]) brush.fillRect(0,mb.getHeight() - this.borderSize,mb.getWidth(),mb.getHeight());
                if(i == EnigmaUIValues.LEFT_BORDER && this.showedBorders[i]) brush.fillRect(0,0,this.borderSize,mb.getHeight());
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



    public EnigmaMenuBarUI duplicate() {
        EnigmaMenuBarUI clone = new EnigmaMenuBarUI();

        clone.setBackground(this.getBackground());
        clone.setBorder(this.getBorder());
        clone.setBorderSize(this.getBorderSize());
        clone.setShowedBorders(this.getShowedBorders());

        return clone;
    }
}
