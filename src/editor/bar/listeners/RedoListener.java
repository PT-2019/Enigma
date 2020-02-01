package editor.bar.listeners;

import api.utils.Observer;
import common.hud.EnigmaButton;
import common.hud.EnigmaWindow;
import editor.bar.Outil;
import editor.bar.edition.ActionsManager;

import java.awt.Component;
import java.awt.event.ActionEvent;

/**
 * Observateur de l'annulation de "revenir en arrière"
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 01/02/2020
 * @since 5.0 01/02/2020
 */
public class RedoListener extends MenuListener implements Observer<ActionsManager> {

	private final ActionsManager instance;

	/**
	 * Observateur de l'annulation de "revenir en arrière"
	 * @param window window
	 * @param parent parent
	 */
	public RedoListener(EnigmaWindow window, Component parent) {
		super(window, parent);
		this.instance = ActionsManager.getInstance();
		this.instance.addObserver(this);
		this.update(this.instance);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.instance.redo();
	}

	@Override
	public void update(ActionsManager object) {
		if(object.isRedoAvailable()){
			if(parent instanceof EnigmaButton)
				((EnigmaButton)parent).setIcon(Outil.REDO_OK);
			else parent.setEnabled(true);
		} else {
			if(parent instanceof EnigmaButton) ((EnigmaButton)parent).setIcon(Outil.REDO_KO);
			else parent.setEnabled(false);
		}
	}
}
