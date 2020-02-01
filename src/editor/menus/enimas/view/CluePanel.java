package editor.menus.enimas.view;

import api.ui.base.ResetComponent;
import api.utils.Observer;
import common.entities.GameObject;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.hud.EnigmaTextArea;
import data.config.EnigmaUIValues;
import editor.menus.enimas.listeners.ClueListener;
import editor.popup.cases.panel.MenuPanel;

import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


/**
 * Interface graphique permettant d'ajouter des indices
 */
public class CluePanel extends EnigmaViewPanel implements Observer<GameObject>, ResetComponent {

	private static final String TITLE = "Ajouter un indice";
	private static final String TIME = "Temps (minutes) avant activation de l'indice :";
	private static final String CLUE = "Indice :";
	private final EnigmaLabel clue, time;

	public CluePanel(EnigmaView parent) {
		super(parent);
		/*EnigmaPanel panel = new EnigmaPanel();
		panel.setLayout(new GridLayout(4,1));
		final Color COLOR = new JPanel().getBackground();
		panel.getComponentUI().setAllBackgrounds(COLOR, COLOR, COLOR);

		JLabel clue = new JLabel("Indice :");
		EnigmaTextArea clueField = new EnigmaTextArea();
		clueField.setScrollBar();
		JLabel time = new JLabel("Temps en minute apparition de l'indice :");
		JTextField timeField = new JTextField();

		panel.add(clue);
		panel.add(clueField);
		panel.add(time);
		panel.add(timeField);*/

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

		EnigmaButton submit = new EnigmaButton("Valider");
		submit.addActionListener(new ClueListener(parent, timeField, clueField, this));

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 0, 0, 0);
		p2.add(submit, gbc);

		this.setLayout(new BorderLayout());
		this.add(new MenuPanel(TITLE, "", parent, this), BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		this.add(p2, BorderLayout.SOUTH);
	}

	//TODO: string as 2nd param for details
	public void setClueInvalid(boolean invalid) {
		if (invalid) {
			clue.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
			clue.setText(CLUE + " (valeur invalide)");
		} else {
			clue.getComponentUI().setAllForegrounds(EnigmaUIValues.ENIGMA_LABEL_FOREGROUND,
					EnigmaUIValues.ENIGMA_LABEL_FOREGROUND, EnigmaUIValues.ENIGMA_LABEL_FOREGROUND);
			clue.setText(CLUE);
		}
		clue.invalidate();
		clue.revalidate();
	}

	//TODO: string as 2nd param for details
	public void setTimeInvalid(boolean invalid) {
		if (invalid) {
			time.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
			time.setText(TIME + " (valeur invalide)");
		} else {
			time.getComponentUI().setAllForegrounds(EnigmaUIValues.ENIGMA_LABEL_FOREGROUND,
					EnigmaUIValues.ENIGMA_LABEL_FOREGROUND, EnigmaUIValues.ENIGMA_LABEL_FOREGROUND);
			time.setText(TIME);
		}
		time.invalidate();
		time.revalidate();
	}

	@Override
	public void update(GameObject object) {
	}

	@Override
	public void clean() {

	}

	@Override
	public void initComponent() {

	}
}
