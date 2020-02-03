package data.config;

import common.DesktopLauncher;
import common.data.UserData;
import common.entities.players.Player;
import common.hud.EnigmaOptionPane;
import common.save.UserSave;
import common.utils.Logger;

import java.io.IOException;

/**
 * Défini les configurations et informations de l'utilisateur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 3.0
 */
public class UserConfiguration {

	/**
	 * Instance
	 */
	private final static UserConfiguration instance = new UserConfiguration();

	/**
	 * Données de l'utilisateur
	 */
	private UserData data;

	/**
	 * Messages
	 */
	private final static String LOAD_ERROR = "Impossible de charger vos données. Peur être êtes vous nouveau? Choisissez un nom d'utilisateur avant de commencer :";

	/**
	 * Utilisateur
	 * @deprecated
	 */
	private Player user;

	private UserConfiguration() {
		try {
			System.out.println("fffffffff");
			this.data = UserSave.read();
		} catch (IOException | IllegalArgumentException e) {
			String name = EnigmaOptionPane.showInputDialog(DesktopLauncher.getInstance().getWindow(),LOAD_ERROR);
			this.data = new UserData(name);
			try {
				UserSave.write(this.data);
			} catch (IOException ex) {
				Logger.printError("UserConfiguration.java","Impossible de savegarder les données");
			}
		}
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
	 * Obtenir le nom
	 * @return Le nom
	 */
	public UserData getData(){
		return this.data;
	}

	/**
	 * Obtenir l'utilisateur
	 *
	 * @return Un objet Player de l'utilisateur
	 * @deprecated
	 */
	public Player getUser() {
		return this.user;
	}
}
