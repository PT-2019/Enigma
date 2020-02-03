package editor.bar.listeners;

import api.utils.Utility;
import com.badlogic.gdx.Gdx;
import common.data.MapData;
import common.hud.EnigmaLabel;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaTextArea;
import common.hud.EnigmaWindow;
import common.save.DataSave;
import common.save.EmptyMapGenerator;
import data.EnigmaScreens;
import data.config.Config;
import data.config.UserConfiguration;
import game.EnigmaGame;
import game.screens.TestScreen;

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
	private static final String CREATE = "Créer", CANCEL = "Annuler";

	/**
	 * title
	 */
	private static final String TITLE = "Création d'une nouvelle map.";

	/**
	 * fields
	 */
	private final EnigmaTextArea nameF, widthF, heightF;

	/**
	 * popup content
	 */
	private final Object content;

	/**
	 * Observateur de la création d'une map
	 *
	 * @param window fenêtre
	 */
	public CreateListener(EnigmaWindow window, JComponent parent) {
		super(window, parent);

		//fields
		EnigmaLabel nom = new EnigmaLabel("Nom :");
		EnigmaLabel width = new EnigmaLabel("Largeur (blocs de 16x16) :");
		EnigmaLabel height = new EnigmaLabel("Hauteur (blocs de 16x16) :");

		//input
		this.nameF = new EnigmaTextArea();
		this.widthF = new EnigmaTextArea();
		this.heightF = new EnigmaTextArea();

		this.content = new Object[]{
				nom, this.nameF,
				width, this.widthF,
				height, this.heightF,
		};
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final int CHOICES = 2;
		String[] choices = new String[CHOICES];
		choices[CREATE_POS] = CREATE;
		choices[CANCEL_POS] = CANCEL;

		int choice = EnigmaOptionPane.showOptionDialog(window, content, TITLE, choices);

		if (choice == CREATE_POS) {
			try {
				String mapName = Utility.normalize(nameF.getText());
				final int col = Integer.parseInt(widthF.getText()), row = Integer.parseInt(heightF.getText());
				String path = Config.MAP_FOLDER + mapName;


				for (String s : Utility.getAllMapName()) {
					if (s.equals(mapName)) {
						EnigmaOptionPane.showAlert(this.window, "Ce nom existe déjà");
						return;
					}
				}

				//TODO: vérifier col, row, chemin (caractères spéciaux) et afficher une erreur

				if (!path.endsWith(Config.MAP_EXTENSION)) {
					path += Config.MAP_EXTENSION;
				}

				MapData data = new MapData(UserConfiguration.getInstance().getData().getName(),mapName);
				DataSave.writeMapData(data);

				EmptyMapGenerator.generate(path, col, row);

				if (((TestScreen) EnigmaGame.getCurrentScreen()).setMap(path))
					Gdx.app.postRunnable(() -> EnigmaGame.reload(EnigmaScreens.TEST.name()));

			} catch (NumberFormatException | IOException ex) {
				System.err.println("gérer les erreurs!!!!");
				System.out.println(choice);
				System.out.println(widthF.getText() + " " + heightF.getText() + " " + nameF.getText());
			}
		}
	}
}
