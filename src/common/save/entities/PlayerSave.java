package common.save.entities;

/**
 * Sauvegarde de player, npc
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 27/01/2020
 * @since 5.0 27/01/2020
 */
public enum PlayerSave implements SaveKey {
	JSON, KEY, NAME, INVENTORY, HERO, ACTIVATED, LOCKED, CONTENT,PATH,MAIN,STARTER;

	@Override
	public String getKey() {
		return this.name();
	}
}
