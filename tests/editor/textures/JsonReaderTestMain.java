package editor.textures;

import common.utils.textures.AtlasReader;
import org.jetbrains.annotations.TestOnly;

public class JsonReaderTestMain {

	/**
	 * Ce main montre les information gardées en mémoire après
	 * la lecture du json.
	 */
	@TestOnly
	public static void main(String[] ignore) {
		System.out.println(AtlasReader.importJson("assets/files/atlas/uiskin.atlas"));
	}

}
