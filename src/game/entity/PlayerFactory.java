package game.entity;

import api.utils.Utility;
import com.badlogic.gdx.utils.Json;
import game.entity.map.MapGame;

/**
 * Factory qui permet à partir d'un fichier Json et d'un nom de charger un player
 * avec une animation
 */
public class PlayerFactory {

    public PlayerFactory(){}

    public static PlayerGame createPlayerGame(String name, String path, MapGame map){
        Json json = new Json();
        PlayerGame game = json.fromJson(PlayerGame.class, Utility.loadFile(path));



        return game;
    }
}
