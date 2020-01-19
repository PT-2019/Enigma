package api.hud.ui.base;

import java.awt.Cursor;

/**
 * Méthodes pour les composants de l'UI voulant un curseur...
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 27/12/2019
 * @since 4.0 27/12/2019
 */
public interface CustomUICursor {

	/**
	 * Retourne le curseur actuel
	 *
	 * @return le curseur actuel
	 * @see Cursor
	 */
	Cursor getCursor();

	/**
	 * Définit le curseur
	 *
	 * @param cursor le curseur
	 * @see Cursor
	 */
	void setCursor(Cursor cursor);
}
