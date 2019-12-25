package starter;

import api.Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO: comment LauncherManagement and write Readme.md in starter
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class LauncherManagement implements ActionListener {

	private Application app;

	public LauncherManagement(Application app) {
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		DesktopLauncher.startApp(this.app);
	}
}
