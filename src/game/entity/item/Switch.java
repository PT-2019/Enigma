package game.entity.item;

import api.entity.utils.Activatable;
import api.enums.TypeEntite;

import java.util.EnumMap;

/**
 * Un bouton
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 4.0 24/12/2019
 * @since 2.0 24/12/2019
 * @see Activatable
 */
public class Switch extends Activatable {

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
	public EnumMap<TypeEntite, Boolean> getImplements() {
		EnumMap<TypeEntite,Boolean> imp = new EnumMap<>(TypeEntite.class);
		imp.put(TypeEntite.item,true);
		imp.put(TypeEntite.activatable,true);
		imp.put(TypeEntite.lockable,false);
		imp.put(TypeEntite.passage,false);
		return imp;
	}
}
