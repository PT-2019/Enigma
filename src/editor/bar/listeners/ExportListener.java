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

import javax.swing.JComponent;
import java.awt.event.ActionEvent;

/**
 * Observateur de l'exportation de la map
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 01/02/2020
 * @since 6.0 01/02/2020
 */
public class ExportListener extends MenuListener {

	public ExportListener(EnigmaWindow window, JComponent parent) {
		super(window, parent);
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
