package api.hud.ui.base.states;

import api.hud.base.DefaultUIValues;
import api.hud.ui.base.CustomUI;
import api.hud.ui.base.CustomUIBackground;

import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * Représente les éléments que l'on peut survoler et cliquer dessus.
 *
 * @param <T> la classe d'UI
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 27/12/2019
 * @since 4.0 27/12/2019
 */
public interface CustomUIHoverAndPressed<T extends CustomUI> extends CustomUIPressed<T>, CustomUIHover<T>, CustomUIBackground {

	/**
	 * Définit la taille de toutes les bordures
	 *
	 * @param borderSize        taille bordure normale
	 * @param hoveredBorderSize taille de la bordure au survol
	 * @param pressedBorderSize taille de la bordure au clic
	 */
	void setAllBordersSize(int borderSize, int hoveredBorderSize, int pressedBorderSize);

	/**
	 * Définit toutes les bordures
	 *
	 * @param showedBorders        tableau de 4, true pour afficher
	 * @param hoveredShowedBorders tableau de 4, true pour afficher
	 * @param pressedShowedBorders tableau de 4, true pour afficher
	 * @throws IllegalArgumentException si la taille d'un tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	void setAllShowedBorders(boolean[] showedBorders, boolean[] hoveredShowedBorders, boolean[] pressedShowedBorders);

	/**
	 * Définit les couleurs de tous fond
	 *
	 * @param background        fond normal
	 * @param hoveredBackground fond au survol
	 * @param pressedBackground fond au clic
	 */
	void setAllBackgrounds(Color background, Color hoveredBackground, Color pressedBackground);

	/**
	 * Définit les couleurs de toutes les bordures
	 *
	 * @param border        couleur normale
	 * @param hoveredBorder couleur survol
	 * @param pressedBorder couleur clic
	 */
	void setAllBorders(Color border, Color hoveredBorder, Color pressedBorder);

	/**
	 * Définit les couleurs de toutes les bordures
	 *
	 * @param border couleur normale
	 */
	void setAllBorders(Color border);

	/**
	 * Définit les images de tous les fonds
	 *
	 * @param background        fond normal
	 * @param hoveredBackground fond au survol
	 * @param pressedBackground fond au clic
	 */
	void setAllBackgroundImage(ImageIcon background, ImageIcon hoveredBackground, ImageIcon pressedBackground);

}
