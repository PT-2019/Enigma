package editor.menus.enimas.listeners;

import api.utils.Utility;
import editor.menus.enimas.view.CluePanel;
import editor.menus.enimas.view.EnigmaView;
import general.enigmas.Advice;
import general.hud.EnigmaTextArea;
import general.save.enigmas.EnigmaAttributes;

import javax.swing.JSpinner;
import java.awt.CardLayout;
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
	private EnigmaView parent;
	private EnigmaTextArea clueField;
	private JSpinner timeField;

	public ClueListener(EnigmaView parent, JSpinner timeField, EnigmaTextArea clueField, CluePanel cluePanel) {
		this.parent = parent;
		this.clueField = clueField;
		this.timeField = timeField;
		this.cluePanel = cluePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean error = false;
		String clue = clueField.getText();
		int time = -1;

		if (clue == null || clue.length() == 0) {
			error = true;
			cluePanel.setClueInvalid(true);
		} else {
			cluePanel.setClueInvalid(false);
		}

		try {
			Object o = timeField.getValue();
			if (o instanceof Integer) {
				time = (Integer) o;
			} else if (o instanceof String) {
				time = Integer.parseInt((String) o);
			} else {
				throw new IllegalArgumentException();
			}
			if (time <= 0) throw new NumberFormatException();
			cluePanel.setTimeInvalid(false);
		} catch (NumberFormatException ex) {
			cluePanel.setTimeInvalid(true);
			error = true;
		} catch (IllegalArgumentException ex) {
			System.err.println("Error iconnue dans la valeur du spinner de la saisie d'indices");
			return;
		}

		timeField.revalidate();
		clueField.revalidate();

		if (!error) {
			//tout est bon
			//placement des attributs de l'indice
			HashMap<String, Object> attribute = new HashMap<>();
			attribute.put(EnigmaAttributes.ADVICE, clueField.getText());
			attribute.put(EnigmaAttributes.DELAY, time + "");
			Advice advice = new Advice(attribute);
			parent.getEnigma().addAdvice(advice);

			Utility.printDebug("addClue", advice.toString());

			//reset
			timeField.setValue(1);
			clueField.setText("");

			CardLayout layout = parent.getCardLayout();
			layout.previous(parent.getPanel());
		}
	}
}
