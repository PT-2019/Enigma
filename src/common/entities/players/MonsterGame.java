package common.entities.players;

import api.libgdx.actor.GameActorAnimation;
import common.dialog.Dialog;

/**
 * Monstre du jeu
 */
public class MonsterGame extends GameActorAnimation {
	/**
	 * Dialogue du monster
	 */
	private Dialog dialog;

	public MonsterGame() {
		this.setAnimationPaused(true);
	}

	public MonsterGame(Dialog dialog) {
		this.setAnimationPaused(true);
		this.dialog = dialog;
	}

	public Dialog getDialog() {
		return dialog;
	}
}
