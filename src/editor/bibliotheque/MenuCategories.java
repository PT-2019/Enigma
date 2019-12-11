package editor.bibliotheque;

public enum MenuCategories {
	ROOMS("Salles"),
	DECORS("Décors"),
	ITEMS("Objects"),
	ENTITIES("Personnages"),
	ACTIONS("Actions");

	public final String name;

	MenuCategories(String name){
		this.name = name;
	}

}
