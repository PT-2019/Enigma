package editor.bar.listeners;

import api.ui.CustomOptionPane;
import api.utils.Utility;
import com.badlogic.gdx.Gdx;
import common.data.MapData;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.map.MapTestScreen;
import common.save.DataSave;
import common.save.EmptyMapGenerator;
import common.utils.Logger;
import data.EnigmaScreens;
import data.config.Config;
import game.EnigmaGame;
import game.screens.TestScreen;

import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Listener enregistrer sous
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 01/02/2020
 * @since 6.0 01/02/2020
 */
public class SaveAsListener extends MenuListener {

	public SaveAsListener(EnigmaWindow window, JComponent parent) {
		super(window, parent);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String mapName = "";
		boolean notExist = true;

		while (notExist) {
			mapName = EnigmaOptionPane.showInputDialog(this.window, "Sauvegarder sous :");
			notExist = false;
			for (String s : Utility.getAllMapName()) {
				if (s.equals(mapName)) {
					notExist = true;
					EnigmaOptionPane.showAlert(this.window, "Ce nom existe déjà");
				}
			}
		}

		if (!mapName.equals(CustomOptionPane.CANCEL) && !mapName.equals("")) {
			String author = EnigmaGame.getCurrentScreen().getMap().getMapData().getAuthor();

			if (author.equals("")) {
				author = Utility.normalize(EnigmaOptionPane.showInputDialog(this.window, "Entrez votre nom d'auteur (irréversible) :"));
				MapData data = new MapData(author, Utility.normalize(EnigmaGame.getCurrentScreen().getMap().getMapData().getMapName()));
				try {
					DataSave.writeMapData(data);
				} catch (IOException e) {
					Logger.printError("SaveListener.java", "DataSave error");
					EnigmaOptionPane.showAlert(this.window, "Erreur lors de la sauvegarde");
				}
			}

			String path = Config.MAP_FOLDER + mapName;

			//sauvegarde
			MapTestScreen map = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap();
			EmptyMapGenerator.save(path, map.getTiledMap(), map.getEntities());

			//change la map avant de recharger
			EnigmaGame.getCurrentScreen().setMap(path + Config.MAP_EXTENSION);

			Gdx.app.postRunnable(() -> {
				//rechargement de la map
				EnigmaGame.reload(EnigmaScreens.TEST.name());
				//charge les entités sur la bonne map !
				EmptyMapGenerator.load(path + Config.MAP_EXTENSION);
			});


			//TODO: message ok

		}
	}

}
