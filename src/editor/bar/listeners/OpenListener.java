package editor.bar.listeners;

import api.ui.CustomOptionPane;
import com.badlogic.gdx.Gdx;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.save.EmptyMapGenerator;
import common.utils.Logger;
import data.EnigmaScreens;
import data.config.Config;
import editor.popup.listeners.CaseListener;
import game.EnigmaGame;
import game.screens.TestScreen;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Component;
import java.awt.event.ActionEvent;

/**
 * Observateur de l'ouverture de la map
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0
 * @since 3.0
 */
public class OpenListener extends MenuListener {

	public OpenListener(EnigmaWindow window, Component parent) {
		super(window, parent);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String map;
		CaseListener.close();
		if (!(map = EnigmaOptionPane.showMapChoiceDialog(this.window)).equals(CustomOptionPane.CANCEL)) {
			String path =  Config.MAP_FOLDER + map + ".tmx";
			Logger.printDebug("OpenListener#actionPerformed","ouverture de " + path);

			//change la map avant de recharger
			EnigmaGame.getCurrentScreen().setMap(path);

			Gdx.app.postRunnable(() -> {
				//rechargement de la map
				EnigmaGame.reload(EnigmaScreens.TEST.name());
				//charge les entités sur la bonne map !
				EmptyMapGenerator.load(path);
			});

			//TODO: message ok
		}
	}
}
