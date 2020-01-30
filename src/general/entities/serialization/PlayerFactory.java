package general.entities.serialization;

import api.utils.Utility;
import com.badlogic.gdx.utils.Json;
import general.entities.players.PlayerGame;
import general.map.MapGame;

import java.util.ArrayList;

/**
 * Factory qui permet à partir d'un fichier Json et d'un nom de charger un player
 * avec une animation
 */
public class PlayerFactory {

    private static ArrayList<PlayerSerializable> players;

    public PlayerFactory(){}

    public static PlayerGame createPlayerGame(String name, String path, MapGame map){
        PlayerGame game = new PlayerGame(map);
        Json json = new Json();
        PlayerSerializable playerInfo = null;

        players = json.fromJson(ArrayList.class,PlayerSerializable.class, Utility.loadFile(path));
        for (PlayerSerializable p: players) {
            if (p.getName().equals(name)){
                playerInfo = p;
                break;
            }
        }

        game.setAnimation(playerInfo.getPath(),
                playerInfo.getCols(), playerInfo.getRows(),
                playerInfo.getDuration(),
                playerInfo.getColPerImage(),playerInfo.getRowPerImage(),
                playerInfo.getIndex());

        return game;
    }
}
