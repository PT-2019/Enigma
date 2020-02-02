package editor.bar.listeners;

import api.ui.CustomOptionPane;
import com.badlogic.gdx.Gdx;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.save.EmptyMapGenerator;
import data.EnigmaScreens;
import data.config.Config;
import game.EnigmaGame;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Component;
import java.awt.event.ActionEvent;

public class OpenListener extends MenuListener {

	/*private static final FileChooser.ExtensionFilter extensions = new FileChooser.ExtensionFilter("Fichier map .tmx",
			"*.tmx");*/

	//private volatile boolean finished;
	//private volatile File file;

	public OpenListener(EnigmaWindow window, Component parent) {
		super(window, parent);
		//force lancement javafx
		//new JFXPanel();
		//this.finished = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*this.finished = false;

		Platform.runLater(
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

		String map;
		if (!(map = EnigmaOptionPane.showMapChoiceDialog(new EnigmaWindow())).equals(CustomOptionPane.CANCEL)) {
			System.out.println("ouverture de "+Config.MAP_FOLDER + map + ".tmx");
			EmptyMapGenerator.load( Config.MAP_FOLDER + map + ".tmx");
			Gdx.app.postRunnable(() -> EnigmaGame.reload(EnigmaScreens.TEST.name()));
			//TODO: message ok
		}
	}
}
