package editor.enigma.create.enigma;

import api.entity.types.EnigmaContainer;
import editor.enigma.Enigma;
import editor.hud.EnigmaTextArea;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlleur qui gère la navigation entre les différents états de la fenetre de création d'énigmes
 *
 * @see EnigmaView
 */
public class NavigationEnigmaListener implements ActionListener {

	private JTextField title;

	private JTextArea description;
	private final EnigmaView view;

	public NavigationEnigmaListener(JTextField title, JTextArea description, EnigmaView view) {
		this.title = title;
		this.description = description;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String tmp = e.getActionCommand();

		if (tmp.equals(EnigmaMenu.ADD_CLUE)) {
			CardLayout layout = view.getCardLayout();
			layout.next(view.getPanel());
		} else if (tmp.equals(EnigmaMenu.ADD_STEP)) {
			view.setModal(false);
			CardLayout layout = view.getCardLayout();
			layout.next(view.getPanel());
			layout.next(view.getPanel());
		} else if (tmp.equals(EnigmaMenu.SAVE)) {
			EnigmaContainer entity = (EnigmaContainer) view.getCell().getEntity();
			Enigma eng = view.getEnigma();
			eng.setDescription(description.getText());
			eng.setTitle(title.getText());
			entity.addEnigma(eng);
			view.dispose();
			view.getPopUp().setVisible(true);
		} else if(tmp.equals(EnigmaMenu.ADD_OP)) {
			view.setModal(false);
			CardLayout layout = view.getCardLayout();
			layout.next(view.getPanel());
			layout.next(view.getPanel());
			layout.next(view.getPanel());
		}
	}
}
