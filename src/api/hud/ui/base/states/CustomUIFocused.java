package api.hud.ui.base.states;

import api.hud.DefaultUIValues;
import api.hud.ui.base.CustomUI;
import api.hud.ui.base.CustomUIBorder;
import api.hud.ui.base.CustomUICursor;

import java.awt.Color;

/**
 * Composants UI pouvant être ciblés
 *
 * @param <T> la classe d'UI
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 28/12/2019
 * @since 4.0 28/12/2019
 */
public interface CustomUIFocused<T extends CustomUI> extends CustomUICursor, CustomUI<T>, CustomUIBorder {

	/**
	 * Retourne la couleur de fond au focus
	 *
	 * @return la couleur de fond au focus
	 */
	Color getFocusedBackground();

	/**
	 * Définit la couleur de fond au focus
	 *
	 * @param focusedBackground la couleur de fond au focus
	 */
	void setFocusedBackground(Color focusedBackground);

	/**
	 * Définit la couleur de police au focus
	 *
	 * @return la couleur de police au focus
	 */
	Color getFocusedForeground();

	/**
	 * Définit la couleur de police au focus
	 *
	 * @param focusedForeground la couleur de police au focus
	 */
	void setFocusedForeground(Color focusedForeground);

	/**
	 * Définit la couleur de bordure au focus
	 *
	 * @return la couleur de bordure au focus
	 */
	Color getFocusedBorder();

	/**
	 * Définit la couleur de bordure au focus
	 *
	 * @param focusedBorder la couleur de bordure au focus
	 */
	void setFocusedBorder(Color focusedBorder);

	/**
	 * Retourne les bordures à afficher au focus
	 *
	 * @return les bordures à afficher au focus, tableau de 4, true pour affiché
	 * @throws IllegalArgumentException si la taille d'un tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	boolean[] getFocusedShowedBorders();

	/**
	 * Définit les bordures à afficher au focus
	 *
	 * @param focusedShowedBorders tableau de 4, true pour afficher
	 * @throws IllegalArgumentException si la taille d'un tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	void setFocusedShowedBorders(boolean[] focusedShowedBorders);

	/**
	 * Retourne la taille des bordure au focus
	 *
	 * @return la taille des bordure au focus
	 */
	int getFocusedBorderSize();

	/**
	 * Retourne la taille des bordure au focus
	 *
	 * @param focusedBorderSize la taille des bordure au focus
	 */
	void setFocusedBorderSize(int focusedBorderSize);

}
