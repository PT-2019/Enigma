package editor.entity.actions;

import api.entity.AbstractGameObject;
import api.enums.TypeEntity;
import editor.utils.lang.GameLanguage;
import editor.utils.lang.fields.HUDFields;

import java.util.EnumMap;

/**
 * Sortie du jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 26/01/2020
 * @since 5.0 26/01/2020
 */
public class GameExit extends AbstractGameObject {

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
		return TypeEntity.emptyMap();
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(HUDFields.GAME_EXIT);
	}
}
