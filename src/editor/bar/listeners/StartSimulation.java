package editor.bar.listeners;

import api.utils.Observer;
import com.badlogic.gdx.Gdx;
import common.hud.EnigmaMenuItem;
import common.hud.EnigmaWindow;
import common.save.EmptyMapGenerator;
import data.EditorState;
import data.EnigmaScreens;
import editor.EditorLauncher;
import editor.bar.EnigmaMenuBarItems;
import game.EnigmaGame;
import game.screens.TestScreen;

import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import java.awt.Component;
import java.awt.event.ActionEvent;

/**
 * Lance la simulation du jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 08/02/2020
 * @since 6.0 08/02/2020
 */
public class StartSimulation extends MenuListener implements Observer<MapLoaded> {

	private static final String STOP = EnigmaMenuBarItems.STOP.name;

	/**
	 * Lance la simulation du jeu
	 *
	 * @param window fenêtre
	 * @param parent parent
	 */
	public StartSimulation(EnigmaWindow window, Component parent) {
		super(window, parent);
		MapLoaded instance = MapLoaded.getInstance();
		instance.addObserver(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Garde les mode persistants
		EditorLauncher.clearStates(EditorState.PERSISTANT);
		//simulation
		EditorLauncher.addState(EditorState.SIMULATION);

		//change la map avant de recharger
		EnigmaGame.load(EnigmaScreens.GAME.name()).setMap(TestScreen.getMapPath());
		Gdx.app.postRunnable(new Start());

		//désactive le bouton start
		this.parent.setEnabled(false);

		//active le bouton stop
		for (MenuElement item : ((JPopupMenu) this.parent.getParent()).getSubElements()) {
			if (item instanceof EnigmaMenuItem) {
				EnigmaMenuItem enigmaItem = (EnigmaMenuItem) item;
				if (STOP.equals(enigmaItem.getText())) {
					enigmaItem.setEnabled(true);
					return;
				}
			}
		}
	}

	@Override
	public void update(MapLoaded object) {
		this.parent.setEnabled(object.isMapLoaded());
	}

	/**
	 * Lance
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 6.0 08/02/2020
	 * @since 6.0 08/02/2020
	 */
	private static final class Start implements Runnable {

		@Override
		public void run() {
			//rechargement de la map
			EnigmaGame.reload(EnigmaScreens.GAME.name());
			//charge les entités sur la bonne map !
			EmptyMapGenerator.load(TestScreen.getMapPath(), false);
		}
	}
}
