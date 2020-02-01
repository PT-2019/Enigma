package common.entities.players;

import api.libgdx.actor.GameActorTextured;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import common.entities.Entity;
import common.entities.Item;
import common.entities.types.Container;
import common.entities.types.Living;
import common.language.GameFields;
import common.language.GameLanguage;
import common.save.entities.PlayerSave;
import common.save.entities.SaveKey;
import data.Layer;
import data.TypeEntity;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * Un personnage non joueur (NPC)
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class NPC extends GameActorTextured implements Entity, Living, Container {

	/**
	 * Points de vie maximaux du joueur
	 */
	public final int MAX_NPC_PV = 10000;
	/**
	 * Point de vie du NPC
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
	 * Nom du npc
	 */
	private String name;

	private String json, key;

	/**
	 * True si c'est un héro possible
	 */
	private boolean hero;

	private ArrayList<Item> items;

	public NPC() {
		this(-1);
	}

	/**
	 * @param name Nom du npc
	 */
	public NPC(String name) {
		this(-1);
		this.name = name;
		this.items = new ArrayList<>();
	}

	/**
	 * @param id ID
	 */
	public NPC(int id) {
		this.pv = MAX_NPC_PV;
		this.id = id;
		this.bounds = new Rectangle();
		this.tiles = new HashMap<>();
		this.items = new ArrayList<>();
	}

	/**
	 * @param id   ID
	 * @param name Nom du joueur
	 */
	public NPC(int id, String name) {
		this.pv = MAX_NPC_PV;
		this.id = id;
		this.bounds = new Rectangle();
		this.tiles = new HashMap<>();
		this.name = name;
		this.items = new ArrayList<>();
	}

	@Override
	public void interactsWith(Player p) {
		throw new UnsupportedOperationException("node codé. InteractWidth NPC");
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
		imp.put(TypeEntity.NPC, true);

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

	public boolean isHero() {
		return hero;
	}

	public void setHero(boolean hero) {
		this.hero = hero;
	}

	//toString

	@Override
	public String toString() {
		return "NPC{" + "MAX_NPC_PV=" + MAX_NPC_PV + ", pv=" + pv + ", id=" + id +
				", tiles=" + tiles + ", bounds=" + bounds + ", name='" + name + '\'' + ", hero=" + hero + '}';
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.NPC);
	}

	@Override
	public HashMap<SaveKey, String> getSave() {
		HashMap<SaveKey, String> save = new HashMap<>();
		save.put(PlayerSave.KEY, this.key);
		save.put(PlayerSave.JSON, this.json);
		save.put(PlayerSave.HERO, String.valueOf(this.hero));
		save.put(PlayerSave.INVENTORY, this.items.toString());
		save.put(PlayerSave.NAME, this.name);
		return save;
	}

	@Override
	public void load(MapProperties data) {
		this.key = data.get(PlayerSave.KEY.getKey(), String.class);
		this.json = data.get(PlayerSave.JSON.getKey(), String.class);
		this.hero = Boolean.valueOf(data.get(PlayerSave.HERO.getKey(), String.class));
		String list = data.get(PlayerSave.INVENTORY.getKey(), String.class);
		this.name = data.get(PlayerSave.NAME.getKey(), String.class);
	}

	/**
	 * Définit les valeurs du json (+clef)
	 *
	 * @param json json
	 * @param key  clef pour retrouver l'entitée dans le json
	 */
	public void setJson(String json, String key) {
		this.json = json;
		this.key = key;
	}

	@Override
	public boolean addItem(Item item) {
		return this.items.add(item);
	}

	@Override
	public boolean removeItem(Item item) {
		return this.items.remove(item);
	}

	@Override
	public ArrayList<Item> getItems() {
		return this.items;
	}
}
