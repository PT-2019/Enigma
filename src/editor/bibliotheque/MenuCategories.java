package editor.bibliotheque;

public enum MenuCategories {
	SALLES("Salles"),
	DECORS("Décors"),
	PERSONNAGES("Personnages"),
	ACTIONS("Actions");

	public final String name;

	MenuCategories(String name){
		this.name = name;
	}

}
