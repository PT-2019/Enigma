package common.enigmas;

import common.enigmas.reporting.EnigmaReport;
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
@SuppressWarnings("WeakerAccess")
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
	 * Ajoute le contenu d'un tileEvent à un autre
	 *
	 * @param event un tileEvent
	 */
	public void add(TileEvent event) {
		this.onExit.addAll(event.onExit);
		this.onEnter.addAll(event.onEnter);
		this.onUse.addAll(event.onUse);
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
	 * @return liste des reports
	 */
	public ArrayList<EnigmaReport> onEnter(Player player) {
		ArrayList<EnigmaReport> reports = new ArrayList<>();
		for (Enigma e : this.onEnter) {
			ArrayList<EnigmaReport> tmp = e.verifyConditions(player);
			if (tmp != null) reports.addAll(tmp);
		}

		return reports;
	}

	/**
	 * Lors de la sortie de la tile
	 *
	 * @param player Joueur concerné
	 * @return liste des reports
	 */
	public ArrayList<EnigmaReport> onExit(Player player) {
		ArrayList<EnigmaReport> reports = new ArrayList<>();
		for (Enigma e : this.onExit) {
			ArrayList<EnigmaReport> tmp = e.verifyConditions(player);
			if (tmp != null) reports.addAll(tmp);
		}

		return reports;
	}

	/**
	 * Lors d'interaction avec la tile
	 *
	 * @param player Joueur concerné
	 * @return tous les retours des conditions et opérations
	 */
	public ArrayList<EnigmaReport> onUse(Player player) {
		ArrayList<EnigmaReport> reports = new ArrayList<>();
		for (Enigma e : this.onUse) {
			ArrayList<EnigmaReport> tmp = e.verifyConditions(player);
			if (tmp != null) reports.addAll(tmp);
		}

		return reports;
	}

	@Override
	public String toString() {
		return "TileEvent{" + "onEnter=" + onEnter + ", onExit=" + onExit + ", onUse=" + onUse + '}';
	}
}
