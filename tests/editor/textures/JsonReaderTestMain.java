package editor.textures;

import org.jetbrains.annotations.TestOnly;

public class JsonReaderTestMain {

	/**
	 * Ce main montre les information gardées en mémoire après
	 * la lecture du json.
	 */
	@TestOnly
	public static void main(String[] ignore) {
		System.out.println(JsonReader.importJson("assets/files/atlas/uiskin.atlas"));
	}

}
