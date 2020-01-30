package game.entity;

import api.enums.Layer;
import api.utils.Utility;
import com.badlogic.gdx.utils.Json;
import game.entity.map.MapGame;

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

    public PlayerFactory(){}

    /**
     * Permet de créer un playergame à partir d'un fichier Json
     * @param name
     * @param path
     * @param map
     * @return un playerGame du Json
     */
    public static PlayerGame createPlayerGame(String name, String path, MapGame map){
        PlayerGame game = new PlayerGame(map);
        Json json = new Json();
        PlayerSerializable playerInfo = null;

        //parseur de json de la libgdx
        players = json.fromJson(ArrayList.class,PlayerSerializable.class, Utility.loadFile(path));
        for (PlayerSerializable p: players) {
            if (p.getName().equals(name)){
                playerInfo = p;
                break;
            }
        }
        //on instantie les paramètres de l'animation
        game.setAnimation(playerInfo.getPath(),
                playerInfo.getCols(), playerInfo.getRows(),
                playerInfo.getDuration(),
                playerInfo.getColPerImage(),playerInfo.getRowPerImage(),
                playerInfo.getIndex());

        game.setLayer(Layer.FLOOR2);

        return game;
    }
}
