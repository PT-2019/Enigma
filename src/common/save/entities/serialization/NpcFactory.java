package common.save.entities.serialization;

import api.utils.Utility;
import com.badlogic.gdx.utils.Json;
import common.dialog.Dialog;
import common.entities.players.NpcGame;
import data.Layer;

import java.util.ArrayList;

/**
 * Factory qui permet à partir d'un fichier Json et d'un nom de charger un npc
 */
public class NpcFactory {

	/**
	 * Liste des NPC qu'on a récupérere dans le Json
	 */
	private static ArrayList<PlayerSerializable> players;

	public NpcFactory() {
	}

	/**
	 * Permet de créer un Npcgame à partir d'un fichier Json
	 *
	 * @param name
	 * @param path
	 * @return un playerGame du Json
	 */
	@SuppressWarnings("unchecked")
	public static NpcGame createNpcGame(String name, String path,String content) {
		Dialog d = new Dialog(content);
		NpcGame game = new NpcGame(d);
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
		//on initialise la hitbox du pnj
		game.setBounds(7);

		return game;
	}
}
