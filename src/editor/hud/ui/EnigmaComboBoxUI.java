package editor.hud.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class EnigmaComboBoxUI extends BasicComboBoxUI {

    private Color border;
    private int borderSize;
    private boolean[] showedBorders;
    private Cursor cursor;
    private EnigmaLabelUI labelUI;
    private EnigmaButtonUI buttonUI;
    private EnigmaPopupMenuUI popupUI;
    private Font font;

    public EnigmaComboBoxUI(){
        this.border = EnigmaUIValues.ENIGMA_COMBOBOX_BORDER;
        this.borderSize = EnigmaUIValues.ENIGMA_COMBOBOX_BORDER_SIZE;
        this.showedBorders = EnigmaUIValues.ENIGMA_COMBOBOX_SHOWED_BORDERS;
        this.cursor = new Cursor(Cursor.DEFAULT_CURSOR);
        this.font = EnigmaUIValues.ENIGMA_FONT;

        this.labelUI = new EnigmaLabelUI();
        this.labelUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        this.buttonUI = new EnigmaButtonUI();
        Color color = new Color(100,100,100);
        this.buttonUI.setAllBackgrounds(color,color,color);
        this.buttonUI.setAllBorders(Color.WHITE,Color.WHITE,Color.WHITE);
        boolean[] sb = new boolean[4];
        boolean[] shb = new boolean[4];
        boolean[] spb = new boolean[4];
        sb[EnigmaUIValues.LEFT_BORDER] = true;
        shb[EnigmaUIValues.LEFT_BORDER] = true;
        spb[EnigmaUIValues.LEFT_BORDER] = true;
        this.buttonUI.setAllShowedBorders(sb,shb,spb);
        this.buttonUI.setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        this.buttonUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        this.popupUI = new EnigmaPopupMenuUI();
        this.popupUI.setPopupBorder(Color.WHITE);
        boolean[] borders = new boolean[4];
        borders[EnigmaUIValues.TOP_BORDER] = true;
        this.popupUI.setShowedPopupBorders(borders);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics brush = g.create();
        JPanel p = (JPanel)c;
        p.setBorder(BorderFactory.createEmptyBorder());
        if(this.border != null){
            brush.setColor(this.border);
            brush.fillRect(0,0,c.getWidth(),c.getHeight());
            this.paintBorder(p,this.border,this.borderSize,this.showedBorders);
        }
    }

    public void paintBorder(JPanel p, Color borderColor, int borderSize, boolean[] showedBorders){
        int[] borders = new int[4];
        for(int i = 0; i < borders.length; i++) {
            if (showedBorders[i]) borders[i] = borderSize;
            else borders[i] = 0;
        }
        p.setBorder(BorderFactory.createMatteBorder(borders[EnigmaUIValues.TOP_BORDER],borders[EnigmaUIValues.LEFT_BORDER],borders[EnigmaUIValues.BOTTOM_BORDER],borders[EnigmaUIValues.RIGHT_BORDER],borderColor));
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

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        if(borderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
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

    public Color getBorder() {
        return border;
    }

    public void setBorder(Color border) {
        this.border = border;
    }

    public EnigmaLabelUI getLabelUI() {
        return labelUI;
    }

    public void setLabelUI(EnigmaLabelUI labelUI) {
        this.labelUI = labelUI;
    }

    public EnigmaButtonUI getButtonUI() {
        return buttonUI;
    }

    public void setButtonUI(EnigmaButtonUI buttonUI) {
        this.buttonUI = buttonUI;
    }

    public EnigmaPopupMenuUI getPopupUI() {
        return popupUI;
    }

    public void setPopupUI(EnigmaPopupMenuUI popupUI) {
        this.popupUI = popupUI;
    }

    public EnigmaComboBoxUI duplicate() {
        EnigmaComboBoxUI clone = new EnigmaComboBoxUI();

        clone.setCursor(this.getCursor());
        clone.setBorder(this.getBorder());
        clone.setBorderSize(this.getBorderSize());
        clone.setShowedBorders(this.getShowedBorders());
        clone.setLabelUI(this.getLabelUI());
        clone.setButtonUI(this.getButtonUI());
        clone.setPopupUI(this.getPopupUI());
        clone.setFont(this.getFont());

        return clone;
    }
}
