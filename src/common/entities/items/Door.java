package common.entities.items;

import com.badlogic.gdx.maps.MapProperties;
import common.entities.Item;
import common.entities.types.AbstractLockable;
import common.entities.types.Lockable;
import common.language.GameFields;
import common.language.GameLanguage;
import common.save.entities.PlayerSave;
import common.save.entities.SaveKey;
import common.save.entities.SaveTiles;
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
 * @version 6.0
 * @see Item
 * @see Lockable
 * @since 2.0
 */
public class Door extends AbstractLockable {

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
		super(id, true);
	}


	/**
	 * Crée une porte
	 *
	 * @param locked true si l'objet est verrouillé de base, false sinon
	 * @since 2.0
	 */
	public Door(boolean locked) {
		super(-1, locked);
	}

	//toString


	@Override
	public String toString() {
		return "Door{" + "locked=" + locked + ", enigmas=" + enigmas + ", id=" + id + '}';
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
	public HashMap<SaveKey, String> getSave() {
		HashMap<SaveKey, String> save = new HashMap<>();
		save.put(PlayerSave.LOCKED, String.valueOf(this.locked));
		save.put(PlayerSave.ALT_TILES, SaveTiles.save(this.altTiles));
		return save;
	}

	@Override
	public void load(MapProperties data) {
		this.locked = Boolean.parseBoolean(data.get(PlayerSave.LOCKED.getKey(), String.class));
		this.altTiles = SaveTiles.load(data.get(PlayerSave.ALT_TILES.getKey(), String.class));
	}
}
