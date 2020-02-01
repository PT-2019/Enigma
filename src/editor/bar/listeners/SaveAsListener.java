package editor.bar.listeners;

import api.ui.CustomOptionPane;
import api.utils.Utility;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.map.MapTestScreen;
import common.save.EmptyMapGenerator;
import data.config.Config;
import game.EnigmaGame;
import game.screens.TestScreen;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;

public class SaveAsListener extends MenuListener {

	/*private static final FileChooser.ExtensionFilter extensions = new FileChooser.ExtensionFilter("Fichier map .tmx",
			"*.tmx");*/

	//private volatile boolean finished;
	//private volatile File file;

	public SaveAsListener(EnigmaWindow window) {
		super(window);
		//force lancement javafx
		//new JFXPanel();
		//this.finished = false;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String mapName = "";
		boolean notExist = true;

		while(notExist) {
			mapName = EnigmaOptionPane.showInputDialog(new EnigmaWindow(),"Sauvegarder sous :");
			notExist = false;
			for (String s : Utility.getAllMapName()) {
				if (s.equals(mapName)) {
					notExist = true;
					EnigmaOptionPane.showAlert(new EnigmaWindow(), "Ce nom existe déjà");
				}
			}
		}

		if (!mapName.equals(CustomOptionPane.CANCEL) && !mapName.equals("")) {
			MapTestScreen map = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap();
			EmptyMapGenerator.save(Config.MAP_FOLDER + mapName, map.getTiledMap(), map.getEntities());
			//TODO: message ok
		}
	}

}
