package api.ui.skin.base;

import java.awt.Font;

/**
 * Méthodes pour changer la police
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 28/12/2019
 * @since 4.0 28/12/2019
 */
public interface CustomUIFont {

	/**
	 * Renvoi la police actuelle
	 *
	 * @return la police actuelle
	 */
	Font getFont();

	/**
	 * Définit la police actuelle
	 *
	 * @param font une police
	 */
	void setFont(Font font);

}
