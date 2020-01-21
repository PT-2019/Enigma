package editor.view.cases.listeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import editor.view.cases.CasePopUp;
import editor.view.listeners.CaseListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Controle la fenêtre d'un CasePopUp
 *
 * @see CasePopUp
 */
public class CasePopWindowListener implements WindowListener {

	private CasePopUp popUp;

	/**
	 * Groupe d'actor
	 *
	 * @see Actor
	 */
	private Group group;

	public CasePopWindowListener(CasePopUp pop, Group group) {
		this.popUp = pop;
		this.group = group;
	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		//on récupère tout les actor
		SnapshotArray<Actor> arr = this.group.getChildren();

		//on désactive le mode enigmacreate
		for (Actor act : arr) {
			Array<EventListener> listeners = act.getListeners();
			((CaseListener) listeners.get(0)).setEnigmacreate(false);
		}
		popUp.clean();
		popUp.setVisible(false);
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}
}
