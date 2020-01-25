package editor.view.cases.panel;

import api.hud.ResetComponent;
import editor.enigma.create.enigma.EnigmaView;
import editor.enigma.create.listeners.ListenerMenu;
import editor.hud.EnigmaButton;
import editor.hud.EnigmaLabel;
import editor.hud.EnigmaPanel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe représente un Menu lors de la création d'enigme
 */
public class MenuPanel extends EnigmaPanel {

	public MenuPanel(String title, String helpText, EnigmaView parent, ResetComponent reset) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		EnigmaButton prev = new EnigmaButton("Retour");
		EnigmaLabel titleView = new EnigmaLabel(title);
		titleView.setVerticalAlignment(JLabel.CENTER);
		//titleView.setEditable(false);
		JLabel help = new JLabel(new ImageIcon("assets/hud/help.png"));
		help.addMouseListener(new ListenerMenu(helpText));
		prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				reset.clean();
				CardLayout layout = parent.getCardLayout();
				layout.show(parent.getPanel(), "menu");
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(5,0,5,0);
		this.add(prev, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(10,0,10,0);
		this.add(titleView, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(5,0,5,0);
		this.add(help, gbc);
	}
}
