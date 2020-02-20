package editor.menus.enimas.create.listeners;

import api.utils.convenience.ConvenienceMouseAdapter;

import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;

/**
 * Le listener du bouton help.
 * Pas de bouton help, pour l'instant, donc pas de listener.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 3.0
 */
public class ListenerMenu implements ConvenienceMouseAdapter {

	private String help;

	public ListenerMenu(String help) {
		this.help = help;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JOptionPane information = new JOptionPane();
		JOptionPane.showMessageDialog(null, help, "Information",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
