package editor.bar.listeners;

import api.ui.CustomOptionPane;
import api.utils.Observer;
import api.utils.Utility;
import com.badlogic.gdx.Gdx;
import common.data.MapData;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.map.MapTestScreen;
import common.save.DataSave;
import common.save.EmptyMapGenerator;
import common.utils.Logger;
import data.NeedToBeTranslated;
import data.EnigmaScreens;
import data.config.Config;
import game.EnigmaGame;
import game.screens.TestScreen;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;

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
public class SaveAsListener extends MenuListener  implements Observer<MapLoaded> {

	/**
	 * Textes
	 */
	private static final String SAVE_ENDED = NeedToBeTranslated.SAVE_ENDED;
	private static final String SAVE_CANCELED = NeedToBeTranslated.SAVE_CANCELED;
	private static final String SAVE_FAILED = NeedToBeTranslated.SAVE_FAILED;
	private static final String REPLACE_MAP = NeedToBeTranslated.REPLACE_MAP;
	private static final String MAP_NAME = NeedToBeTranslated.MAP_NAME;

	public SaveAsListener(EnigmaWindow window, JComponent parent) {
		super(window, parent);
		MapLoaded instance = MapLoaded.getInstance();
		instance.addObserver(this);
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

			String path = Config.MAP_FOLDER + mapName;

			//sauvegarde
			MapTestScreen map = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap();
			EmptyMapGenerator.save(path, map.getTiledMap(), map.getEntities());

			//change la map avant de la recharger
			EnigmaGame.getCurrentScreen().setMap(path + Config.MAP_EXTENSION);

			Gdx.app.postRunnable(() -> {
				//rechargement de la map
				EnigmaGame.reload(EnigmaScreens.TEST.name());
				//charge les entités sur la bonne map !
				EmptyMapGenerator.load(path + Config.MAP_EXTENSION);
			});

			//message ok
			EnigmaGame.getCurrentScreen().showToast(SAVE_ENDED);
		}else{
			EnigmaGame.getCurrentScreen().showToast(SAVE_CANCELED);
		}
	}

	@Override
	public void update(MapLoaded object) {
		this.parent.setEnabled(object.isMapLoaded());
	}
}
