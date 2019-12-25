package starter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * TODO: comment AppClosingManager and write Readme.md in starter
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class AppClosingManager extends WindowAdapter {

	@Override
	public void windowClosed(WindowEvent windowEvent) {
		DesktopLauncher.closeRunningApp();
	}

}
