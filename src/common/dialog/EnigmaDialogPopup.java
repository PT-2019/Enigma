package common.dialog;

import api.libgdx.utils.InputAdapter;
import api.libgdx.utils.LibgdxUtility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import common.enigmas.condition.Answer;
import common.enigmas.reporting.EnigmaReport;
import common.map.GameMap;
import common.utils.Question;
import data.keys.PlayerKeys;

/**
 * Permet d'afficher les dialogues dans le Jeu.
 * Pour l'instant ne peut afficher que 2 choix
 */
public class EnigmaDialogPopup extends Window implements InputAdapter {
	private static final String SKIN_PATH = "assets/files/atlas/inventory.json";

	private static final String ATLAS_PATH = "assets/files/atlas/inventory.atlas";

	private static final String SKIN_PATH2 = "assets/files/atlas/uiskin.json";

	private static final String ATLAS_PATH2 = "assets/files/atlas/uiskin.atlas";

	/**
	 * String qui représente comment son codé les retours à la ligne dans le
	 * fichier de sauvegarde de la map
	 */
	private static String NEW_LINE = "&amp;#10;";

	/**
	 * dialogue que l'on va afficher
	 */
	private Dialog dialog;

	private GameMap map;

	/**
	 * Layout de la fenêtre
	 */
	private Table container;

	private Table choiceContainer;

	/**
	 * Texte du dialogue à afficher
	 */
	private Label text;
	/**
	 * Label qui représente les choix
	 */
	private Label[] choice;
	/**
	 * Image pour représenter la flèche
	 */
	private Image[] img;
	/**
	 * Image utilisé pour les composants
	 */
	private Skin skin;
	/**
	 * Indice de l'image actuellement sélectionné
	 */
	private int current;

	private Label enter;

	/**
	 * Pour savoir si c'est une question
	 */
	private Answer answer;

	private TextField answerF;

	public EnigmaDialogPopup() {
		super("", LibgdxUtility.loadSkin(SKIN_PATH, ATLAS_PATH));

		this.setSize(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 4);
		this.setPosition(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight());
		this.setVisible(false);
	}

	/**
	 * initialise l'interface graphique lorsqu'on fait face à un dialogue simple
	 */
	private void init() {
		container = new Table(this.getSkin());
		text = new Label("", this.getSkin());

		enter = new Label("Appuyer sur Entrée pour continuer", getSkin());
		container.add(text).expand().top().fillX().padTop(25).padLeft(15);
		container.row();

		if (answer != null) {
			answerF = new TextField("", LibgdxUtility.loadSkin(SKIN_PATH2, ATLAS_PATH2));
			container.add(answerF).expandX().padBottom(20);

		} else {
			container.add(enter).expandX().padBottom(20);
		}

		this.add(container).expand().fill();
	}

	/**
	 * Initialise l'interface graphique lorsqu'on fait face à un choix
	 *
	 * @param dialog dialogue
	 */
	private void initChoice(Dialog dialog) {
		String[] solution = dialog.getChoice();
		int nbChoice = solution.length;
		//initialisation des composants
		choiceContainer = new Table(this.getSkin());
		skin = LibgdxUtility.loadSkin(SKIN_PATH, ATLAS_PATH);
		choice = new Label[nbChoice];
		img = new Image[nbChoice];
		img[0] = new Image(skin, "arrow1");
		current = 0;
		text = new Label("", getSkin());
		container = new Table(this.getSkin());

		container.add(text).expand().top().fillX().padTop(25).padLeft(15);
		container.row();
		for (int i = 0; i < nbChoice; i++) {
			choice[i] = new Label(solution[i], getSkin());
		}
		for (int i = 1; i < nbChoice; i++) {
			img[i] = new Image(skin, "rect");
		}

		for (int i = 0; i < nbChoice; i++) {
			choiceContainer.add(img[i]).right();
			choiceContainer.add(choice[i]).expandX().left().padBottom(8);
		}

		container.add(choiceContainer).expand();
		this.add(container).expand().fill();
	}

	/**
	 * Afficher une question
	 *
	 * @param answer affiche une question
	 * @param map    la gameMap
	 */
	public void showAnswer(Answer answer, GameMap map) {
		this.map = map;
		this.clear();
		Question quest = answer.getQuestion();
		String text = quest.getQuestion();
		this.dialog = new Dialog(text);
		this.answer = answer;
		init();
		if (!dialog.isFinish()) {
			String dialogText = this.dialog.getCurrentText();
			//pour avoir des saut de ligne
			dialogText = dialogText.replace(NEW_LINE, "\n");
			this.text.setText(dialogText);
			this.setVisible(true);
		}
	}

	/**
	 * Cette méthode affiche la prochaine parti du dialogue si elle existe sinon elle cache
	 * la fenêtre
	 */
	private void nextPart() {
		if (dialog.isFinish()) {
			//le dialogue est fini
			this.dialog.reset();
			this.setVisible(false);
			//si c'était saisie de réponse
			if (this.answer != null) {
				//on enregistre la réponse
				this.answer.setAnswer(this.answerF.getText());
				//on la test
				EnigmaReport report = this.answer.verify(null);
				EnigmaDialogPopup dialogPopup = this.map.getEnigmaDialog();
				Dialog dialog = new Dialog(report.getReport().getReport());
				dialogPopup.showDialog(dialog, this.map);
				this.answer = null;
			}
		} else {
			String dialogText = dialog.getCurrentText();
			//pour avoir des saut de ligne
			dialogText = dialogText.replace(NEW_LINE, "\n");
			this.text.setText(dialogText);
		}
	}

	/**
	 * Afficher le dialogue au départ
	 *
	 * @param dialog dialogue
	 * @param map    map
	 */
	public void showDialog(Dialog dialog, GameMap map) {
		this.dialog = dialog;
		this.map = map;
		this.answer = null;

		if (dialog.isChoice()) {
			this.clear();
			initChoice(dialog);
		} else {
			this.clear();
			init();
		}

		if (!dialog.isFinish()) {
			String dialogText = dialog.getCurrentText();
			//pour avoir des saut de ligne
			dialogText = dialogText.replace(NEW_LINE, "\n");
			text.setText(dialogText);
			this.setVisible(true);
		}
	}

	@Override
	public boolean keyDown(int i) {
		//si la fenêtre est visible alors on contrôle le dialogue
		if (isVisible()) {
			if (PlayerKeys.PLAYER_USE.isKey(i)) {
				this.nextPart();
			}
			if (dialog.isChoice()) {
				if (PlayerKeys.PLAYER_LEFT.isKey(i)) {
					img[current].setDrawable(skin, "rect");
					current = 0;
					img[current].setDrawable(skin, "arrow1");
				} else if (PlayerKeys.PLAYER_RIGHT.isKey(i)) {
					img[current].setDrawable(skin, "rect");
					current = 1;
					img[current].setDrawable(skin, "arrow1");
				}
			}
		}
		return false;
	}

	@Override
	public boolean keyUp(int i) {
		//Pour pas que la touche qui permette d'afficher le popup ce mette dans le TextField
		if (this.answer != null) {
			this.getStage().setKeyboardFocus(this.answerF);
		}
		return false;
	}
}