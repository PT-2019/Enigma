package api.ui.skin.base;

import java.awt.Color;

/**
 * Desc
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 28/12/2019
 * @since 4.0 28/12/2019
 */
public interface CustomUIForeground extends CustomUIFont {

	/**
	 * Couleur de texte normale
	 *
	 * @return Couleur de texte au survol
	 */
	Color getForeground();

	/**
	 * Définit la couleur du texte normale
	 *
	 * @param foreground la couleur du texte normale
	 */
	void setForeground(Color foreground);

}
