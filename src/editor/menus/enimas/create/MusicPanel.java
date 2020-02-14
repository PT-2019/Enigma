package editor.menus.enimas.create;


import editor.menus.enimas.create.listeners.MusicListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.GridLayout;

/**
 * Cette classe est a utiliser uniquement pour le choix de musique
 */
public class MusicPanel extends JPanel {

	private JLabel link;

	private OperationPanel parent;

	public MusicPanel(String text, JRadioButton button, OperationPanel parent, Operations operation) {
		this.link = new JLabel(text);

		this.link.addMouseListener(new MusicListener(operation));
		this.setLayout(new GridLayout(1, 2));
		this.add(button);
		this.parent = parent;
	}

	public void dispLink() {
		this.add(link);
		this.revalidate();
	}

	public void remove() {
		this.remove(link);
		this.revalidate();
	}

	public OperationPanel getPanelOperation() {
		return parent;
	}
}
