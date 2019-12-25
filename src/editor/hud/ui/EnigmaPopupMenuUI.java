package editor.hud.ui;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.plaf.basic.BasicPopupMenuUI;
import java.awt.Color;
import java.awt.Graphics;

public class EnigmaPopupMenuUI extends BasicPopupMenuUI {

	private Color popupBackground;
	private Color popupBorder;
	private int popupBorderSize;
	private boolean[] showedPopupBorders;

	public EnigmaPopupMenuUI() {
		this.popupBackground = EnigmaUIValues.ENIGMA_POPUP_MENU_BACKGROUND;
		this.popupBorder = EnigmaUIValues.ENIGMA_POPUP_MENU_BORDER;
		this.popupBorderSize = EnigmaUIValues.ENIGMA_POPUP_MENU_BORDER_SIZE;
		this.showedPopupBorders = EnigmaUIValues.ENIGMA_POPUP_MENU_SHOWED_BORDER;
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		Graphics brush = g.create();
		JPopupMenu pm = (JPopupMenu) c;
		pm.setBorder(BorderFactory.createEmptyBorder());

		if (this.popupBorder != null) {
			int[] borders = new int[4];
			for (int i = 0; i < borders.length; i++) {
				if (this.showedPopupBorders[i]) borders[i] = this.popupBorderSize;
				else borders[i] = 0;
			}
			pm.setBorder(BorderFactory.createMatteBorder(borders[EnigmaUIValues.TOP_BORDER], borders[EnigmaUIValues.LEFT_BORDER], borders[EnigmaUIValues.BOTTOM_BORDER], borders[EnigmaUIValues.RIGHT_BORDER], this.popupBorder));
		}
		super.paint(brush, c);
	}

	public Color getPopupBorder() {
		return popupBorder;
	}

	public void setPopupBorder(Color popupBorder) {
		this.popupBorder = popupBorder;
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

		clone.setPopupBorder(this.getPopupBorder());
		clone.setPopupBackground(this.getPopupBackground());
		clone.setPopupBorderSize(this.getPopupBorderSize());
		clone.setShowedPopupBorders(this.getShowedPopupBorders());

		return clone;
	}
}
