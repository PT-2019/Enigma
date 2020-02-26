package common.entities.items;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.utils.Array;
import common.enigmas.TileEventEnum;
import common.enigmas.reporting.EnigmaReport;
import common.entities.Item;
import common.entities.players.PlayerGame;
import common.entities.types.AbstractLockable;
import common.entities.types.ChangeStateReport;
import common.entities.types.Container;
import common.language.GameFields;
import common.language.GameLanguage;
import common.save.entities.PlayerSave;
import common.save.entities.SaveInventory;
import common.save.entities.SaveKey;
import data.Layer;
import data.TypeEntity;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

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
public class Library extends AbstractLockable implements Container {

	private ArrayList<Item> items;

	/**
	 * Id des items chargés
	 */
	private ArrayList<Integer> load;

	/**
	 * Crée une bibliothèque
	 *
	 * @since 4.0
	 */
	public Library() {
		super(-1, false);
		this.items = new ArrayList<>();
	}

	/**
	 * Crée une bibliothèque avec un id unique pour identifier ses énigmes
	 *
	 * @param id ID
	 * @since 4.0
	 */
	public Library(int id) {
		super(id, false);
		this.items = new ArrayList<>();
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

	//container

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

	//save

	@Override
	public HashMap<SaveKey, String> getSave() {
		HashMap<SaveKey, String> save = new HashMap<>();
		save.put(PlayerSave.LOCKED, String.valueOf(this.locked));
		//save des ids des éléments de l'inventaire
		save.put(PlayerSave.INVENTORY, SaveInventory.save(this.items));
		return save;
	}

	@Override
	public void load(MapProperties data) {
		this.locked = Boolean.parseBoolean(data.get(PlayerSave.LOCKED.getKey(), String.class));
		this.altTiles = new HashMap<>();
		for (Map.Entry<Layer, Array<Float>> entry : this.getTiles().entrySet()) {
			this.altTiles.put(entry.getKey().name(), entry.getValue());
		}
		this.load = SaveInventory.load(data.get(PlayerSave.INVENTORY.getKey(), String.class));
	}

	//alt
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
			} else {
				return new EnigmaReport(ChangeStateReport.OPEN, true, this);
			}
		}
		return null;
	}

	@Override
	public boolean needReloadAfterStateChange() {
		return true;
	}

	@Override
	public ArrayList<Integer> getLoadItems() {
		return this.load;
	}
}
