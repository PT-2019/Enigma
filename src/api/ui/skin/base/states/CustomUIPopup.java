package api.ui.skin.base.states;

import api.ui.base.DefaultUIValues;

import java.awt.Color;

/**
 * Représente le style d'un popup ?
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 29/12/2019
 * @since 4.0 29/12/2019
 */
public interface CustomUIPopup {

	/**
	 * Retourne la couleur de la bordure du popup
	 *
	 * @return la couleur de la bordure du popup
	 */
	Color getPopupBorder();

	/**
	 * Définit la couleur de la bordure du popup
	 *
	 * @param popupBorder la couleur de la bordure du popup
	 */
	void setPopupBorder(Color popupBorder);

	/**
	 * Retourne la couleur du fond du popup
	 *
	 * @return la couleur du fond du popup
	 */
	Color getPopupBackground();

	/**
	 * Définit la couleur du fond du popup
	 *
	 * @param popupBackground la couleur du fond du popup
	 */
	void setPopupBackground(Color popupBackground);

	/**
	 * Retourne la taille de la bordure du popup
	 *
	 * @return la taille de la bordure du popup
	 */
	int getPopupBorderSize();

	/**
	 * Définit la taille de la bordure du popup
	 *
	 * @param popupBorderSize la taille de la bordure du popup
	 */
	void setPopupBorderSize(int popupBorderSize);

	/**
	 * Retourne les bordures à afficher au survol
	 *
	 * @return les bordures à afficher au survol, tableau de 4, true pour affiché
	 * @throws IllegalArgumentException si la taille d'un tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	boolean[] getShowedPopupBorders();

	/**
	 * Définit les bordures à afficher au survol
	 *
	 * @param showedPopupBorders tableau de 4, true pour afficher
	 * @throws IllegalArgumentException si la taille d'un tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	void setShowedPopupBorders(boolean[] showedPopupBorders);

}
