package editor.hud.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicPanelUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;

/**
 * TODO: comment EnigmaPanelUI and write Readme.md in editor.hud.ui
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class EnigmaPanelUI extends BasicPanelUI {

	private Color background;
	private Color hoveredBackground;
	private Color pressedBackground;
	private Color border;
	private Color hoveredBorder;
	private Color pressedBorder;
	private boolean hovered;
	private boolean pressed;
	private Cursor cursor;
	private int borderSize;
	private int hoveredBorderSize;
	private int pressedBorderSize;
	private boolean[] showedBorders;
	private boolean[] hoveredShowedBorders;
	private boolean[] pressedShowedBorders;
	private ImageIcon backgroundImage;
	private ImageIcon hoveredBackgroundImage;
	private ImageIcon pressedBackgroundImage;

	public EnigmaPanelUI() {
		this.background = EnigmaUIValues.ENIGMA_PANEL_BACKGROUND;
		this.hoveredBackground = EnigmaUIValues.ENIGMA_PANEL_HOVERED_BACKGROUND;
		this.pressedBackground = EnigmaUIValues.ENIGMA_PANEL_PRESSED_BACKGROUND;
		this.border = EnigmaUIValues.ENIGMA_PANEL_BORDER;
		this.hoveredBorder = EnigmaUIValues.ENIGMA_PANEL_HOVERED_BORDER;
		this.pressedBorder = EnigmaUIValues.ENIGMA_PANEL_PRESSED_BORDER;
		this.borderSize = EnigmaUIValues.ENIGMA_PANEL_BORDER_SIZE;
		this.hoveredBorderSize = EnigmaUIValues.ENIGMA_PANEL_HOVERED_BORDER_SIZE;
		this.pressedBorderSize = EnigmaUIValues.ENIGMA_PANEL_PRESSED_BORDER_SIZE;
		this.showedBorders = EnigmaUIValues.ENIGMA_PANEL_SHOWED_BORDERS;
		this.hoveredShowedBorders = EnigmaUIValues.ENIGMA_PANEL_HOVERED_SHOWED_BORDERS;
		this.pressedShowedBorders = EnigmaUIValues.ENIGMA_PANEL_PRESSED_SHOWED_BORDERS;
		this.hovered = false;
		this.pressed = false;
		this.cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		this.backgroundImage = null;
		this.hoveredBackgroundImage = null;
		this.pressedBackgroundImage = null;
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		Graphics brush = g.create();
		JPanel p = (JPanel) c;
		p.setBorder(BorderFactory.createEmptyBorder());
		boolean[] borders;
		if (this.pressed) {
			brush.setColor(this.pressedBackground);
			brush.fillRect(0, 0, p.getWidth(), p.getHeight());
			if (this.pressedBorder != null) {
				this.paintBorder(p, this.pressedBorder, this.pressedBorderSize, this.pressedShowedBorders);
				borders = this.pressedShowedBorders;
			} else borders = EnigmaUIValues.ALL_BORDER_HIDDEN;

			if (this.pressedBackgroundImage != null)
				this.paintImage(brush, p, this.pressedBackgroundImage, this.pressedBorderSize, borders);

		} else if (this.hovered) {
			brush.setColor(this.hoveredBackground);
			brush.fillRect(0, 0, p.getWidth(), p.getHeight());
			if (this.hoveredBorder != null) {
				this.paintBorder(p, this.hoveredBorder, this.hoveredBorderSize, this.hoveredShowedBorders);
				borders = this.hoveredShowedBorders;
			} else borders = EnigmaUIValues.ALL_BORDER_HIDDEN;

			if (this.hoveredBackgroundImage != null)
				this.paintImage(brush, p, this.hoveredBackgroundImage, this.hoveredBorderSize, borders);

		} else {
			brush.setColor(this.background);
			brush.fillRect(0, 0, p.getWidth(), p.getHeight());
			if (this.border != null) {
				this.paintBorder(p, this.border, this.borderSize, this.showedBorders);
				borders = this.showedBorders;
			} else borders = EnigmaUIValues.ALL_BORDER_HIDDEN;

			if (this.backgroundImage != null)
				this.paintImage(brush, p, this.backgroundImage, this.borderSize, borders);

		}
		super.paint(brush, c);
	}

	public void paintBorder(JPanel p, Color borderColor, int borderSize, boolean[] showedBorders) {
		int[] borders = new int[4];
		for (int i = 0; i < borders.length; i++) {
			if (showedBorders[i]) borders[i] = borderSize;
			else borders[i] = 0;
		}
		p.setBorder(BorderFactory.createMatteBorder(borders[EnigmaUIValues.TOP_BORDER], borders[EnigmaUIValues.LEFT_BORDER], borders[EnigmaUIValues.BOTTOM_BORDER], borders[EnigmaUIValues.RIGHT_BORDER], borderColor));
	}

	private void paintImage(Graphics g, JPanel p, ImageIcon image, int borderSize, boolean[] showedBorders) {
		int x = 0;
		int y = 0;
		int w = p.getWidth();
		int h = p.getHeight();

		for (int i = 0; i < 4; i++) {
			if (i == EnigmaUIValues.TOP_BORDER && showedBorders[i]) {
				y += borderSize;
				w -= borderSize;
			}
			if (i == EnigmaUIValues.RIGHT_BORDER && showedBorders[i]) w -= borderSize;
			if (i == EnigmaUIValues.BOTTOM_BORDER && showedBorders[i]) h -= borderSize;
			if (i == EnigmaUIValues.LEFT_BORDER && showedBorders[i]) {
				x += borderSize;
				h -= borderSize;
			}
		}

		ImageIcon i = new ImageIcon(image.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		i.paintIcon(p, g, x, y);
	}

	public void setAllBackgroundImage(ImageIcon backgroundImage, ImageIcon hoveredBackgroundImage, ImageIcon pressedBackgroundImage) {
		this.backgroundImage = backgroundImage;
		this.hoveredBackgroundImage = hoveredBackgroundImage;
		this.pressedBackgroundImage = pressedBackgroundImage;
	}

	public ImageIcon getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(ImageIcon backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public ImageIcon getHoveredBackgroundImage() {
		return hoveredBackgroundImage;
	}

	public void setHoveredBackgroundImage(ImageIcon hoveredBackgroundImage) {
		this.hoveredBackgroundImage = hoveredBackgroundImage;
	}

	public ImageIcon getPressedBackgroundImage() {
		return pressedBackgroundImage;
	}

	public void setPressedBackgroundImage(ImageIcon pressedBackgroundImage) {
		this.pressedBackgroundImage = pressedBackgroundImage;
	}

	public boolean[] getShowedBorders() {
		return this.showedBorders;
	}

	public void setShowedBorders(boolean[] showedBorders) {
		if (showedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.showedBorders = showedBorders;
	}

	public boolean[] getHoveredShowedBorders() {
		return this.hoveredShowedBorders;
	}

	public void setHoveredShowedBorders(boolean[] hoveredShowedBorders) {
		if (showedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.hoveredShowedBorders = hoveredShowedBorders;
	}

	public boolean[] getPressedShowedBorders() {
		return this.pressedShowedBorders;
	}

	public void setPressedShowedBorders(boolean[] pressedShowedBorders) {
		if (showedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.pressedShowedBorders = pressedShowedBorders;
	}

	public int getBorderSize() {
		return borderSize;
	}

	public void setBorderSize(int borderSize) {
		if (borderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.borderSize = borderSize;
	}

	public int getHoveredBorderSize() {
		return hoveredBorderSize;
	}

	public void setHoveredBorderSize(int hoveredBorderSize) {
		if (hoveredBorderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.hoveredBorderSize = hoveredBorderSize;
	}

	public int getPressedBorderSize() {
		return pressedBorderSize;
	}

	public void setPressedBorderSize(int pressedBorderSize) {
		if (pressedBorderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.pressedBorderSize = pressedBorderSize;
	}

	public void setAllBordersSize(int borderSize, int hoveredBorderSize, int pressedBorderSize) {
		if (borderSize < 0 || hoveredBorderSize < 0 || pressedBorderSize < 0)
			throw new IllegalArgumentException("La taille des bordures ne peuvent être négatives");
		this.borderSize = borderSize;
		this.hoveredBorderSize = hoveredBorderSize;
		this.pressedBorderSize = pressedBorderSize;
	}

	public void setAllShowedBorders(boolean[] showedBorders, boolean[] hoveredShowedBorders, boolean[] pressedShowedBorders) {
		if (showedBorders.length != 4 || hoveredShowedBorders.length != 4 || pressedShowedBorders.length != 4)
			throw new IllegalArgumentException("Les tableaux doivent être de 4 éléments");
		this.showedBorders = showedBorders;
		this.hoveredShowedBorders = hoveredShowedBorders;
		this.pressedShowedBorders = pressedShowedBorders;
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

	public void setIsPressed(boolean isPressed) {
		this.pressed = isPressed;
	}

	public boolean isPressed() {
		return pressed;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		if (background == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.background = background;
	}

	public Color getHoveredBackground() {
		return hoveredBackground;
	}

	public void setHoveredBackground(Color hoveredBackground) {
		if (hoveredBackground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.hoveredBackground = hoveredBackground;
	}

	public Color getPressedBackground() {
		return pressedBackground;
	}

	public void setPressedBackground(Color pressedBackground) {
		if (pressedBackground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.pressedBackground = pressedBackground;
	}

	public Color getBorder() {
		return border;
	}

	public void setBorder(Color border) {
		this.border = border;
	}

	public Color getHoveredBorder() {
		return hoveredBorder;
	}

	public void setHoveredBorder(Color hoveredBorder) {
		this.hoveredBorder = hoveredBorder;
	}

	public Color getPressedBorder() {
		return pressedBorder;
	}

	public void setPressedBorder(Color pressedBorder) {
		this.pressedBorder = pressedBorder;
	}

	public void setAllBackgrounds(Color background, Color hoveredBackground, Color pressedBackground) {
		if (background == null || hoveredBackground == null || pressedBackground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.background = background;
		this.hoveredBackground = hoveredBackground;
		this.pressedBackground = pressedBackground;
	}

	public void setAllBorders(Color border, Color hoveredBorder, Color pressedBorder) {
		this.border = border;
		this.hoveredBorder = hoveredBorder;
		this.pressedBorder = pressedBorder;
	}

	public EnigmaPanelUI duplicate() {
		EnigmaPanelUI clone = new EnigmaPanelUI();

		clone.setCursor(this.getCursor());
		clone.setAllBackgrounds(this.getBackground(), this.getHoveredBackground(), this.getPressedBackground());
		clone.setAllBorders(this.getBorder(), this.getHoveredBorder(), this.getPressedBorder());
		clone.setIsHovered(this.isHovered());
		clone.setAllBordersSize(this.getBorderSize(), this.getHoveredBorderSize(), this.getPressedBorderSize());
		clone.setAllShowedBorders(this.getShowedBorders(), this.getHoveredShowedBorders(), this.getPressedShowedBorders());
		clone.setAllBackgroundImage(this.getBackgroundImage(), this.getHoveredBackgroundImage(), this.getPressedBackgroundImage());

		return clone;
	}
}
