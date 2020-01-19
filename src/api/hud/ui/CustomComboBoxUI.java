package api.hud.ui;

import api.hud.DefaultUIValues;
import api.hud.ui.base.CustomUI;
import api.hud.ui.base.CustomUIBorder;
import api.hud.ui.base.CustomUICursor;
import api.hud.ui.base.CustomUIFont;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Apparence d'une liste déroulante
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 29/12/2019
 * @since 4.0 29/12/2019
 */
public class CustomComboBoxUI extends BasicComboBoxUI implements CustomUI<CustomComboBoxUI>, CustomUIFont, CustomUICursor, CustomUIBorder {

	protected Color border;
	protected int borderSize;
	protected boolean[] showedBorders;
	protected Cursor cursor;
	protected CustomLabelUI labelUI;
	protected CustomButtonUI buttonUI;
	protected CustomPopupMenuUI popupUI;
	protected Font font;

	public CustomComboBoxUI() {
		this.border = DefaultUIValues.DEFAULT_COMBOBOX_BORDER;
		this.borderSize = DefaultUIValues.DEFAULT_COMBOBOX_BORDER_SIZE;
		this.showedBorders = DefaultUIValues.DEFAULT_COMBOBOX_SHOWED_BORDERS;
		this.cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		this.font = DefaultUIValues.DEFAULT_FONT;

		this.labelUI = new CustomLabelUI();
		this.labelUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		this.buttonUI = new CustomButtonUI();
		Color color = new Color(100, 100, 100);
		this.buttonUI.setAllBackgrounds(color, color, color);
		this.buttonUI.setAllBorders(Color.WHITE, Color.WHITE, Color.WHITE);
		boolean[] sb = new boolean[4];
		boolean[] shb = new boolean[4];
		boolean[] spb = new boolean[4];
		sb[DefaultUIValues.LEFT_BORDER] = true;
		shb[DefaultUIValues.LEFT_BORDER] = true;
		spb[DefaultUIValues.LEFT_BORDER] = true;
		this.buttonUI.setAllShowedBorders(sb, shb, spb);
		this.buttonUI.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		this.buttonUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		this.popupUI = new CustomPopupMenuUI();
		this.popupUI.setPopupBorder(Color.WHITE);
		boolean[] borders = new boolean[4];
		borders[DefaultUIValues.TOP_BORDER] = true;
		this.popupUI.setShowedPopupBorders(borders);
	}

	@Override
	public CustomComboBoxUI duplicate() {
		return duplicate(this);
	}

	/**
	 * Crée une copie de l'ui d'une liste déroulante
	 *
	 * @param customComboBoxUI l'ui d'une liste déroulante
	 * @param <T>              qui doit extends CustomComboBoxUI
	 * @return une copie de l'ui d'une liste déroulante
	 * @throws IllegalStateException si une copie échoue a être crée
	 */
	@SuppressWarnings("unchecked")
	protected <T extends CustomComboBoxUI> T duplicate(T customComboBoxUI) {
		T clone = null;
		try {
			Constructor<?> c = customComboBoxUI.getClass().getDeclaredConstructor();
			clone = (T) c.newInstance();
		} catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}

		clone.setCursor(this.getCursor());
		clone.setBorder(this.getBorder());
		clone.setBorderSize(this.getBorderSize());
		clone.setShowedBorders(this.getShowedBorders());
		clone.setLabelUI(this.getLabelUI());
		clone.setButtonUI(this.getButtonUI());
		clone.setPopupUI(this.getPopupUI());
		clone.setFont(this.getFont());

		return clone;
	}

	// dessin

	@Override
	public void paint(Graphics g, JComponent c) {
		Graphics brush = g.create();
		JPanel p = (JPanel) c;
		p.setBorder(BorderFactory.createEmptyBorder());
		if (this.border != null) {
			brush.setColor(this.border);
			brush.fillRect(0, 0, c.getWidth(), c.getHeight());
			this.paintBorder(p, this.border, this.borderSize, this.showedBorders);
		}
	}

	/**
	 * Dessine les bordure d'une combo Box
	 *
	 * @param p             ignore
	 * @param borderColor   ignore
	 * @param borderSize    ignore
	 * @param showedBorders ignore
	 */
	public void paintBorder(JPanel p, Color borderColor, int borderSize, boolean[] showedBorders) {
		int[] borders = new int[4];
		for (int i = 0; i < borders.length; i++) {
			if (showedBorders[i]) borders[i] = borderSize;
			else borders[i] = 0;
		}
		p.setBorder(BorderFactory.createMatteBorder(
				borders[DefaultUIValues.TOP_BORDER],
				borders[DefaultUIValues.LEFT_BORDER],
				borders[DefaultUIValues.BOTTOM_BORDER],
				borders[DefaultUIValues.RIGHT_BORDER], borderColor)
		);
	}

	//own

	/**
	 * Retourne l'ui utilisée par les labels pour le style des liste déroulantes
	 *
	 * @return l'ui utilisée par les labels pour le style des liste déroulantes
	 */
	public CustomLabelUI getLabelUI() {
		return labelUI;
	}

	/**
	 * Définit l'ui utilisée par les labels pour le style des liste déroulantes
	 *
	 * @param labelUI l'ui utilisée par les labels pour le style des liste déroulantes
	 */
	public void setLabelUI(CustomLabelUI labelUI) {
		this.labelUI = labelUI;
	}

	/**
	 * Retourne l'ui utilisée par les boutons pour le style des liste déroulantes
	 *
	 * @return l'ui utilisée par les boutons pour le style des liste déroulantes
	 */
	public CustomButtonUI getButtonUI() {
		return buttonUI;
	}

	/**
	 * Retourne l'ui utilisée par les boutons pour le style des liste déroulantes
	 *
	 * @param buttonUI l'ui utilisée par les boutons pour le style des liste déroulantes
	 */
	public void setButtonUI(CustomButtonUI buttonUI) {
		this.buttonUI = buttonUI;
	}

	/**
	 * Retourne l'ui utilisée par les popup pour le style des liste déroulantes
	 *
	 * @return l'ui utilisée par les popup pour le style des liste déroulantes
	 */
	public CustomPopupMenuUI getPopupUI() {
		return popupUI;
	}

	/**
	 * Retourne l'ui utilisée par les popup pour le style des liste déroulantes
	 *
	 * @param popupUI l'ui utilisée par les popup pour le style des liste déroulantes
	 */
	public void setPopupUI(CustomPopupMenuUI popupUI) {
		this.popupUI = popupUI;
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
	public boolean[] getShowedBorders() {
		return this.showedBorders;
	}

	@Override
	public void setShowedBorders(boolean[] showedBorders) {
		if (showedBorders.length != 4) throw new IllegalArgumentException("Le tableau doit être de 4 éléments");
		this.showedBorders = showedBorders;
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
	public Cursor getCursor() {
		return this.cursor;
	}

	@Override
	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}

	@Override
	public Color getBorder() {
		return border;
	}

	@Override
	public void setBorder(Color border) {
		this.border = border;
	}
}
