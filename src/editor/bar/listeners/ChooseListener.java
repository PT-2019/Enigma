package editor.bar.listeners;

import api.utils.Observer;
import api.utils.Utility;
import com.badlogic.gdx.math.Vector2;
import common.entities.special.MusicEditor;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.map.MapObjects;
import common.map.MapTestScreen;
import data.config.Config;
import game.EnigmaGame;
import game.screens.TestScreen;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Listener de la sélection de la musique principale
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 14/02/2020
 * @since 6.0 14/02/2020
 */
public class ChooseListener extends MenuListener implements Observer<MapLoaded> {

	/**
	 * Listener de la sélection de la musique principale
	 *
	 * @param window    fenêtre
	 * @param component parent
	 */
	public ChooseListener(EnigmaWindow window, @Nullable JComponent component) {
		super(window, component);
		//besoin d'une map pour fonctionner
		MapLoaded instance = MapLoaded.getInstance();
		instance.addObserver(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String choice = EnigmaOptionPane.showMusicChoiceDialog(window);
		if (!choice.equals(EnigmaOptionPane.CANCEL)) {
			MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();

			final String path = Config.MUSIC_FOLDER + choice + Utility.getExtension(Config.MUSIC_FOLDER, choice);

			MusicEditor object = new MusicEditor(path,choice);
			map.getIdFactory().newID(object);
			object.setMainMusic(true);
			object.setStarter(true);

			//on cherche dans la map si il y a pas une autre mainMusic starter pour la virer
			MapObjects data = map.getEntities();
			ArrayList<MusicEditor> musics = data.getAllObjectsByClass(MusicEditor.class, true);
			//lorsqu'on supprime une entité on supprime toute les entités au même endroit donc
			// les musiques sont toutes placées au même endroit donc on va toutes les virer

			for (MusicEditor music : musics) {
				if (music.isMainMusic() && music.isStarter()) {
					musics.remove(music);
					//cela va détruire toutes les musiques
					map.removeEntity(music);
				}
			}
			//on remet nos chères musiques sur la map
			for (MusicEditor music : musics) {
				map.set(music, new Vector2(0, 0));
			}

			//on met la nouvelle musique
			map.set(object, new Vector2(0, 0));
		}
	}

	@Override
	public void update(MapLoaded object) {
		this.parent.setEnabled(object.isMapLoaded());
	}
}

