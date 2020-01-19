package editor.utils.lang;

public enum Language {
	FRENCH("Français","assets/lang/french.json", false),
	ENGLISH("English","assets/lang/english.json");

	public final String name;
	public final String json;
	public final boolean isLong;

	Language(String name, String json){
		this(name, json, false);
	}

	Language(String name, String json, boolean isLong){
		this.name= name;
		this.json = json;
		this.isLong = isLong;
	}
}
