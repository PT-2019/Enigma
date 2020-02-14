package editor.bar.listeners;

import api.utils.Observer;
import common.hud.EnigmaButton;
import common.hud.EnigmaWindow;
import common.map.MapTestScreen;
import common.save.EmptyMapGenerator;
import common.utils.Logger;
import data.NeedToBeTranslated;
import data.config.Config;
import editor.bar.Outil;
import game.EnigmaGame;
import game.screens.TestScreen;

import java.awt.Component;
import java.awt.event.ActionEvent;

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
	 * Textes
	 */
	private static final String SAVE_ENDED = NeedToBeTranslated.SAVE_ENDED;

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
		//si on ne peut pas sauvegarder
		if(!this.canSave){
			//quitte
			Logger.printDebugALL("SaveListener","Save bloquée.");
			return;
		}
		//save
		this.save();
		//affiche succès
		EnigmaGame.getCurrentScreen().showToast(SAVE_ENDED);
	}

	/**
	 * Sauvegarde
	 * Existe car utilisé par d'autres classes
	 */
	public void save(){
		MapTestScreen map = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap();
		EmptyMapGenerator.save(TestScreen.getMapPath().replace(Config.MAP_EXTENSION,""), map.getTiledMap(), map.getEntities());
		map.freeId(null); //libères les ids temporaires
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
