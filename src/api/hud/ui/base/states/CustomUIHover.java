package api.hud.ui.base.states;

import api.hud.base.DefaultUIValues;
import api.hud.ui.base.CustomUI;
import api.hud.ui.base.CustomUIBorder;
import api.hud.ui.base.CustomUICursor;

import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * Composants UI pouvant être survolés
 *
 * @param <T> la classe d'UI
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 28/12/2019
 * @since 4.0 28/12/2019
 */
public interface CustomUIHover<T extends CustomUI> extends CustomUICursor, CustomUI<T>, CustomUIBorder {

	/**
	 * Retourne l'image de survol
	 *
	 * @return l'image de survol
	 */
	ImageIcon getHoveredBackgroundImage();

	/**
	 * Définit une image au survol
	 *
	 * @param hoveredBackgroundImage une image au survol
	 */
	void setHoveredBackgroundImage(ImageIcon hoveredBackgroundImage);

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
	boolean[] getHoveredShowedBorders();

	/**
	 * Définit les bordures à afficher au survol
	 *
	 * @param hoveredShowedBorders tableau de 4, true pour afficher
	 * @throws IllegalArgumentException si la taille d'un tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	void setHoveredShowedBorders(boolean[] hoveredShowedBorders);

	/**
	 * Retourne la taille des bordure au survol
	 *
	 * @return la taille des bordure au survol
	 */
	int getHoveredBorderSize();

	/**
	 * Définit la taille des bordure au survol
	 *
	 * @param hoveredBorderSize la taille des bordure au survol
	 */
	void setHoveredBorderSize(int hoveredBorderSize);

	/**
	 * Retourne si élément est survol
	 *
	 * @return true si élément est survol
	 */
	boolean isHovered();


	/**
	 * Retourne la couleur du fond de survol
	 *
	 * @return la couleur du fond de survol
	 */
	Color getHoveredBackground();

	/**
	 * Définit la couleur de survol
	 *
	 * @param hoveredBackground la couleur de survol
	 */
	void setHoveredBackground(Color hoveredBackground);

	/**
	 * Retourne la couleur de la bordure au survol
	 *
	 * @return la couleur de la bordure au survol
	 */
	Color getHoveredBorder();

	/**
	 * Définit la bordure de survol
	 *
	 * @param hoveredBorder la bordure de survol
	 */
	void setHoveredBorder(Color hoveredBorder);

	/**
	 * Définit si on survol l'object
	 *
	 * @param isHovered true si on survol l'object
	 */
	void setIsHovered(boolean isHovered);
}
