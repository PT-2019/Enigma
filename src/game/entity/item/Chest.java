package game.entity.item;

import api.entity.interfaces.AbstractItem;
import api.entity.interfaces.Item;
import api.entity.interfaces.Lockable;
import editor.enigma.Enigma;
import editor.entity.player.Player;
import editor.utils.textures.Texture;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Un coffre
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 4.0 24/12/2019
 * @since 2.0
 *
 * @see api.entity.interfaces.Lockable
 * @see api.entity.interfaces.Item
 */
public class Chest extends AbstractItem implements Lockable {

	/**
	 * Indique si l'objet est verrouillé
	 */
	private boolean locked;

	/**
	 * Un coffre
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
}
