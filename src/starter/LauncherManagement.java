package starter;

import api.Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Lance une application par la lanceur du bureau lors de l'appui
 * sur un bouton
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class LauncherManagement implements ActionListener {

	/**
	 * L'application à lancer
	 */
	private Application app;

	/**
	 * Lance une application par la lanceur du bureau lors de l'appui
	 * sur un bouton
	 *
	 * @param app l'application à lancer
	 * @since 3.0
	 */
	public LauncherManagement(Application app) {
		this.app = app;
	}

	/**
	 * Lance une application par la lanceur du bureau lors de l'appui
	 * sur un bouton
	 *
	 * @param actionEvent l'évènement de clic sur le bouton
	 * @since 3.0
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		DesktopLauncher.startApp(this.app);
	}
}
