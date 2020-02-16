package common.entities.players;

import api.libgdx.actor.GameActorTextured;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import common.entities.Entity;
import common.entities.Item;
import common.entities.special.inventory.Inventory;
import common.entities.types.Container;
import common.entities.types.Living;
import common.language.GameFields;
import common.language.GameLanguage;
import data.Layer;
import data.TypeEntity;

import java.util.ArrayList;
import java.util.Arrays;
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
public class Player extends GameActorTextured implements Entity, Living, Container {

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
	 * Main droite
	 */
	private Item rightHand;

	/**
	 * Main gauche
	 */
	private Item leftHand;

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

	/**
	 * Obtenir l'objet dans la main droite
	 * @return Objet dans la main droite, null si il n'y en a pas
	 */
	public Item getItemInRightHand() {
		return rightHand;
	}

	/**
	 * Obtenir l'objet dans la main gauche
	 * @return Objet dans la main gauche, null si il n'y en a pas
	 */
	public Item getItemInLeftHand() {
		return leftHand;
	}

	/**
	 * Définir l'objet dans la main droite
	 * null pour dire qu'il ne tien rien
	 *
	 * @param item Objet dans la main droite
	 */
	public void setItemInRightHand(Item item) {
		Item tmp = this.rightHand;
		this.rightHand = item;
		if(tmp != null)
			this.addItem(tmp);
	}

	/**
	 * Définir l'objet dans la main gauche
	 * null pour dire qu'il ne tien rien
	 *
	 * @param item Objet dans la main gauche
	 */
	public void setItemInLeftHand(Item item) {
		Item tmp = this.leftHand;
		this.leftHand = item;
		if(tmp != null)
			this.addItem(tmp);
	}

	/**
	 * Est-ce que le joueur tiens un objet dans sa main droite
	 * @return true s'il tiens un objet dans sa main droite, false sinon
	 */
	public boolean holdItemInRightHand() {
		return (this.rightHand != null);
	}

	/**
	 * Est-ce que le joueur tiens un objet dans sa main gauche
	 * @return true s'il tiens un objet dans sa main gauche, false sinon
	 */
	public boolean holdItemInLeftHand() {
		return (this.leftHand != null);
	}

	/**
	 * Obtenir l'inventaire
	 * @return Inventaire
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * Ajoute un item
	 *
	 * @param item un item
	 * @return true si ajouté sans problèmes
	 */
	@Override
	public boolean addItem(Item item) {
		if(!this.inventory.isFull())
			this.inventory.add(item);
		else if(!this.holdItemInRightHand())
			this.setItemInRightHand(item);
		else if(!this.holdItemInLeftHand())
			this.setItemInLeftHand(item);
		else
			return false;

		return true;
	}

	/**
	 * Retire un item
	 *
	 * @param item un item
	 * @return true si retiré sans problèmes
	 */
	@Override
	public boolean removeItem(Item item) {
		this.inventory.remove(item);
		return true;
	}

	/**
	 * Retourne les items contenus
	 *
	 * @return retourne les items contenus
	 */
	@Override
	public ArrayList<Item> getItems() {
		return new ArrayList<>(Arrays.asList(this.inventory.getItems()));
	}
}