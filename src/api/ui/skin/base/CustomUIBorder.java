package api.ui.skin.base;

import api.ui.base.DefaultUIValues;

import java.awt.Color;

/**
 * Méthodes pour les composants de l'UI voulant une bordure.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 27/12/2019
 * @since 4.0 27/12/2019
 */
public interface CustomUIBorder {

	/**
	 * Retourne la couleur des bordures
	 *
	 * @return la couleur des bordures
	 */
	Color getBorder();

	/**
	 * Définit la couleur des bordures
	 *
	 * @param border la couleur des bordures
	 */
	void setBorder(Color border);

	/**
	 * Retourne un tableau de taille 4 au format de {@link #setShowedBorders(boolean[])}
	 *
	 * @return un tableau de taille 4 au format de {@link #setShowedBorders(boolean[])}
	 */
	boolean[] getShowedBorders();

	/**
	 * Si on doit afficher seulement certaines bordures
	 *
	 * @param showedBorders true pour afficher sinon false, tableau de taille [4]
	 * @throws IllegalArgumentException si la taille du tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	void setShowedBorders(boolean[] showedBorders);

	/**
	 * Retourne la taille des bordures
	 *
	 * @return la taille des bordures
	 */
	int getBorderSize();

	/**
	 * Définit la taille des bordures
	 *
	 * @param borderSize la taille des bordures
	 */
	void setBorderSize(int borderSize);
}
