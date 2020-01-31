package general.utils;

import api.utils.annotations.ConvenienceClass;
import api.utils.annotations.ConvenienceMethod;
import com.badlogic.gdx.math.Vector2;
import data.Layer;
import general.map.model.Map;
import general.save.MapLoader;
import general.utils.textures.Texture;

/**
 * Cette classe ne doit pas être utilisée dans le code.
 * Elle sert a calculer le contenu de assets/rooms.json.
 * <p>
 * Il faut naturellement lui passer tous les tmx dans le main.
 * La succession d'appels a fromRoomToJson donne le contenu de rooms.json
 * <p>
 * Cette classe calcule également le contenu de assets/entities.json.
 * Il faut lui passer leur fichier.tmx et le nombre d'entités.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.2 25/12/2019
 * @since 4.0 24/12/2019
 */
@ConvenienceClass
class FromTmxToJson {

	/**
	 * Prends un room .tmx et renvoi sa représentation json
	 *
	 * @param tmx  .tmx d'une room
	 * @param name le nom de la room
	 * @return String représentant la room en json.
	 * @since 4.0
	 */
	@ConvenienceMethod
	private static String fromRoomToJson(String tmx, String name) {
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
			System.out.println(layer.name + " " + layer.name());
			sb.append(": [\n");

			//values
			for (int i = 0; i < m.getRow(); i++) {
				for (int j = 0; j < m.getCol(); j++) {
					Texture entity = m.getCase(i * m.getCol() + j).getEntity(layer);
					System.out.println(entity);
					sb.append(entity.getPosition());
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

	/**
	 * Prends un fichier .tmx d'entités et génère leur .json
	 *
	 * @param tmx   le fichier .tmx
	 * @param count le nombre d'entités
	 * @return sa représentation .json
	 * <p>
	 * Attention, les entités sont en 32x32, donc prennent 2 cases en hauteur.
	 * Les entités sont placées depuis le sommet haut gauche
	 */
	@ConvenienceMethod
	@SuppressWarnings("SameParameterValue")
	private static String fromEntitiesToJson(String tmx, int count) {
		final int SIZE = 2;
		final Layer tmxLayer = Layer.FLOOR1;
		final Layer insideLayer = Layer.FLOOR2;

		StringBuilder sb = new StringBuilder();

		MapLoader loader = new MapLoader();

		loader.load(tmx);

		Map m = loader.getMap();

		for (int i = 1; i <= count; i++) {
			sb.append("{\n");
			sb.append("  className: editor.entity.player.Player\n");
			sb.append("  path: assets/map/datas/players/player");
			sb.append(i);
			sb.append(".png\n");
			sb.append("  category: ENTITIES\n");
			sb.append("  width: 2 \n");
			sb.append("  height: 2\n");
			sb.append("  tiles: {\n");


			sb.append("   ");
			sb.append(insideLayer);
			sb.append(": [\n");

			int offset = (i - 1) * SIZE;

			Vector2 start = new Vector2(offset % m.getCol(), (offset / m.getCol()) * SIZE);

			//values
			sb.append("      ");
			for (int k = (int) start.x; k < start.x + SIZE; k++) {
				int index = (int) start.y * SIZE + k;
				sb.append(m.getCase(index).getEntity(tmxLayer).getPosition());
				sb.append(',');
			}
			sb.append('\n');

			sb.append("      ");
			for (int k = (int) start.x; k < start.x + SIZE; k++) {
				int index = (int) start.y * SIZE + k + m.getCol();
				sb.append(m.getCase(index).getEntity(tmxLayer).getPosition());
				sb.append(',');
			}
			sb.append('\n');

			sb.append("   ]\n");

			sb.append("  }\n");
			sb.append("}\n");
		}

		return sb.toString();
	}

	/**
	 * Lance le main qui génère assets/rooms.json.
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 4.2 25/12/2019
	 * @since 4.1 25/12/2019
	 */
	static final class MainFromRoomToJson {

		/**
		 * Génère le fichier rooms.json
		 * <p>
		 * Il contient actuellement 3 rooms.
		 *
		 * @param ignore ignored
		 * @since 4.1
		 */
		public static void main(String[] ignore) {
			System.out.println(fromRoomToJson("assets/map/map_system/Room1.tmx", "Room1"));
			System.out.println(fromRoomToJson("assets/map/map_system/Room2.tmx", "Room2"));
			System.out.println(fromRoomToJson("assets/map/map_system/Room3.tmx", "Room3"));
			System.out.println(fromRoomToJson("assets/map/map_system/Room4.tmx", "Room4"));
			System.out.println(fromRoomToJson("assets/map/map_system/Room5.tmx", "Room5"));
		}
	}

	/**
	 * Lance le main qui génère assets/rooms.json.
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 4.2 25/12/2019
	 * @since 4.1 25/12/2019
	 */
	static final class MainFromEntitiesToJson {

		/**
		 * Génère le fichier rooms.json
		 * <p>
		 * Il contient actuellement 3 rooms.
		 *
		 * @param ignore ignored
		 * @since 4.1
		 */
		public static void main(String[] ignore) {
			System.out.println(fromEntitiesToJson("assets/map/Entities.tmx", 5));
		}
	}

}
