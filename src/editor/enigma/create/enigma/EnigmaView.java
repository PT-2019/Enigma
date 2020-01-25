package editor.enigma.create.enigma;

import com.badlogic.gdx.Gdx;
import editor.enigma.Enigma;
import editor.view.cases.CasePopUp;
import editor.view.cases.listeners.EntityChoseListener;
import game.entity.map.MapTestScreenCell;
import starter.EditorLauncher;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

/**
 * Element principal dans la création de l'enigme, contient toute les informations nécessaire
 *
 * @see CasePopUp
 */
public class EnigmaView extends JDialog {

	public static final int WIDTH = 700, HEIGHT = 300;

	/**
	 * Enigme que l'utilisateur va créer
	 */
	private Enigma enigma;

	private CardLayout layout;

	/**
	 * Composant principal de la fenêtre
	 */
	private JPanel panel;

	private CasePopUp popUp;

	private MapTestScreenCell cell;

	public EnigmaView(CasePopUp popUp, MapTestScreenCell cell, EntityChoseListener observer) {
		super(EditorLauncher.getInstance().getWindow(), "Créer une enigme", false);
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(Gdx.graphics.getWidth() / 2 - WIDTH/2, Gdx.graphics.getHeight() / 2 - HEIGHT/2);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.layout = new CardLayout();
		panel = new JPanel();
		panel.setLayout(this.layout);
		this.add(panel);
		this.popUp = popUp;

		ConditionPanel condition = new ConditionPanel(this);
		observer.addObserveur(condition);
		OperationPanel operation = new OperationPanel(this);
		observer.addObserveur(operation);
		CluePanel clue = new CluePanel(this);
		panel.add(operation, "operation");
		panel.add(new EnigmaMenu(this), "menu");
		panel.add(clue, "clue");
		panel.add(condition, "condition");
		enigma = new Enigma();
		this.cell = cell;
	}

	public CardLayout getCardLayout() {
		return this.layout;
	}

	public JPanel getPanel() {
		return this.panel;
	}

	public Enigma getEnigma() {
		return enigma;
	}

	public CasePopUp getPopUp() {
		return popUp;
	}

	public MapTestScreenCell getCell() {
		return cell;
	}
}
