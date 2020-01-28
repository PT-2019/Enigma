package editor.enigma.create.listeners;

import javax.swing.JDialog;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Controlleur de EnigmaView
 */
public class EnigmaWindowListener implements WindowListener {

	private JDialog popUp;

	public EnigmaWindowListener(JDialog popUp) {
		this.popUp = popUp;
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		JDialog frame = (JDialog) e.getSource();
		frame.dispose();
		popUp.setVisible(true);
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}
}
