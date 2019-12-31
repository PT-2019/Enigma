package editor.utils.lang;

import api.utils.Utility;
import com.badlogic.gdx.utils.Json;

import java.util.HashMap;

/**
 * Charger le json du langage par default.
 * Pouvoir changer de langage.
 *
 * Phase de tests.
 */
public class GameLanguage implements EnigmaLanguage {

	public static final Language DEFAULT = Language.ENGLISH;

	public static GameLanguage gl;

	private static HashMap<Language, EnigmaLanguage> languages = new HashMap<>();

	private EnigmaLanguage l;

	private Language language;

	private GameLanguage(){
		this.setLanguage(DEFAULT);
	}

	public static void init(){
		if(gl == null){
			gl = new GameLanguage();
		}
	}

	public void setLanguage(Language language) {
		this.language = language;

		if(!languages.containsKey(language)){
			//load from json
			Json j = new Json();
			l = j.fromJson(LanguageSerialization.class, Utility.loadFile(language.json));

			languages.put(language, l);
		} else {
			l = languages.get(language);
		}
	}

	public Language getLanguage() { return language; }

	@Override
	public String getPlayButton() { return l.getPlayButton(); }

	@Override
	public String getEditorButton() { return l.getEditorButton(); }

	@Override
	public String getRunningMessage() {
		return l.getRunningMessage();
	}

	@Override
	public String getCreate() { return l.getCreate(); }

	@Override
	public String getOpen() { return l.getOpen(); }

	@Override
	public String getSave() { return l.getSave(); }

	@Override
	public String getUndo() { return l.getUndo();	}

	@Override
	public String getRedo() { return l.getRedo(); }

	@Override
	public String getBrush() { return l.getBrush(); }

	@Override
	public String getEraser() { return l.getEraser(); }

	@Override
	public String getMove() { return l.getMove(); }
}
