package editor.bar.listeners;

import api.utils.Utility;
import common.data.MapData;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaTextArea;
import common.hud.EnigmaWindow;
import common.map.MapTestScreen;
import common.save.DataSave;
import common.save.EmptyMapGenerator;
import common.utils.Logger;
import data.config.Config;
import editor.EditorLauncher;
import game.EnigmaGame;
import game.screens.TestScreen;

import javax.swing.JFileChooser;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class SaveListener extends MenuListener {

	/*private static final FileChooser.ExtensionFilter extensions = new FileChooser.ExtensionFilter("Fichier map .tmx",
			"*.tmx");*/

	//private volatile boolean finished;
	//private volatile File file;

	public SaveListener(EnigmaWindow window, Component parent) {
		super(window, parent);
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

		String author = EnigmaGame.getCurrentScreen().getMap().getMapData().getAuthor();

		if(author.equals("")) {
			author = Utility.normalize(EnigmaOptionPane.showInputDialog(this.window,"Entrez votre nom d'auteur (irréversible) :"));
			MapData data = new MapData(author,Utility.normalize( EnigmaGame.getCurrentScreen().getMap().getMapData().getMapName() ));
			try {
				DataSave.writeMapData(data);
			} catch (IOException e) {
				Logger.printError("SaveListener.java","DataSave error");
				EnigmaOptionPane.showAlert(this.window,"Erreur lors de la sauvegarde");
			}
		}

		MapTestScreen map = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap();
		EmptyMapGenerator.save(TestScreen.getMapPath().replace(Config.MAP_EXTENSION,""), map.getTiledMap(), map.getEntities());
		//TODO: message ok
	}

}
