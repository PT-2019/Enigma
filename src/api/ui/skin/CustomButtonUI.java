package api.ui.skin;

import api.ui.base.DefaultUIValues;
import api.ui.skin.base.CustomUIFont;
import api.ui.skin.base.CustomUIForeground;
import api.ui.skin.base.states.CustomUIHoverAndPressed;
import api.ui.skin.base.states.CustomUISelected;

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
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Apparence d'un bouton
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1
 * @since 3.0
 */
public class CustomButtonUI extends BasicButtonUI implements CustomUIHoverAndPressed<CustomButtonUI>,
		CustomUIFont, CustomUIForeground, CustomUISelected<CustomButtonUI> {

	//state
	protected boolean hovered, selected;
	protected Cursor cursor, selectedCursor;
	protected Font font;

	protected int borderSize, hoveredBorderSize, pressedBorderSize;
	protected boolean[] showedBorders, hoveredShowedBorders, pressedShowedBorders;

	protected int selectedBorderSize, selectedHoveredBorderSize, selectedPressedBorderSize;
	protected boolean[] selectedShowedBorders, selectedHoveredShowedBorders, selectedPressedShowedBorders;

	//Style
	//hover
	protected Color background, foreground;
	protected Color hoveredBackground, hoveredForeground;
	protected Color pressedBackground, pressedForeground;
	protected Color border, hoveredBorder, pressedBorder;
	//select
	protected Color selectedBackground, selectedForeground;
	protected Color selectedHoveredBackground, selectedHoveredForeground;
	protected Color selectedPressedBackground, selectedPressedForeground;
	protected Color selectedBorder, selectedHoveredBorder, selectedPressedBorder;
	//background
	protected ImageIcon backgroundImage, hoveredBackgroundImage, pressedBackgroundImage;
	protected ImageIcon selectedBackgroundImage, selectedHoveredBackgroundImage, selectedPressedBackgroundImage;

	/**
	 * Crée l'ui de base
	 */
	public CustomButtonUI() {
		super();
		this.background = DefaultUIValues.DEFAULT_BUTTON_BACKGROUND;
		this.hoveredBackground = DefaultUIValues.DEFAULT_BUTTON_HOVERED_BACKGROUND;
		this.pressedBackground = DefaultUIValues.DEFAULT_BUTTON_PRESSED_BACKGROUND;
		this.foreground = DefaultUIValues.DEFAULT_BUTTON_FOREGROUND;
		this.hoveredForeground = DefaultUIValues.DEFAULT_BUTTON_HOVERED_FOREGROUND;
		this.pressedForeground = DefaultUIValues.DEFAULT_BUTTON_PRESSED_FOREGROUND;
		this.border = DefaultUIValues.DEFAULT_BUTTON_BORDER;
		this.hoveredBorder = DefaultUIValues.DEFAULT_BUTTON_HOVERED_BORDER;
		this.pressedBorder = DefaultUIValues.DEFAULT_BUTTON_PRESSED_BORDER;
		this.borderSize = DefaultUIValues.DEFAULT_BUTTON_BORDER_SIZE;
		this.hoveredBorderSize = DefaultUIValues.DEFAULT_BUTTON_HOVERED_BORDER_SIZE;
		this.pressedBorderSize = DefaultUIValues.DEFAULT_BUTTON_PRESSED_BORDER_SIZE;
		this.showedBorders = DefaultUIValues.DEFAULT_BUTTON_SHOWED_BORDERS;
		this.hoveredShowedBorders = DefaultUIValues.DEFAULT_BUTTON_HOVERED_SHOWED_BORDERS;
		this.pressedShowedBorders = DefaultUIValues.DEFAULT_BUTTON_PRESSED_SHOWED_BORDERS;
		this.hovered = false;
		this.cursor = new Cursor(Cursor.HAND_CURSOR);
		this.backgroundImage = null;
		this.hoveredBackgroundImage = null;
		this.pressedBackgroundImage = null;

		this.selectedBackground = DefaultUIValues.DEFAULT_BUTTON_SELECTED_BACKGROUND;
		this.selectedHoveredBackground = DefaultUIValues.DEFAULT_BUTTON_SELECTED_HOVERED_BACKGROUND;
		this.selectedPressedBackground = DefaultUIValues.DEFAULT_BUTTON_SELECTED_PRESSED_BACKGROUND;
		this.selectedForeground = DefaultUIValues.DEFAULT_BUTTON_SELECTED_FOREGROUND;
		this.selectedHoveredForeground = DefaultUIValues.DEFAULT_BUTTON_SELECTED_HOVERED_FOREGROUND;
		this.selectedPressedForeground = DefaultUIValues.DEFAULT_BUTTON_SELECTED_PRESSED_FOREGROUND;
		this.selectedBorder = DefaultUIValues.DEFAULT_BUTTON_SELECTED_BORDER;
		this.selectedHoveredBorder = DefaultUIValues.DEFAULT_BUTTON_SELECTED_HOVERED_BORDER;
		this.selectedPressedBorder = DefaultUIValues.DEFAULT_BUTTON_SELECTED_PRESSED_BORDER;
		this.selectedBorderSize = DefaultUIValues.DEFAULT_BUTTON_SELECTED_BORDER_SIZE;
		this.selectedHoveredBorderSize = DefaultUIValues.DEFAULT_BUTTON_SELECTED_HOVERED_BORDER_SIZE;
		this.selectedPressedBorderSize = DefaultUIValues.DEFAULT_BUTTON_SELECTED_PRESSED_BORDER_SIZE;
		this.selectedShowedBorders = DefaultUIValues.DEFAULT_BUTTON_SELECTED_SHOWED_BORDERS;
		this.selectedHoveredShowedBorders = DefaultUIValues.DEFAULT_BUTTON_SELECTED_HOVERED_SHOWED_BORDERS;
		this.selectedPressedShowedBorders = DefaultUIValues.DEFAULT_BUTTON_SELECTED_PRESSED_SHOWED_BORDERS;
		this.selected = false;
		this.selectedCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		this.selectedBackgroundImage = null;
		this.selectedHoveredBackgroundImage = null;
		this.selectedPressedBackgroundImage = null;

		this.font = DefaultUIValues.DEFAULT_FONT;
	}

	//clone

	/**
	 * Crée une copie d'une ui
	 *
	 * @return une copie d'une ui
	 * @see #duplicate(CustomButtonUI)
	 */
	public CustomButtonUI duplicate() {
		return duplicate(this);
	}

	/**
	 * Crée une copie de l'ui d'un bouton
	 *
	 * @param customButtonUI l'ui d'un bouton
	 * @param <T>            qui doit extends CustomButtonUi
	 * @return une copie de l'ui d'un bouton
	 * @throws IllegalStateException si une copie échoue a être crée
	 */
	@SuppressWarnings("unchecked")
	protected <T extends CustomButtonUI> T duplicate(T customButtonUI) {
		T clone;
		try {
			Constructor<?> c = customButtonUI.getClass().getDeclaredConstructor();
			clone = (T) c.newInstance();
		} catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}

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

	//dessin

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
				} else borders = DefaultUIValues.ALL_BORDER_HIDDEN;

				if (this.selectedHoveredBackgroundImage != null)
					this.paintImage(brush, b, this.selectedHoveredBackgroundImage, this.selectedHoveredBorderSize, borders);
			} else {
				b.setBackground(this.hoveredBackground);
				b.setForeground(this.hoveredForeground);
				if (this.hoveredBorder != null) {
					this.paintBorder(brush, b, this.hoveredBorder, this.hoveredBorderSize, this.hoveredShowedBorders);
					borders = this.hoveredShowedBorders;
				} else borders = DefaultUIValues.ALL_BORDER_HIDDEN;

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
				} else borders = DefaultUIValues.ALL_BORDER_HIDDEN;

				if (this.selectedBackgroundImage != null)
					this.paintImage(brush, b, this.selectedBackgroundImage, this.selectedBorderSize, borders);
			} else {
				b.setBackground(this.background);
				b.setForeground(this.foreground);
				if (this.border != null) {
					this.paintBorder(brush, b, this.border, this.borderSize, this.showedBorders);
					borders = this.showedBorders;
				} else borders = DefaultUIValues.ALL_BORDER_HIDDEN;

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
			} else borders = DefaultUIValues.ALL_BORDER_HIDDEN;

			if (this.selectedPressedBackgroundImage != null)
				this.paintImage(brush, b, this.selectedPressedBackgroundImage, this.selectedPressedBorderSize, borders);
		} else {
			b.setBackground(this.pressedBackground);
			b.setForeground(this.pressedForeground);
			if (this.pressedBorder != null) {
				this.paintBorder(brush, b, this.pressedBorder, this.pressedBorderSize, this.pressedShowedBorders);
				borders = this.pressedShowedBorders;
			} else borders = DefaultUIValues.ALL_BORDER_HIDDEN;

			if (this.pressedBackgroundImage != null)
				this.paintImage(brush, b, this.pressedBackgroundImage, this.pressedBorderSize, borders);
		}
		super.paintButtonPressed(brush, b);
	}

	/**
	 * Dessine les bordure du bouton
	 *
	 * @param g             ignore
	 * @param b             ignore
	 * @param borderColor   ignore
	 * @param borderSize    ignore
	 * @param showedBorders ignore
	 */
	private void paintBorder(Graphics g, AbstractButton b, Color borderColor, int borderSize, boolean[] showedBorders) {
		g.setColor(borderColor);
		for (int i = 0; i < 4; i++) {
			if (i == DefaultUIValues.TOP_BORDER && showedBorders[i]) g.fillRect(0, 0, b.getWidth(), borderSize);
			if (i == DefaultUIValues.RIGHT_BORDER && showedBorders[i])
				g.fillRect(b.getWidth() - borderSize, 0, b.getWidth(), b.getHeight());
			if (i == DefaultUIValues.BOTTOM_BORDER && showedBorders[i])
				g.fillRect(0, b.getHeight() - borderSize, b.getWidth(), b.getHeight());
			if (i == DefaultUIValues.LEFT_BORDER && showedBorders[i]) g.fillRect(0, 0, borderSize, b.getHeight());
		}
	}

	/**
	 * Dessine une image ?
	 *
	 * @param g             ignore
	 * @param b             ignore
	 * @param image         ignore
	 * @param borderSize    ignore
	 * @param showedBorders ignore
	 */
	private void paintImage(Graphics g, AbstractButton b, ImageIcon image, int borderSize, boolean[] showedBorders) {
		int x = 0;
		int y = 0;
		int w = b.getWidth();
		int h = b.getHeight();

		for (int i = 0; i < 4; i++) {
			if (i == DefaultUIValues.TOP_BORDER && showedBorders[i]) {
				y += borderSize;
				w -= borderSize;
			}
			if (i == DefaultUIValues.RIGHT_BORDER && showedBorders[i]) w -= borderSize;
			if (i == DefaultUIValues.BOTTOM_BORDER && showedBorders[i]) h -= borderSize;
			if (i == DefaultUIValues.LEFT_BORDER && showedBorders[i]) {
				x += borderSize;
				h -= borderSize;
			}
		}

		ImageIcon i = new ImageIcon(image.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		i.paintIcon(b, g, x, y);
	}

	//own

	public Color getHoveredForeground() {
		return hoveredForeground;
	}

	public void setHoveredForeground(Color hoveredForeground) {
		if (hoveredForeground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.hoveredForeground = hoveredForeground;
	}

	public Color getPressedForeground() {
		return pressedForeground;
	}

	public void setPressedForeground(Color pressedForeground) {
		if (pressedForeground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.pressedForeground = pressedForeground;
	}

	public void setAllForegrounds(Color foreground, Color hoveredForeground, Color pressedForeground) {
		if (foreground == null || hoveredForeground == null || pressedForeground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.foreground = foreground;
		this.hoveredForeground = hoveredForeground;
		this.pressedForeground = pressedForeground;
	}

	//

	@Override
	public void setAllSelectedBackgroundImage(ImageIcon selectedBackgroundImage,
	                                          ImageIcon selectedHoveredBackgroundImage,
	                                          ImageIcon selectedPressedBackgroundImage) {
		this.selectedBackgroundImage = selectedBackgroundImage;
		this.selectedHoveredBackgroundImage = selectedHoveredBackgroundImage;
		this.selectedPressedBackgroundImage = selectedPressedBackgroundImage;
	}

	@Override
	public void setAllSelectedBackgroundImage(ImageIcon backgroundImage) {
		this.selectedBackgroundImage = backgroundImage;
		this.selectedHoveredBackgroundImage = backgroundImage;
		this.selectedPressedBackgroundImage = backgroundImage;
	}

	@Override
	public ImageIcon getSelectedBackgroundImage() {
		return selectedBackgroundImage;
	}

	@Override
	public void setSelectedBackgroundImage(ImageIcon selectedBackgroundImage) {
		this.selectedBackgroundImage = selectedBackgroundImage;
	}

	@Override
	public ImageIcon getSelectedHoveredBackgroundImage() {
		return selectedHoveredBackgroundImage;
	}

	@Override
	public void setSelectedHoveredBackgroundImage(ImageIcon selectedHoveredBackgroundImage) {
		this.selectedHoveredBackgroundImage = selectedHoveredBackgroundImage;
	}

	@Override
	public ImageIcon getSelectedPressedBackgroundImage() {
		return selectedPressedBackgroundImage;
	}

	@Override
	public void setSelectedPressedBackgroundImage(ImageIcon selectedPressedBackgroundImage) {
		this.selectedPressedBackgroundImage = selectedPressedBackgroundImage;
	}

	@Override
	public Cursor getSelectedCursor() {
		return this.selectedCursor;
	}

	@Override
	public void setSelectedCursor(Cursor selectedCursor) {
		this.selectedCursor = selectedCursor;
	}

	@Override
	public Color getForeground() {
		return foreground;
	}

	@Override
	public void setForeground(Color foreground) {
		if (foreground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.foreground = foreground;
	}

	@Override
	public boolean[] getSelectedShowedBorders() {
		return this.selectedShowedBorders;
	}

	@Override
	public void setSelectedShowedBorders(boolean[] selectedShowedBorders) {
		if (selectedShowedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.selectedShowedBorders = selectedShowedBorders;
	}

	@Override
	public boolean[] getSelectedHoveredShowedBorders() {
		return this.selectedHoveredShowedBorders;
	}

	@Override
	public void setSelectedHoveredShowedBorders(boolean[] selectedHoveredShowedBorders) {
		if (selectedHoveredShowedBorders.length != 4)
			throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.selectedHoveredShowedBorders = selectedHoveredShowedBorders;
	}

	@Override
	public boolean[] getSelectedPressedShowedBorders() {
		return this.selectedPressedShowedBorders;
	}

	@Override
	public void setSelectedPressedShowedBorders(boolean[] selectedPressedShowedBorders) {
		if (selectedPressedShowedBorders.length != 4)
			throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.selectedPressedShowedBorders = selectedPressedShowedBorders;
	}

	@Override
	public int getSelectedBorderSize() {
		return this.selectedBorderSize;
	}

	@Override
	public void setSelectedBorderSize(int selectedBorderSize) {
		if (borderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.selectedBorderSize = selectedBorderSize;
	}

	@Override
	public int getSelectedHoveredBorderSize() {
		return this.selectedHoveredBorderSize;
	}

	@Override
	public void setSelectedHoveredBorderSize(int selectedHoveredBorderSize) {
		if (selectedHoveredBorderSize < 0)
			throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.selectedHoveredBorderSize = selectedHoveredBorderSize;
	}

	@Override
	public int getSelectedPressedBorderSize() {
		return this.selectedPressedBorderSize;
	}

	@Override
	public void setSelectedPressedBorderSize(int selectedPressedBorderSize) {
		if (selectedPressedBorderSize < 0)
			throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.selectedPressedBorderSize = selectedPressedBorderSize;
	}

	@Override
	public void setAllSelectedBordersSize(int selectedBorderSize, int selectedHoveredBorderSize, int selectedPressedBorderSize) {
		if (selectedBorderSize < 0 || selectedHoveredBorderSize < 0 || selectedPressedBorderSize < 0)
			throw new IllegalArgumentException("La taille des bordures ne peuvent être négatives");
		this.selectedBorderSize = selectedBorderSize;
		this.selectedHoveredBorderSize = selectedHoveredBorderSize;
		this.selectedPressedBorderSize = selectedPressedBorderSize;
	}

	@Override
	public void setAllSelectedShowedBorders(boolean[] selectedShowedBorders, boolean[] selectedHoveredShowedBorders, boolean[] selectedPressedShowedBorders) {
		if (selectedShowedBorders.length != 4 || selectedHoveredShowedBorders.length != 4 || selectedPressedShowedBorders.length != 4)
			throw new IllegalArgumentException("Les tableaux doivent être de 4 éléments");
		this.selectedShowedBorders = selectedShowedBorders;
		this.selectedHoveredShowedBorders = selectedHoveredShowedBorders;
		this.selectedPressedShowedBorders = selectedPressedShowedBorders;
	}

	@Override
	public void setIsSelected(boolean isSelected) {
		this.selected = isSelected;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public Color getSelectedBackground() {
		return selectedBackground;
	}

	@Override
	public void setSelectedBackground(Color selectedBackground) {
		if (selectedBackground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.selectedBackground = selectedBackground;
	}

	@Override
	public Color getSelectedForeground() {
		return selectedForeground;
	}

	@Override
	public void setSelectedForeground(Color selectedForeground) {
		if (selectedForeground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.selectedForeground = selectedForeground;
	}

	@Override
	public Color getSelectedHoveredBackground() {
		return selectedHoveredBackground;
	}

	@Override
	public void setSelectedHoveredBackground(Color selectedHoveredBackground) {
		if (selectedHoveredBackground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.selectedHoveredBackground = selectedHoveredBackground;
	}

	@Override
	public Color getSelectedHoveredForeground() {
		return selectedHoveredForeground;
	}

	@Override
	public void setSelectedHoveredForeground(Color selectedHoveredForeground) {
		if (selectedHoveredForeground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.selectedHoveredForeground = selectedHoveredForeground;
	}

	@Override
	public Color getSelectedPressedBackground() {
		return selectedPressedBackground;
	}

	@Override
	public void setSelectedPressedBackground(Color selectedPressedBackground) {
		if (selectedPressedBackground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.selectedPressedBackground = selectedPressedBackground;
	}

	@Override
	public Color getSelectedPressedForeground() {
		return selectedPressedForeground;
	}

	@Override
	public void setSelectedPressedForeground(Color selectedPressedForeground) {
		if (selectedPressedForeground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.selectedPressedForeground = selectedPressedForeground;
	}

	@Override
	public Color getSelectedBorder() {
		return selectedBorder;
	}

	@Override
	public void setSelectedBorder(Color selectedBorder) {
		this.selectedBorder = selectedBorder;
	}

	@Override
	public Color getSelectedHoveredBorder() {
		return selectedHoveredBorder;
	}

	@Override
	public void setSelectedHoveredBorder(Color selectedHoveredBorder) {
		this.selectedHoveredBorder = selectedHoveredBorder;
	}

	@Override
	public Color getSelectedPressedBorder() {
		return selectedPressedBorder;
	}

	@Override
	public void setSelectedPressedBorder(Color selectedPressedBorder) {
		this.selectedPressedBorder = selectedPressedBorder;
	}

	@Override
	public void setAllSelectedBackgrounds(Color selectedBackground, Color selectedHoveredBackground, Color selectedPressedBackground) {
		if (selectedBackground == null || hoveredBackground == null || selectedPressedBackground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.selectedBackground = selectedBackground;
		this.selectedHoveredBackground = selectedHoveredBackground;
		this.selectedPressedBackground = selectedPressedBackground;
	}

	@Override
	public void setAllSelectedForegrounds(Color selectedForeground, Color selectedHoveredForeground, Color selectedPressedForeground) {
		if (selectedForeground == null || selectedHoveredForeground == null || selectedPressedForeground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.selectedForeground = selectedForeground;
		this.selectedHoveredForeground = selectedHoveredForeground;
		this.selectedPressedForeground = selectedPressedForeground;
	}

	@Override
	public void setAllSelectedBorders(Color selectedBorder, Color selectedHoveredBorder, Color selectedPressedBorder) {
		this.selectedBorder = selectedBorder;
		this.selectedHoveredBorder = selectedHoveredBorder;
		this.selectedPressedBorder = selectedPressedBorder;
	}

	//autres

	@Override
	public Font getFont() {
		return font;
	}

	@Override
	public void setFont(Font font) {
		this.font = font;
	}

	@Override
	public void setAllBackgroundImage(ImageIcon backgroundImage, ImageIcon hoveredBackgroundImage, ImageIcon pressedBackgroundImage) {
		this.backgroundImage = backgroundImage;
		this.hoveredBackgroundImage = hoveredBackgroundImage;
		this.pressedBackgroundImage = pressedBackgroundImage;
	}

	@Override
	public ImageIcon getBackgroundImage() {
		return backgroundImage;
	}

	@Override
	public void setBackgroundImage(ImageIcon backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	@Override
	public ImageIcon getHoveredBackgroundImage() {
		return hoveredBackgroundImage;
	}

	@Override
	public void setHoveredBackgroundImage(ImageIcon hoveredBackgroundImage) {
		this.hoveredBackgroundImage = hoveredBackgroundImage;
	}

	@Override
	public ImageIcon getPressedBackgroundImage() {
		return pressedBackgroundImage;
	}

	@Override
	public void setPressedBackgroundImage(ImageIcon pressedBackgroundImage) {
		this.pressedBackgroundImage = pressedBackgroundImage;
	}

	@Override
	public boolean[] getShowedBorders() {
		return this.showedBorders;
	}

	@Override
	public void setShowedBorders(boolean[] showedBorders) {
		if (showedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.showedBorders = showedBorders;
	}

	@Override
	public boolean[] getHoveredShowedBorders() {
		return this.hoveredShowedBorders;
	}

	@Override
	public void setHoveredShowedBorders(boolean[] hoveredShowedBorders) {
		if (hoveredShowedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.hoveredShowedBorders = hoveredShowedBorders;
	}

	@Override
	public boolean[] getPressedShowedBorders() {
		return this.pressedShowedBorders;
	}

	@Override
	public void setPressedShowedBorders(boolean[] pressedShowedBorders) {
		if (pressedShowedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.pressedShowedBorders = pressedShowedBorders;
	}

	@Override
	public int getBorderSize() {
		return this.borderSize;
	}

	@Override
	public void setBorderSize(int borderSize) {
		if (borderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.borderSize = borderSize;
	}

	@Override
	public int getHoveredBorderSize() {
		return this.hoveredBorderSize;
	}

	@Override
	public void setHoveredBorderSize(int hoveredBorderSize) {
		if (hoveredBorderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.hoveredBorderSize = hoveredBorderSize;
	}

	@Override
	public int getPressedBorderSize() {
		return this.pressedBorderSize;
	}

	@Override
	public void setPressedBorderSize(int pressedBorderSize) {
		if (pressedBorderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.pressedBorderSize = pressedBorderSize;
	}

	@Override
	public void setAllBordersSize(int borderSize, int hoveredBorderSize, int pressedBorderSize) {
		if (borderSize < 0 || hoveredBorderSize < 0 || pressedBorderSize < 0)
			throw new IllegalArgumentException("La taille des bordures ne peuvent être négatives");
		this.borderSize = borderSize;
		this.hoveredBorderSize = hoveredBorderSize;
		this.pressedBorderSize = pressedBorderSize;
	}

	@Override
	public void setAllShowedBorders(boolean[] showedBorders, boolean[] hoveredShowedBorders, boolean[] pressedShowedBorders) {
		if (showedBorders.length != 4 || hoveredShowedBorders.length != 4 || pressedShowedBorders.length != 4)
			throw new IllegalArgumentException("Les tableaux doivent être de 4 éléments");
		this.showedBorders = showedBorders;
		this.hoveredShowedBorders = hoveredShowedBorders;
		this.pressedShowedBorders = pressedShowedBorders;
	}

	@Override
	public Cursor getCursor() {
		return this.cursor;
	}

	@Override
	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}

	@Override
	public void setIsHovered(boolean isHovered) {
		this.hovered = isHovered;
	}

	@Override
	public boolean isHovered() {
		return hovered;
	}

	@Override
	public Color getBackground() {
		return background;
	}

	@Override
	public void setBackground(Color background) {
		if (background == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.background = background;
	}

	@Override
	public Color getHoveredBackground() {
		return hoveredBackground;
	}

	@Override
	public void setHoveredBackground(Color hoveredBackground) {
		if (hoveredBackground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.hoveredBackground = hoveredBackground;
	}

	@Override
	public Color getPressedBackground() {
		return pressedBackground;
	}

	@Override
	public void setPressedBackground(Color pressedBackground) {
		if (pressedBackground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.pressedBackground = pressedBackground;
	}

	@Override
	public Color getBorder() {
		return border;
	}

	@Override
	public void setBorder(Color border) {
		this.border = border;
	}

	@Override
	public Color getHoveredBorder() {
		return hoveredBorder;
	}

	@Override
	public void setHoveredBorder(Color hoveredBorder) {
		this.hoveredBorder = hoveredBorder;
	}

	@Override
	public Color getPressedBorder() {
		return pressedBorder;
	}

	@Override
	public void setPressedBorder(Color pressedBorder) {
		this.pressedBorder = pressedBorder;
	}

	@Override
	public void setAllBackgrounds(Color background, Color hoveredBackground, Color pressedBackground) {
		if (background == null || hoveredBackground == null || pressedBackground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.background = background;
		this.hoveredBackground = hoveredBackground;
		this.pressedBackground = pressedBackground;
	}

	@Override
	public void setAllBackgrounds(Color background) {
		if (background == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.background = background;
		this.hoveredBackground = background;
		this.pressedBackground = background;
	}

	@Override
	public void setAllBorders(Color border, Color hoveredBorder, Color pressedBorder) {
		this.border = border;
		this.hoveredBorder = hoveredBorder;
		this.pressedBorder = pressedBorder;
	}

	@Override
	public void setAllBorders(Color border) {
		this.border = border;
		this.hoveredBorder = border;
		this.pressedBorder = border;
	}
}
