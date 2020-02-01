package editor.menus.enimas.view;

import api.utils.Observer;
import com.badlogic.gdx.Gdx;
import common.enigmas.Enigma;
import common.map.MapTestScreenCell;
import editor.EditorLauncher;
import editor.popup.cases.CasePopUp;
import editor.popup.cases.listeners.EntityChoseListener;
import game.dnd.DragAndDropBuilder;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Element principal dans la création de l'enigme, contient toute les informations nécessaire
 *
 * @see CasePopUp
 */
public class EnigmaView extends JDialog {

	public static final int WIDTH = 700, HEIGHT = 300;

	private static Observer available = null;

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
		this.setLocation(Gdx.graphics.getWidth() / 2 - WIDTH / 2, Gdx.graphics.getHeight() / 2 - HEIGHT / 2);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new Close());
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
		panel.add(new EnigmaMenu(this), "menu");
		panel.add(clue, "clue");
		panel.add(operation, "operation");
		panel.add(condition, "condition");
		enigma = new Enigma();
		this.cell = cell;
	}

	public static Observer getAvailable() {
		return available;
	}

	public static void setAvailable(Observer available) {
		EnigmaView.available = available;
	}

	public static boolean isAvailable(Observer observer) {
		if (available == null) return false;
		return available.equals(observer);
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

	/**
	 * Reset l'éditor dans son état avant ouverture
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 5.0 26/01/2020
	 * @since 5.0 26/01/2020
	 */
	private static final class Close extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			DragAndDropBuilder.setForPopup(null);
		}
	}
}
