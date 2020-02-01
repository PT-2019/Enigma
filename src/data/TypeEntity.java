package data;

import java.util.EnumMap;

public enum TypeEntity {
	ALL,

	ENIGMA_CONTAINER,//items
	ITEM,//items

	CONSUMABLE, //key...
	NEED_CONTAINER,//key...

	NEED_CONTAINER_MANAGER, //items+living

	CONTAINER,//chest, library
	LOCKABLE,//chest, library

	CONTENT,//book, pane

	PASSAGE,//door...

	ACTIVATABLE,//button

	LIVING,//npc, players, monsters ,peut avoir un nom
	NPC,//npc ,si npc alors peut devenir player
	PLAYER,
	MONSTER,

	CONTAINER_MANAGER,//room
	;

	/**
	 * Retourne une enumMap vide
	 *
	 * @return une enumMap vide
	 */
	public static EnumMap<TypeEntity, Boolean> emptyMap() {
		EnumMap<TypeEntity, Boolean> map = new EnumMap<>(TypeEntity.class);
		for (TypeEntity type : TypeEntity.values()) {
			map.put(type, false);
		}
		map.put(ALL, true);
		return map;
	}
}
