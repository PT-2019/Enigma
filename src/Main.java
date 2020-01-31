import api.libgdx.utils.LoadGameLibgdxApplication;
import common.DesktopLauncher;
import common.language.GameLanguage;
import game.EnigmaGame;

import javax.swing.SwingUtilities;

/**
 * Lance l'application
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1
 * @since 4.0
 */
public class Main {

	/**
	 * Lance l'application
	 *
	 * @param ignore ignore
	 */
	public static void main(String[] ignore) {
		//init de la langue
		GameLanguage.init();

		//définit le jeu
		LoadGameLibgdxApplication.setGame(EnigmaGame.getInstance());

		//lancement dans le thread des événements
		SwingUtilities.invokeLater(DesktopLauncher.getInstance());
	}
}
