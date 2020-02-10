package api.ui.skin;

import api.ui.base.DefaultUIValues;
import api.ui.skin.base.CustomUIFont;
import api.ui.skin.base.CustomUIForeground;
import api.ui.skin.base.states.CustomUIHoverAndPressed;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Style d'un label de base
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 28/12/2019
 * @since 4.0 28/12/2019
 */
public class CustomLabelUI extends BasicLabelUI implements CustomUIHoverAndPressed<CustomLabelUI>, CustomUIFont, CustomUIForeground {

	protected Color background, foreground;
	protected Color hoveredBackground, hoveredForeground;
	protected Color pressedBackground, pressedForeground;
	protected Color border, hoveredBorder, pressedBorder;
	protected boolean hovered, pressed;
	protected Cursor cursor;
	protected int borderSize, hoveredBorderSize, pressedBorderSize;
	protected boolean[] showedBorders, hoveredShowedBorders, pressedShowedBorders;
	protected Font font;
	protected ImageIcon backgroundImage, hoveredBackgroundImage, pressedBackgroundImage;

	/**
	 * Crée l'ui d'un label de base
	 */
	public CustomLabelUI() {
		this.background = DefaultUIValues.DEFAULT_LABEL_BACKGROUND;
		this.hoveredBackground = DefaultUIValues.DEFAULT_LABEL_HOVERED_BACKGROUND;
		this.pressedBackground = DefaultUIValues.DEFAULT_LABEL_PRESSED_BACKGROUND;
		this.foreground = DefaultUIValues.DEFAULT_LABEL_FOREGROUND;
		this.hoveredForeground = DefaultUIValues.DEFAULT_LABEL_HOVERED_FOREGROUND;
		this.pressedForeground = DefaultUIValues.DEFAULT_LABEL_PRESSED_FOREGROUND;
		this.border = DefaultUIValues.DEFAULT_LABEL_BORDER;
		this.hoveredBorder = DefaultUIValues.DEFAULT_LABEL_HOVERED_BORDER;
		this.pressedBorder = DefaultUIValues.DEFAULT_LABEL_PRESSED_BORDER;
		this.borderSize = DefaultUIValues.DEFAULT_LABEL_BORDER_SIZE;
		this.hoveredBorderSize = DefaultUIValues.DEFAULT_LABEL_HOVERED_BORDER_SIZE;
		this.pressedBorderSize = DefaultUIValues.DEFAULT_LABEL_PRESSED_BORDER_SIZE;
		this.showedBorders = DefaultUIValues.DEFAULT_LABEL_SHOWED_BORDERS;
		this.hoveredShowedBorders = DefaultUIValues.DEFAULT_LABEL_HOVERED_SHOWED_BORDERS;
		this.pressedShowedBorders = DefaultUIValues.DEFAULT_LABEL_PRESSED_SHOWED_BORDERS;
		this.hovered = false;
		this.pressed = false;
		this.cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		this.font = DefaultUIValues.DEFAULT_FONT;
		this.backgroundImage = null;
		this.hoveredBackgroundImage = null;
		this.pressedBackgroundImage = null;
	}

	@Override
	public CustomLabelUI duplicate() {
		return duplicate(this);
	}

	/**
	 * Crée une copie de l'ui d'un label
	 *
	 * @param customLabelUI l'ui d'un label
	 * @param <T>           qui doit extends CustomLabelUI
	 * @return une copie de l'ui d'un label
	 * @throws IllegalStateException si une copie échoue a être crée
	 */
	@SuppressWarnings("unchecked")
	protected <T extends CustomLabelUI> T duplicate(T customLabelUI) {
		T clone;
		try {
			Constructor<?> c = customLabelUI.getClass().getDeclaredConstructor();
			clone = (T) c.newInstance();
		} catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}

