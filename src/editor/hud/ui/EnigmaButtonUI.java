package editor.hud.ui;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class EnigmaButtonUI extends BasicButtonUI {

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
	private int hoveredBorderSize;
	private int pressedBorderSize;
	private boolean[] showedBorders;
	private boolean[] hoveredShowedBorders;
	private boolean[] pressedShowedBorders;
	private ImageIcon backgroundImage;
	private ImageIcon hoveredBackgroundImage;
	private ImageIcon pressedBackgroundImage;

	private Color selectedBackground;
	private Color selectedForeground;
	private Color selectedHoveredBackground;
	private Color selectedHoveredForeground;
	private Color selectedPressedBackground;
	private Color selectedPressedForeground;
	private Color selectedBorder;
	private Color selectedHoveredBorder;
	private Color selectedPressedBorder;
	private boolean selected;
	private Cursor selectedCursor;
	private int selectedBorderSize;
	private int selectedHoveredBorderSize;
	private int selectedPressedBorderSize;
	private boolean[] selectedShowedBorders;
	private boolean[] selectedHoveredShowedBorders;
	private boolean[] selectedPressedShowedBorders;
	private ImageIcon selectedBackgroundImage;
	private ImageIcon selectedHoveredBackgroundImage;
	private ImageIcon selectedPressedBackgroundImage;

	private Font font;

	public EnigmaButtonUI() {
		this.background = EnigmaUIValues.ENIGMA_BUTTON_BACKGROUND;
		this.hoveredBackground = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_BACKGROUND;
		this.pressedBackground = EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BACKGROUND;
		this.foreground = EnigmaUIValues.ENIGMA_BUTTON_FOREGROUND;
		this.hoveredForeground = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_FOREGROUND;
		this.pressedForeground = EnigmaUIValues.ENIGMA_BUTTON_PRESSED_FOREGROUND;
		this.border = EnigmaUIValues.ENIGMA_BUTTON_BORDER;
		this.hoveredBorder = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_BORDER;
		this.pressedBorder = EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BORDER;
		this.borderSize = EnigmaUIValues.ENIGMA_BUTTON_BORDER_SIZE;
		this.hoveredBorderSize = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_BORDER_SIZE;
		this.pressedBorderSize = EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BORDER_SIZE;
		this.showedBorders = EnigmaUIValues.ENIGMA_BUTTON_SHOWED_BORDERS;
		this.hoveredShowedBorders = EnigmaUIValues.ENIGMA_BUTTON_HOVERED_SHOWED_BORDERS;
		this.pressedShowedBorders = EnigmaUIValues.ENIGMA_BUTTON_PRESSED_SHOWED_BORDERS;
		this.hovered = false;
		this.cursor = new Cursor(Cursor.HAND_CURSOR);
		this.backgroundImage = null;
		this.hoveredBackgroundImage = null;
		this.pressedBackgroundImage = null;

		this.selectedBackground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_BACKGROUND;
		this.selectedHoveredBackground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_HOVERED_BACKGROUND;
		this.selectedPressedBackground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_PRESSED_BACKGROUND;
		this.selectedForeground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_FOREGROUND;
		this.selectedHoveredForeground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_HOVERED_FOREGROUND;
		this.selectedPressedForeground = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_PRESSED_FOREGROUND;
		this.selectedBorder = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_BORDER;
		this.selectedHoveredBorder = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_HOVERED_BORDER;
		this.selectedPressedBorder = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_PRESSED_BORDER;
		this.selectedBorderSize = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_BORDER_SIZE;
		this.selectedHoveredBorderSize = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_HOVERED_BORDER_SIZE;
		this.selectedPressedBorderSize = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_PRESSED_BORDER_SIZE;
		this.selectedShowedBorders = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_SHOWED_BORDERS;
		this.selectedHoveredShowedBorders = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_HOVERED_SHOWED_BORDERS;
		this.selectedPressedShowedBorders = EnigmaUIValues.ENIGMA_BUTTON_SELECTED_PRESSED_SHOWED_BORDERS;
		this.selected = false;
		this.selectedCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		this.selectedBackgroundImage = null;
		this.selectedHoveredBackgroundImage = null;
		this.selectedPressedBackgroundImage = null;

		this.font = EnigmaUIValues.ENIGMA_FONT;
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		Graphics brush = g.create();
		JButton b = (JButton) c;
		boolean[] borders;
		if (this.selected) b.setCursor(this.selectedCursor);
		else b.setCursor(this.cursor);

		if (this.hovered) {
			if (this.selected) {
				b.setBackground(this.selectedHoveredBackground);
				b.setForeground(this.selectedHoveredForeground);
				if (this.selectedHoveredBorder != null) {
					this.paintBorder(brush, b, this.selectedHoveredBorder, this.selectedHoveredBorderSize, this.selectedHoveredShowedBorders);
					borders = this.selectedHoveredShowedBorders;
				} else borders = EnigmaUIValues.ALL_BORDER_HIDDEN;

				if (this.selectedHoveredBackgroundImage != null)
					this.paintImage(brush, b, this.selectedHoveredBackgroundImage, this.selectedHoveredBorderSize, borders);
			} else {
				b.setBackground(this.hoveredBackground);
				b.setForeground(this.hoveredForeground);
				if (this.hoveredBorder != null) {
					this.paintBorder(brush, b, this.hoveredBorder, this.hoveredBorderSize, this.hoveredShowedBorders);
					borders = this.hoveredShowedBorders;
				} else borders = EnigmaUIValues.ALL_BORDER_HIDDEN;

				if (this.hoveredBackgroundImage != null)
					this.paintImage(brush, b, this.hoveredBackgroundImage, this.hoveredBorderSize, borders);
			}
		} else {
			if (this.selected) {
				b.setBackground(this.selectedBackground);
				b.setForeground(this.selectedForeground);
				if (this.selectedBorder != null) {
					this.paintBorder(brush, b, this.selectedBorder, this.selectedBorderSize, this.selectedShowedBorders);
					borders = this.selectedShowedBorders;
				} else borders = EnigmaUIValues.ALL_BORDER_HIDDEN;

				if (this.selectedBackgroundImage != null)
					this.paintImage(brush, b, this.selectedBackgroundImage, this.selectedBorderSize, borders);
			} else {
				b.setBackground(this.background);
				b.setForeground(this.foreground);
				if (this.border != null) {
					this.paintBorder(brush, b, this.border, this.borderSize, this.showedBorders);
					borders = this.showedBorders;
				} else borders = EnigmaUIValues.ALL_BORDER_HIDDEN;

				if (this.backgroundImage != null)
					this.paintImage(brush, b, this.backgroundImage, this.borderSize, borders);
			}
		}
		super.paint(brush, c);
	}

	@Override
	protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
	}

	@Override
	protected void paintButtonPressed(Graphics g, AbstractButton b) {
		Graphics brush = g.create();
		boolean[] borders;
		if (this.selected) {
			b.setBackground(this.selectedPressedBackground);
			b.setForeground(this.selectedPressedForeground);
			if (this.selectedPressedBorder != null) {
				this.paintBorder(brush, b, this.selectedPressedBorder, this.selectedPressedBorderSize, this.selectedPressedShowedBorders);
				borders = this.selectedPressedShowedBorders;
			} else borders = EnigmaUIValues.ALL_BORDER_HIDDEN;

			if (this.selectedPressedBackgroundImage != null)
				this.paintImage(brush, b, this.selectedPressedBackgroundImage, this.selectedPressedBorderSize, borders);
		} else {
			b.setBackground(this.pressedBackground);
			b.setForeground(this.pressedForeground);
			if (this.pressedBorder != null) {
				this.paintBorder(brush, b, this.pressedBorder, this.pressedBorderSize, this.pressedShowedBorders);
				borders = this.pressedShowedBorders;
			} else borders = EnigmaUIValues.ALL_BORDER_HIDDEN;

			if (this.pressedBackgroundImage != null)
				this.paintImage(brush, b, this.pressedBackgroundImage, this.pressedBorderSize, borders);
		}
		super.paintButtonPressed(brush, b);
	}

	private void paintBorder(Graphics g, AbstractButton b, Color borderColor, int borderSize, boolean[] showedBorders) {
		g.setColor(borderColor);
		for (int i = 0; i < 4; i++) {
			if (i == EnigmaUIValues.TOP_BORDER && showedBorders[i]) g.fillRect(0, 0, b.getWidth(), borderSize);
			if (i == EnigmaUIValues.RIGHT_BORDER && showedBorders[i])
				g.fillRect(b.getWidth() - borderSize, 0, b.getWidth(), b.getHeight());
			if (i == EnigmaUIValues.BOTTOM_BORDER && showedBorders[i])
				g.fillRect(0, b.getHeight() - borderSize, b.getWidth(), b.getHeight());
			if (i == EnigmaUIValues.LEFT_BORDER && showedBorders[i]) g.fillRect(0, 0, borderSize, b.getHeight());
		}
	}

	private void paintImage(Graphics g, AbstractButton b, ImageIcon image, int borderSize, boolean[] showedBorders) {
		int x = 0;
		int y = 0;
		int w = b.getWidth();
		int h = b.getHeight();

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
		i.paintIcon(b, g, x, y);
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

	public void setAllSelectedBackgroundImage(ImageIcon selectedBackgroundImage, ImageIcon selectedHoveredBackgroundImage, ImageIcon selectedPressedBackgroundImage) {
		this.selectedBackgroundImage = selectedBackgroundImage;
		this.selectedHoveredBackgroundImage = selectedHoveredBackgroundImage;
		this.selectedPressedBackgroundImage = selectedPressedBackgroundImage;
	}

	public ImageIcon getSelectedBackgroundImage() {
		return selectedBackgroundImage;
	}

	public void setSelectedBackgroundImage(ImageIcon selectedBackgroundImage) {
		this.selectedBackgroundImage = selectedBackgroundImage;
	}

	public ImageIcon getSelectedHoveredBackgroundImage() {
		return selectedHoveredBackgroundImage;
	}

	public void setSelectedHoveredBackgroundImage(ImageIcon selectedHoveredBackgroundImage) {
		this.selectedHoveredBackgroundImage = selectedHoveredBackgroundImage;
	}

	public ImageIcon getSelectedPressedBackgroundImage() {
		return selectedPressedBackgroundImage;
	}

	public void setSelectedPressedBackgroundImage(ImageIcon selectedPressedBackgroundImage) {
		this.selectedPressedBackgroundImage = selectedPressedBackgroundImage;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
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
		if (hoveredShowedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.hoveredShowedBorders = hoveredShowedBorders;
	}

	public boolean[] getPressedShowedBorders() {
		return this.pressedShowedBorders;
	}

	public void setPressedShowedBorders(boolean[] pressedShowedBorders) {
		if (pressedShowedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.pressedShowedBorders = pressedShowedBorders;
	}

	public int getBorderSize() {
		return this.borderSize;
	}

	public void setBorderSize(int borderSize) {
		if (borderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.borderSize = borderSize;
	}

	public int getHoveredBorderSize() {
		return this.hoveredBorderSize;
	}

	public void setHoveredBorderSize(int hoveredBorderSize) {
		if (hoveredBorderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.hoveredBorderSize = hoveredBorderSize;
	}

	public int getPressedBorderSize() {
		return this.pressedBorderSize;
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

	public Cursor getSelectedCursor() {
		return this.selectedCursor;
	}

	public void setSelectedCursor(Cursor selectedCursor) {
		this.selectedCursor = selectedCursor;
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

	public Color getPressedBackground() {
		return pressedBackground;
	}

	public void setPressedBackground(Color pressedBackground) {
		if (pressedBackground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.pressedBackground = pressedBackground;
	}

	public Color getPressedForeground() {
		return pressedForeground;
	}

	public void setPressedForeground(Color pressedForeground) {
		if (pressedForeground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.pressedForeground = pressedForeground;
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

	public void setAllForegrounds(Color foreground, Color hoveredForeground, Color pressedForeground) {
		if (foreground == null || hoveredForeground == null || pressedForeground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.foreground = foreground;
		this.hoveredForeground = hoveredForeground;
		this.pressedForeground = pressedForeground;
	}

	public void setAllBorders(Color border, Color hoveredBorder, Color pressedBorder) {
		this.border = border;
		this.hoveredBorder = hoveredBorder;
		this.pressedBorder = pressedBorder;
	}

	public boolean[] getSelectedShowedBorders() {
		return this.selectedShowedBorders;
	}

	public void setSelectedShowedBorders(boolean[] selectedShowedBorders) {
		if (selectedShowedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.selectedShowedBorders = selectedShowedBorders;
	}

	public boolean[] getSelectedHoveredShowedBorders() {
		return this.selectedHoveredShowedBorders;
	}

	public void setSelectedHoveredShowedBorders(boolean[] selectedHoveredShowedBorders) {
		if (selectedHoveredShowedBorders.length != 4)
			throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.selectedHoveredShowedBorders = selectedHoveredShowedBorders;
	}

	public boolean[] getSelectedPressedShowedBorders() {
		return this.selectedPressedShowedBorders;
	}

	public void setSelectedPressedShowedBorders(boolean[] selectedPressedShowedBorders) {
		if (selectedPressedShowedBorders.length != 4)
			throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.selectedPressedShowedBorders = selectedPressedShowedBorders;
	}

	public int getSelectedBorderSize() {
		return this.selectedBorderSize;
	}

	public void setSelectedBorderSize(int selectedBorderSize) {
		if (borderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.selectedBorderSize = selectedBorderSize;
	}

	public int getSelectedHoveredBorderSize() {
		return this.selectedHoveredBorderSize;
	}

	public void setSelectedHoveredBorderSize(int selectedHoveredBorderSize) {
		if (selectedHoveredBorderSize < 0)
			throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.selectedHoveredBorderSize = selectedHoveredBorderSize;
	}

	public int getSelectedPressedBorderSize() {
		return this.selectedPressedBorderSize;
	}

	public void setSelectedPressedBorderSize(int selectedPressedBorderSize) {
		if (selectedPressedBorderSize < 0)
			throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.selectedPressedBorderSize = selectedPressedBorderSize;
	}

	public void setAllSelectedBordersSize(int selectedBorderSize, int selectedHoveredBorderSize, int selectedPressedBorderSize) {
		if (selectedBorderSize < 0 || selectedHoveredBorderSize < 0 || selectedPressedBorderSize < 0)
			throw new IllegalArgumentException("La taille des bordures ne peuvent être négatives");
		this.selectedBorderSize = selectedBorderSize;
		this.selectedHoveredBorderSize = selectedHoveredBorderSize;
		this.selectedPressedBorderSize = selectedPressedBorderSize;
	}

	public void setAllSelectedShowedBorders(boolean[] selectedShowedBorders, boolean[] selectedHoveredShowedBorders, boolean[] selectedPressedShowedBorders) {
		if (selectedShowedBorders.length != 4 || selectedHoveredShowedBorders.length != 4 || selectedPressedShowedBorders.length != 4)
			throw new IllegalArgumentException("Les tableaux doivent être de 4 éléments");
		this.selectedShowedBorders = selectedShowedBorders;
		this.selectedHoveredShowedBorders = selectedHoveredShowedBorders;
		this.selectedPressedShowedBorders = selectedPressedShowedBorders;
	}

	public void setIsSelected(boolean isSelected) {
		this.selected = isSelected;
	}

	public boolean isSelected() {
		return selected;
	}

	public Color getSelectedBackground() {
		return selectedBackground;
	}

	public void setSelectedBackground(Color selectedBackground) {
		if (selectedBackground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.selectedBackground = selectedBackground;
	}

	public Color getSelectedForeground() {
		return selectedForeground;
	}

	public void setSelectedForeground(Color selectedForeground) {
		if (selectedForeground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.selectedForeground = selectedForeground;
	}

	public Color getSelectedHoveredBackground() {
		return selectedHoveredBackground;
	}

	public void setSelectedHoveredBackground(Color selectedHoveredBackground) {
		if (selectedHoveredBackground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.selectedHoveredBackground = selectedHoveredBackground;
	}

	public Color getSelectedHoveredForeground() {
		return selectedHoveredForeground;
	}

	public void setSelectedHoveredForeground(Color selectedHoveredForeground) {
		if (selectedHoveredForeground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.selectedHoveredForeground = selectedHoveredForeground;
	}

	public Color getSelectedPressedBackground() {
		return selectedPressedBackground;
	}

	public void setSelectedPressedBackground(Color selectedPressedBackground) {
		if (selectedPressedBackground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.selectedPressedBackground = selectedPressedBackground;
	}

	public Color getSelectedPressedForeground() {
		return selectedPressedForeground;
	}

	public void setSelectedPressedForeground(Color selectedPressedForeground) {
		if (selectedPressedForeground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.selectedPressedForeground = selectedPressedForeground;
	}

	public Color getSelectedBorder() {
		return selectedBorder;
	}

	public void setSelectedBorder(Color selectedBorder) {
		this.selectedBorder = selectedBorder;
	}

	public Color getSelectedHoveredBorder() {
		return selectedHoveredBorder;
	}

	public void setSelectedHoveredBorder(Color selectedHoveredBorder) {
		this.selectedHoveredBorder = selectedHoveredBorder;
	}

	public Color getSelectedPressedBorder() {
		return selectedPressedBorder;
	}

	public void setSelectedPressedBorder(Color selectedPressedBorder) {
		this.selectedPressedBorder = selectedPressedBorder;
	}

	public void setAllSelectedBackgrounds(Color selectedBackground, Color selectedHoveredBackground, Color selectedPressedBackground) {
		if (selectedBackground == null || hoveredBackground == null || selectedPressedBackground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.selectedBackground = selectedBackground;
		this.selectedHoveredBackground = selectedHoveredBackground;
		this.selectedPressedBackground = selectedPressedBackground;
	}

	public void setAllSelectedForegrounds(Color selectedForeground, Color selectedHoveredForeground, Color selectedPressedForeground) {
		if (selectedForeground == null || selectedHoveredForeground == null || selectedPressedForeground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.selectedForeground = selectedForeground;
		this.selectedHoveredForeground = selectedHoveredForeground;
		this.selectedPressedForeground = selectedPressedForeground;
	}

	public void setAllSelectedBorders(Color selectedBorder, Color selectedHoveredBorder, Color selectedPressedBorder) {
		this.selectedBorder = selectedBorder;
		this.selectedHoveredBorder = selectedHoveredBorder;
		this.selectedPressedBorder = selectedPressedBorder;
	}

	public EnigmaButtonUI duplicate() {
		EnigmaButtonUI clone = new EnigmaButtonUI();

		clone.setCursor(this.getCursor());
		clone.setAllBackgrounds(this.getBackground(), this.getHoveredBackground(), this.getPressedBackground());
		clone.setAllForegrounds(this.getForeground(), this.getHoveredForeground(), this.getPressedForeground());
		clone.setAllBorders(this.getBorder(), this.getHoveredBorder(), this.getPressedBorder());
		clone.setAllBordersSize(this.getBorderSize(), this.getHoveredBorderSize(), this.getPressedBorderSize());
		clone.setAllShowedBorders(this.getShowedBorders(), this.getHoveredShowedBorders(), this.getPressedShowedBorders());
		clone.setAllBackgroundImage(this.getBackgroundImage(), this.getHoveredBackgroundImage(), this.getPressedBackgroundImage());

		clone.setSelectedCursor(this.getSelectedCursor());
		clone.setAllSelectedBackgrounds(this.getSelectedBackground(), this.getSelectedHoveredBackground(), this.getSelectedPressedBackground());
		clone.setAllSelectedForegrounds(this.getSelectedForeground(), this.getSelectedHoveredForeground(), this.getSelectedPressedForeground());
		clone.setAllSelectedBorders(this.getSelectedBorder(), this.getSelectedHoveredBorder(), this.getSelectedPressedBorder());
		clone.setAllSelectedBordersSize(this.getSelectedBorderSize(), this.getSelectedHoveredBorderSize(), this.getSelectedPressedBorderSize());
		clone.setAllSelectedShowedBorders(this.getSelectedShowedBorders(), this.getSelectedHoveredShowedBorders(), this.getSelectedPressedShowedBorders());
		clone.setAllSelectedBackgroundImage(this.getSelectedBackgroundImage(), this.getSelectedHoveredBackgroundImage(), this.getSelectedPressedBackgroundImage());

		clone.setFont(this.getFont());

		return clone;
	}
}
