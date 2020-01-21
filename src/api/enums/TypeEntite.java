package api.enums;

import java.util.EnumMap;

public enum TypeEntite {
	container,
	EnigmaContainer,
	content,
	item,
	lockable,
	NeedContainer,
	passage,
	activatable;

	public static EnumMap<TypeEntite, Boolean> emptyMap() {
		EnumMap<TypeEntite, Boolean> map = new EnumMap<>(TypeEntite.class);
		for (TypeEntite type : TypeEntite.values()) {
			map.put(type, false);
		}
		return map;
	}
}
