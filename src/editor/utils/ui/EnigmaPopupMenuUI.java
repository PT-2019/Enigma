package editor.utils.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicPopupMenuUI;
import java.awt.*;
import java.util.Arrays;

public class EnigmaPopupMenuUI extends BasicPopupMenuUI {

    private Color popupBackground;
    private int popupBorderSize;
    private boolean[] showedPopupBorders;

    public EnigmaPopupMenuUI(){
        this.popupBackground = EnigmaUIValues.ENIGMA_POPUP_MENU_BACKGROUND;
        this.popupBorderSize = EnigmaUIValues.ENIGMA_POPUP_MENU_BORDER_SIZE;
        this.showedPopupBorders = EnigmaUIValues.ENIGMA_POPUP_MENU_SHOWED_BORDER;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics brush = g.create();
        JPopupMenu pm = (JPopupMenu) c;

        int[] borders = new int[4];
        for(int i = 0; i < borders.length; i++) {
            if (this.showedPopupBorders[i]) borders[i] = this.popupBorderSize;
            else borders[i] = 0;
        }

        pm.setBorder(BorderFactory.createMatteBorder(borders[0],borders[1],borders[2],borders[3],Color.RED));
        super.paint(brush,c);
    }

    public Color getPopupBackground() {
        return popupBackground;
    }

    public void setPopupBackground(Color popupBackground) {
        this.popupBackground = popupBackground;
    }

    public int getPopupBorderSize() {
        return popupBorderSize;
    }

    public void setPopupBorderSize(int popupBorderSize) {
        this.popupBorderSize = popupBorderSize;
    }

    public boolean[] getShowedPopupBorders() {
        return showedPopupBorders;
    }

    public void setShowedPopupBorders(boolean[] showedPopupBorders) {
        this.showedPopupBorders = showedPopupBorders;
    }

    public EnigmaPopupMenuUI duplicate() {
        EnigmaPopupMenuUI clone = new EnigmaPopupMenuUI();

        clone.setPopupBackground(this.getPopupBackground());
        clone.setPopupBorderSize(this.getPopupBorderSize());
        clone.setShowedPopupBorders(this.getShowedPopupBorders());

        return clone;
    }
}
