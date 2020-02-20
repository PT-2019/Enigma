package common.entities.players;

import api.libgdx.actor.GameActorAnimation;
import common.dialog.Dialog;

/**
 * Entités pnj dans le jeu
 */
public class NpcGame extends GameActorAnimation {

	/**
	 * Dialogue du monster
	 */
	private Dialog dialog;

	public NpcGame() {
		this.setAnimationPaused(true);
	}

	public NpcGame(Dialog dialog) {
		this.setAnimationPaused(true);
		this.dialog = dialog;
	}

	public Dialog getDialog() {
		return dialog;
	}
}
