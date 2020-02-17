package common.entities.consumable;

import com.badlogic.gdx.maps.MapProperties;
import common.entities.types.AbstractConsumable;
import common.entities.types.NeedContainer;
import common.language.GameFields;
import common.language.GameLanguage;
import common.save.entities.PlayerSave;
import common.save.entities.SaveKey;
import common.save.entities.SaveTiles;
import data.TypeEntity;

import java.util.EnumMap;
import java.util.HashMap;

/**
 * Une clef
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 24/12/2019
 * @since 4.0 24/12/2019
 */
public class Key extends AbstractConsumable implements NeedContainer {

	/**
	 * Crée un clef
	 *
	 * @since 4.0
	 */
	public Key() {
		this(-1);
	}

	/**
	 * Crée un clef avec un id unique pour identifier ses énigmes
	 *
	 * @param id ID
	 * @since 4.0
	 */
	public Key(int id) {
		super(id);
	}

	//toString

	@Override
	public String toString() {
		return "Key{" + "enigmas=" + enigmas + ", id=" + id + '}';
	}

	@Override
	public EnumMap<TypeEntity, Boolean> getImplements() {
		EnumMap<TypeEntity, Boolean> imp = TypeEntity.emptyMap();
		imp.put(TypeEntity.CONSUMABLE, true);
		imp.put(TypeEntity.NEED_CONTAINER, true);
		imp.put(TypeEntity.NEED_CONTAINER_MANAGER, true);
		imp.put(TypeEntity.ENIGMA_CONTAINER, true);
		return imp;
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.KEY);
	}

	@Override
	public HashMap<SaveKey, String> getSave() {
		HashMap<SaveKey, String> save = new HashMap<>();
		save.put(PlayerSave.ALT_TILES, SaveTiles.save(this.altTiles));
		return save;
	}

	@Override
	public void load(MapProperties data) {
		this.altTiles = SaveTiles.load(data.get(PlayerSave.ALT_TILES.getKey(), String.class));
	}
}