		clone.setCursor(this.getCursor());
		clone.setAllBackgrounds(this.getBackground(), this.getHoveredBackground(), this.getPressedBackground());
		clone.setAllForegrounds(this.getForeground(), this.getHoveredForeground(), this.getPressedForeground());
		clone.setAllBorders(this.getBorder(), this.getHoveredBorder(), this.getPressedBorder());
		clone.setIsHovered(this.isHovered());
		clone.setAllBordersSize(this.getBorderSize(), this.getHoveredBorderSize(), this.getPressedBorderSize());
		clone.setAllShowedBorders(this.getShowedBorders(), this.getHoveredShowedBorders(), this.getPressedShowedBorders());
		clone.setFont(this.getFont());
		clone.setAllBackgroundImage(this.getBackgroundImage(), this.getHoveredBackgroundImage(), this.getPressedBackgroundImage());

		return clone;
	}

	//dessin

	@Override
	public void paint(Graphics g, JComponent c) {
		Graphics brush = g.create();
		JLabel l = (JLabel) c;
		boolean[] borders;
		if (this.pressed) {
			brush.setColor(this.pressedBackground);
			brush.fillRect(0, 0, l.getWidth(), l.getHeight());
			l.setForeground(this.pressedForeground);
			if (this.pressedBorder != null) {
				this.paintBorder(brush, l, this.pressedBorder, this.pressedBorderSize, this.pressedShowedBorders);
				borders = this.pressedShowedBorders;
			} else borders = DefaultUIValues.ALL_BORDER_HIDDEN;

			if (this.pressedBackgroundImage != null)
				this.paintImage(brush, l, this.pressedBackgroundImage, this.pressedBorderSize, borders);

		} else if (this.hovered) {
			brush.setColor(this.hoveredBackground);
			brush.fillRect(0, 0, l.getWidth(), l.getHeight());
			l.setForeground(this.hoveredForeground);
			if (this.hoveredBorder != null) {
				this.paintBorder(brush, l, this.hoveredBorder, this.hoveredBorderSize, this.hoveredShowedBorders);
				borders = this.hoveredShowedBorders;
			} else borders = DefaultUIValues.ALL_BORDER_HIDDEN;

			if (this.hoveredBackgroundImage != null)
				this.paintImage(brush, l, this.hoveredBackgroundImage, this.hoveredBorderSize, borders);

		} else {
			brush.setColor(this.background);
			brush.fillRect(0, 0, l.getWidth(), l.getHeight());
			l.setForeground(this.foreground);
			if (this.border != null) {
				this.paintBorder(brush, l, this.border, this.borderSize, this.showedBorders);
				borders = this.showedBorders;
			} else borders = DefaultUIValues.ALL_BORDER_HIDDEN;

			if (this.backgroundImage != null)
				this.paintImage(brush, l, this.backgroundImage, this.borderSize, borders);

		}
		super.paint(brush, c);
	}

	/**
	 * Dessine l'image du label
	 *
	 * @param g             ignore
	 * @param l             ignore
	 * @param image         ignore
	 * @param borderSize    ignore
	 * @param showedBorders ignore
	 */
	private void paintImage(Graphics g, JLabel l, ImageIcon image, int borderSize, boolean[] showedBorders) {
		int x = 0;
		int y = 0;
		int w = l.getWidth();
		int h = l.getHeight();

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
		i.paintIcon(l, g, x, y);
	}

	/**
	 * Dessine les bordure du label
	 *
	 * @param g             ignore
	 * @param l             ignore
	 * @param borderColor   ignore
	 * @param borderSize    ignore
	 * @param showedBorders ignore
	 */
	private void paintBorder(Graphics g, JLabel l, Color borderColor, int borderSize, boolean[] showedBorders) {
		g.setColor(borderColor);
		for (int i = 0; i < 4; i++) {
			if (i == DefaultUIValues.TOP_BORDER && showedBorders[i]) g.fillRect(0, 0, l.getWidth(), borderSize);
			if (i == DefaultUIValues.RIGHT_BORDER && showedBorders[i])
				g.fillRect(l.getWidth() - borderSize, 0, l.getWidth(), l.getHeight());
			if (i == DefaultUIValues.BOTTOM_BORDER && showedBorders[i])
				g.fillRect(0, l.getHeight() - borderSize, l.getWidth(), l.getHeight());
			if (i == DefaultUIValues.LEFT_BORDER && showedBorders[i]) g.fillRect(0, 0, borderSize, l.getHeight());
		}
	}

	//ses méthodes

	/**
	 * Définit si appuyé
	 *
	 * @param isPressed true si appuyé
	 */
	public void setIsPressed(boolean isPressed) {
		this.pressed = isPressed;
	}

	/**
	 * retourne si appuyé
	 *
	 * @return true si appuyé
	 */
	public boolean isPressed() {
		return pressed;
	}


	/**
	 * Retourne fond (premier plan) survol
	 *
	 * @return fond (premier plan) survol
	 */
	public Color getHoveredForeground() {
		return hoveredForeground;
	}

	/**
	 * Définit fond (premier plan) survol
	 *
	 * @param hoveredForeground fond (premier plan) survol
	 */
	public void setHoveredForeground(Color hoveredForeground) {
		if (hoveredForeground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.hoveredForeground = hoveredForeground;
	}

	/**
	 * Retourne fond (premier plan) appui
	 *
	 * @return fond (premier plan) appui
	 */
	public Color getPressedForeground() {
		return pressedForeground;
	}

	/**
	 * Définit fond (premier plan) appui
	 *
	 * @param pressedForeground fond (premier plan) appui
	 */
	public void setPressedForeground(Color pressedForeground) {
		if (pressedForeground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.pressedForeground = pressedForeground;
	}

	/**
	 * Définit tous les fonds (premier plan)
	 *
	 * @param foreground        normal
	 * @param hoveredForeground survol
	 * @param pressedForeground appui
	 */
	public void setAllForegrounds(Color foreground, Color hoveredForeground, Color pressedForeground) {
		if (foreground == null || hoveredForeground == null || pressedForeground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.foreground = foreground;
		this.hoveredForeground = hoveredForeground;
		this.pressedForeground = pressedForeground;
	}

	/**
	 * Définit tous les fonds (premier plan)
	 *
	 * @param foreground fond
	 */
	public void setAllForegrounds(Color foreground) {
		if (foreground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.foreground = foreground;
		this.hoveredForeground = foreground;
		this.pressedForeground = foreground;
	}

	// implements

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
	public boolean[] getHoveredShowedBorders() {
		return this.hoveredShowedBorders;
	}

	@Override
	public void setHoveredShowedBorders(boolean[] hoveredShowedBorders) {
		if (showedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.hoveredShowedBorders = hoveredShowedBorders;
	}

	@Override
	public boolean[] getPressedShowedBorders() {
		return this.pressedShowedBorders;
	}

	@Override
	public void setPressedShowedBorders(boolean[] pressedShowedBorders) {
		if (showedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.pressedShowedBorders = pressedShowedBorders;
	}

	@Override
	public int getBorderSize() {
		return borderSize;
	}

	@Override
	public void setBorderSize(int borderSize) {
		if (borderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.borderSize = borderSize;
	}

	@Override
	public int getHoveredBorderSize() {
		return hoveredBorderSize;
	}

	@Override
	public void setHoveredBorderSize(int hoveredBorderSize) {
		if (hoveredBorderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.hoveredBorderSize = hoveredBorderSize;
	}

	@Override
	public int getPressedBorderSize() {
		return pressedBorderSize;
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
	public void setAllShowedBorders(boolean[] showedBorders) {
		if (showedBorders.length != 4)
			throw new IllegalArgumentException("Les tableaux doivent être de 4 éléments");
		this.showedBorders = showedBorders;
		this.hoveredShowedBorders = showedBorders;
		this.pressedShowedBorders = showedBorders;
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
