package editor.utils;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class JsonFile {//todo: serializable ?

	private final String image;
	private final HashMap<String, Point> map;
	private final HashMap<String,Image> loadedSubTextures;
	private final String[] jsonFile;

	public JsonFile(String imagePath, HashMap<String, Point> mapIndex, String[] jsonFile) {
		this.image = imagePath;
		this.map = mapIndex;
		this.jsonFile = jsonFile;
		this.loadedSubTextures = new HashMap<>();
	}

	public Image getTexture(String name) {
		if(loadedSubTextures.containsKey(name))
			return loadedSubTextures.get(name);

		return JsonSubTexture.getSubTexture(jsonFile,image, map.get(name));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("JsonFile\n" + "image='" + image + "'\n" + "map=\n");
		//print map
		for (Map.Entry<String, Point> entry:this.map.entrySet()) {
			sb.append(entry.getKey());
			sb.append(" ");
			sb.append(entry.getValue().x);
			sb.append(",");
			sb.append(entry.getValue().y);
			sb.append("\n");
		}
		return sb.toString();
	}
}
