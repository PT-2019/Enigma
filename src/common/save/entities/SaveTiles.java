package common.save.entities;

import api.utils.Utility;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

/**
 * Save des tiles
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 15/02/2020
 * @since 6.0 15/02/2020
 */
public final class SaveTiles {

	public static String save(HashMap<String, Array<Float>> tiles) {
		if (tiles == null) return "";
		StringBuilder sb = new StringBuilder("{");

		for (Map.Entry<String, Array<Float>> entry : tiles.entrySet()) {
			sb.append(entry.getKey());
			sb.append(":[");
			//tiles
			for (Float val : new Array.ArrayIterator<>(entry.getValue())) {
				sb.append(MathUtils.round(val));
				sb.append(",");
			}
			sb.append("]");
		}

		sb.append("}");
		return sb.toString();
	}

	public static HashMap<String, Array<Float>> load(String tiles) {
		if (tiles == null || tiles.isEmpty() || Utility.isBlank(tiles)) return null;
		HashMap<String, Array<Float>> map = new HashMap<>();

		if (tiles.charAt(0) != '{') return null;

		StringBuilder name, number;
		Array<Float> tilesArray;
		for (int i = 1; i < tiles.length() - 1; i++) {
			name = new StringBuilder();
			tilesArray = new Array<>();
			//lis le nom du niveau
			for (char r = tiles.charAt(i); r != ':'; i++, r = tiles.charAt(i)) {
				name.append(r);
				if (i + 1 > tiles.length()) break;
			}
			if (i + 1 > tiles.length()) break;
			//saute : et [
			i += 2;
			for (char r = tiles.charAt(i); r != ']'; i++, r = tiles.charAt(i)) {
				//lis le nombre
				number = new StringBuilder();
				for (; r != ',' && r != ']'; i++, r = tiles.charAt(i)) {
					number.append(r);
				}
				tilesArray.add(Float.parseFloat(number.toString()));
			}

			map.put(name.toString(), tilesArray);
		}

		return map;
	}

}
