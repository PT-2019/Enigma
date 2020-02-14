package game.hmi;

import api.ui.CustomPanel;

/**
 * Permet de gérer des affichages
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public abstract class Content {
	/**
	 * Etat non précisé
	 */
	public final static int NO_PRECISED_STATE = -1;
	/**
	 * Contenu de l'affichage
	 */
	protected CustomPanel content;

	/**
	 * @param content Contenu
	 */
	public Content(CustomPanel content) {
		this.content = content;
	}

	/**
	 * Initialise le contenu
	 * Doit être normalement appelé qu'une fois, dans le constructeur
	 */
	protected abstract void initContent();

	/**
	 * Rafraichi l'affichage
	 *
	 * @param state Etat
	 */
	protected abstract void refresh(int state);

	/**
	 * Obtenir le contenu de l'affichage
	 *
	 * @return Contenu
	 */
	public CustomPanel getContent() {
		return this.content;
	}
}
