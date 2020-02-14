package editor.bar.listeners;

import api.utils.Utility;
import com.badlogic.gdx.Gdx;
import common.data.MapData;
import common.hud.EnigmaLabel;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaTextField;
import common.hud.EnigmaWindow;
import common.save.DataSave;
import common.save.EmptyMapGenerator;
import data.EnigmaScreens;
import data.NeedToBeTranslated;
import data.config.Config;
import data.config.UserConfiguration;
import game.EnigmaGame;

import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Observateur de la création d'une map
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 31/12/2019
 * @since 4.0 31/12/2019
 */
public class CreateListener extends MenuListener {

	private static final int CREATE_POS = 0, CANCEL_POS = 1;
	private static final String CREATE = NeedToBeTranslated.CREATE, CANCEL = NeedToBeTranslated.CANCEL;
	//TODO: erreur lors de la création
	//private static final Color ERROR = Color.YELLOW, BASE = EnigmaUIValues.ENIGMA_LABEL_FOREGROUND;

	/**
	 * title
	 */
	private static final String TITLE = NeedToBeTranslated.CREATE_NEW_MAP;

	private static final String NAME_ALREADY_EXIST = NeedToBeTranslated.NAME_ALREADY_EXIST;
	private static final String CREATE_ERROR = NeedToBeTranslated.CREATE_ERROR;

	private static final String INVALID = "invalide";

	private static final String NAME = NeedToBeTranslated.INPUT_MAP_NAME + " :";
	private static final String WIDTH = NeedToBeTranslated.WIDTH + " :";
	private static final String HEIGHT = NeedToBeTranslated.HEIGHT + " :";
	private static final String EMPTY = NeedToBeTranslated.EMPTY;

	/**
	 * fields
	 */
	private final EnigmaTextField nameF, widthF, heightF;

	/**
	 * popup content
	 */
	private final Object content;

	/**
	 * Nom des champs
	 */
	private final EnigmaLabel nom, width, height;

	/**
	 * Observateur de la création d'une map
	 *
	 * @param window fenêtre
	 * @param parent parent
	 */
	public CreateListener(EnigmaWindow window, JComponent parent) {
		super(window, parent);

		//fields
		this.nom = new EnigmaLabel(NAME);
		this.width = new EnigmaLabel(WIDTH);
		this.height = new EnigmaLabel(HEIGHT);

		//input
		this.nameF = new EnigmaTextField();
		this.widthF = new EnigmaTextField("50");
		this.heightF = new EnigmaTextField("50");

		//content
		this.content = new Object[]{
				this.nom, this.nameF,
				this.width, this.widthF,
				this.height, this.heightF,
		};
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] choices = new String[2];
		choices[CREATE_POS] = CREATE;
		choices[CANCEL_POS] = CANCEL;
		boolean error = false;

		do {
			int choice = EnigmaOptionPane.showOptionDialog(this.window, this.content, TITLE, choices);
			//s'il ne veut pas/plus créer, on quitte
			if (choice != CREATE_POS) break;

			//récupération de la saisie
			String mapName = Utility.normalize(this.nameF.getText());
			int col = -1, row = -1;
			//test width
			try {
				col = Integer.parseInt(this.widthF.getText());

				if (col > Config.MAP_MAX_WIDTH) {
					error = true;
					this.width.setText(NeedToBeTranslated.WIDTH + " (" + "max " + Config.MAP_MAX_WIDTH + ") :");
				}

				if (col <= 0) throw new NumberFormatException();

				//reset
				this.width.setText(WIDTH);
			} catch (NumberFormatException ex) {
				error = true;
				this.width.setText(NeedToBeTranslated.WIDTH + " (" + INVALID + ") :");
			}

			//test height
			try {
				row = Integer.parseInt(this.heightF.getText());

				if (row > Config.MAP_MAX_HEIGHT) {
					error = true;
					this.height.setText(NeedToBeTranslated.HEIGHT + " (" + "max " + Config.MAP_MAX_HEIGHT + ") :");
				}

				if (row <= 0) throw new NumberFormatException();

				//reset
				this.height.setText(HEIGHT);
			} catch (NumberFormatException ex) {
				error = true;
				this.height.setText(NeedToBeTranslated.HEIGHT + " (" + INVALID + ") :");
			}

			//test map
			boolean mapNameError = false;
			if (mapName.isEmpty() || mapName.isBlank()) {
				error = true;
				mapNameError = true;
				this.nom.setText(NeedToBeTranslated.INPUT_NAME + " (" + EMPTY + ") :");
			}
			if (!mapNameError) {
				//correction
				mapName = Utility.escape(mapName);
				this.nameF.setText(mapName);
			}

			//nom déjà pris
			if (!mapNameError) {
				for (String s : Utility.getAllMapName()) {
					if (s.equals(mapName)) {
						//this.nom.setText(NeedToBeTranslated.INPUT_NAME + " (" + ALREADY_TAKEN + ") :");
						EnigmaOptionPane.showAlert(this.window, NAME_ALREADY_EXIST);
						error = true;
						mapNameError = true;
						break;
					}
				}
			}

			//reset
			if (!mapNameError) {
				this.nom.setText(NAME);
			}

			//aucune erreur, création
			if (!error) {
				//retire l'extension dans mapName
				if (mapName.contains(Config.MAP_EXTENSION)) mapName = mapName.replaceAll(Config.MAP_EXTENSION, "");
				String path = Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION;

				MapData data = new MapData(UserConfiguration.getInstance().getData().getName(),mapName);
				try {
					DataSave.writeMapData(data);
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				EmptyMapGenerator.generate(path, col, row);

				if (EnigmaGame.getCurrentScreen().setMap(path))
					Gdx.app.postRunnable(() -> EnigmaGame.reload(EnigmaScreens.TEST.name()));

				//préviens map chargée aux autres
				MapLoaded.getInstance().setMapLoaded(true);
			} else {
				EnigmaGame.getCurrentScreen().showToast(CREATE_ERROR);
			}
		} while (error);
	}
}
