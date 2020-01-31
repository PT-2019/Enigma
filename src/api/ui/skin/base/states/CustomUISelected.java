package api.ui.skin.base.states;

import api.ui.base.DefaultUIValues;
import api.ui.skin.base.CustomUI;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;

/**
 * Composant UI sélectionnable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 31/01/2020
 * @since 5.0 31/01/2020
 */
public interface CustomUISelected<T extends CustomUI> extends CustomUI<T> {


	/**
	 * Définit tous les fonds
	 *
	 * @param selectedBackgroundImage        fond
	 * @param selectedHoveredBackgroundImage fond
	 * @param selectedPressedBackgroundImage fond
	 */
	void setAllSelectedBackgroundImage(ImageIcon selectedBackgroundImage,
	                                   ImageIcon selectedHoveredBackgroundImage,
	                                   ImageIcon selectedPressedBackgroundImage);

	/**
	 * Définit tous les fonds
	 *
	 * @param backgroundImage fond
	 */
	void setAllSelectedBackgroundImage(ImageIcon backgroundImage);

	/**
	 * Retourne fond sélection
	 *
	 * @return fond sélection
	 */
	ImageIcon getSelectedBackgroundImage();

	/**
	 * Définit fond sélection
	 *
	 * @param selectedBackgroundImage fond sélection
	 */
	void setSelectedBackgroundImage(ImageIcon selectedBackgroundImage);

	/**
	 * Retourne fond sélection survol
	 *
	 * @return fond sélection survol
	 */
	ImageIcon getSelectedHoveredBackgroundImage();

	/**
	 * Définit fond sélection survol
	 *
	 * @param selectedHoveredBackgroundImage fond sélection survol
	 */
	void setSelectedHoveredBackgroundImage(ImageIcon selectedHoveredBackgroundImage);

	/**
	 * Retourne fond sélection appuyé
	 *
	 * @return fond sélection appuyé
	 */
	ImageIcon getSelectedPressedBackgroundImage();

	/**
	 * Définit fond sélection appuyé
	 *
	 * @param selectedPressedBackgroundImage fond sélection appuyé
	 */
	void setSelectedPressedBackgroundImage(ImageIcon selectedPressedBackgroundImage);

	/**
	 * Retourne cursor sélection
	 *
	 * @return cursor sélection
	 */
	Cursor getSelectedCursor();

	/**
	 * Définit cursor sélection
	 *
	 * @param selectedCursor cursor sélection
	 */
	void setSelectedCursor(Cursor selectedCursor);

	/**
	 * Retourne un tableau de taille 4, true pour afficher sinon false
	 *
	 * @return un tableau de taille 4, true pour afficher sinon false
	 */
	boolean[] getSelectedShowedBorders();

	/**
	 * Si on doit afficher seulement certaines bordures
	 *
	 * @param selectedShowedBorders true pour afficher sinon false, tableau de taille [4]
	 * @throws IllegalArgumentException si la taille du tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	void setSelectedShowedBorders(boolean[] selectedShowedBorders);

	/**
	 * Retourne un tableau de taille 4, true pour afficher sinon false
	 *
	 * @return un tableau de taille 4, true pour afficher sinon false
	 */
	boolean[] getSelectedHoveredShowedBorders();

	/**
	 * Si on doit afficher seulement certaines bordures
	 *
	 * @param selectedHoveredShowedBorders true pour afficher sinon false, tableau de taille [4]
	 * @throws IllegalArgumentException si la taille du tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	void setSelectedHoveredShowedBorders(boolean[] selectedHoveredShowedBorders);

	/**
	 * Retourne un tableau de taille 4, true pour afficher sinon false
	 *
	 * @return un tableau de taille 4, true pour afficher sinon false
	 */
	boolean[] getSelectedPressedShowedBorders();

	/**
	 * Si on doit afficher seulement certaines bordures
	 *
	 * @param selectedPressedShowedBorders true pour afficher sinon false, tableau de taille [4]
	 * @throws IllegalArgumentException si la taille du tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	void setSelectedPressedShowedBorders(boolean[] selectedPressedShowedBorders);

	/**
	 * Retourne taille bordure sélection
	 *
	 * @return taille bordure sélection
	 */
	int getSelectedBorderSize();

