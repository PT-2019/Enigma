package common.entities.items;

import com.badlogic.gdx.maps.MapProperties;
import common.entities.Item;
import common.entities.types.AbstractItem;
import common.entities.types.Lockable;
import common.entities.types.Passage;
import common.language.GameFields;
import common.language.GameLanguage;
import common.map.model.Room;
import common.save.entities.PlayerSave;
import common.save.entities.SaveKey;
import data.TypeEntity;

import java.util.EnumMap;
import java.util.HashMap;

/**
 * Une porte
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 24/12/2019
 * @see Item
 * @see Lockable
 * @see Passage
 * @since 2.0
 */
public class Door extends AbstractItem implements Passage, Lockable {

	/**
	 * Indique si l'objet est verrouillé
	 */
	private boolean locked;

	/**
	 * Pièce 1
	 */
	private Room room1;

	/**
	 * Pièce 2
	 */
	private Room room2;
	private String atlasName;
	private String atlasPath;

	/**
	 * Crée une porte
	 *
	 * @since 2.0
	 */
	public Door() {
		this(-1);
	}

	/**
	 * Crée une porte avec un id unique pour identifier ses énigmes
	 *
	 * @param id ID
	 * @since 2.0
	 */
	public Door(int id) {
		super(id);
		this.locked = true;
		this.room1 = null;
		this.room2 = null;
	}


	/**
	 * Crée une porte
	 *
	 * @param locked true si l'objet est verrouillé de base, false sinon
	 * @since 2.0
	 */
	public Door(boolean locked) {
		this(-1);
		this.locked = locked;
	}

	//passage

	@Override
	public Room getRoom1() {
		return this.room1;
	}

	@Override
	public Room getRoom2() {
		return this.room2;
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

	//toString

	@Override
	public String toString() {
		return "Door{" + "locked=" + locked + ", room1=" + room1 + ", room2=" + room2 +
				", enigmas=" + enigmas + ", id=" + id + '}';
	}

	@Override
	public EnumMap<TypeEntity, Boolean> getImplements() {
		EnumMap<TypeEntity, Boolean> imp = TypeEntity.emptyMap();
		imp.put(TypeEntity.LOCKABLE, true);
		imp.put(TypeEntity.PASSAGE, true);

		imp.put(TypeEntity.ITEM, true);
		imp.put(TypeEntity.NEED_CONTAINER_MANAGER, true);
		imp.put(TypeEntity.ENIGMA_CONTAINER, true);
		return imp;
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.DOOR);
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
