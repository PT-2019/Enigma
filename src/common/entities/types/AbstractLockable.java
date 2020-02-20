package common.entities.types;

import com.badlogic.gdx.utils.Array;
import common.enigmas.TileEventEnum;
import common.enigmas.reporting.EnigmaReport;
import common.entities.GameObject;
import common.entities.players.PlayerGame;
import common.save.entities.serialization.EntitySerializable;
import data.Layer;

import java.util.HashMap;

/**
 * Un object verrouillable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 17/02/2020
 * @since 6.0 17/02/2020
 */
public abstract class AbstractLockable extends AbstractItem implements Lockable, ChangeState {

	/**
	 * Tiles alternatives si changement d'état
	 */
	protected HashMap<String, Array<Float>> altTiles;

	/**
	 * Indique si l'objet est verrouillé
	 */
	protected boolean locked;

	/**
	 * Object déjà déverrouillé
	 */
	protected boolean alreadyUnlocked;

	/**
	 * Le lockable est affiché fermé même si ouvert
	 */
	protected boolean hidden;

	/**
	 * Crée une object verrouillable avec un id unique pour identifier ses énigmes
	 *
	 * @param id     ID
	 * @param locked true si l'objet est verrouillé de base, false sinon
	 * @since 2.0
	 */
	public AbstractLockable(int id, boolean locked) {
		super(id);
		this.locked = locked;
		this.alreadyUnlocked = false;
		this.hidden = true;
	}

	//lock

	@Override
	public void lock() {
		this.locked = true;
	}

	@Override
	public void unlock() {
		this.locked = false;
	}

	@Override
	public boolean isLocked() {
		return this.locked;
	}

	//imp
	@Override
	public void serialization(EntitySerializable serializable, GameObject created) {
		HashMap<String, Array<Float>> altTiles = serializable.getAltTiles();
		if (altTiles != null) {//il y a des tiles
			this.altTiles = altTiles;
		}
	}

	//changeState

	@Override
	public EnigmaReport changeState(PlayerGame actor, TileEventEnum event) {
		if (event.equals(TileEventEnum.ON_USE)) {
			if (isLocked()) {
				this.alreadyUnlocked = false;
				return new EnigmaReport(ChangeStateReport.LOCKED, true, this);
			} else if (!this.alreadyUnlocked) {
				this.alreadyUnlocked = true;
				this.hidden = false;
				return new EnigmaReport(ChangeStateReport.UNLOCK, true, this);
			}
		}
		return null;
	}

	@Override
	public boolean needReloadAfterStateChange() {
		return true;
	}

	@Override
	public boolean isNormalState() {
		return this.locked;
	}

	@Override
	public Array<Float> getTilesFromState(Layer layer) {
		if (isNormalState() || this.hidden) {
			return this.getTiles(layer);
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
		return false;
	}

	//toString

	@Override
	public String toString() {
		return "AbstractLockable{" + "locked=" + locked + '}';
	}
}
