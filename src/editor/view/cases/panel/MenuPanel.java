package editor.view.cases.panel;

import editor.enigma.create.enigma.EnigmaView;
import editor.enigma.create.listeners.ListenerMenu;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe représente un Menu lors de la création d'enigme
 */
public class MenuPanel extends JPanel {

	public MenuPanel(String title, String helpText, EnigmaView parent) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		JButton prev = new JButton("Retour");
		JTextArea titleView = new JTextArea(title);
		titleView.setEditable(false);
		JLabel help = new JLabel(new ImageIcon("assets/hud/help.png"));
		help.addMouseListener(new ListenerMenu(helpText));
		prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				CardLayout layout = parent.getCardLayout();
				layout.show(parent.getPanel(), "menu");
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		this.add(prev, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		this.add(titleView, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		this.add(help, gbc);
	}
}
