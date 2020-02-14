package common.entities.items;

import com.badlogic.gdx.maps.MapProperties;
import common.entities.Item;
import common.entities.types.AbstractItem;
import common.entities.types.Container;
import common.entities.types.Lockable;
import common.language.GameFields;
import common.language.GameLanguage;
import common.save.entities.PlayerSave;
import common.save.entities.SaveKey;
import data.TypeEntity;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * Un coffre
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 24/12/2019
 * @see Lockable
 * @see common.entities.Item
 * @since 2.0
 */
public class Chest extends AbstractItem implements Lockable, Container {

	/**
	 * Indique si l'objet est verrouillé
	 */
	private boolean locked;
	private ArrayList<Item> items;
	private String atlasPath;
	private String atlasName;

	/**
	 * Un coffre
	 *
	 * @since 2.0
	 */
	public Chest() {
		this(-1);
	}

	/**
	 * Crée un coffre avec un id unique pour identifier ses énigmes
	 *
	 * @param id ID
	 * @since 2.0
	 */
	public Chest(int id) {
		super(id);
		this.locked = false;
		this.items = new ArrayList<>();
	}

	/**
	 * @param locked true si l'objet est verrouillé de base, false sinon
	 */
	public Chest(boolean locked) {
		this(-1);
		this.locked = locked;
		this.items = new ArrayList<>();
	}

	/**
	 * @param locked true si l'objet est verrouillé de base, false sinon
	 * @param id     ID
	 */
	public Chest(boolean locked, int id) {
		this(id);
		this.locked = locked;
		this.items = new ArrayList<>();
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

	//toString

	@Override
	public String toString() {
		return "Chest{" + "locked=" + locked + ", enigmas=" + enigmas + ", id=" + id + '}';
	}

	/**
	 * Chemin vers l'atlas
	 *
	 * @return Chemin vers l'atlas
	 */
	@Override
	public String getAtlasPath() {
		return this.atlasPath;
	}

	/**
	 * Nom de la région dans l'atlas
	 *
	 * @return Nom de la région dans l'atlas
	 */
	@Override
	public String getAtlasRegionName() {
		return this.atlasName;
	}

	/**
	 * Définie les données de l'atlas
	 *
	 * @param atlasPath Chemin vers l'atlas
	 * @param atlasName Nom de la région dans l'atlas
	 */
	@Override
	public void setAtlas(String atlasPath, String atlasName) {
		this.atlasPath = atlasPath;
		this.atlasName = atlasName;
	}

	@Override
	public EnumMap<TypeEntity, Boolean> getImplements() {
		EnumMap<TypeEntity, Boolean> imp = TypeEntity.emptyMap();
		imp.put(TypeEntity.LOCKABLE, true);
		imp.put(TypeEntity.CONTAINER, true);

		imp.put(TypeEntity.ITEM, true);
		imp.put(TypeEntity.NEED_CONTAINER_MANAGER, true);
		imp.put(TypeEntity.ENIGMA_CONTAINER, true);
		return imp;
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.CHEST);
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

	@Override
	public HashMap<SaveKey,String> getSave(){
		HashMap<SaveKey, String> save = new HashMap<>();
		save.put(PlayerSave.LOCKED, String.valueOf(this.locked));
		save.put(PlayerSave.KEY, String.valueOf(this.atlasName));
		save.put(PlayerSave.ATLAS, String.valueOf(this.atlasPath));
		return save;
	}

	@Override
	public void load(MapProperties data) {
		this.locked = Boolean.parseBoolean(data.get(PlayerSave.LOCKED.getKey(), String.class));
		this.atlasPath = data.get(PlayerSave.ATLAS.getKey(),String.class);
		this.atlasName = data.get(PlayerSave.KEY.getKey(),String.class);
	}
}
