package editor.menus.enimas.view;

import api.utils.Utility;
import common.enigmas.Enigma;
import common.entities.types.EnigmaContainer;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 * Controlleur qui gère la navigation entre les différents états de la fenetre de création d'énigmes
 *
 * @see EnigmaView
 */
public class NavigationEnigmaListener implements ActionListener {

	private final EnigmaView view;
	private final EnigmaMenu menu;
	private JTextField title;
	private JTextArea description;

	public NavigationEnigmaListener(JTextField title, JTextArea description, EnigmaView view, EnigmaMenu menu) {
		this.title = title;
		this.description = description;
		this.view = view;
		this.menu = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String tmp = e.getActionCommand();

		if (tmp.equals(EnigmaMenu.ADD_CLUE)) {
			CardLayout layout = view.getCardLayout();
			layout.next(view.getPanel());
		} else if (tmp.equals(EnigmaMenu.ADD_OP)) {
			view.setModal(false);
			CardLayout layout = view.getCardLayout();
			layout.next(view.getPanel());
			layout.next(view.getPanel());
		} else if (tmp.equals(EnigmaMenu.SAVE)) {
			String title = this.title.getText();
			if (title.isEmpty()) {
				menu.setTitleInvalid(true, "vide");
				return;
			}
			EnigmaContainer entity = (EnigmaContainer) view.getCell().getEntity();
			Enigma eng = view.getEnigma();
			Iterator<Enigma> enigmas = entity.getAllEnigmas();
			while (enigmas.hasNext()) {
				Enigma enigma = enigmas.next();
				if (enigma.getTitle().equals(title)) {
					menu.setTitleInvalid(true, "déjà pris");
					return;
				}
			}
			eng.setDescription(description.getText());
			eng.setTitle(title);
			entity.addEnigma(eng);
			Utility.printDebug("AddEnigma", eng.toLongString());
			view.dispose();
			view.getPopUp().setVisible(true);
		} else if (tmp.equals(EnigmaMenu.ADD_COND)) {
			view.setModal(false);
			CardLayout layout = view.getCardLayout();
			layout.next(view.getPanel());
			layout.next(view.getPanel());
			layout.next(view.getPanel());
		}
	}
}
