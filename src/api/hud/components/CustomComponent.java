package api.hud.components;

import api.utils.annotations.ConvenienceClass;

/**
 * Classe pratique pour les méthode d'un composant customizable.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 27/12/2019
 * @since 4.0 27/12/2019
 */
@ConvenienceClass
interface CustomComponent<T> {

	/**
	 * Retourne l'ui
	 *
	 * @return l'ui
	 */
	T getComponentUI();

	/**
	 * Définit l'ui
	 *
	 * @param ui la nouvelle ui
	 */
	void setComponentUI(T ui);

}
