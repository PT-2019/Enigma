package editor.menus.enimas.create.listeners;

import common.enigmas.Enigma;
import common.entities.GameObject;
import common.entities.types.EnigmaContainer;
import common.utils.Logger;
import editor.menus.enimas.create.ManageEnigmaAddMenu;
import editor.menus.enimas.create.ManageEnigmasAddView;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 * Validation de la création de l'énigme
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 02/02/2020
 * @since 6.0 02/02/2020
 */
public class SubmitEnigmaListener implements ActionListener {

	private final ManageEnigmasAddView view;
	private final ManageEnigmaAddMenu menu;
	private JTextField title;
	private JTextArea description;

	/**
	 * Validation de la création de l'énigme
	 * @param title titre
	 * @param description description
	 * @param view addView
	 * @param menu addMenu
	 */
	public SubmitEnigmaListener(JTextField title, JTextArea description, ManageEnigmasAddView view,
	                                ManageEnigmaAddMenu menu) {
		this.title = title;
		this.description = description;
		this.view = view;
		this.menu = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String title = this.title.getText();
		if (title.isEmpty()) {
			this.menu.setTitleInvalid(true, "vide");
			return;
		}
		EnigmaContainer entity = view.getEntity();
		Enigma eng = view.getEnigma();
		Iterator<Enigma> enigmas = entity.getAllEnigmas();
		while (enigmas.hasNext()) {
			Enigma enigma = enigmas.next();
			if (enigma.getTitle().equals(title)) {
				this.menu.setTitleInvalid(true, "déjà pris");
				return;
			}
		}

		this.menu.setTitleInvalid(false,"");

		eng.setDescription(this.description.getText());
		eng.setTitle(title);
		eng.setID(((GameObject)entity).getID());
		entity.addEnigma(eng);
		Logger.printDebug("AddEnigma", eng.toLongString());

		this.title.setText("");
		this.description.setText("");

		//ferme le menu de création
		this.view.close();
	}
}
