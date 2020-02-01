package common.entities.players;

import api.libgdx.actor.GameActorTextured;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import common.entities.Entity;
import common.entities.Item;
import common.entities.special.Inventory;
import common.entities.types.Living;
import common.language.GameFields;
import common.language.GameLanguage;
import data.Layer;
import data.TypeEntity;

import java.util.EnumMap;
import java.util.HashMap;

/**
 * Définie un personnage contrôlable : un joueur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see common.entities.Entity
 * @see common.entities.types.Living
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

	/**
	 * Nom du joueur
	 */
	private String name;

	/**
	 * Inventaire
	 */
	private Inventory inventory;

	/**
	 * Main
	 */
	private Item hand;

	public Player() {
		this(-1);
	}

	/**
	 * @param name Nom du joueur
	 */
	public Player(String name) {
		this(-1, name);
	}

	/**
	 * @param id ID
	 */
	public Player(int id) {
		super();
		this.pv = MAX_PLAYER_PV;
		this.id = id;
		this.bounds = new Rectangle();
		this.tiles = new HashMap<>();
		this.inventory = new Inventory();
		this.hand = null;
	}

	/**
	 * @param id   ID
	 * @param name Nom du joueur
	 */
	public Player(int id, String name) {
		super();
		this.pv = MAX_PLAYER_PV;
		this.id = id;
		this.bounds = new Rectangle();
		this.tiles = new HashMap<>();
		this.name = name;
		this.inventory = new Inventory();
		this.hand = null;
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
	public EnumMap<TypeEntity, Boolean> getImplements() {
		EnumMap<TypeEntity, Boolean> imp = TypeEntity.emptyMap();
		imp.put(TypeEntity.PLAYER, true);

		imp.put(TypeEntity.LIVING, true);
		imp.put(TypeEntity.CONTAINER, true);
		imp.put(TypeEntity.NEED_CONTAINER_MANAGER, true);
		return imp;
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

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	//toString

	@Override
	public String toString() {
		return "Player{" + "MAX_PLAYER_PV=" + MAX_PLAYER_PV + ", pv=" + pv + ", id=" + id +
				", tiles=" + tiles + ", bounds=" + bounds + '}';
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.PLAYER);
	}

	public Item getItemInHand() {
		return hand;
	}

	public void setItemInHand(Item hand) {
		this.hand = hand;
	}

	public boolean holdSomething() {
		return (this.hand != null);
	}

	public Inventory getInventory() {
		return inventory;
	}
}