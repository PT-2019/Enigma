package api.ui.skin.base.states;

import api.ui.base.DefaultUIValues;
import api.ui.skin.base.CustomUI;
import api.ui.skin.base.CustomUIBackground;

import java.awt.Color;

/**
 * Représente les éléments que l'on peut survoler et focus.
 *
 * @param <T> la classe d'UI
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 28/12/2019
 * @since 4.0 28/12/2019
 */
public interface CustomUIFocusedAndHover<T extends CustomUI> extends CustomUIFocused<T>, CustomUIHover<T>, CustomUIBackground {

	/**
	 * Définit la taille de toutes les bordures
	 *
	 * @param borderSize        taille bordure normale
	 * @param hoveredBorderSize taille de la bordure au survol
	 * @param focusedBorderSize taille de la bordure au focus
	 */
	void setAllBordersSize(int borderSize, int hoveredBorderSize, int focusedBorderSize);

	/**
	 * Définit toutes les bordures
	 *
	 * @param showedBorders        tableau de 4, true pour afficher
	 * @param hoveredShowedBorders tableau de 4, true pour afficher
	 * @param focusedShowedBorders tableau de 4, true pour afficher
	 * @throws IllegalArgumentException si la taille d'un tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	void setAllShowedBorders(boolean[] showedBorders, boolean[] hoveredShowedBorders, boolean[] focusedShowedBorders);

	/**
	 * Définit les couleurs de tous fond
	 *
	 * @param background        fond normal
	 * @param hoveredBackground fond au survol
	 * @param focusedBackground fond au focus
	 */
	void setAllBackgrounds(Color background, Color hoveredBackground, Color focusedBackground);

	/**
	 * Définit les couleurs de tous fond
	 *
	 * @param background fond
	 */
	void setAllBackgrounds(Color background);

	/**
	 * Définit les couleurs au premier plan
	 *
	 * @param foreground        fond normal
	 * @param hoveredForeground fond au survol
	 * @param focusedForeground fond au focus
	 */
	void setAllForegrounds(Color foreground, Color hoveredForeground, Color focusedForeground);

	/**
	 * Définit les couleurs au premier plan
	 *
	 * @param foreground fond
	 */
	void setAllForegrounds(Color foreground);

	/**
	 * Définit les couleurs de toutes les bordures
	 *
	 * @param border        couleur normale
	 * @param hoveredBorder couleur survol
	 * @param focusedBorder couleur focus
	 */
	void setAllBorders(Color border, Color hoveredBorder, Color focusedBorder);

}
