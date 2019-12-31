package api.hud.ui;

import api.hud.DefaultUIValues;
import api.hud.ui.base.CustomUIForeground;
import api.hud.ui.base.states.CustomUIFocusedAndHover;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicTextAreaUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Style d'une zone de saisie customizable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 28/12/2019
 * @since 4.0 28/12/2019
 */
public class CustomTextAreaUI extends BasicTextAreaUI implements CustomUIFocusedAndHover<CustomTextAreaUI>, CustomUIForeground {

	protected Color background, foreground;
	protected Color hoveredBackground, hoveredForeground;
	protected Color focusedBackground, focusedForeground;
	protected Color border, hoveredBorder, focusedBorder;
	protected boolean hovered;
	protected Cursor cursor;
	protected int borderSize, hoveredBorderSize, focusedBorderSize;
	protected boolean[] showedBorders, hoveredShowedBorders, focusedShowedBorders;
	protected Font font;

	public CustomTextAreaUI() {
		this.background = DefaultUIValues.DEFAULT_TEXTAREA_BACKGROUND;
		this.hoveredBackground = DefaultUIValues.DEFAULT_TEXTAREA_HOVERED_BACKGROUND;
		this.focusedBackground = DefaultUIValues.DEFAULT_TEXTAREA_FOCUSED_BACKGROUND;
		this.foreground = DefaultUIValues.DEFAULT_TEXTAREA_FOREGROUND;
		this.hoveredForeground = DefaultUIValues.DEFAULT_TEXTAREA_HOVERED_FOREGROUND;
		this.focusedForeground = DefaultUIValues.DEFAULT_TEXTAREA_FOCUSED_FOREGROUND;
		this.border = DefaultUIValues.DEFAULT_TEXTAREA_BORDER;
		this.hoveredBorder = DefaultUIValues.DEFAULT_TEXTAREA_HOVERED_BORDER;
		this.focusedBorder = DefaultUIValues.DEFAULT_TEXTAREA_FOCUSED_BORDER;
		this.borderSize = DefaultUIValues.DEFAULT_TEXTAREA_BORDER_SIZE;
		this.hoveredBorderSize = DefaultUIValues.DEFAULT_TEXTAREA_HOVERED_BORDER_SIZE;
		this.focusedBorderSize = DefaultUIValues.DEFAULT_TEXTAREA_FOCUSED_BORDER_SIZE;
		this.showedBorders = DefaultUIValues.DEFAULT_TEXTAREA_SHOWED_BORDERS;
		this.hoveredShowedBorders = DefaultUIValues.DEFAULT_TEXTAREA_HOVERED_SHOWED_BORDERS;
		this.focusedShowedBorders = DefaultUIValues.DEFAULT_TEXTAREA_FOCUSED_SHOWED_BORDERS;
		this.hovered = false;
		this.cursor = new Cursor(Cursor.TEXT_CURSOR);
		this.font = DefaultUIValues.DEFAULT_FONT;
	}

	/**
	 * Dessine la zone de saisie
	 *
	 * @param g pinceau de dessin
	 * @param c zone de saisie
	 */
	public void paintTextArea(Graphics g, JComponent c) {
		Graphics brush = g.create();
		JTextArea ta = (JTextArea) c;
		ta.setBorder(BorderFactory.createEmptyBorder());
		if (ta.hasFocus()) {
			ta.setBackground(this.focusedBackground);
			ta.setForeground(this.focusedForeground);
			ta.setCaretColor(this.focusedForeground);
			if (this.focusedBorder != null) {
				this.paintBorder(ta, this.focusedBorder, this.focusedBorderSize, this.focusedShowedBorders);
			}
		} else if (this.hovered) {
			ta.setBackground(this.hoveredBackground);
			ta.setForeground(this.hoveredForeground);
			ta.setCaretColor(this.hoveredForeground);
			if (this.hoveredBorder != null) {
				this.paintBorder(ta, this.hoveredBorder, this.hoveredBorderSize, this.hoveredShowedBorders);
			}
		} else {
			ta.setBackground(this.background);
			ta.setForeground(this.foreground);
			ta.setCaretColor(this.foreground);
			if (this.border != null) {
				this.paintBorder(ta, this.border, this.borderSize, this.showedBorders);
			}
		}
		super.paint(brush, c);
	}

	/**
	 * Dessine les bordure de la zone de saisie
	 *
	 * @param c             ignore
	 * @param borderColor   ignore
	 * @param borderSize    ignore
	 * @param showedBorders ignore
	 */
	private void paintBorder(JComponent c, Color borderColor, int borderSize, boolean[] showedBorders) {
		int[] borders = new int[showedBorders.length];
		for (int i = 0; i < 4; i++) {
			if (showedBorders[i]) borders[i] = borderSize;
		}
		c.setBorder(BorderFactory.createMatteBorder(borders[DefaultUIValues.TOP_BORDER],
				borders[DefaultUIValues.LEFT_BORDER],
				borders[DefaultUIValues.BOTTOM_BORDER],
				borders[DefaultUIValues.RIGHT_BORDER], borderColor));
	}

	@Override
	public CustomTextAreaUI duplicate() {
		return duplicate(this);
	}

	/**
	 * Crée une copie de l'ui d'une zone de saisie
	 *
	 * @param customTextAreaUI l'ui d'une zone de saisie
	 * @param <T>              qui doit extends CustomTextAreaUI
	 * @return une copie de l'ui d'une zone de saisie
	 * @throws IllegalStateException si une copie échoue a être crée
	 */
	@SuppressWarnings("unchecked")
	protected <T extends CustomTextAreaUI> T duplicate(T customTextAreaUI) {
		T clone;
		try {
			Constructor<?> c = customTextAreaUI.getClass().getDeclaredConstructor();
			clone = (T) c.newInstance();
		} catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}

