package game.entity.item;

import api.entity.AbstractItem;
import api.entity.types.Container;
import api.entity.types.Lockable;
import api.entity.types.NeedContainerManager;
import api.enums.TypeEntity;
import editor.utils.lang.GameLanguage;
import editor.utils.lang.fields.GameFields;

import java.util.EnumMap;

/**
 * Un coffre
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 24/12/2019
 * @see Lockable
 * @see api.entity.Item
 * @since 2.0
 */
public class Chest extends AbstractItem implements Lockable, Container {

	/**
	 * Indique si l'objet est verrouillé
	 */
	private boolean locked;

	/**
	 * Un coffre
	 *
	 * @since 2.0
	 */
	public Chest() {
		this(-1);
	}

	/**
	 * Crée un coffre avec un id unique pour identifier ses énigmes
	 *
	 * @param id ID
	 * @since 2.0
	 */
	public Chest(int id) {
		super(id);
		this.locked = false;
	}

	/**
	 * @param locked true si l'objet est verrouillé de base, false sinon
	 */
	public Chest(boolean locked) {
		this(-1);
		this.locked = locked;
	}

	/**
	 * @param locked true si l'objet est verrouillé de base, false sinon
	 * @param id     ID
	 */
	public Chest(boolean locked, int id) {
		this(id);
		this.locked = locked;
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

	//toString

	@Override
	public String toString() {
		return "Chest{" + "locked=" + locked + ", enigmas=" + enigmas + ", id=" + id + '}';
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
		return GameLanguage.gl.get(GameFields.CHEST);
	}
}
