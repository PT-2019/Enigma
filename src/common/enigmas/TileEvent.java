package common.enigmas;

import common.entities.players.Player;

import java.util.ArrayList;

/**
 * Permet de gérer le lancement d'événements via la vérification d'{@link Enigma énigmes}
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 5.0
 */
public class TileEvent {

	/**
	 * Liste des énigme à lancer à l'entrée sur la tile
	 */
	private ArrayList<Enigma> onEnter;
	/**
	 * Liste des énigme à lancer à la de la tile
	 */
	private ArrayList<Enigma> onExit;
	/**
	 * Liste des énigme à lancer lors de l'intercation avec la tile
	 */
	private ArrayList<Enigma> onUse;

	public TileEvent() {
		this.onEnter = new ArrayList<>();
		this.onExit = new ArrayList<>();
		this.onUse = new ArrayList<>();
	}

	/**
	 * Ajoute une énigme en fonction de son type
	 *
	 * @param enigma Enigma à ajouter
	 */
	public void add(Enigma enigma) {
		if (enigma.getType().equals(TileEventEnum.ON_ENTER))
			this.addOnEnter(enigma);
		else if (enigma.getType().equals(TileEventEnum.ON_USE))
			this.addOnUse(enigma);
		else if (enigma.getType().equals(TileEventEnum.ON_EXIT))
			this.addOnExit(enigma);
	}

	/**
	 * Ajoute l'énigme à l'entrée sur la tile
	 *
	 * @param onEnter Enigma à ajouter
	 */
	public void addOnEnter(Enigma onEnter) {
		this.onEnter.add(onEnter);
	}

	/**
	 * Ajoute l'énigme lors de la sortie de la tile
	 *
	 * @param onExit Enigma à ajouter
	 */
	public void addOnExit(Enigma onExit) {
		this.onExit.add(onExit);
	}

	/**
	 * Ajoute l'énigme lors d'interaction avec la tile
	 *
	 * @param onUse Enigma à ajouter
	 */
	public void addOnUse(Enigma onUse) {
		this.onUse.add(onUse);
	}

	/**
	 * A l'entrée sur la tile
	 *
	 * @param player Joueur concerné
	 */
	public void onEnter(Player player) {
		for (Enigma e : this.onEnter)
			e.verifyConditions(player);
	}

	/**
	 * Lors de la sortie de la tile
	 *
	 * @param player Joueur concerné
	 */
	public void onExit(Player player) {
		for (Enigma e : this.onExit)
			e.verifyConditions(player);
	}

	/**
	 * Lors d'interaction avec la tile
	 *
	 * @param player Joueur concerné
	 */
	public void onUse(Player player) {
		//TODO: choisi un message et le retourner
		for (Enigma e : this.onUse)
			e.verifyConditions(player);
	}

}
