package editor.bar.listeners;

import api.ui.CustomOptionPane;
import api.utils.Utility;
import common.data.MapData;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.map.MapTestScreen;
import common.save.DataSave;
import common.save.EmptyMapGenerator;
import common.utils.Logger;
import data.config.Config;
import game.EnigmaGame;
import game.screens.TestScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Listener enregistrer sous
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 01/02/2020
 * @since 6.0 01/02/2020
 */
public class SaveAsListener extends MenuListener {

	public SaveAsListener(EnigmaWindow window, JComponent parent) {
		super(window, parent);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String mapName = EnigmaOptionPane.showInputDialog(this.window,"Nom de la map :");
		mapName = Utility.normalize(mapName);

		for(String s : Utility.getAllMapName()) {
			if (s.equals(mapName)){
				if(!EnigmaOptionPane.showConfirmDialog(this.window,"Une map nommée \"" + mapName + "\" existe déjà, remplacer?")){
					return;
				}
			}
		}

		if (!mapName.equals(CustomOptionPane.CANCEL) && !mapName.equals("")) {
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
			EmptyMapGenerator.save(Config.MAP_FOLDER + mapName, map.getTiledMap(), map.getEntities());
			//TODO: message ok
		}
	}

}
