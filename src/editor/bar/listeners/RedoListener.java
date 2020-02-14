package editor.bar.listeners;

import api.utils.Observer;
import common.hud.EnigmaButton;
import common.hud.EnigmaWindow;
import editor.bar.Outil;
import editor.bar.edition.ActionsManager;
import org.jetbrains.annotations.Nullable;

import java.awt.Component;
import java.awt.event.ActionEvent;

/**
 * Observateur de l'annulation de "revenir en arrière"
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 01/02/2020
 * @since 5.0 01/02/2020
 */
public class RedoListener extends MenuListener implements Observer<ActionsManager> {

	/**
	 * Sauvegarde de l'action manager pour plus de rapidité
	 */
	private final ActionsManager instance;

	/**
	 * Observateur de l'annulation de "revenir en arrière"
	 *
	 * @param window window
	 * @param parent parent
	 */
	public RedoListener(EnigmaWindow window, @Nullable Component parent) {
		super(window, parent);
		//ajoute en tant que listener
		this.instance = ActionsManager.getInstance();
		this.instance.addObserver(this);
		this.update(this.instance);
	}

	@Override
	public void actionPerformed(@Nullable ActionEvent e) {
		this.instance.redo();
	}

	@Override
	public void update(ActionsManager object) {
		//si pas de redo, alors je disable le bouton sinon je l'active
		if (object.isRedoAvailable()) {
			if (this.parent instanceof EnigmaButton)
				((EnigmaButton) this.parent).setIcon(Outil.REDO_OK);
			else this.parent.setEnabled(true);
		} else {
			if (this.parent instanceof EnigmaButton) ((EnigmaButton) this.parent).setIcon(Outil.REDO_KO);
			else this.parent.setEnabled(false);
		}
	}
}
