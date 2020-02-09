package api.ui.skin;

import api.ui.base.DefaultUIValues;
import api.ui.skin.base.states.CustomUIHoverAndPressed;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicPanelUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Crée un panneau customizable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 4.0
 */
public class CustomPanelUI extends BasicPanelUI implements CustomUIHoverAndPressed<CustomPanelUI> {

	/**
	 * Fond du panneau
	 */
	protected Color background, hoveredBackground, pressedBackground;
	/**
	 * Bordures
	 */
	protected Color border, hoveredBorder, pressedBorder;
	/**
	 * états
	 */
	protected boolean hovered, pressed;
	/**
	 * Curseur
	 */
	protected Cursor cursor;

	/**
	 * Borders size
	 */
	protected int borderSize, hoveredBorderSize, pressedBorderSize;

	/**
	 * Si on doit afficher seulement certaines bordures
	 *
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 * <p>
	 * Tableau de 4, true si on doit afficher la bordure sinon false
	 */
	protected boolean[] showedBorders, hoveredShowedBorders, pressedShowedBorders;

	/**
	 * Background images
	 */
	protected ImageIcon backgroundImage, hoveredBackgroundImage, pressedBackgroundImage;

	/**
	 * Crée un panneau customizable
	 */
	public CustomPanelUI() {
		this.background = DefaultUIValues.DEFAULT_PANEL_BACKGROUND;
		this.hoveredBackground = DefaultUIValues.DEFAULT_PANEL_HOVERED_BACKGROUND;
		this.pressedBackground = DefaultUIValues.DEFAULT_PANEL_PRESSED_BACKGROUND;
		this.border = DefaultUIValues.DEFAULT_PANEL_BORDER;
		this.hoveredBorder = DefaultUIValues.DEFAULT_PANEL_HOVERED_BORDER;
		this.pressedBorder = DefaultUIValues.DEFAULT_PANEL_PRESSED_BORDER;
		this.borderSize = DefaultUIValues.DEFAULT_PANEL_BORDER_SIZE;
		this.hoveredBorderSize = DefaultUIValues.DEFAULT_PANEL_HOVERED_BORDER_SIZE;
		this.pressedBorderSize = DefaultUIValues.DEFAULT_PANEL_PRESSED_BORDER_SIZE;
		this.showedBorders = DefaultUIValues.DEFAULT_PANEL_SHOWED_BORDERS;
		this.hoveredShowedBorders = DefaultUIValues.DEFAULT_PANEL_HOVERED_SHOWED_BORDERS;
		this.pressedShowedBorders = DefaultUIValues.DEFAULT_PANEL_PRESSED_SHOWED_BORDERS;
		this.hovered = false;
		this.pressed = false;
		this.cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		this.backgroundImage = null;
		this.hoveredBackgroundImage = null;
		this.pressedBackgroundImage = null;
	}

	@Override
	public CustomPanelUI duplicate() {
		return duplicate(this);
	}

	/**
	 * Crée une copie de l'ui d'un panel
	 *
	 * @param customButtonUI l'ui d'un panel
	 * @param <T>            qui doit extends CustomPanelUI
	 * @return une copie de l'ui d'un panel
	 * @throws IllegalStateException si une copie échoue a être crée
	 */
	@SuppressWarnings("unchecked")
	protected <T extends CustomPanelUI> T duplicate(T customButtonUI) {
		T clone;
		try {
			Constructor<?> c = customButtonUI.getClass().getDeclaredConstructor();
			clone = (T) c.newInstance();
		} catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}

		clone.setCursor(this.getCursor());
		clone.setAllBackgrounds(this.getBackground(), this.getHoveredBackground(), this.getPressedBackground());
		clone.setAllBorders(this.getBorder(), this.getHoveredBorder(), this.getPressedBorder());
		clone.setIsHovered(this.isHovered());
		clone.setAllBordersSize(this.getBorderSize(), this.getHoveredBorderSize(), this.getPressedBorderSize());
		clone.setAllShowedBorders(this.getShowedBorders(), this.getHoveredShowedBorders(), this.getPressedShowedBorders());
		clone.setAllBackgroundImage(this.getBackgroundImage(), this.getHoveredBackgroundImage(), this.getPressedBackgroundImage());

