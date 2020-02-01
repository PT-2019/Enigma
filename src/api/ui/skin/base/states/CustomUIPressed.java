package api.ui.skin.base.states;

import api.ui.base.DefaultUIValues;
import api.ui.skin.base.CustomUI;
import api.ui.skin.base.CustomUIBorder;
import api.ui.skin.base.CustomUICursor;

import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * Composant UI pouvant être appuyés
 *
 * @param <T> la classe d'UI
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 28/12/2019
 * @since 4.0 28/12/2019
 */
public interface CustomUIPressed<T extends CustomUI> extends CustomUICursor, CustomUI<T>, CustomUIBorder {

	/**
	 * Retourne l'image à l'appui
	 *
	 * @return l'image à l'appui
	 */
	ImageIcon getPressedBackgroundImage();


	/**
	 * Définit l'image à l'appui
	 *
	 * @param pressedBackgroundImage l'image à l'appui
	 */
	void setPressedBackgroundImage(ImageIcon pressedBackgroundImage);

	/**
	 * Retourne les bordures à afficher si on appui
	 *
	 * @return les bordures à afficher si on appui, tableau de 4, true pour affiché
	 * @throws IllegalArgumentException si la taille d'un tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	boolean[] getPressedShowedBorders();

	/**
	 * Définit les bordures a afficher si on appui
	 *
	 * @param pressedShowedBorders tableau de 4, true pour afficher
	 * @throws IllegalArgumentException si la taille d'un tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	void setPressedShowedBorders(boolean[] pressedShowedBorders);

	/**
	 * Retourne la taille des bordure au clic
	 *
	 * @return la taille des bordure au clic
	 */
	int getPressedBorderSize();

	/**
	 * la taille des bordure au clic
	 *
	 * @param pressedBorderSize la taille des bordure au clic
	 */
	void setPressedBorderSize(int pressedBorderSize);

	/**
	 * Retourne la couleur du fond au clic
	 *
	 * @return la couleur du fond au clic
	 */
	Color getPressedBackground();

	/**
	 * Définit la couleur au clic
	 *
	 * @param pressedBackground la couleur au clic
	 */
	void setPressedBackground(Color pressedBackground);

	/**
	 * Retourne la couleur de la bordure au clic
	 *
	 * @return la couleur de la bordure au clic
	 */
	Color getPressedBorder();

	/**
	 * Définit la bordure au clic
	 *
	 * @param pressedBorder la bordure au clic
	 */
	void setPressedBorder(Color pressedBorder);

}