package editor.bar.listeners;

import api.utils.Observer;
import common.hud.EnigmaButton;
import common.hud.EnigmaWindow;
import editor.bar.Outil;
import editor.bar.edition.ActionsManager;

import java.awt.Component;
import java.awt.event.ActionEvent;

/**
 * Observateur du "revenir en arrière" ou UNDO
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 01/02/2020
 * @since 5.0 01/02/2020
 */
public class UndoListener extends MenuListener implements Observer<ActionsManager> {

	private final ActionsManager instance;

	/**
	 * Observateur du "revenir en arrière" ou UNDO
	 * @param window window
	 * @param parent parent
	 */
	public UndoListener(EnigmaWindow window, Component parent) {
		super(window, parent);
		this.instance = ActionsManager.getInstance();
		this.instance.addObserver(this);
		this.update(this.instance);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.instance.undo();
	}

	@Override
	public void update(ActionsManager object) {
		if(object.isUndoAvailable()){
			if(parent instanceof EnigmaButton) ((EnigmaButton)parent).setIcon(Outil.UNDO_OK);
			else parent.setEnabled(true);
		} else {
			if(parent instanceof EnigmaButton) ((EnigmaButton)parent).setIcon(Outil.UNDO_KO);
			else parent.setEnabled(false);
		}
	}
}

