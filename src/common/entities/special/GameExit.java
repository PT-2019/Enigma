package common.entities.special;

import common.entities.types.AbstractGameObject;
import common.entities.types.NeedContainerManager;
import common.language.GameLanguage;
import common.language.HUDFields;
import data.TypeEntity;

import java.util.EnumMap;

/**
 * Sortie du jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 26/01/2020
 * @since 5.0 26/01/2020
 */
public class GameExit extends AbstractGameObject implements NeedContainerManager {

	/**
	 * Crée la sortie
	 *
	 * @since 2.0
	 */
	public GameExit() {
		this(-1);
	}

	/**
	 * Crée la sortie avec un id unique pour identifier ses énigmes
	 *
	 * @param id ID
	 * @since 2.2
	 */
	public GameExit(int id) {
		super(id);
	}

	@Override
	public String toString() {
		return "GameExit{" + "id=" + id + '}';
	}

	@Override
	public EnumMap<TypeEntity, Boolean> getImplements() {
		EnumMap<TypeEntity, Boolean> imp = TypeEntity.emptyMap();
		imp.put(TypeEntity.NEED_CONTAINER_MANAGER, true);
		return imp;
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(HUDFields.GAME_EXIT);
	}
}
