package editor.hud.ui;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;

public class EnigmaMenuUI extends BasicMenuUI {

	private Color background;
	private Color foreground;
	private Color hoveredBackground;
	private Color hoveredForeground;
	private Color popupBackground;
	private int popupBorderSize;
	private boolean[] showedPopupBorders;
	private boolean hovered;
	private Cursor cursor;
	private Font font;

	public EnigmaMenuUI() {
		this.background = EnigmaUIValues.ENIGMA_MENU_BACKGROUND;
		this.hoveredBackground = EnigmaUIValues.ENIGMA_MENU_HOVERED_BACKGROUND;
		this.foreground = EnigmaUIValues.ENIGMA_MENU_FOREGROUND;
		this.hoveredForeground = EnigmaUIValues.ENIGMA_MENU_HOVERED_FOREGROUND;
		this.popupBackground = EnigmaUIValues.ENIGMA_MENU_POPUP_BACKGROUND;
		this.popupBorderSize = EnigmaUIValues.ENIGMA_MENU_POPUP_BORDER_SIZE;
		this.showedPopupBorders = EnigmaUIValues.ENIGMA_MENU_POPUP_SHOWED_BORDER;
		this.hovered = false;
		this.cursor = new Cursor(Cursor.HAND_CURSOR);
		this.font = EnigmaUIValues.ENIGMA_FONT;
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		Graphics brush = g.create();
		JMenu m = (JMenu) c;
		m.setBorder(BorderFactory.createEmptyBorder());
		if (this.hovered) {
			m.setBackground(this.hoveredBackground);
			m.setForeground(this.hoveredForeground);
		} else {
			m.setBackground(this.background);
			m.setForeground(this.foreground);
		}

		int[] borders = new int[4];
		for (int i = 0; i < borders.length; i++)
			if (this.showedPopupBorders[i]) borders[i] = this.popupBorderSize;
			else borders[i] = 0;

		m.getPopupMenu().setBorder(BorderFactory.createMatteBorder(borders[EnigmaUIValues.TOP_BORDER], borders[EnigmaUIValues.LEFT_BORDER], borders[EnigmaUIValues.BOTTOM_BORDER], borders[EnigmaUIValues.RIGHT_BORDER], popupBackground));
		super.paint(brush, c);
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Cursor getCursor() {
		return this.cursor;
	}

	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}

	public void setIsHovered(boolean isHovered) {
		this.hovered = isHovered;
	}

	public boolean isHovered() {
		return hovered;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		if (background == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.background = background;
	}

	public Color getForeground() {
		return foreground;
	}

	public void setForeground(Color foreground) {
		if (foreground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.foreground = foreground;
	}

	public Color getHoveredBackground() {
		return hoveredBackground;
	}

	public void setHoveredBackground(Color hoveredBackground) {
		if (hoveredBackground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.hoveredBackground = hoveredBackground;
	}

	public Color getHoveredForeground() {
		return hoveredForeground;
	}

	public void setHoveredForeground(Color hoveredForeground) {
		if (hoveredForeground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.hoveredForeground = hoveredForeground;
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

	public void setAllBackgrounds(Color background, Color hoveredBackground) {
		if (background == null || hoveredBackground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.background = background;
		this.hoveredBackground = hoveredBackground;
	}

	public void setAllForegrounds(Color foreground, Color hoveredForeground) {
		if (foreground == null || hoveredForeground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.foreground = foreground;
		this.hoveredForeground = hoveredForeground;
	}

	public EnigmaMenuUI duplicate() {
		EnigmaMenuUI clone = new EnigmaMenuUI();

		clone.setCursor(this.getCursor());
		clone.setAllBackgrounds(this.getBackground(), this.getHoveredBackground());
		clone.setAllForegrounds(this.getForeground(), this.getHoveredForeground());
		clone.setPopupBackground(this.getPopupBackground());
		clone.setPopupBorderSize(this.getPopupBorderSize());
		clone.setShowedPopupBorders(this.getShowedPopupBorders());
		clone.setFont(this.getFont());

		return clone;
	}
}
