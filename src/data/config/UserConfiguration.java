package data.config;

import api.utils.Utility;
import common.data.UserData;
import common.entities.players.Player;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.save.UserSave;
import common.utils.Logger;

import java.awt.*;
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
	private final static String LOAD_ERROR = "Vous semblez être nouveau! Choisissez un nom d'utilisateur avant de commencer :";

	/**
	 * Utilisateur
	 * @deprecated
	 */
	private Player user;

	private UserConfiguration() {
		try {
			this.data = UserSave.read();
		} catch (IOException | IllegalArgumentException e) {
			String name = "";
			while(name.equals("") || name.equals(EnigmaOptionPane.CANCEL)) {
				name = EnigmaOptionPane.showInputDialog(new EnigmaWindow(),new Dimension(800,350),LOAD_ERROR);
			}
			name = Utility.normalize(name);
			this.data = new UserData(name);
			try {
				UserSave.write(this.data);
			} catch (IOException ex) {
				this.data = null;
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
