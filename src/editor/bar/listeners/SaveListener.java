package editor.bar.listeners;

import api.utils.Observer;
import api.utils.Utility;
import common.data.MapData;
import common.hud.EnigmaButton;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.map.MapTestScreen;
import common.save.DataSave;
import common.save.EmptyMapGenerator;
import common.utils.Logger;
import data.config.Config;
import editor.bar.Outil;
import game.EnigmaGame;
import game.screens.TestScreen;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Observateur de la sauvegarde (rapide) de la map.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 3.0
 */
public class SaveListener extends MenuListener implements Observer<MapLoaded> {

	/**
	 * Si on peut sauvegarder
	 */
	private boolean canSave;

	public SaveListener(EnigmaWindow window, Component parent) {
		super(window, parent);
		MapLoaded instance = MapLoaded.getInstance();
		instance.addObserver(this);
		this.canSave = instance.isMapLoaded();
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if(!this.canSave){
			Logger.printDebugALL("SaveListener","Save bloquée.");
			return;
		}
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

		MapTestScreen map = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap();
		EmptyMapGenerator.save(TestScreen.getMapPath().replace(Config.MAP_EXTENSION, ""), map.getTiledMap(), map.getEntities());
		map.freeId(null); //libères les ids temporaires

		//TODO: message ok
	}

	/**
	 * Affiche icône de sauvegarde et désactive le bouton
	 *
	 * @param loaded true affiche icône sauvegarde ok sinon ko
	 */
	@Override
	public void update(MapLoaded loaded) {
		if (loaded != null && loaded.isMapLoaded()) {
			if (parent instanceof EnigmaButton) ((EnigmaButton) parent).setIcon(Outil.SAVE_OK);
			else parent.setEnabled(true);
			this.canSave = loaded.isMapLoaded();
		} else {
			if (parent instanceof EnigmaButton) ((EnigmaButton) parent).setIcon(Outil.SAVE_KO);
			else parent.setEnabled(false);
		}
	}

}
