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
import data.NeedToBeTranslated;
import data.config.Config;
import editor.EditorLauncher;
import game.EnigmaGame;
import game.screens.TestScreen;

import javax.swing.JFileChooser;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Observateur de la sauvegarde (rapide) de la map.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0
 * @since 3.0
 */
public class SaveListener extends MenuListener {
	/**
	 * Textes
	 */
	public static final String SAVE_ENDED = NeedToBeTranslated.SAVE_ENDED;

	public SaveListener(EnigmaWindow window, Component parent) {
		super(window, parent);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.save();
		EnigmaGame.getCurrentScreen().showToast(SAVE_ENDED);
	}

	/**
	 * Sauvegarde
	 * Existe car utilisé par d'autres classes
	 */
	public void save(){
		MapTestScreen map = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap();
		EmptyMapGenerator.save(TestScreen.getMapPath().replace(Config.MAP_EXTENSION,""), map.getTiledMap(), map.getEntities());
	}
}
