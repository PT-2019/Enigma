package general.hud;

import api.hud.CustomAlert;
import api.hud.CustomMenuBar;
import api.hud.ui.CustomMenuBarUI;
import datas.config.Config;

import javax.swing.ImageIcon;

/**
 * Un popup de l'application enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1
 * @since 3.0
 */
public class EnigmaAlert extends CustomAlert {

	/**
	 * Crée un popup
	 */
	public EnigmaAlert() {
		super();
	}

	/**
	 * Crée un popup
	 *
	 * @param width  largeur
	 * @param height hauteur
	 */
	public EnigmaAlert(int width, int height) {
		super(width, height);
	}

	@Override
	protected void init() {
		this.setIconImage(new ImageIcon(Config.LOGO).getImage());

		//remplacement des panels
		this.windowContent = new EnigmaPanel();
		this.content = new EnigmaPanel();

		createMenuBar(new CustomMenuBar(), new CustomMenuBarUI());
		initContent();
	}
}
