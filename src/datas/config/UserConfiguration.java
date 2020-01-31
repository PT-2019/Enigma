package datas.config;

import general.entities.players.Player;

/**
 * Défini les configurations et informations de l'utilisateur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class UserConfiguration {

	/**
	 * Instance
	 */
	private final static UserConfiguration instance = new UserConfiguration();
	/**
	 * Utilisateur
	 */
	private Player user;

	private UserConfiguration() {
		this.user = new Player("nom pour l'instant");
	}

	/**
	 * Obtenir l'instance
	 *
	 * @return L'instance
	 */
	public static UserConfiguration getInstance() {
		return instance;
	}

	/**
	 * Obtenir l'utilisateur
	 *
	 * @return Un objet Player de l'utilisateur
	 */
	public Player getUser() {
		return this.user;
	}
}
