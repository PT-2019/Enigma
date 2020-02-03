package editor.menus.enimas.listeners;

import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ListenerMenu implements MouseListener {

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

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}