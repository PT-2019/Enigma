package editor.menus.enimas.create;

import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.hud.EnigmaTextArea;
import data.config.EnigmaUIValues;
import editor.menus.AbstractPopUpView;
import editor.menus.AbstractSubPopUpView;
import editor.menus.enimas.create.OperationPanel;
import editor.menus.enimas.create.listeners.SubmitEnigmaListener;
import org.jetbrains.annotations.Nullable;

import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menu d'ajout d'une énigme
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 02/02/2020
 * @since 6.0 02/02/2020
 */
public class ManageEnigmaAddMenu extends AbstractSubPopUpView {

	private static final String TITLE = "Titre : ";
	private static final String DESC = "Description : ";
	private static final int INSET_VALUE = 10, COL = 2, ROW = 1;
	private static final int CLUE_POS = 0, STEP_POS = 1, OP_POS = 2, SAVE_POS = 3;
	private final EnigmaLabel enigmaTitle;

	/**
	 * Menu d'ajout d'une énigme
	 * @param parent parent
	 */
	ManageEnigmaAddMenu(AbstractPopUpView parent, ManageEnigmasAddView addView) {
		super("", parent, false);

		this.removeAll();//suppression du menu, pas besoin
		this.setLayout(new BorderLayout());
		this.add(content, BorderLayout.CENTER);

		//title
		this.enigmaTitle = new EnigmaLabel(TITLE);
		JTextField title = new JTextField();
		title.setFont(EnigmaUIValues.ENIGMA_FONT);
		//desc
		EnigmaLabel desc = new EnigmaLabel(DESC);
		EnigmaTextArea description = new EnigmaTextArea();
		description.setRows(3);
		description.getComponentUI().setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		description.getComponentUI().setAllForegrounds(Color.BLACK, Color.BLACK, Color.BLACK);
		//buttons
		EnigmaButton submit = new EnigmaButton(ManageEnigmasAddView.SAVE);
		EnigmaButton clue = new EnigmaButton(ManageEnigmasAddView.ADD_CLUE);
		EnigmaButton condition = new EnigmaButton(ManageEnigmasAddView.ADD_COND);
		EnigmaButton operation = new EnigmaButton(ManageEnigmasAddView.ADD_OP);

		//add
		this.content.setLayout(new GridLayout(ROW, COL));

		EnigmaPanel left = new EnigmaPanel(new BorderLayout());
		EnigmaPanel titleP = new EnigmaPanel(new GridBagLayout());
		EnigmaPanel descP = new EnigmaPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(INSET_VALUE, INSET_VALUE, INSET_VALUE, INSET_VALUE);
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		titleP.add(this.enigmaTitle, gbc);
		gbc.insets = new Insets(INSET_VALUE, INSET_VALUE, INSET_VALUE, INSET_VALUE);
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		titleP.add(title, gbc);
		gbc.insets = new Insets(INSET_VALUE, INSET_VALUE, INSET_VALUE, INSET_VALUE);
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		descP.add(desc, gbc);
		gbc.insets = new Insets(INSET_VALUE, INSET_VALUE, INSET_VALUE, INSET_VALUE);
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		descP.add(description.setScrollBar(), gbc);
		left.add(titleP, BorderLayout.NORTH);
		left.add(descP, BorderLayout.CENTER);
		this.content.add(left);

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
		this.content.add(right);

		//listeners
		clue.addActionListener(new NextListener(ManageEnigmasAddView.CLUE, CluePanel.TITLE, addView));
		condition.addActionListener(new NextListener(ManageEnigmasAddView.CONDITION, ConditionPanel.TITLE, addView));
		operation.addActionListener(new NextListener(ManageEnigmasAddView.OPERATION, OperationPanel.TITLE, addView));
		submit.addActionListener(new SubmitEnigmaListener(title,description, addView, this));
	}

	/**
	 * Définition du titre
	 * @param invalid true pour invalide
	 * @param vide indication sur l'erreur
	 */
	public void setTitleInvalid(boolean invalid, @Nullable String vide) {
		if (invalid) {
			if(vide == null) vide = "";
			else vide = " (" + vide + ")";
			this.enigmaTitle.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
			this.enigmaTitle.setText(TITLE + vide);
		} else {
			this.enigmaTitle.getComponentUI().setAllForegrounds(EnigmaUIValues.ENIGMA_LABEL_FOREGROUND,
					EnigmaUIValues.ENIGMA_LABEL_FOREGROUND, EnigmaUIValues.ENIGMA_LABEL_FOREGROUND);
			this.enigmaTitle.setText(TITLE);
		}
		this.enigmaTitle.invalidate();
		this.enigmaTitle.revalidate();
	}

	/**
	 * Ajuste gbc, y=0, wx=1 wy=1, BOTH
	 *
	 * @param gbc grid bag constraints
	 * @param i   row
	 */
	private void setOptions(GridBagConstraints gbc, int i) {
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.insets = new Insets(INSET_VALUE, INSET_VALUE, INSET_VALUE, INSET_VALUE);
	}

	/**
	 * Passe a l'écran suivant
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 *
	 * @version 6.0 02/02/2020
	 * @since 6.0 02/02/2020
	 */
	private static final class NextListener implements ActionListener {

		private final String name;
		private final String title;
		private final ManageEnigmasAddView view;

		NextListener(String name, String title, ManageEnigmasAddView view ){
			this.name = name;
			this.title = title;
			this.view = view;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.view.setCard(this.name, this.title);
		}
	}

	@Override
	public void clean() {
	}

	@Override
	public void initComponent() {
	}
}