	/**
	 * Définit taille bordure sélection
	 *
	 * @param selectedBorderSize taille bordure sélection
	 */
	void setSelectedBorderSize(int selectedBorderSize);

	/**
	 * Retourne taille bordure sélection survol
	 *
	 * @return taille bordure sélection survol
	 */
	int getSelectedHoveredBorderSize();

	/**
	 * Définit taille bordure sélection survol
	 *
	 * @param selectedHoveredBorderSize taille bordure sélection survol
	 */
	void setSelectedHoveredBorderSize(int selectedHoveredBorderSize);

	/**
	 * Retourne taille bordure sélection appuie
	 *
	 * @return taille bordure sélection survol
	 */
	int getSelectedPressedBorderSize();

	/**
	 * Définit taille bordure sélection appuie
	 *
	 * @param selectedPressedBorderSize taille bordure sélection survol
	 */
	void setSelectedPressedBorderSize(int selectedPressedBorderSize);

	/**
	 * Définit taille toutes bordures sélection
	 *
	 * @param selectedBorderSize        bordures sélection
	 * @param selectedHoveredBorderSize bordures sélection survol
	 * @param selectedPressedBorderSize bordures sélection appuie
	 */
	void setAllSelectedBordersSize(int selectedBorderSize, int selectedHoveredBorderSize,
	                               int selectedPressedBorderSize);

	/**
	 * Si on doit afficher seulement certaines bordures
	 *
	 * @param selectedPressedShowedBorders true pour afficher sinon false, tableau de taille [4]
	 * @param selectedHoveredShowedBorders true pour afficher sinon false, tableau de taille [4]
	 * @param selectedShowedBorders        true pour afficher sinon false, tableau de taille [4]
	 * @throws IllegalArgumentException si la taille du tableau est différente de 4
	 * @see DefaultUIValues#TOP_BORDER
	 * @see DefaultUIValues#RIGHT_BORDER
	 * @see DefaultUIValues#LEFT_BORDER
	 * @see DefaultUIValues#BOTTOM_BORDER
	 */
	void setAllSelectedShowedBorders(boolean[] selectedShowedBorders,
	                                 boolean[] selectedHoveredShowedBorders,
	                                 boolean[] selectedPressedShowedBorders);

	/**
	 * Définit si sélectionné
	 *
	 * @param isSelected true si sélectionné
	 */
	void setIsSelected(boolean isSelected);

	/**
	 * Retourne si sélectionné
	 *
	 * @return true si sélectionné
	 */
	boolean isSelected();

	Color getSelectedBackground();

	void setSelectedBackground(Color selectedBackground);

	Color getSelectedForeground();

	void setSelectedForeground(Color selectedForeground);

	Color getSelectedHoveredBackground();

	void setSelectedHoveredBackground(Color selectedHoveredBackground);

	Color getSelectedHoveredForeground();

	void setSelectedHoveredForeground(Color selectedHoveredForeground);

	Color getSelectedPressedBackground();

	void setSelectedPressedBackground(Color selectedPressedBackground);

	Color getSelectedPressedForeground();

	void setSelectedPressedForeground(Color selectedPressedForeground);

	Color getSelectedBorder();

	void setSelectedBorder(Color selectedBorder);

	Color getSelectedHoveredBorder();

	void setSelectedHoveredBorder(Color selectedHoveredBorder);

	Color getSelectedPressedBorder();

	void setSelectedPressedBorder(Color selectedPressedBorder);

	void setAllSelectedBackgrounds(Color selectedBackground, Color selectedHoveredBackground,
	                               Color selectedPressedBackground);

	void setAllSelectedForegrounds(Color selectedForeground, Color selectedHoveredForeground,
	                               Color selectedPressedForeground);

	void setAllSelectedBorders(Color selectedBorder, Color selectedHoveredBorder, Color selectedPressedBorder);
}
