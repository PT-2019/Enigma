package common.save.entities.serialization;

import api.utils.Utility;
import com.badlogic.gdx.utils.Json;
import common.entities.players.Monster;
import common.entities.players.MonsterGame;
import common.entities.players.PlayerGame;
import common.map.GameMap;
import data.Layer;

import java.util.ArrayList;

public class MonsterFactory {

    /**
     * Liste des joueurs qu'on a récupérere dans le Json
     */
    private static ArrayList<PlayerSerializable> monsters;

    /**
     * Permet de créer un playergame à partir d'un fichier Json
     *
     * @param path
     * @return un playerGame du Json
     */
    @SuppressWarnings("unchecked")
    public static MonsterGame createMonsterGame(String name,String path) {
        MonsterGame game = new MonsterGame();
        Json json = new Json();
        PlayerSerializable playerInfo = null;

        //parseur de json de la libgdx
        monsters = json.fromJson(ArrayList.class, PlayerSerializable.class, Utility.loadFile(path));
        for (PlayerSerializable  p : monsters) {
            if (p.getName().equals(name)) {
                playerInfo = p;
                break;
            }
        }

        //on instantie les paramètres de l'animation
        game.setAnimation(playerInfo.getPath(),
                playerInfo.getCols(), playerInfo.getRows(),
                playerInfo.getDuration(),
                playerInfo.getColPerImage(), playerInfo.getRowPerImage(),
                playerInfo.getIndex());

        game.setLayer(Layer.DECORATIONS1);
        game.setBounds(7);

        return game;
    }
}
