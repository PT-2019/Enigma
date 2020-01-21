package editor.screens.menus.listeners;

import api.enums.EnigmaScreens;
import api.enums.Outil;
import com.badlogic.gdx.Gdx;
import editor.hud.EnigmaLabel;
import editor.hud.EnigmaOptionPane;
import editor.hud.EnigmaTextArea;
import editor.hud.EnigmaWindow;
import game.EnigmaGame;
import game.screen.TestScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Observateur des boutons de la barre d'outil
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0 19 décembre 2019
 * @see editor.screens.EditorScreen
 * @since 3.0 19 décembre 2019
 */
@Deprecated
public class OutilAction implements ActionListener {

	/*private static final ExtensionFilter extensions = new ExtensionFilter("Fichier map .tmx",
			"*.tmx");*/
	private final EnigmaWindow window;

	private volatile boolean finished;
	private volatile File file;

	public OutilAction(EnigmaWindow window) {
		this.window = window;
		//force lancement javafx
		//new JFXPanel();
		this.finished = false;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String msg = actionEvent.getActionCommand();
		this.finished = false;

		if (msg.equals(Outil.OPEN.name)) {

			/*Platform.runLater(
					() ->
					{
						FileChooser fileChooser = new FileChooser();
						fileChooser.setInitialDirectory(new File("./assets/map"));
						fileChooser.getExtensionFilters().addAll(extensions);

						this.file = fileChooser.showOpenDialog(null);
						this.finished = true;
					}
			);

			while (!this.finished) ; //attends le thread file chooser

			//relance le screen
			if (this.file != null) {
				if (((TestScreen) EnigmaGame.getCurrentScreen()).setMap(this.file.getAbsolutePath()))
					Gdx.app.postRunnable(() -> EnigmaGame.reload(EnigmaScreens.TEST.name()));
			}*/

		} else if (msg.equals(Outil.SAVE.name)) {

			/*Platform.runLater(
					() ->
					{
						FileChooser fileChooser = new FileChooser();
						fileChooser.setInitialDirectory(new File("./assets/map"));
						fileChooser.getExtensionFilters().addAll(extensions);

						this.file = fileChooser.showSaveDialog(null);
						this.finished = true;
					}
			);*/

			while (!this.finished) ; //attends le thread file chooser

			//sauvegarde
			if (this.file != null) {

			}
		} else if(msg.equals(Outil.NEW.name)){
			//TODO: new

			String title = "Création d'une nouvelle map.";

			EnigmaLabel jardinL = new EnigmaLabel("Nom :");
			EnigmaTextArea jardin = new EnigmaTextArea();

			EnigmaLabel descriptionL = new EnigmaLabel("Largeur (blocs de 16x16) :");
			EnigmaTextArea description = new EnigmaTextArea();

			EnigmaLabel lieuL = new EnigmaLabel("Hauteur (blocs de 16x16) :");
			EnigmaTextArea lieu = new EnigmaTextArea();

			Object[] content = new Object[]{
					jardinL, jardin,
					descriptionL, description,
					lieuL, lieu,
			};

			int retour = EnigmaOptionPane.showOptionDialog(window, content, title, new String[]{"Ok", "Annuler"});

			System.out.println(retour);
		}
	}

}
