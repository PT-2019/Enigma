package game.utils;

import api.entity.Entity;
import api.entity.GameObject;
import api.entity.types.EnigmaContainer;
import api.utils.Utility;
import com.badlogic.gdx.math.Vector2;
import editor.enigma.Enigma;
import editor.enigma.condition.Answer;
import editor.enigma.condition.Condition;
import editor.enigma.operation.Operation;
import editor.enigma.operation.Summon;
import editor.utils.json.EnigmaJsonReader;
import editor.utils.map.Case;
import game.EnigmaGame;
import game.entity.map.MapTestScreen;
import game.event.TileEvent;
import game.screen.TestScreen;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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

        for (GameObject value : entities.values()) {
            if(value instanceof EnigmaContainer){
                TileEvent ope = new TileEvent();
                for (Iterator<Enigma> ite = ((EnigmaContainer) value).getAllEnigmas(); ite.hasNext(); ) {
                    Enigma e = ite.next();
                    ope.add(e);

                    for (Iterator<Condition> itc = e.getAllConditions(); itc.hasNext(); ) {
                        Condition c = itc.next();
                        if(!(c instanceof Answer)) {
                            for (GameObject go : entities.values()) {
                                if (c.getEntity().getID() == go.getID())
                                    c.setEntity((Entity) go);
                            }
                        }
                    }

                    for (Iterator<Operation> ito = e.getAllOperations(); ito.hasNext(); ) {
                        Operation o = ito.next();
                        if(!(o instanceof Summon)) {
                            for (GameObject go : entities.values()) {
                                if (o.getEntity().getID() == go.getID())
                                    o.setEntity((Entity) go);
                            }
                        } else {
                            for (GameObject go : entities.values()) {
                                if (o.getEntity().getID() == go.getID())
                                    o.setEntity((Entity) go);
                                else if(((Summon) o).getSpawn().getID() == go.getID())
                                    ((Summon) o).setSpawn((Case) go);
                            }
                        }
                    }
                }
            }
        }
    }
}
