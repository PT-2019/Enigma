package editor.menus.enimas.create;

import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.hud.EnigmaTextArea;
import data.NeedToBeTranslated;
import data.config.EnigmaUIValues;
import editor.menus.AbstractPopUpView;
import editor.menus.AbstractSubPopUpView;
import editor.menus.enimas.create.listeners.ClueListener;

import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Interface graphique permettant d'ajouter des indices
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 02/02/2020
 * @since 6.0 02/02/2020
 */
public class CluePanel extends AbstractSubPopUpView {

	public static final String TITLE = NeedToBeTranslated.ADD_CLUE;
	private static final String TIME = NeedToBeTranslated.TIME_CLUE;
	private static final String CLUE = NeedToBeTranslated.INPUT_CLUE;
	private static final String SUBMIT = NeedToBeTranslated.SUBMIT;
	private final EnigmaLabel clue, time;

	CluePanel(AbstractPopUpView parent, ManageEnigmasAddView addView) {
		super("", parent, false);

		//title
		clue = new EnigmaLabel(CLUE);
		clue.setFont(EnigmaUIValues.ENIGMA_FONT);
		clue.setVerticalAlignment(EnigmaLabel.CENTER);
		clue.setHorizontalAlignment(EnigmaLabel.LEFT);
		clue.setBorder(new EmptyBorder(0, 0, 10, 0));//padding
		EnigmaTextArea clueField = new EnigmaTextArea();
		clueField.setRows(2);
		clueField.getComponentUI().setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		clueField.getComponentUI().setAllForegrounds(Color.BLACK, Color.BLACK, Color.BLACK);

		//desc
		time = new EnigmaLabel(TIME);
		time.setFont(EnigmaUIValues.ENIGMA_FONT);
		time.setVerticalAlignment(EnigmaLabel.CENTER);
		time.setHorizontalAlignment(EnigmaLabel.LEFT);
		time.setBorder(new EmptyBorder(5, 0, 10, 0));//padding
		//sélection du nombre de minutes
		JSpinner timeField = new JSpinner();
		timeField.setValue(1);

		EnigmaPanel panel = new EnigmaPanel(new GridBagLayout());
		EnigmaPanel panelT = new EnigmaPanel(new BorderLayout());
		EnigmaPanel titleP = new EnigmaPanel(new BorderLayout(10, 0));
		EnigmaPanel descP = new EnigmaPanel(new BorderLayout(10, 0));
		titleP.add(clue, BorderLayout.NORTH);
		titleP.add(clueField.setScrollBar(), BorderLayout.CENTER);
		descP.add(time, BorderLayout.NORTH);
		descP.add(timeField, BorderLayout.SOUTH);
		panelT.add(titleP, BorderLayout.CENTER);
		panelT.add(descP, BorderLayout.SOUTH);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(panelT, gbc);

		EnigmaPanel p2 = new EnigmaPanel();
		p2.setLayout(new GridBagLayout());

		EnigmaButton submit = new EnigmaButton(SUBMIT);
		submit.addActionListener(new ClueListener(addView, timeField, clueField, this));

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 0, 0, 0);
		p2.add(submit, gbc);

		this.content.setLayout(new BorderLayout());
		this.content.add(panel, BorderLayout.CENTER);
		this.content.add(p2, BorderLayout.SOUTH);
		this.content.revalidate();
	}

	/**
	 * Indice invalide
	 * @param invalid true si invalide
	 * @param info message si invalide
	 */
	public void setClueInvalid(boolean invalid, String info) {
		if (invalid) {
			this.clue.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
			this.clue.setText(CLUE + info);
		} else {
			this.clue.getComponentUI().setAllForegrounds(EnigmaUIValues.ENIGMA_LABEL_FOREGROUND,
					EnigmaUIValues.ENIGMA_LABEL_FOREGROUND, EnigmaUIValues.ENIGMA_LABEL_FOREGROUND);
			this.clue.setText(CLUE);
		}
		this.clue.invalidate();
		this.clue.revalidate();
	}

	/**
	 * Valeur délais invalide
	 * @param invalid true si invalide
	 * @param info message si invalide
	 */
	public void setTimeInvalid(boolean invalid, String info) {
		if (invalid) {
			this.time.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
			this.time.setText(TIME + info);
		} else {
			this.time.getComponentUI().setAllForegrounds(EnigmaUIValues.ENIGMA_LABEL_FOREGROUND,
					EnigmaUIValues.ENIGMA_LABEL_FOREGROUND, EnigmaUIValues.ENIGMA_LABEL_FOREGROUND);
			this.time.setText(TIME);
		}
		this.time.invalidate();
		this.time.revalidate();
	}

	@Override
	public void clean() {
	}

	@Override
	public void initComponent() {
	}
}