package api.ui.skin.base;

import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * Méthodes pour les composants de l'UI voulant un fond.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 27/12/2019
 * @since 4.0 27/12/2019
 */
public interface CustomUIBackground {

	/**
	 * Retourne la couleur de fond du composant
	 *
	 * @return la couleur de fond
	 * @see Color
	 */
	Color getBackground();

	/**
	 * Donne une couleur de fond au composant
	 *
	 * @param background une couleur
	 * @see Color
	 */
	void setBackground(Color background);

	/**
	 * Retourne l'image de fond du composant
	 *
	 * @return l'image de fond du composant
	 */
	ImageIcon getBackgroundImage();

	/**
	 * Donne une image de fond au composant
	 *
	 * @param backgroundImage une image de fond
	 */
	void setBackgroundImage(ImageIcon backgroundImage);
}
