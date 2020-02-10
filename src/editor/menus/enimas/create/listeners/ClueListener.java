package editor.menus.enimas.create.listeners;

import common.enigmas.Advice;
import common.hud.EnigmaTextArea;
import common.save.enigmas.EnigmaAttributes;
import common.utils.Logger;
import editor.menus.enimas.create.CluePanel;
import editor.menus.enimas.create.ManageEnigmasAddView;

import javax.swing.JSpinner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Listener des indices
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 30/01/2020
 * @since 5.0 30/01/2020
 */
public class ClueListener implements ActionListener {

	private final CluePanel cluePanel;
	private ManageEnigmasAddView parent;
	private EnigmaTextArea clueField;
	private JSpinner timeField;

	public ClueListener(ManageEnigmasAddView parent, JSpinner timeField, EnigmaTextArea clueField, CluePanel cluePanel) {
		this.parent = parent;
		this.clueField = clueField;
		this.timeField = timeField;
		this.cluePanel = cluePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean error = false;
		String clue = this.clueField.getText();
		int time = -1;

		if (clue == null || clue.length() == 0) {
			error = true;
			this.cluePanel.setClueInvalid(true, " (valeur invalide)");
		} else {
			this.cluePanel.setClueInvalid(false, "");
		}

		try {
			Object o = this.timeField.getValue();
			if (o instanceof Integer) {
				time = (Integer) o;
			} else if (o instanceof String) {
				time = Integer.parseInt((String) o);
			} else {
				throw new IllegalArgumentException();
			}
			if (time <= 0) throw new NumberFormatException();
			this.cluePanel.setTimeInvalid(false, "");
		} catch (NumberFormatException ex) {
			this.cluePanel.setTimeInvalid(true, " (nombre incorrect)");
			error = true;
		} catch (IllegalArgumentException ex) {
			System.err.println("Error iconnue dans la valeur du spinner de la saisie d'indices");
			return;
		}

		this.timeField.revalidate();
		this.clueField.revalidate();

		if (!error) {
			//tout est bon
			//placement des attributs de l'indice
			HashMap<String, Object> attribute = new HashMap<>();
			attribute.put(EnigmaAttributes.ADVICE, clueField.getText());
			attribute.put(EnigmaAttributes.DELAY, time + "");
			Advice advice = new Advice(attribute);
			this.parent.getEnigma().addAdvice(advice);

			Logger.printDebug("addClue", advice.toString());

			//reset
			this.timeField.setValue(1);
			this.clueField.setText("");

			//retour au menu
			this.parent.setCard(ManageEnigmasAddView.MENU, ManageEnigmasAddView.TITLE);
		}
	}
}
