package editor.enigma.create.enigma;

import editor.hud.EnigmaButton;
import editor.hud.EnigmaLabel;
import editor.hud.EnigmaPanel;
import editor.hud.EnigmaTextArea;
import editor.hud.EnigmaUIValues;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

/**
 * Menu des énigmes
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class EnigmaMenu extends EnigmaViewPanel {

	private static final String TITLE = "Titre : ";
	private static final String DESC = "Description : ";
	public static final String SAVE = "Sauvegarder Enigme";
	public static final String ADD_CLUE = "Ajouter un indice";
	public static final String ADD_STEP = "Ajouter une condition";
	public static final String ADD_OP = "Ajouter une conséquence";
	private static final int INSET_VALUE = 10, COL = 2, ROW = 1;
	private static final int CLUE_POS = 0, STEP_POS = 1, OP_POS = 2, SAVE_POS = 3;

	public EnigmaMenu(EnigmaView parent) {
		super(parent);

		//title
		EnigmaLabel titl = new EnigmaLabel(TITLE);
		JTextField title = new JTextField();
		title.setFont(EnigmaUIValues.ENIGMA_FONT);
		//desc
		EnigmaLabel desc = new EnigmaLabel(DESC);
		EnigmaTextArea description = new EnigmaTextArea();
		description.setRows(3);
		description.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
		description.getComponentUI().setAllForegrounds(Color.BLACK,Color.BLACK,Color.BLACK);
		//buttons
		EnigmaButton submit = new EnigmaButton(SAVE);
		EnigmaButton clue = new EnigmaButton(ADD_CLUE);
		EnigmaButton condition = new EnigmaButton(ADD_STEP);
		EnigmaButton operation = new EnigmaButton(ADD_OP);

		//add
		this.setLayout(new GridLayout(ROW,COL));

		EnigmaPanel left = new EnigmaPanel(new GridLayout(2,1));
		EnigmaPanel titleP = new EnigmaPanel(new GridBagLayout());
		EnigmaPanel descP = new EnigmaPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(INSET_VALUE,INSET_VALUE,INSET_VALUE,INSET_VALUE);
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		titleP.add(titl, gbc);
		gbc.insets = new Insets(INSET_VALUE,INSET_VALUE,INSET_VALUE,INSET_VALUE);
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		titleP.add(title, gbc);
		gbc.insets = new Insets(INSET_VALUE,INSET_VALUE,INSET_VALUE,INSET_VALUE);
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		descP.add(desc, gbc);
		gbc.insets = new Insets(INSET_VALUE,INSET_VALUE,INSET_VALUE,INSET_VALUE);
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		descP.add(description.setScrollBar(), gbc);
		left.add(titleP);
		left.add(descP);
		this.add(left);

		EnigmaPanel right = new EnigmaPanel(new GridBagLayout());
		GridBagConstraints gbcOptions = new GridBagConstraints();
		this.setOptions(gbcOptions, CLUE_POS);
		right.add(clue, gbcOptions);
		this.setOptions(gbcOptions, STEP_POS);
		right.add(condition, gbcOptions);
		this.setOptions(gbcOptions, OP_POS);
		right.add(operation, gbcOptions);
		this.setOptions(gbcOptions, SAVE_POS);
		right.add(submit, gbcOptions);
		this.add(right);

		//listeners

		NavigationEnigmaListener buttonListener = new NavigationEnigmaListener(title, description, parent);
		clue.addActionListener(buttonListener);
		condition.addActionListener(buttonListener);
		operation.addActionListener(buttonListener);
		submit.addActionListener(buttonListener);
	}

	/**
	 * Ajuste gbc, y=0, wx=1 wy=1, BOTH
	 * @param gbc grid bag constraints
	 * @param i row
	 */
	private void setOptions(GridBagConstraints gbc, int i){
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.insets = new Insets(INSET_VALUE,INSET_VALUE,INSET_VALUE,INSET_VALUE);
	}
}