		return clone;
	}

	// draw

	@Override
	public void paint(Graphics g, JComponent c) {
		Graphics brush = g.create();
		JPanel p = (JPanel) c;
		boolean[] borders;
		if (this.pressed) {
			brush.setColor(this.pressedBackground);
			brush.fillRect(0, 0, p.getWidth(), p.getHeight());
			if (this.pressedBorder != null) {
				this.paintBorder(brush, p, this.pressedBorder, this.pressedBorderSize, this.pressedShowedBorders);
				borders = this.pressedShowedBorders;
			} else borders = DefaultUIValues.ALL_BORDER_HIDDEN;

			if (this.pressedBackgroundImage != null)
				this.paintImage(brush, p, this.pressedBackgroundImage, this.pressedBorderSize, borders);

		} else if (this.hovered) {
			brush.setColor(this.hoveredBackground);
			brush.fillRect(0, 0, p.getWidth(), p.getHeight());
			if (this.hoveredBorder != null) {
				this.paintBorder(brush, p, this.hoveredBorder, this.hoveredBorderSize, this.hoveredShowedBorders);
				borders = this.hoveredShowedBorders;
			} else borders = DefaultUIValues.ALL_BORDER_HIDDEN;

			if (this.hoveredBackgroundImage != null)
				this.paintImage(brush, p, this.hoveredBackgroundImage, this.hoveredBorderSize, borders);

		} else {
			brush.setColor(this.background);
			brush.fillRect(0, 0, p.getWidth(), p.getHeight());
			if (this.border != null) {
				this.paintBorder(brush, p, this.border, this.borderSize, this.showedBorders);
				borders = this.showedBorders;
			} else borders = DefaultUIValues.ALL_BORDER_HIDDEN;

			if (this.backgroundImage != null)
				this.paintImage(brush, p, this.backgroundImage, this.borderSize, borders);

		}
		super.paint(brush, c);
	}

	/**
	 * Dessine les bordure du panneau
	 *
	 * @param g             ignore
	 * @param p             ignore
	 * @param borderColor   ignore
	 * @param borderSize    ignore
	 * @param showedBorders ignore
	 */
	private void paintBorder(Graphics g, JPanel p, Color borderColor, int borderSize, boolean[] showedBorders) {
		g.setColor(borderColor);
		for (int i = 0; i < 4; i++) {
			if (i == DefaultUIValues.TOP_BORDER && showedBorders[i]) g.fillRect(0, 0, p.getWidth(), borderSize);
			if (i == DefaultUIValues.RIGHT_BORDER && showedBorders[i])
				g.fillRect(p.getWidth() - borderSize, 0, p.getWidth(), p.getHeight());
			if (i == DefaultUIValues.BOTTOM_BORDER && showedBorders[i])
				g.fillRect(0, p.getHeight() - borderSize, p.getWidth(), p.getHeight());
			if (i == DefaultUIValues.LEFT_BORDER && showedBorders[i]) g.fillRect(0, 0, borderSize, p.getHeight());
		}
	}

	/**
	 * Dessine une image ?
	 *
	 * @param g             ignore
	 * @param p             ignore
	 * @param image         ignore
	 * @param borderSize    ignore
	 * @param showedBorders ignore
	 */
	private void paintImage(Graphics g, JPanel p, ImageIcon image, int borderSize, boolean[] showedBorders) {
		int x = 0;
		int y = 0;
		int w = p.getWidth();
		int h = p.getHeight();

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
		i.paintIcon(p, g, x, y);
	}

	//own

	/**
	 * Défini si le panneau est appuyé ou non
	 *
	 * @param isPressed true si appuyé sinon false
	 */
	public void setIsPressed(boolean isPressed) {
		this.pressed = isPressed;
	}

	/**
	 * Retourne true si un panneau est appuyé
	 *
	 * @return true si un panneau est appuyé
	 */
	public boolean isPressed() {
		return pressed;
	}

	//customize methods (get and set)

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
			throw new NullPointerException("Argument ne peut pas être null");
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
