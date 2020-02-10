package editor.bar.listeners;

import api.ui.CustomOptionPane;
import api.utils.Utility;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import common.data.MapData;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.map.MapTestScreen;
import common.save.DataSave;
import common.save.EmptyMapGenerator;
import common.utils.Logger;
import data.NeedToBeTranslated;
import data.config.Config;
import game.EnigmaGame;
import game.screens.TestScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
	/**
	 * Textes
	 */
	public static final String SAVE_ENDED = NeedToBeTranslated.SAVE_ENDED;
	public static final String SAVE_CANCELED = NeedToBeTranslated.SAVE_CANCELED;
	public static final String SAVE_FAILED = NeedToBeTranslated.SAVE_FAILED;
	public static final String REPLACE_MAP = NeedToBeTranslated.REPLACE_MAP;
	public static final String MAP_NAME = NeedToBeTranslated.MAP_NAME;

	public SaveAsListener(EnigmaWindow window, JComponent parent) {
		super(window, parent);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String mapName = EnigmaOptionPane.showInputDialog(this.window,MAP_NAME);
		mapName = Utility.normalize(mapName);

		for(String s : Utility.getAllMapName()) {
			if (s.equals(mapName)){
				if(!EnigmaOptionPane.showConfirmDialog(this.window,REPLACE_MAP)){
					return;
				}
			}
		}

		HashMap<String,String> data = EnigmaGame.getCurrentScreen().getMap().getMapData().getData();
		data.replace(MapData.MAP_NAME,mapName);

		if (!mapName.equals(CustomOptionPane.CANCEL) && !mapName.equals("")) {
			try {
				DataSave.writeMapData(new MapData(data));
			}catch (IOException e){
				EnigmaGame.getCurrentScreen().showToast(SAVE_FAILED);
				Logger.printError("SavAsListener.java","dave data : " + e.getMessage());
			}

			MapTestScreen map = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap();
			EmptyMapGenerator.save(Config.MAP_FOLDER + mapName, map.getTiledMap(), map.getEntities());
			EnigmaGame.getCurrentScreen().showToast(SAVE_ENDED);
		}else{
			EnigmaGame.getCurrentScreen().showToast(SAVE_CANCELED);
		}
	}

}
