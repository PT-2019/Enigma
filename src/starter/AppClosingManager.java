package starter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Ferme un sous-application en appelant {@link DesktopLauncher#closeRunningApp()}
 *
 * @author Louka DOZ
 * @version 4.0
 * @since 3.0
 */
public class AppClosingManager extends WindowAdapter {

	@Override
	public void windowClosed(WindowEvent windowEvent) {
		DesktopLauncher.closeRunningApp();
	}

}
