package game.entity.item;

import api.entity.AbstractItem;
import api.entity.types.Container;
import api.entity.types.Lockable;
import api.enums.TypeEntite;
import editor.utils.lang.GameLanguage;
import editor.utils.lang.fields.GameFields;

import java.util.EnumMap;

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

	/**
	 * Crée une bibliothèque
	 *
	 * @since 4.0
	 */
	public Library() {
		this(-1);
	}

	/**
	 * Crée une bibliothèque avec un id unique pour identifier ses énigmes
	 *
	 * @param id ID
	 * @since 4.0
	 */
	public Library(int id) {
		super(id);
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
	public EnumMap<TypeEntite, Boolean> getImplements() {
		EnumMap<TypeEntite, Boolean> imp = TypeEntite.emptyMap();
		imp.put(TypeEntite.LOCKABLE, true);
		imp.put(TypeEntite.CONTAINER, true);

		imp.put(TypeEntite.ITEM, true);
		imp.put(TypeEntite.NEED_CONTAINER, true);
		imp.put(TypeEntite.ENIGMA_CONTAINER, true);
		return imp;
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.LIBRARY);
	}
}
