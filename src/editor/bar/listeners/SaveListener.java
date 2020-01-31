package editor.bar.listeners;

import data.config.Config;
import game.EnigmaGame;
import game.screens.TestScreen;
import general.hud.EnigmaWindow;
import general.map.MapTestScreen;
import general.save.EmptyMapGenerator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;

public class SaveListener extends MenuListener {

	/*private static final FileChooser.ExtensionFilter extensions = new FileChooser.ExtensionFilter("Fichier map .tmx",
			"*.tmx");*/

	//private volatile boolean finished;
	//private volatile File file;

	public SaveListener(EnigmaWindow window) {
		super(window);
		//force lancement javafx
		//new JFXPanel();
		//this.finished = false;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		//this.finished = false;


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

		//while (!this.finished) ; //attends le thread file chooser

		//sauvegarde
		/*if (this.file != null) {

		}*/

		//TODO: regarder s'il y a une map de chargée avant de le laisser voir menu sauvegarde

		FileNameExtensionFilter tmx = new FileNameExtensionFilter("Fichier map (*.tmx)", "tmx");
		JFileChooser fileChooser = new JFileChooser(Config.MAP_FOLDER);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(tmx);

		if (fileChooser.showSaveDialog(new EnigmaWindow()) == JFileChooser.APPROVE_OPTION) {
			MapTestScreen map = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap();
			EmptyMapGenerator.save(fileChooser.getSelectedFile().getAbsolutePath(), map.getTiledMap(), map.getEntities());
			//TODO: message ok
		}
	}

}
