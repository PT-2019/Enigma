package api.hud.ui;

import api.hud.base.DefaultUIValues;
import api.hud.ui.base.CustomUI;
import api.hud.ui.base.CustomUIBackground;
import api.hud.ui.base.CustomUICursor;
import api.hud.ui.base.CustomUIFont;
import api.hud.ui.base.CustomUIForeground;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Style d'un menu customizable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 29/12/2019
 * @since 4.0 29/12/2019
 */
public class CustomMenuUI extends BasicMenuUI implements CustomUI<CustomMenuUI>, CustomUIForeground, CustomUIFont, CustomUICursor, CustomUIBackground {

	protected Color background, foreground;
	protected Color hoveredBackground, hoveredForeground, popupBackground;
	protected int popupBorderSize;
	protected boolean[] showedPopupBorders;
	protected boolean hovered;
	protected Cursor cursor;
	protected Font font;

	public CustomMenuUI() {
		this.background = DefaultUIValues.DEFAULT_MENU_BACKGROUND;
		this.hoveredBackground = DefaultUIValues.DEFAULT_MENU_HOVERED_BACKGROUND;
		this.foreground = DefaultUIValues.DEFAULT_MENU_FOREGROUND;
		this.hoveredForeground = DefaultUIValues.DEFAULT_MENU_HOVERED_FOREGROUND;
		this.popupBackground = DefaultUIValues.DEFAULT_MENU_POPUP_BACKGROUND;
		this.popupBorderSize = DefaultUIValues.DEFAULT_MENU_POPUP_BORDER_SIZE;
		this.showedPopupBorders = DefaultUIValues.DEFAULT_MENU_POPUP_SHOWED_BORDER;
		this.hovered = false;
		this.cursor = new Cursor(Cursor.HAND_CURSOR);
		this.font = DefaultUIValues.DEFAULT_FONT;
	}

	@Override
	public CustomMenuUI duplicate() {
		return duplicate(this);
	}

	/**
	 * Crée une copie de l'ui d'un menu
	 *
	 * @param customMenuUI l'ui d'un menu
	 * @param <T>          qui doit extends CustomMenuUI
	 * @return une copie de l'ui d'un menu
	 * @throws IllegalStateException si une copie échoue a être crée
	 */
	@SuppressWarnings("unchecked")
	protected <T extends CustomMenuUI> T duplicate(T customMenuUI) {
		T clone = null;
		try {
			Constructor<?> c = customMenuUI.getClass().getDeclaredConstructor();
			clone = (T) c.newInstance();
		} catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}

		clone.setCursor(this.getCursor());
		clone.setAllBackgrounds(this.getBackground(), this.getHoveredBackground());
		clone.setAllForegrounds(this.getForeground(), this.getHoveredForeground());
		clone.setPopupBackground(this.getPopupBackground());
		clone.setPopupBorderSize(this.getPopupBorderSize());
		clone.setShowedPopupBorders(this.getShowedPopupBorders());
		clone.setFont(this.getFont());

		return clone;
	}

	// dessin

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

		m.getPopupMenu().setBorder(BorderFactory.createMatteBorder(borders[DefaultUIValues.TOP_BORDER],
				borders[DefaultUIValues.LEFT_BORDER],
				borders[DefaultUIValues.BOTTOM_BORDER],
				borders[DefaultUIValues.RIGHT_BORDER], popupBackground));
		super.paint(brush, c);
	}

	//own

	/**
	 * Définit si on survol l'object
	 *
	 * @param isHovered true si on survol l'object
	 */
	public void setIsHovered(boolean isHovered) {
		this.hovered = isHovered;
	}

	/**
	 * Retourne si élément est survol
	 *
	 * @return true si élément est survol
	 */
	public boolean isHovered() {
		return hovered;
	}

	/**
	 * Retourne la couleur du fond de survol
	 *
	 * @return la couleur du fond de survol
	 */
	public Color getHoveredBackground() {
		return hoveredBackground;
	}

	/**
	 * Définit la couleur de survol
	 *
	 * @param hoveredBackground la couleur de survol
	 */
	public void setHoveredBackground(Color hoveredBackground) {
		if (hoveredBackground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.hoveredBackground = hoveredBackground;
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
	 * Retourne fond popup du menu
	 *
	 * @return fond popup du menu
	 */
	public Color getPopupBackground() {
		return popupBackground;
	}

	/**
	 * Définit fond popup du menu
	 *
	 * @param popupBackground fond popup du menu
	 */
	public void setPopupBackground(Color popupBackground) {
		this.popupBackground = popupBackground;
	}

	/**
	 * Retourne taille popup du menu
	 *
	 * @return taille popup du menu
	 */
	public int getPopupBorderSize() {
		return popupBorderSize;
	}

	/**
	 * Définit taille popup du menu
	 *
	 * @param popupBorderSize taille popup du menu
	 */
	public void setPopupBorderSize(int popupBorderSize) {
		this.popupBorderSize = popupBorderSize;
	}

	/**
	 * Retourne les bordures à afficher
	 *
	 * @return les bordures à afficher, tableau de 4, true pour affiché
	 * @throws IllegalArgumentException si la taille d'un tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	public boolean[] getShowedPopupBorders() {
		return showedPopupBorders;
	}

	/**
	 * Définit les bordures à afficher
	 *
	 * @param showedPopupBorders les bordures à afficher, tableau de 4, true pour affiché
	 * @throws IllegalArgumentException si la taille d'un tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	public void setShowedPopupBorders(boolean[] showedPopupBorders) {
		this.showedPopupBorders = showedPopupBorders;
	}

	/**
	 * Définit tous les fond
	 *
	 * @param background        fond normal
	 * @param hoveredBackground fond survol
	 */
	public void setAllBackgrounds(Color background, Color hoveredBackground) {
		if (background == null || hoveredBackground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.background = background;
		this.hoveredBackground = hoveredBackground;
	}

	/**
	 * Définit tous les fond (premier plan)
	 *
	 * @param foreground        fond normal
	 * @param hoveredForeground fond survol
	 */
	public void setAllForegrounds(Color foreground, Color hoveredForeground) {
		if (foreground == null || hoveredForeground == null)
			throw new NullPointerException("Les arguments ne peuvent pas être null");
		this.foreground = foreground;
		this.hoveredForeground = hoveredForeground;
	}

	// implements

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
	public Color getForeground() {
		return foreground;
	}

	@Override
	public void setForeground(Color foreground) {
		if (foreground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.foreground = foreground;
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

	//trash

	@Override
	public ImageIcon getBackgroundImage() {
		throw new UnsupportedOperationException("non supporté");
	}

	@Override
	public void setBackgroundImage(ImageIcon backgroundImage) {
		throw new UnsupportedOperationException("non supporté");
	}


}
