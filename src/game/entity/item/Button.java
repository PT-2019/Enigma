package game.entity.item;

import api.entity.interfaces.Activatable;
import api.enums.TypeEntite;
import editor.enigma.Enigma;
import editor.entity.player.Player;
import editor.utils.textures.Texture;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;

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
 * @see api.entity.interfaces.Activatable
 */
public class Button extends Activatable {

	/**
	 * Crée un bouton
	 *
	 * @since 2.0
	 */
	public Button() {
		this(false);
	}

	/**
	 * Crée un bouton
	 *
	 * @param activated true si l'objet est activé de base, false sinon
	 * @since 2.1
	 */
	public Button(boolean activated) {
		super(activated);
	}

	//toString

	@Override
	public String toString() {
		return "Button{" + "activated=" + activated + ", enigmas=" + enigmas + ", id=" + id + '}';
	}

	@Override
	public EnumMap<TypeEntite, Boolean> getImplements() {
		EnumMap<TypeEntite,Boolean> imp = new EnumMap<>(TypeEntite.class);
		imp.put(TypeEntite.activatable,true);
		imp.put(TypeEntite.item,true);
		imp.put(TypeEntite.lockable,false);
		imp.put(TypeEntite.passage,false);
		return imp;
	}
}
