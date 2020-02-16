package common.save.entities.serialization;

import api.utils.Utility;
import com.badlogic.gdx.utils.Json;
import common.dialog.Dialog;
import common.entities.players.MonsterGame;
import data.Layer;

import java.util.ArrayList;

/**
 * Factory qui permet de créer des monstres
 */
public class MonsterFactory {

	/**
	 * Liste des monstres qu'on a récupère dans le Json
	 */
	@SuppressWarnings("FieldCanBeLocal")
	private static ArrayList<PlayerSerializable> monsters;

	/**
	 * Permet de créer un monsterGame à partir d'un fichier Json
	 *
	 * @param path chemin
	 * @return un playerGame du Json
	 */
	@SuppressWarnings("unchecked")
	public static MonsterGame createMonsterGame(String name, String path,String content) {
		Dialog d = new Dialog(content);
		MonsterGame game = new MonsterGame(d);
		Json json = new Json();
		PlayerSerializable playerInfo = null;

		//parseur de json de la libgdx
		monsters = json.fromJson(ArrayList.class, PlayerSerializable.class, Utility.loadFile(path));
		for (PlayerSerializable p : monsters) {
			if (p.getName().equals(name)) {
				playerInfo = p;
				break;
			}
		}

		//on instancie les paramètres de l'animation
		assert playerInfo != null;

		game.setAnimation(playerInfo.getPath(),
				playerInfo.getCols(), playerInfo.getRows(),
				playerInfo.getDuration(),
				playerInfo.getColPerImage(), playerInfo.getRowPerImage(),
				playerInfo.getIndex());

		game.setLayer(Layer.DECORATIONS1);
		//on initialise la hitBox du monstre
		game.setBounds(7);
		return game;
	}
}
