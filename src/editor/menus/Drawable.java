package editor.menus;

import java.awt.Container;

/**
 * Un object dessinable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 02/02/2020
 * @since 6.0 02/02/2020
 */
public interface Drawable {

	/**
	 * Container pour dessiner dedans
	 *
	 * @return Container pour dessiner dedans
	 * @since 6.0
	 */
	Container getDrawable();

	/**
	 * Une méthode pour forcer le composant a se mettre à jour
	 * par exemple pour une liste.
	 *
	 * @since 6.0
	 */
	void invalidateDrawable();
}
