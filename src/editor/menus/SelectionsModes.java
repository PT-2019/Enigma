package editor.menus;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Les modes de sélection des entités
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 31/01/2020
 * @since 5.0 31/01/2020
 */
public enum SelectionsModes {
	/**
	 * Depuis la map ("utiliser cette entité")
	 */
	MAP("carte uniquement"),
	/**
	 * Depuis le menu (la liste des entités/librairie)
	 */
	MENU("menu uniquement"),
	/**
	 * Depuis un popup par exemple d'une entité qui en contient d'autres
	 */
	POPUP("contenu uniquement"),

	//combinaisons (utiles)

	/**
	 * Un nouveau ou un object existant
	 */
	MAP_AND_MENU("carte et menu", MAP, MENU),

	/**
	 * Un objet d'un conteneur
	 */
	MENU_AND_POPUP("menu et contenu", MENU, POPUP),

	/**
	 * Seulement un object existant
	 */
	MAP_AND_POPUP("carte et contenu", MAP, POPUP),

	/**
	 * Peu importe
	 */
	MAP_AND_MENU_AND_POPUP("carte, menu et contenu", MAP, MENU, POPUP),

	/**
	 * Aucune restriction
	 */
	NONE("Spécial"),

	/**
	 * Map, menu ou inventaire
	 */
	ALL("carte, menu et contenu");

	public final ArrayList<SelectionsModes> modes;
	public final String msg;

	SelectionsModes(String msg, SelectionsModes... modes) {
		this.msg = msg;
		this.modes = new ArrayList<>();
		this.modes.addAll(Arrays.asList(modes));
	}

	/**
	 * Retourne si le mode contient un autre mode. (inclut lui même)
	 *
	 * @param mode un mode
	 * @return true si this contient mode.
	 */
	public boolean contains(SelectionsModes mode) {
		return this.modes.contains(mode) || mode.equals(this);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{[" + this.name() + "]");
		for (SelectionsModes mode : modes) {
			sb.append(", [");
			sb.append(mode.name());
			sb.append("]");
		}
		sb.append("}");
		return sb.toString();
	}
}
