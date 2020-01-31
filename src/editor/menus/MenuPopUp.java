package editor.menus;

import api.hud.base.ResetComponent;
import data.config.EnigmaUIValues;
import editor.menus.enimas.listeners.ListenerMenu;
import editor.menus.enimas.view.EnigmaView;
import general.hud.EnigmaButton;
import general.hud.EnigmaLabel;
import general.hud.EnigmaPanel;

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
public class MenuPopUp extends EnigmaPanel {

	public MenuPopUp(String title, String helpText, AbstractPopUpView parent, ResetComponent reset, boolean showBack) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		EnigmaButton prev = new EnigmaButton("Retour");
		EnigmaLabel titleView = new EnigmaLabel(title);
		titleView.setVerticalAlignment(JLabel.CENTER);
		//titleView.setEditable(false);
		JLabel help = new JLabel(new ImageIcon("assets/hud/help.png"));
		help.addMouseListener(new ListenerMenu(helpText));
		if (showBack) {
			prev.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					reset.clean();
					CardLayout layout = parent.getCardLayout();
					EnigmaView.setAvailable(null);
					layout.show(parent.getPanel(), "menu");
				}
			});
		} else {
			prev.setText("");
			prev.getComponentUI().setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN,
					EnigmaUIValues.ALL_BORDER_HIDDEN, EnigmaUIValues.ALL_BORDER_HIDDEN);
			prev.setEnabled(false);
		}

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(5, 0, 5, 0);
		this.add(prev, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(10, 0, 10, 0);
		this.add(titleView, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(5, 0, 5, 0);
		this.add(help, gbc);
	}
}
