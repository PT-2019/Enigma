package common.entities.types;

import api.utils.annotations.ConvenienceClass;
import com.badlogic.gdx.utils.Array;
import common.enigmas.Enigma;
import common.enigmas.TileEventEnum;
import common.enigmas.reporting.EnigmaReport;
import common.entities.Consumable;
import common.entities.GameObject;
import common.entities.players.Player;
import common.entities.players.PlayerGame;
import common.save.entities.serialization.EntitySerializable;
import data.Layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Ce classe ne doit pas se retrouver ailleurs que dans un extends
 * <p>
 * Abstraction d'un Consommable.
 * <p>
 * Toutes les méthodes sont implémentées.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1 25/12/2019
 * @since 4.0 24/12/2019
 */
@ConvenienceClass
public abstract class AbstractConsumable extends AbstractGameObject implements Consumable, ChangeState {

	/**
	 * Tiles alternatives si changement d'état
	 */
	protected HashMap<String, Array<Float>> altTiles;

	/**
	 * Enigmes données à l'objet
	 *
	 * @since 4.0
	 */
	protected ArrayList<Enigma> enigmas;

	/**
	 * Si ajouté dans l'inventaire
	 */
	private boolean addedToInventory;

	/**
	 * Ce classe ne doit pas se retrouver ailleurs que dans un extends
	 * <p>
	 * Abstraction d'un Consommable.
	 */
	protected AbstractConsumable() {
		this(-1);
	}

	/**
	 * Ce classe ne doit pas se retrouver ailleurs que dans un extends
	 * <p>
	 * Abstraction d'un Consommable.
	 *
	 * @param id id unique de l'object pour les énigmes
	 */
	protected AbstractConsumable(int id) {
		super(id);
		this.enigmas = new ArrayList<>();
		this.addedToInventory = false;
	}

	//Enigmas

	@Override
	public void interactsWith(Player p) {
		for (Enigma e : this.enigmas) {
			if (!e.isKnown()) e.discovered();
			else e.verifyConditions(p);
		}
	}

	//enigmas

	@Override
	public void addEnigma(Enigma e) {
		this.enigmas.add(e);
	}

	@Override
	public void removeEnigma(Enigma e) {
		this.enigmas.remove(e);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<Enigma> getAllEnigmas() {
		ArrayList<Enigma> e = (ArrayList<Enigma>) this.enigmas.clone();
		return e.iterator();
	}

	//toString

	@Override
	public String toLongString() {
		StringBuilder s = new StringBuilder(this.toString() + ":: enigmas = {");
		int size = this.enigmas.size() - 1;
		int i = 0;
		for (Enigma e : this.enigmas) {
			s.append(e.toLongString());
			if (i < size) s.append(", ");
			i++;
		}
		s.append("}]");
		return s.toString();
	}

	//imp
	@Override
	public void serialization(EntitySerializable serializable, GameObject created) {
		HashMap<String, Array<Float>> altTiles = serializable.getAltTiles();
		if (altTiles != null) {//il y a des tiles
			this.altTiles = altTiles;
		}
	}

	//toString

	@Override
	public abstract String toString();

	@Override
	public EnigmaReport changeState(PlayerGame actor, TileEventEnum event) {
		if (event.equals(TileEventEnum.ON_USE)) {
			//Récupère l'inventaire du joueur
			//ajoute cet item
			//return que c'est ok
			this.addedToInventory = true;
			return new EnigmaReport(ChangeStateReport.INVENTORY, true, this);
		}
		return null;
	}

	@Override
	public boolean needReloadAfterStateChange() {
		return false;
	}

	@Override
	public boolean isNormalState() {
		return !addedToInventory;
	}

	@Override
	public Array<Float> getTilesFromState(Layer layer) {
		if (isNormalState()) {
			return this.tiles.get(layer);
		} else {
			return this.altTiles.get(layer.name());
		}
	}

	@Override
	public HashMap<String, Array<Float>> getTilesFromState() {
		return this.altTiles;
	}

	@Override
	public boolean shouldAutomaticRepaint() {
		return true;
	}
}
