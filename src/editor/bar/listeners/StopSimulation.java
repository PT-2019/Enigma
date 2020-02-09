package editor.bar.listeners;

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
 *
 * @version 6.0 08/02/2020
 * @since 6.0 08/02/2020
 */
public class StopSimulation extends MenuListener {

	private static final String START = EnigmaMenuBarItems.START.name;

	/**
	 * Lance la simulation du jeu
	 *
	 * @param window fenêtre
	 * @param parent parent
	 */
	public StopSimulation(EnigmaWindow window, Component parent) {
		super(window, parent);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//rétablit l'état
		EditorLauncher.clearStates(EditorState.ZOOM);

		Gdx.app.postRunnable(new Stop());

		//désactive le bouton stop
		this.parent.setEnabled(false);

		//active le bouton start
		for (MenuElement item : ((JPopupMenu) this.parent.getParent()).getSubElements()) {
			if(item instanceof EnigmaMenuItem){
				EnigmaMenuItem enigmaItem = (EnigmaMenuItem) item;
				if(START.equals(enigmaItem.getText())){
					enigmaItem.setEnabled(true);
					return;
				}
			}
		}
	}

	/**
	 * Lance
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 *
	 * @version 6.0 08/02/2020
	 * @since 6.0 08/02/2020
	 */
	private static final class Stop implements Runnable {

		@Override
		public void run() {
			EnigmaGame.setScreen(EnigmaScreens.TEST.name());
		}
	}
}
