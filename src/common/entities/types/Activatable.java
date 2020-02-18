package common.entities.types;

import api.utils.annotations.ConvenienceClass;
import com.badlogic.gdx.utils.Array;
import common.enigmas.Enigma;
import common.enigmas.TileEventEnum;
import common.enigmas.reporting.EnigmaReport;
import common.entities.GameObject;
import common.entities.players.Player;
import common.entities.players.PlayerGame;
import common.save.entities.serialization.EntitySerializable;
import data.Layer;

import java.util.HashMap;

/**
 * Désigne des objets pouvant être activés ou désactivés
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * apiNote : on peux entends une seule classe donc autant que ce soit un actor (possible)
 * @see common.entities.Item
 * @since 1.0
 */
@ConvenienceClass
public abstract class Activatable extends AbstractItem implements ChangeState {

	/**
	 * Tiles alternatives si changement d'état
	 */
	protected HashMap<String, Array<Float>> altTiles;

	/**
	 * Indique si l'objet est activé
	 */
	protected boolean activated;

	/**
	 * @param activated true si l'objet est activé de base, false sinon
	 */
	public Activatable(boolean activated) {
		this(activated, -1);
	}

	/**
	 * @param activated true si l'objet est activé de base, false sinon
	 * @param id        ID
	 */
	public Activatable(boolean activated, int id) {
		super(id);
		this.activated = activated;
	}

	@Override
	public Array<Float> getTiles(Layer layer) {
		return super.getTiles(layer);
	}

	/**
	 * Indique si l'objet est activé
	 *
	 * @return true si l'objet est activé, false sinon
	 */
	public boolean isActivated() {
		return this.activated;
	}

	/**
	 * Définit si l'object est activé
	 *
	 * @param activated true si l'object est activé
	 * @since 6.0
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	@Override
	public EnigmaReport changeState(PlayerGame actor, TileEventEnum event) {
		if(event.equals(TileEventEnum.ON_USE)){
			this.activated = !activated;
			return new EnigmaReport(ChangeStateReport.DONE, true, this);
		}
		return null;
	}

	@Override
	public boolean shouldAutomaticRepaint() {
		return true;
	}

	@Override
	public void serialization(EntitySerializable serializable, GameObject created) {
		HashMap<String, Array<Float>> altTiles = serializable.getAltTiles();
		if(altTiles != null){//il y a des tiles
			this.altTiles = altTiles;
		}
	}

	@Override
	public boolean isNormalState() {
		return this.activated;
	}

	@Override
	public void interactsWith(Player p) {
		this.activated = !this.activated;

		for (Enigma e : this.enigmas) {
			if (!e.isKnown()) e.discovered();
			else e.verifyConditions(p);
		}
	}

	@Override
	public HashMap<String, Array<Float>> getTilesFromState() {
		return this.altTiles;
	}

	@Override
	public Array<Float> getTilesFromState(Layer layer) {
		if(!this.activated){
			return this.getTiles(layer);
		} else {
			return this.altTiles.get(layer.name());
		}
	}
}
