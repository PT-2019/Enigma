package common.save.entities.serialization;

import api.utils.Utility;
import com.badlogic.gdx.utils.Json;
import common.entities.players.PlayerGame;
import common.map.GameMap;
import data.Layer;

import java.util.ArrayList;

/**
 * Factory qui permet à partir d'un fichier Json et d'un nom de charger un player
 * avec une animation
 */
public class PlayerFactory {

	/**
	 * Liste des joueurs qu'on a récupérere dans le Json
	 */
	private static ArrayList<PlayerSerializable> players;

	public PlayerFactory() {
	}

	/**
	 * Permet de créer un playergame à partir d'un fichier Json
	 *
	 * @param name
	 * @param path
	 * @param map
	 * @return un playerGame du Json
	 */
	@SuppressWarnings("unchecked")
	public static PlayerGame createPlayerGame(String name, String path, GameMap map) {
		PlayerGame game = new PlayerGame(map);
		Json json = new Json();
		PlayerSerializable playerInfo = null;

		//parseur de json de la libgdx
		players = json.fromJson(ArrayList.class, PlayerSerializable.class, Utility.loadFile(path));
		for (PlayerSerializable p : players) {
			if (p.getName().equals(name)) {
				playerInfo = p;
				break;
			}
		}

		if (playerInfo == null) throw new IllegalArgumentException("Clef non trouvée:" + name + " dans " + path);

		//on instantie les paramètres de l'animation
		game.setAnimation(playerInfo.getPath(),
				playerInfo.getCols(), playerInfo.getRows(),
				playerInfo.getDuration(),
				playerInfo.getColPerImage(), playerInfo.getRowPerImage(),
				playerInfo.getIndex());

		game.setLayer(Layer.DECORATIONS1);
		//on initialise la hitbox du joueur
		game.setBounds(7);

		return game;
	}
}
