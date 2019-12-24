package api.utils;

import api.enums.Layer;
import editor.entity.map.Map;
import editor.utils.save.MapLoader;

public class FromTmxToJson {

	private static String fromRoomToJson(String tmx, String name){
		StringBuilder sb = new StringBuilder();

		sb.append("{\n");
		sb.append("  className: game.entity.item.Room\n");
		sb.append("  path: assets/map/datas/rooms/");
		sb.append(name);
		sb.append(".png\n");
		sb.append("  hover: une salle\n");
		sb.append("  category: ROOMS\n");

		MapLoader loader = new MapLoader();

		loader.load(tmx);

		Map m = loader.getMap();

		sb.append("  width: ");
		sb.append(m.getCol());
		sb.append('\n');

		sb.append("  height: ");
		sb.append(m.getRow());
		sb.append('\n');

		sb.append("  tiles: {\n");

		for (Layer layer : Layer.values()) {
			sb.append("   ");
			sb.append(layer.name());
			sb.append(": [\n");

			//values
			for (int i = 0; i < m.getRow(); i++) {
				for (int j = 0; j < m.getCol(); j++) {
					sb.append(m.getCase(i*m.getCol()+j).getEntity(layer).getPosition());
					sb.append(',');
				}
				sb.append('\n');
			}

			sb.append("   ]\n");
		}

		sb.append("  }\n");
		sb.append("}\n");

		return sb.toString();

	}

	public static void main(String[] args) {
		System.out.println(fromRoomToJson("assets/map/Room1.tmx", "Room1"));
		System.out.println(fromRoomToJson("assets/map/Room2.tmx", "Room2"));
		System.out.println(fromRoomToJson("assets/map/Room3.tmx", "Room3"));
	}

}
