package editor.menus;

import api.ui.base.ResetComponent;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import data.config.EnigmaUIValues;
import editor.menus.enimas.create.listeners.ListenerMenu;
import editor.popup.listeners.CaseListener;

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
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 4.0
 */
public class MenuPopUp extends EnigmaPanel {

	private static final String BACK = "Retour";
	private static final String MENU = "menu";
	private static final String HELP_ICON_PATH = "assets/hud/help.png";
	private final EnigmaButton prev;
	private final BackToMenu backToMenu;
	private final EnigmaLabel titleView;

	/**
	 * Popup menu
	 *
	 * @param title    title
	 * @param helpText help
	 * @param parent   parent
	 * @param reset    reset
	 * @param showBack afficher showBack
	 */
	MenuPopUp(String title, String helpText, AbstractPopUpView parent, ResetComponent reset, boolean showBack) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		prev = new EnigmaButton(BACK);
		titleView = new EnigmaLabel(title);
		titleView.setVerticalAlignment(JLabel.CENTER);
		//titleView.setEditable(false);
		JLabel help = new JLabel(new ImageIcon(HELP_ICON_PATH));
		help.addMouseListener(new ListenerMenu(helpText));
		if (showBack) {
			backToMenu = new BackToMenu(reset, parent);
			prev.addActionListener(backToMenu);
		} else {
			backToMenu = null;
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

	public EnigmaButton getPrev() {
		return prev;
	}

	public BackToMenu getBackToMenu() {
		return backToMenu;
	}

	public void setTitle(String title) {
		this.titleView.setText(title);
	}

	/**
	 * Retour au menu
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 6.0 02/02/2020
	 * @since 6.0 02/02/2020
	 */
	private static final class BackToMenu implements ActionListener {

		private final ResetComponent reset;
		private final AbstractPopUpView parent;

		BackToMenu(ResetComponent reset, AbstractPopUpView parent) {
			this.reset = reset;
			this.parent = parent;
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			this.reset.clean();
			CardLayout layout = this.parent.getCardLayout();
			CaseListener.setAvailable(null);
			layout.show(this.parent.getPanel(), MENU);
		}
	}
}
