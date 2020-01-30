package api.hud.manager;

import api.hud.CustomButton;

/**
 * Interface d'interactions avec des éléments qui
 * contiennent des boutons
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
interface MultipleButtonManager {

	/**
	 * Ajoute un bouton
	 *
	 * @param b un bouton
	 */
	void add(CustomButton b);

	/**
	 * Retourne les boutons sélectionnés
	 *
	 * @return tableau de boutons sélectionnés
	 */
	CustomButton[] getSelected();

	/**
	 * Définit un bouton comme étant sélectionné
	 *
	 * @param b un bouton
	 */
	void setSelected(CustomButton b);
}