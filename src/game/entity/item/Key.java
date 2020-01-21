package game.entity.item;

import api.entity.AbstractItem;
import api.entity.types.NeedContainer;
import api.enums.TypeEntite;
import editor.utils.lang.GameLanguage;
import editor.utils.lang.fields.GameFields;

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
public class Key extends AbstractItem implements NeedContainer {

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
	public EnumMap<TypeEntite, Boolean> getImplements() {
		EnumMap<TypeEntite, Boolean> imp = new EnumMap<>(TypeEntite.class);
		imp.put(TypeEntite.item, true);
		imp.put(TypeEntite.lockable, false);
		imp.put(TypeEntite.passage, false);
		imp.put(TypeEntite.activatable, false);
		return imp;
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.KEY);
	}
}
