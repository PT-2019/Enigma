package common.entities.items;

import com.badlogic.gdx.maps.MapProperties;
import common.entities.types.Activatable;
import common.language.GameFields;
import common.language.GameLanguage;
import common.save.entities.PlayerSave;
import common.save.entities.SaveKey;
import data.TypeEntity;

import java.util.EnumMap;
import java.util.HashMap;

/**
 * Un bouton
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 24/12/2019
 * @see Activatable
 * @since 2.0 24/12/2019
 */
public class Switch extends Activatable {

	private String atlasPath;
	private String atlasName;

	/**
	 * Crée un levier
	 *
	 * @since 2.0
	 */
	public Switch() {
		this(-1);
	}

	/**
	 * Crée un levier avec un id unique pour identifier ses énigmes
	 *
	 * @param id ID
	 * @since 2.0
	 */
	public Switch(int id) {
		super(false, id);
	}

	/**
	 * Crée un levier
	 *
	 * @param activated true si l'objet est activé de base, false sinon
	 * @since 2.1
	 */
	public Switch(boolean activated) {
		super(activated, -1);
	}

	/**
	 * @param activated true si l'objet est activé de base, false sinon
	 * @param id        ID
	 */
	public Switch(boolean activated, int id) {
		super(activated, id);
	}

	//toString

	@Override
	public String toString() {
		return "Switch{" + "activated=" + activated + ", enigmas=" + enigmas + ", id=" + id + '}';
	}

	@Override
	public EnumMap<TypeEntity, Boolean> getImplements() {
		EnumMap<TypeEntity, Boolean> imp = TypeEntity.emptyMap();
		imp.put(TypeEntity.ACTIVATABLE, true);

		imp.put(TypeEntity.ITEM, true);
		imp.put(TypeEntity.NEED_CONTAINER_MANAGER, true);
		imp.put(TypeEntity.ENIGMA_CONTAINER, true);
		return imp;
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.SWITCH);
	}

	@Override
	public HashMap<SaveKey,String> getSave(){
		HashMap<SaveKey, String> save = new HashMap<>();
		save.put(PlayerSave.ACTIVATED, String.valueOf(this.activated));
		save.put(PlayerSave.KEY, String.valueOf(this.atlasName));
		save.put(PlayerSave.ATLAS, String.valueOf(this.atlasPath));
		return save;
	}

	@Override
	public void load(MapProperties data) {
		this.activated = Boolean.parseBoolean(data.get(PlayerSave.ACTIVATED.getKey(), String.class));
		this.atlasPath = data.get(PlayerSave.ATLAS.getKey(),String.class);
		this.atlasName = data.get(PlayerSave.KEY.getKey(),String.class);
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
}
