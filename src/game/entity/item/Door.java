package game.entity.item;

import api.entity.AbstractItem;
import api.entity.Item;
import api.entity.types.Lockable;
import api.entity.types.Passage;
import editor.entity.map.Room;

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

	//toString

	@Override
	public String toString() {
		return "Door{" + "locked=" + locked + ", room1=" + room1 + ", room2=" + room2 +
				", enigmas=" + enigmas + ", id=" + id + '}';
	}
}