package starter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AppClosingManager extends WindowAdapter {

	@Override
	public void windowClosed(WindowEvent windowEvent) {
		DesktopLauncher.closeRunningApp();
	}

}
