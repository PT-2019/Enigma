package game.utils;

import api.entity.GameObject;
import api.entity.types.EnigmaContainer;
import api.utils.Utility;
import api.utils.annotations.ConvenienceMethod;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import editor.enigma.Enigma;
import editor.utils.json.EnigmaJsonReader;
import editor.utils.json.EnigmaJsonWriter;
import editor.utils.save.SaveMap;
import editor.utils.textures.TextureProxy;
import game.EnigmaGame;
import game.entity.map.MapTestScreen;
import game.screen.TestScreen;
import starter.Config;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Une classe qui génère une map vide
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 27/01/2020
 * @since 5.0 27/01/2020
 */
public class LoadGame {


    /**
     * Recharge le jeu depuis une sauvegarde
     *
     * @param path chemin sauvegarde
     */
    public static void load(String path) {
        //------------ charge .tmx ------------
        ((TestScreen) EnigmaGame.getCurrentScreen()).setMap(path);

        //------------ charge .json ------------
        MapTestScreen map = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap();
        HashMap<Vector2, GameObject> entities = map.getEntities();

        ArrayList<Enigma> enigmas;
        ArrayList<Enigma> copy = new ArrayList<>();
        path = path.substring(0, path.length() - 4) + ".json"; //retire l'extension tmx
        Utility.printDebug("EmptyMapGenerator#load", path);
        int id;
        try {
            enigmas = EnigmaJsonReader.readEnigmas(path);
            for (GameObject o : entities.values()) {
                if (!(o instanceof EnigmaContainer)) continue;
                //récupère les énigmes de cet object
                for (Enigma en : enigmas) {
                    id = en.getID();
                    if (id != -1 && id == o.getID()) {
                        copy.add(en);
                    }
                }
                //ajoute les énigmes
                for (Enigma en : copy) {
                    enigmas.remove(en);
                    ((EnigmaContainer) o).addEnigma(en);
                }
                copy.clear();
            }
        } catch (IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                InstantiationException | IllegalAccessException ignore) {
        } catch (IllegalStateException e) {
            System.err.println(e.toString());
        }

        for (Map.Entry<Vector2, GameObject> entry : entities.entrySet()) {
            Vector2 key = entry.getKey();
            GameObject value = entry.getValue();
            if(value instanceof EnigmaContainer){
                for (Iterator<Enigma> it = ((EnigmaContainer) value).getAllEnigmas(); it.hasNext(); ) {
                    Enigma e = it.next();

                }
            }
        }
    }
}
