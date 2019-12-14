package editor.datas;

public enum EntitiesCategories {
	ROOMS("Salles"),
	DECORS("Décors"),
	ITEMS("Objects"),
	ENTITIES("Personnages"),
	ACTIONS("Actions");

	public final String name;

	EntitiesCategories(String name) {
		this.name = name;
	}

}
