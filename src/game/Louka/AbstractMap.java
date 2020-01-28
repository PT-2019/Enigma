package game.Louka;

import api.entity.Entity;
import api.entity.GameObject;
import api.entity.types.EnigmaContainer;
import api.utils.Bounds;
import api.utils.Utility;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import editor.enigma.Enigma;
import editor.enigma.condition.Answer;
import editor.enigma.condition.Condition;
import editor.enigma.operation.Operation;
import editor.enigma.operation.Summon;
import editor.entity.Player;
import editor.utils.json.EnigmaJsonReader;
import editor.utils.json.EnigmaJsonWriter;
import editor.utils.map.Case;
import game.Louka.event.TileEvent;
import game.entity.map.MapTestScreenCell;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class AbstractMap extends Group {
    protected MapObjects objects;
    protected String name;

    public AbstractMap(String mapName){
        this.objects = new MapObjects();
        this.name = mapName;
        this.loadEnigmas();
    }

    public AbstractMap(String mapName, MapObjects mo){
        this.objects = mo;
        this.name = mapName;
        this.loadEnigmas();
    }

    public MapObjects getObjects(){
        return this.objects;
    }

    public String getName(){
        return this.name;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Enigma> e = new ArrayList<>();
        Enigma ee = new Enigma();
        ee.setID(1);
        MapTestScreenCell cell = new MapTestScreenCell(new TiledMapTileLayer(10, 10, 0, 0),9);
        cell.setTile(new TiledMapTile() {
            @Override
            public int getId() {
                return 0;
            }

            @Override
            public void setId(int i) {

            }

            @Override
            public BlendMode getBlendMode() {
                return null;
            }

            @Override
            public void setBlendMode(BlendMode blendMode) {

            }

            @Override
            public TextureRegion getTextureRegion() {
                return null;
            }

            @Override
            public void setTextureRegion(TextureRegion textureRegion) {

            }

            @Override
            public float getOffsetX() {
                return 0;
            }

            @Override
            public void setOffsetX(float v) {
            }

            @Override
            public float getOffsetY() {
                return 0;
            }

            @Override
            public void setOffsetY(float v) {

            }

            @Override
            public MapProperties getProperties() {
                return null;
            }
        });
        ee.addOperation(new Summon(new Player(5), cell));
        e.add(ee);
        Enigma eee = new Enigma();
        eee.setID(2);
        e.add(eee);
        EnigmaJsonWriter.writeEnigmas("assets/files/enigma/test.json",e);
        GameMap g = new GameMap("assets/files/enigma/test");
        g.getObjects().put(new Vector2(),new Player(5));
        g.loadEnigmas();
    }

    protected void loadEnigmas(){
        HashMap<Vector2, ArrayList<GameObject>> entities = this.getObjects().getAll();
        ArrayList<Enigma> copy = new ArrayList<>();
        int id;
        try {
            ArrayList<Enigma> enigmas = EnigmaJsonReader.readEnigmas(/*Path +*/ this.name + ".json");
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
                                c.setEntity((Entity) this.objects.getObjectByID(c.getEntity().getID()));
                            }
                        }

                        for (Iterator<Operation> ito = e.getAllOperations(); ito.hasNext(); ) {
                            Operation o = ito.next();

                            if(o.getEntity().getID() != -1)
                                o.setEntity((Entity) this.objects.getObjectByID(o.getEntity().getID()));

                            if(o instanceof Summon) {
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Retourne la case (indices) dans la map depuis une positon x,y dans l'espace.
     * <p>
     * Attention! La position  x,y est considérée comme étant toujours dans la map.
     *
     * @param posX position x
     * @param posY position y
     * @param map  la map
     * @return la case (indices) dans la map depuis une positon x,y dans l'espace.
     * @since 3.0 14 décembre 2019
     * <p>
     * <p>
     * VERSION SWING TO LIBGDX
     */
    @SuppressWarnings("WeakerAccess")
    public static Vector2 swingPosToIndex(float posX, float posY, final AbstractMap map) {
        Vector2 index = new Vector2();

        posX /= map.getUnitScale();
        posY /= map.getUnitScale();

        float column = MathUtils.clamp(Math.round(posX / map.getTileWidth()), 0, map.getMapBounds().right);
        float row = MathUtils.clamp(Math.round(posY / map.getTileHeight()), 0, map.getMapBounds().top);

        index.x = column;
        index.y = row;

        return index;
    }

    protected abstract Bounds getMapBounds();

    protected abstract Rectangle getMapSize();

    /**
     * Affiche la grille de la map
     *
     * @param show affiche la grille de la map
     */
    public abstract void showGrid(boolean show);

    /**
     * Renvoi la hauteur de la map
     *
     * @return la hauteur de la map
     */
    public abstract float getMapHeight();

    /**
     * Renvoi la largeur de la map
     *
     * @return la largeur de la map
     */
    public abstract float getMapWidth();

    /**
     * Retourne le taux de distorsion de la taille d'un tile
     *
     * @return le taux de distorsion de la taille d'un tile
     */
    public abstract float getUnitScale();

    /**
     * Retourne la largeur d'un tile
     *
     * @return la largeur d'un tile
     */
    public abstract int getTileWidth();

    /**
     * Retourne la hauteur d'un tile
     *
     * @return la hauteur d'un tile
     */
    public abstract int getTileHeight();

    /**
     * Retourne la map tiled
     *
     * @return la map tiled
     */
    public abstract TiledMap getTiledMap();
}
