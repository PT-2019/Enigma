package common.entities.types;

import api.libgdx.actor.GameActorAnimation;
import api.utils.annotations.ConvenienceClass;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import common.entities.GameObject;
import data.Layer;

import java.util.HashMap;

/**
 * Ce classe ne doit pas se retrouver ailleurs que dans un extends
 * <p>
 * Abstraction d'un GameObject.
 * <p>
 * Toutes les méthodes sont implémentées.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 25/12/2019
 * @since 4.0 25/12/2019
 */
@ConvenienceClass
public abstract class AbstractGameObject extends GameActorAnimation implements GameObject {

	/**
	 * les tiles (a convertir en int)
	 */
	protected HashMap<Layer, Array<Float>> tiles;

	/**
	 * Dimensions de l'object
	 */
	protected Rectangle bounds;

	/**
	 * ID
	 *
	 * @since 2.2
	 */
	protected int id;

	/**
	 * Ce classe ne doit pas se retrouver ailleurs que dans un extends
	 * <p>
	 * Abstraction d'un GameObject
	 */
	protected AbstractGameObject() {
		this(-1);
	}

	/**
	 * Ce classe ne doit pas se retrouver ailleurs que dans un extends
	 * <p>
	 * Abstraction d'un GameObject
	 *
	 * @param id id unique de l'object pour les énigmes
	 */
	protected AbstractGameObject(int id) {
		this.bounds = new Rectangle();
		this.id = id;
		this.tiles = new HashMap<>();
	}

	@Override
	public void setDimension(int width, int height) {
		this.bounds.setSize(width, height);
	}

	@Override
	public float getGameObjectWidth() {
		return this.bounds.getWidth();
	}

	@Override
	public float getGameObjectHeight() {
		return this.bounds.getHeight();
	}

	@Override
	public Vector2 getGameObjectPosition() {
		return this.bounds.getPosition(new Vector2());
	}

	@Override
	public void setGameObjectPosition(Vector2 pos) {
		this.bounds.setPosition(pos);
	}

	@Override
	public Array<Float> getTiles(Layer layer) {
		return this.tiles.get(layer);
	}

	@Override
	public void setTiles(Array<Float> texture, Layer layer) {
		this.tiles.put(layer, texture);
	}

	//id

	@Override
	public int getID() {
		return this.id;
	}

	@Override
	public void setID(int id) {
		this.id = id;
	}
}
