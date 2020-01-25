package api.enums;

import java.util.EnumMap;

public enum TypeEntite {
	ENIGMA_CONTAINER,//items
	ITEM,//items
	NEED_CONTAINER,//items, living

	CONTAINER,//chest, library
	LOCKABLE,//chest, library

	CONTENT,//livre, panneau

	PASSAGE,//porte...

	ACTIVATABLE,//button

	LIVING,//npc, players, monsters ,peut avoir un nom
	NPC,//npc ,si npc alors peut devenir player
	PLAYER,
	MONSTER,
	ROOM,
	;
	/**
	 * Retourne une enumMap vide
	 * @return une enumMap vide
	 */
	public static EnumMap<TypeEntite, Boolean> emptyMap() {
		EnumMap<TypeEntite, Boolean> map = new EnumMap<>(TypeEntite.class);
		for (TypeEntite type : TypeEntite.values()) {
			map.put(type, false);
		}
		return map;
	}
}