		clone.setCursor(this.getCursor());
		clone.setAllBackgrounds(this.getBackground(), this.getHoveredBackground(), this.getFocusedBackground());
		clone.setAllForegrounds(this.getForeground(), this.getHoveredForeground(), this.getFocusedForeground());
		clone.setAllBorders(this.getBorder(), this.getHoveredBorder(), this.getFocusedBorder());
		clone.setIsHovered(this.isHovered());
		clone.setAllBordersSize(this.getBorderSize(), this.getHoveredBorderSize(), this.getFocusedBorderSize());
		clone.setAllShowedBorders(this.getShowedBorders(), this.getHoveredShowedBorders(), this.getFocusedShowedBorders());
		clone.setFont(this.getFont());

		return clone;
	}

	//own

	/**
	 * Couleur de texte au survol
	 *
	 * @return Couleur de texte au survol
	 */
	public Color getHoveredForeground() {
		return hoveredForeground;
	}

	/**
	 * Définit la couleur du texte au survol
	 *
	 * @param hoveredForeground la couleur du texte au survol
	 */
	public void setHoveredForeground(Color hoveredForeground) {
		if (hoveredForeground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.hoveredForeground = hoveredForeground;
	}

	//implements

	@Override
	public Font getFont() {
		return font;
	}

	@Override
	public void setFont(Font font) {
		this.font = font;
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
	public Color getBackground() {
		return background;
	}

	@Override
	public void setBackground(Color background) {
		if (background == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.background = background;
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
	public Color getForeground() {
		return foreground;
	}

	@Override
	public void setForeground(Color foreground) {
		if (foreground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.foreground = foreground;
	}

	@Override
	public Color getFocusedBackground() {
		return focusedBackground;
	}

	@Override
	public void setFocusedBackground(Color focusedBackground) {
		if (focusedBackground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.focusedBackground = focusedBackground;
	}

	@Override
	public Color getFocusedForeground() {
		return focusedForeground;
	}

	@Override
	public void setFocusedForeground(Color focusedForeground) {
		if (focusedForeground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.focusedForeground = focusedForeground;
	}

	@Override
	public boolean[] getFocusedShowedBorders() {
		return this.focusedShowedBorders;
	}

	@Override
	public void setFocusedShowedBorders(boolean[] focusedShowedBorders) {
		if (showedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.focusedShowedBorders = focusedShowedBorders;
	}

	@Override
	public int getFocusedBorderSize() {
		return focusedBorderSize;
	}

	@Override
	public void setFocusedBorderSize(int focusedBorderSize) {
		if (focusedBorderSize < 0) throw new IllegalArgumentException("La taille de la bordure ne peut être négative");
		this.focusedBorderSize = focusedBorderSize;
	}

	@Override
	public Color getFocusedBorder() {
		return focusedBorder;
	}

	@Override
	public void setFocusedBorder(Color focusedBorder) {
		this.focusedBorder = focusedBorder;
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
	public void setIsHovered(boolean isHovered) {
		this.hovered = isHovered;
	}

	@Override
	public boolean isHovered() {
		return hovered;
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
	public void setAllBordersSize(int borderSize, int hoveredBorderSize, int focusedBorderSize) {
		if (borderSize < 0 || hoveredBorderSize < 0 || focusedBorderSize < 0)
			throw new IllegalArgumentException("La taille des bordures ne peuvent être négatives");
		this.borderSize = borderSize;
		this.hoveredBorderSize = hoveredBorderSize;
		this.focusedBorderSize = focusedBorderSize;
	}

	@Override
	public void setAllShowedBorders(boolean[] showedBorders, boolean[] hoveredShowedBorders, boolean[] focusedShowedBorders) {
		if (showedBorders.length != 4 || hoveredShowedBorders.length != 4 || focusedShowedBorders.length != 4)
			throw new IllegalArgumentException("Les tableaux doivent être de 4 éléments");
		this.showedBorders = showedBorders;
		this.hoveredShowedBorders = hoveredShowedBorders;
		this.focusedShowedBorders = focusedShowedBorders;
	}

	@Override
	public void setAllBackgrounds(Color background, Color hoveredBackground, Color focusedBackground) {
		if (background == null || hoveredBackground == null || focusedBackground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.background = background;
		this.hoveredBackground = hoveredBackground;
		this.focusedBackground = focusedBackground;
	}

	@Override
	public void setAllForegrounds(Color foreground, Color hoveredForeground, Color focusedForeground) {
		if (foreground == null || hoveredForeground == null || focusedForeground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.foreground = foreground;
		this.hoveredForeground = hoveredForeground;
		this.focusedForeground = focusedForeground;
	}

	@Override
	public void setAllBorders(Color border, Color hoveredBorder, Color focusedBorder) {
		this.border = border;
		this.hoveredBorder = hoveredBorder;
		this.focusedBorder = focusedBorder;
	}

	//non supporté

	@Override
	@Deprecated
	public ImageIcon getHoveredBackgroundImage() {
		throw new UnsupportedOperationException("non supporté");
	}

	@Override
	@Deprecated
	public void setHoveredBackgroundImage(ImageIcon hoveredBackgroundImage) {
		throw new UnsupportedOperationException("non supporté");
	}

	@Override
	@Deprecated
	public ImageIcon getBackgroundImage() {
		throw new UnsupportedOperationException("non supporté");
	}

	@Override
	@Deprecated
	public void setBackgroundImage(ImageIcon backgroundImage) {
		throw new UnsupportedOperationException("non supporté");
	}
}
