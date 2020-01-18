package editor.entity.player;

import api.entity.GameActorTextured;
import api.entity.interfaces.Entity;
import api.entity.interfaces.Living;
import api.enums.Layer;
import api.enums.TypeEntite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.EnumMap;
import java.util.HashMap;

/**
 * Définie un personnage contrôlable : un joueur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.0
 * @see Entity
 * @see api.entity.interfaces.Living
 * @since 2.0
 */
public class Player extends GameActorTextured implements Entity, Living {

	/**
	 * Points de vie maximaux du joueur
	 */
	public final int MAX_PLAYER_PV = 100;
	/**
	 * Point de vie du joueur
	 */
	private int pv;
	/**
	 * ID
	 */
	private int id;
	/**
	 * les tiles (a convertir en int)
	 */
	private HashMap<Layer, Array<Float>> tiles;
	/**
	 * Dimensions de l'object
	 */
	private Rectangle bounds;

	public Player() {
		this(-1);
	}

	/**
	 * @param id ID
	 */
	public Player(int id) {
		this.pv = MAX_PLAYER_PV;
		this.id = id;
		this.bounds = new Rectangle();
		this.tiles = new HashMap<>();
	}

	@Override
	public void interactsWith(Player p) {
		throw new UnsupportedOperationException("node codé. InteractWidth Player");
	}

	@Override
	public int getHP() {
		return this.pv;
	}

	//size

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

	//pos

	@Override
	public Vector2 getGameObjectPosition() {
		return this.bounds.getPosition(new Vector2());
	}

	@Override
	public void setGameObjectPosition(Vector2 pos) {
		this.bounds.setPosition(pos);
	}

	//tiles

	@Override
	public Array<Float> getTiles(Layer layer) {
		return this.tiles.get(layer);
	}

	@Override
	public void setTiles(Array<Float> texture, Layer layer) {
		this.tiles.put(layer, texture);
	}

	@Override
	public EnumMap<TypeEntite, Boolean> getImplements() {
		return null;
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

	//toString

	@Override
	public String toString() {
		return "Player{" + "MAX_PLAYER_PV=" + MAX_PLAYER_PV + ", pv=" + pv + ", id=" + id +
				", tiles=" + tiles + ", bounds=" + bounds + '}';
	}
}