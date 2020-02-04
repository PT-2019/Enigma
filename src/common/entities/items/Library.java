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
 * Une bibliothèque
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 24/12/2019
 * @since 4.0 24/12/2019
 */
public class Library extends AbstractItem implements Container, Lockable {

	/**
	 * état de la porte
	 */
	private boolean locked;

	private ArrayList<Item> items;

	/**
	 * Crée une bibliothèque
	 *
	 * @since 4.0
	 */
	public Library() {
		this(-1);
		this.items = new ArrayList<>();
	}

	/**
	 * Crée une bibliothèque avec un id unique pour identifier ses énigmes
	 *
	 * @param id ID
	 * @since 4.0
	 */
	public Library(int id) {
		super(id);
		this.items = new ArrayList<>();
	}

	//lock

	@Override
	public void lock() {
		this.locked = true;
	}

	@Override
	public boolean isLocked() {
		return this.locked;
	}

	@Override
	public void unlock() {
		this.locked = false;
	}

	//toString

	@Override
	public String toString() {
		return "Library{" + "enigmas=" + enigmas + ", id=" + id + '}';
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
		return GameLanguage.gl.get(GameFields.LIBRARY);
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
		return save;
	}

	@Override
	public void load(MapProperties data) {
		this.locked = Boolean.parseBoolean(data.get(PlayerSave.LOCKED.getKey(), String.class));
	}
}
