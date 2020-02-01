package api.ui.skin;

import api.ui.base.DefaultUIValues;
import api.ui.skin.base.CustomUI;
import api.ui.skin.base.CustomUIBackground;
import api.ui.skin.base.CustomUIBorder;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.plaf.basic.BasicMenuBarUI;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Barre customizable de menus
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class CustomMenuBarUI extends BasicMenuBarUI implements CustomUI<CustomMenuBarUI>, CustomUIBorder, CustomUIBackground {

	/**
	 * Fond de la barre de menus
	 */
	protected Color background;
	/**
	 * Bordures de la barre de menus
	 */
	protected Color border;
	/**
	 * Taille de la bordure
	 */
	protected int borderSize;
	/**
	 * Si on doit afficher une bordure
	 *
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 * <p>
	 * true pour afficher sinon false
	 * @see #setShowedBorders(boolean[])
	 */
	protected boolean[] showedBorders;

	/**
	 * Crée une barre customizable de menus
	 */
	public CustomMenuBarUI() {
		this.background = DefaultUIValues.DEFAULT_MENU_BAR_BACKGROUND;
		this.border = DefaultUIValues.DEFAULT_MENU_BAR_BORDER;
		this.borderSize = DefaultUIValues.DEFAULT_MENU_BAR_BORDER_SIZE;
		this.showedBorders = DefaultUIValues.DEFAULT_MENU_BAR_SHOWED_BORDERS;
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		Graphics brush = g.create();
		JMenuBar mb = (JMenuBar) c;
		brush.setColor(this.background);
		brush.fillRect(0, 0, mb.getWidth(), mb.getHeight());

		if (this.border != null) {
			brush.setColor(this.border);
			for (int i = 0; i < 4; i++) {
				if (i == DefaultUIValues.TOP_BORDER && this.showedBorders[i])
					brush.fillRect(0, 0, mb.getWidth(), this.borderSize);
				if (i == DefaultUIValues.RIGHT_BORDER && this.showedBorders[i])
					brush.fillRect(mb.getWidth() - this.borderSize, 0, mb.getWidth(), mb.getHeight());
				if (i == DefaultUIValues.BOTTOM_BORDER && this.showedBorders[i])
					brush.fillRect(0, mb.getHeight() - this.borderSize, mb.getWidth(), mb.getHeight());
				if (i == DefaultUIValues.LEFT_BORDER && this.showedBorders[i])
					brush.fillRect(0, 0, this.borderSize, mb.getHeight());
			}
		}
		super.paint(g, c);
	}

	/**
	 * Crée une copie de la barre de menus
	 *
	 * @return une copie de la barre de menus
	 */
	@Override
	public CustomMenuBarUI duplicate() {
		CustomMenuBarUI clone = new CustomMenuBarUI();

		clone.setBackground(this.getBackground());
		clone.setBorder(this.getBorder());
		clone.setBorderSize(this.getBorderSize());
		clone.setShowedBorders(this.getShowedBorders());

		return clone;
	}

	/**
	 * Crée une copie de l'ui d'une barre de menus
	 *
	 * @param customButtonUI l'ui d'une barre de menus
	 * @param <T>            qui doit extends CustomMenuBarUI
	 * @return une copie de l'ui d'une barre de menus
	 * @throws IllegalStateException si une copie échoue a être crée
	 */
	@SuppressWarnings("unchecked")
	protected <T extends CustomMenuBarUI> T duplicate(T customButtonUI) {
		T clone;
		try {
			Constructor<?> c = customButtonUI.getClass().getDeclaredConstructor();
			clone = (T) c.newInstance();
		} catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}

		clone.setBackground(this.getBackground());
		clone.setBorder(this.getBorder());
		clone.setBorderSize(this.getBorderSize());
		clone.setShowedBorders(this.getShowedBorders());

		return clone;
	}

	//basic

	@Override
	public int getBorderSize() {
		return borderSize;
	}

	@Override
	public void setBorderSize(int borderSize) {
		this.borderSize = borderSize;
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
	public Color getBorder() {
		return border;
	}

	@Override
	public void setBorder(Color border) {
		this.border = border;
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
	public ImageIcon getBackgroundImage() {
		throw new UnsupportedOperationException("not available");
	}

	@Override
	public void setBackgroundImage(ImageIcon backgroundImage) {
		throw new UnsupportedOperationException("not available");
	}
}
