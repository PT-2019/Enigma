package api.hud.ui;

import api.hud.base.DefaultUIValues;
import api.hud.ui.base.CustomUI;
import api.hud.ui.base.CustomUIBackground;
import api.hud.ui.base.CustomUICursor;
import api.hud.ui.base.CustomUIFont;
import api.hud.ui.base.CustomUIForeground;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.plaf.basic.BasicMenuItemUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Style de base d'un élément d'un menu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 29/12/2019
 * @since 4.0 29/12/2019
 */
public class CustomMenuItemUI extends BasicMenuItemUI implements CustomUI<CustomMenuItemUI>, CustomUIFont, CustomUIBackground, CustomUIForeground, CustomUICursor {

	protected Color background, foreground;
	protected Cursor cursor;
	protected Font font;

	/**
	 * Style de base d'un élément d'uns menu
	 */
	public CustomMenuItemUI() {
		this.background = DefaultUIValues.DEFAULT_MENU_ITEM_BACKGROUND;
		this.foreground = DefaultUIValues.DEFAULT_MENU_ITEM_FOREGROUND;
		this.cursor = new Cursor(Cursor.HAND_CURSOR);
		this.font = DefaultUIValues.DEFAULT_FONT;
	}

	@Override
	public CustomMenuItemUI duplicate() {
		return duplicate(this);
	}

	/**
	 * Crée une copie de l'ui d'un élément d'un menu
	 *
	 * @param customMenuItemUI l'ui d'un élément d'un menu
	 * @param <T>              qui doit extends CustomMenuItemUI
	 * @return une copie de l'ui d'un élément d'un menu
	 * @throws IllegalStateException si une copie échoue a être crée
	 */
	@SuppressWarnings("unchecked")
	protected <T extends CustomMenuItemUI> T duplicate(T customMenuItemUI) {
		T clone = null;
		try {
			Constructor<?> c = customMenuItemUI.getClass().getDeclaredConstructor();
			clone = (T) c.newInstance();
		} catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}

		clone.setCursor(this.getCursor());
		clone.setBackground(this.getBackground());
		clone.setForeground(this.getForeground());
		clone.setFont(this.getFont());

		return clone;
	}

	//dessin

	@Override
	public void paint(Graphics g, JComponent c) {
		Graphics brush = g.create();
		JMenuItem mi = (JMenuItem) c;
		mi.setBackground(this.background);
		mi.setForeground(this.foreground);
		super.paint(brush, c);
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
	public Color getForeground() {
		return foreground;
	}

	@Override
	public void setForeground(Color foreground) {
		if (foreground == null) throw new NullPointerException("L'argument ne peut pas être null");
		this.foreground = foreground;
	}

	@Override
	public ImageIcon getBackgroundImage() {
		throw new UnsupportedOperationException("non disponible");
	}

	@Override
	public void setBackgroundImage(ImageIcon backgroundImage) {
		throw new UnsupportedOperationException("non disponible");
	}
}