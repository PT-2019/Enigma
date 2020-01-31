package api.hud.ui;

import api.hud.base.DefaultUIValues;
import api.hud.ui.base.CustomUI;
import api.hud.ui.base.states.CustomUIPopup;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.plaf.basic.BasicPopupMenuUI;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Style d'un popup de menu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 29/12/2019
 * @since 4.0 29/12/2019
 */
public class CustomPopupMenuUI extends BasicPopupMenuUI implements CustomUI<CustomPopupMenuUI>, CustomUIPopup {

	protected Color popupBackground;
	protected Color popupBorder;
	protected int popupBorderSize;
	protected boolean[] showedPopupBorders;

	public CustomPopupMenuUI() {
		this.popupBackground = DefaultUIValues.DEFAULT_POPUP_MENU_BACKGROUND;
		this.popupBorder = DefaultUIValues.DEFAULT_POPUP_MENU_BORDER;
		this.popupBorderSize = DefaultUIValues.DEFAULT_POPUP_MENU_BORDER_SIZE;
		this.showedPopupBorders = DefaultUIValues.DEFAULT_POPUP_MENU_SHOWED_BORDER;
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
			pm.setBorder(BorderFactory.createMatteBorder(
					borders[DefaultUIValues.TOP_BORDER],
					borders[DefaultUIValues.LEFT_BORDER],
					borders[DefaultUIValues.BOTTOM_BORDER],
					borders[DefaultUIValues.RIGHT_BORDER], this.popupBorder)
			);
		}
		super.paint(brush, c);
	}

	@Override
	public CustomPopupMenuUI duplicate() {
		return duplicate(this);
	}

	/**
	 * Crée une copie de l'ui popup de menu
	 *
	 * @param customPopupMenuUI l'ui popup de menu
	 * @param <T>               qui doit extends CustomPopupMenuUI
	 * @return une copie de l'ui popup de menu
	 * @throws IllegalStateException si une copie échoue a être crée
	 */
	@SuppressWarnings("unchecked")
	protected <T extends CustomPopupMenuUI> T duplicate(T customPopupMenuUI) {
		T clone;
		try {
			Constructor<?> c = customPopupMenuUI.getClass().getDeclaredConstructor();
			clone = (T) c.newInstance();
		} catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}

		clone.setPopupBorder(this.getPopupBorder());
		clone.setPopupBackground(this.getPopupBackground());
		clone.setPopupBorderSize(this.getPopupBorderSize());
		clone.setShowedPopupBorders(this.getShowedPopupBorders());

		return clone;
	}

	//implements

	@Override
	public Color getPopupBorder() {
		return popupBorder;
	}

	@Override
	public void setPopupBorder(Color popupBorder) {
		this.popupBorder = popupBorder;
	}

	@Override
	public Color getPopupBackground() {
		return popupBackground;
	}

	@Override
	public void setPopupBackground(Color popupBackground) {
		this.popupBackground = popupBackground;
	}

	@Override
	public int getPopupBorderSize() {
		return popupBorderSize;
	}

	@Override
	public void setPopupBorderSize(int popupBorderSize) {
		this.popupBorderSize = popupBorderSize;
	}

	@Override
	public boolean[] getShowedPopupBorders() {
		return showedPopupBorders;
	}

	@Override
	public void setShowedPopupBorders(boolean[] showedPopupBorders) {
		this.showedPopupBorders = showedPopupBorders;
	}
}
