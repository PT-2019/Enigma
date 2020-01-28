package game.Louka;

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
import game.Louka.event.TileEvent;
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
    public static void load(String path, AbstractMap map) {
        //------------ charge .tmx ------------
        ((TestScreen) EnigmaGame.getCurrentScreen()).setMap(path);

        //------------ charge .json ------------
        HashMap<Vector2, ArrayList<GameObject>> entities = map.getObjects().getAll();

        ArrayList<Enigma> enigmas;
        ArrayList<Enigma> copy = new ArrayList<>();
        path = path.substring(0, path.length() - 4) + ".json"; //retire l'extension tmx
        Utility.printDebug("EmptyMapGenerator#load", path);
        int id;
        try {
            enigmas = EnigmaJsonReader.readEnigmas(path);
            for (ArrayList<GameObject> oList : entities.values()) {
                for(GameObject obj : oList) {
                    if (!(obj instanceof EnigmaContainer)) continue;
                    //récupère les énigmes de cet object
                    for (Enigma en : enigmas) {
                        id = en.getID();
                        if (id != -1 && id == obj.getID()) {
                            copy.add(en);
                        }
                    }
                    //ajoute les énigmes
                    for (Enigma en : copy) {
                        enigmas.remove(en);
                        ((EnigmaContainer) obj).addEnigma(en);
                    }
                    copy.clear();
                }
            }
        } catch (IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                InstantiationException | IllegalAccessException ignore) {
        } catch (IllegalStateException e) {
            System.err.println(e.toString());
        }

        for (ArrayList<GameObject> oList : entities.values()) {
            for (GameObject obj : oList) {
                if (obj instanceof EnigmaContainer) {
                    TileEvent ope = new TileEvent();
                    for (Iterator<Enigma> ite = ((EnigmaContainer) obj).getAllEnigmas(); ite.hasNext(); ) {
                        Enigma e = ite.next();
                        ope.add(e);

                        for (Iterator<Condition> itc = e.getAllConditions(); itc.hasNext(); ) {
                            Condition c = itc.next();
                            if (!(c instanceof Answer) && c.getEntity().getID() != -1) {
                                c.setEntity((Entity) map.getObjects().getObjectByID(c.getEntity().getID()));
                            }
                        }

                        for (Iterator<Operation> ito = e.getAllOperations(); ito.hasNext(); ) {
                            Operation o = ito.next();

                            if(o.getEntity().getID() != -1)
                                o.setEntity((Entity) map.getObjects().getObjectByID(o.getEntity().getID()));
                        }
                    }
                }
            }
        }
    }
}
