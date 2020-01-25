package game.entity.item;

import api.entity.utils.Activatable;
import api.enums.TypeEntite;
import editor.utils.lang.GameLanguage;
import editor.utils.lang.fields.GameFields;

import java.util.EnumMap;

/**
 * Un bouton
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 24/12/2019
 * @see Activatable
 * @since 2.0 24/12/2019
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
		EnumMap<TypeEntite, Boolean> imp = TypeEntite.emptyMap();
		imp.put(TypeEntite.ACTIVATABLE, true);

		imp.put(TypeEntite.ITEM, true);
		imp.put(TypeEntite.NEED_CONTAINER, true);
		imp.put(TypeEntite.ENIGMA_CONTAINER, true);
		return imp;
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.BUTTON);
	}
}
