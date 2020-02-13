package common.entities.consumable;

import common.entities.types.AbstractConsumable;
import common.entities.types.NeedContainer;
import common.language.GameFields;
import common.language.GameLanguage;
import data.TypeEntity;

import java.util.EnumMap;

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
	private String atlasPath;
	private String atlasName;

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
