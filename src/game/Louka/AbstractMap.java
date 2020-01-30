package game.Louka;

import api.entity.GameObject;
import api.entity.types.EnigmaContainer;
import api.enums.Layer;
import api.hud.components.CustomWindow;
import api.utils.Bounds;
import api.utils.Utility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import editor.enigma.Enigma;
import editor.entity.EntityFactory;
import editor.entity.EntitySerializable;
import editor.utils.json.EnigmaJsonReader;
import editor.view.cases.CasePopUp;
import editor.view.cases.CaseView;
import editor.view.listeners.CaseListener;
import game.entity.map.MapTestScreenCell;
import game.hud.Border;
import game.hud.CategoriesMenu;
import starter.EditorLauncher;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import static api.MapsNameUtils.*;
import static api.MapsNameUtils.HEIGHT_P;

public abstract class AbstractMap extends Group {
    protected MapObjects objects;
    protected String name;

    /**
     * Dimension d'un tile
     */
    protected final int tileWidth, tileHeight;
    /**
     * Dessinateur de la map
     */
    protected final OrthogonalTiledMapRenderer map;
    /**
     * Caméra de la map
     */
    protected final OrthographicCamera camera;

    /**
     * Fenetre parent qui contient la map
     */
    protected final CustomWindow window;

    /**
     * Bordure des cases de la map
     */
    protected final Border border;
    /**
     * Dimension de la map
     */
    protected int mapWidth, mapHeight;
    /**
     * Les limites de la map dans l'espace
     *
     * @since 3.0
     */
    protected Bounds mapBounds;

    protected boolean showGrid;

    public AbstractMap(String mapName, float unitScale){
        this.objects = new MapObjects();
        this.name = mapName;

        //load the map
        TiledMap tiledMap = new TmxMapLoader().load("assets/files/map/" + mapName + ".tmx");
        this.map = new OrthogonalTiledMapRenderer(tiledMap, unitScale);

        //save needed properties
        MapProperties properties = tiledMap.getProperties();
        this.tileWidth = properties.get(TILE_WIDTH_P.value, Integer.class);
        this.tileHeight = properties.get(TILE_HEIGHT_P.value, Integer.class);
        int width = properties.get(WIDTH_P.value, Integer.class);
        int height = properties.get(HEIGHT_P.value, Integer.class);

        this.showGrid = false;

        //bordures
        this.border = new Border(width, height, this.tileHeight);

        //dimension de la map
        this.mapWidth = width * tileWidth;
        this.mapHeight = height * tileHeight;

        //cache le niveau de collision
        MapLayer collision = this.map.getMap().getLayers().get(Layer.COLLISION.name());
        if (collision != null)
            collision.setVisible(false);

        //setup camera
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.position.set(
                Gdx.graphics.getWidth() / 2f - this.mapWidth / 2f - CategoriesMenu.WIDTH,
                Gdx.graphics.getHeight() / 2f - this.mapHeight / 2f, 0);
        this.camera.update();

        this.window = EditorLauncher.getInstance().getWindow();

        init();
        createCell(this.window.getContentPane());

        //bounds
        this.setMapBounds();

        this.initEntities();
        this.loadEnigmas();
    }

    public AbstractMap(String mapName, float unitScale, MapObjects mo){
        this(mapName,unitScale);
        this.objects = mo;
    }

    private void initEntities() {
        ArrayList<MapProperties> entities = getProperty("entity");
        float x, y;
        int width, height;
        String className;
        EntitySerializable e;
        for (MapProperties prop : entities) {
            width = Math.round(prop.get("width", Float.class));
            height = Math.round(prop.get("height", Float.class));
            className = prop.get("className", String.class);
            x = prop.get("x", Float.class);
            //obligé de faire ce truc sale y2 car y renvoi truc bizarres y=789 renvoi y=0...
            y = Float.parseFloat(prop.get("y2", String.class));
            Vector2 start = new Vector2(x, y);
            e = new EntitySerializable(width, height, className);
            GameObject object = EntityFactory.createEntity(e, this.objects.size(), start);

            Utility.printDebug("MapTestScreen#initEntities", object + " " + start);

            //ajout à la liste des entités de la map
            this.objects.put(start, object);

            // on place les tiles
            this.setFromSave(object, start);
        }
    }

    /**
     * Place une entité
     *
     * @param entity l'entité à charger
     * @param start  le coin supérieur gauche ou commencer a placer des tiles
     * @since 4.0
     */
    private void setFromSave(GameObject entity, Vector2 start) {
        //on parcours toutes les niveaux de la map et on y ajoute les tiles de l'entité
        for (MapLayer mapLayer : this.map.getMap().getLayers()) {
            //c'est un layer de tiles ?
            if (!(mapLayer instanceof TiledMapTileLayer)) continue;

            TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;

            //calcul pour placer les tiles depuis x et y
            //sachant que y est inversé, on part de la dernière tile et on remonte
            //pas de problème pour x
            for (int i = (int) start.y - 1, index = 0; i >= (start.y - entity.getGameObjectHeight()); i--) {
                for (int j = (int) start.x; j < start.x + entity.getGameObjectWidth(); j++, index++) {
                    MapTestScreenCell c = (MapTestScreenCell) tileLayer.getCell(j, i);
                    if (c == null) continue;
                    c.setEntity(entity);
                    tileLayer.setCell(j, i, c);
                }
            }
        }
    }

    /**
     * Retourne une liste des propriétés contenant name
     *
     * @param name un name (tag name d'une property d'un .tmx)
     * @return une liste des propriétés contenant name
     */
    private ArrayList<MapProperties> getProperty(String name) {
        ArrayList<MapProperties> props = new ArrayList<>();
        for (MapLayer layer : this.map.getMap().getLayers()) {
            for (MapObject mapObject : layer.getObjects()) {
                //object contient entité ?
                if (name != null && name.equals(mapObject.getName())) {
                    props.add(mapObject.getProperties());
                }
            }
        }
        return props;
    }

    private void loadEnigmas(){
        HashMap<Vector2, ArrayList<GameObject>> entities = this.getEntities().getAll();
        ArrayList<Enigma> copy = new ArrayList<>();
        int id;
        try {
            ArrayList<Enigma> enigmas = EnigmaJsonReader.readEnigmas("assets/files/enigma/" + this.name + ".json");
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
    }

    private void set(GameObject entity, Vector2 start) {
        //on parcours toutes les niveaux de la map et on y ajoute les tiles de l'entité
        for (MapLayer mapLayer : this.map.getMap().getLayers()) {
            //c'est un layer de tiles ?
            if (!(mapLayer instanceof TiledMapTileLayer)) continue;

            TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;

            //récupère les tiles de l'entités pour ce niveau
            Array<Float> entities = entity.getTiles(Utility.stringToEnum(tileLayer.getName(), Layer.values()));

            //si pas de tiles a mettre sur ce layer, on passe au suivant
            if (entities == null) continue;

            //calcul pour placer les tiles depuis x et y
            //sachant que y est inversé, on part de la dernière tile et on remonte
            //pas de problème pour x
            for (int i = (int) start.y - 1, index = 0; i >= (start.y - entity.getGameObjectHeight()); i--) {
                for (int j = (int) start.x; j < start.x + entity.getGameObjectWidth() && index < entities.size; j++, index++) {
                    MapTestScreenCell c = (MapTestScreenCell) tileLayer.getCell(j, i);
                    c.setTile(this.map.getMap().getTileSets().getTile(MathUtils.ceil(entities.get(index))));
                    c.setEntity(entity);

                    tileLayer.setCell(j, i, c);
                }
            }
        }
    }

    /**
     * Place une entité
     *
     * @param entity l'entité à charger
     * @param start  le coin supérieur gauche ou commencer a placer des tiles
     * @since 4.0
     */
    private void delete(GameObject entity, Vector2 start) {
        //on parcours toutes les niveaux de la map et on y ajoute les tiles de l'entité
        for (MapLayer mapLayer : this.map.getMap().getLayers()) {
            //c'est un layer de tiles ?
            if (!(mapLayer instanceof TiledMapTileLayer)) continue;

            TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;

            //récupère les tiles de l'entités pour ce niveau
            Array<Float> entities = entity.getTiles(Utility.stringToEnum(tileLayer.getName(), Layer.values()));

            //si pas de tiles a mettre sur ce layer, on passe au suivant
            if (entities == null) {
                //TODO: fix car entités sav n'ont pas de tiles, comment les supprimer ?
                for (int i = (int) start.y - 1, index = 0; i >= (start.y - entity.getGameObjectHeight()); i--) {
                    for (int j = (int) start.x; j < start.x + entity.getGameObjectWidth(); j++, index++) {
                        MapTestScreenCell c = (MapTestScreenCell) tileLayer.getCell(j, i);
                        c.setTile(this.map.getMap().getTileSets().getTile(0));
                        tileLayer.setCell(j, i, c);
                    }
                }
                continue;
            }

            //calcul pour placer les tiles depuis x et y
            //sachant que y est inversé, on part de la dernière tile et on remonte
            //pas de problème pour x
            for (int i = (int) start.y - 1, index = 0; i >= (start.y - entity.getGameObjectHeight()); i--) {
                for (int j = (int) start.x; j < start.x + entity.getGameObjectWidth() && index < entities.size; j++, index++) {
                    MapTestScreenCell c = (MapTestScreenCell) tileLayer.getCell(j, i);
                    c.setTile(this.map.getMap().getTileSets().getTile(0));
                    tileLayer.setCell(j, i, c);
                }
            }
        }
    }

    /**
     * Cette méthode transforme toutes les cellules de la map en MapLibgdxCell
     *
     * @see MapTestScreenCell
     */
    private void init() {
        MapLayers layers = map.getMap().getLayers();
        for (int i = 0; i < 5; i++) {
            TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(i);
            for (int y = 0; y < layer.getHeight(); y++) {
                for (int x = 0; x < layer.getWidth(); x++) {
                    MapTestScreenCell cell = new MapTestScreenCell(layer, y * layer.getWidth() + x);

                    TiledMapTileLayer.Cell tmp = layer.getCell(x, y);

                    if (tmp != null)
                        cell.setTile(tmp.getTile());

                    layer.setCell(x, y, cell);
                }
            }
        }
    }

    /**
     * Permet de créer tout les listeners sur les cases
     *
     * @param component component swing
     */
    private void createCell(Container component) {
        JComponent jcomponent = (JComponent) component;
        CasePopUp popUp = new CasePopUp(jcomponent, this.map.getMap());
        CaseListener listenerCase = new CaseListener(popUp);
        MapLayers layers = map.getMap().getLayers();

        TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(0);
        for (int y = 0; y < layer.getHeight(); y++) {
            for (int x = 0; x < layer.getWidth(); x++) {
                MapTestScreenCell cell = (MapTestScreenCell) layer.getCell(x, y);

                CaseView actor = new CaseView(cell);

                actor.setBounds(x * layer.getTileWidth(), y * layer.getTileHeight(),
                        layer.getTileWidth(), layer.getTileHeight());

                addActor(actor);

                layer.setCell(x, y, cell);

                actor.addListener(listenerCase);
            }
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //update camera
        //update map's camera from stage's camera
        Camera c = this.getStage().getCamera();
        this.camera.position.x = c.position.x;
        this.camera.position.y = c.position.y;
        this.camera.update();

        //update borders
        this.border.setProjectionMatrix(this.camera.combined);
    }

    /**
     * Supprime une entité de la map
     *
     * @param entity l'entité
     * @return true si entité supprimée sinon false
     * @since 5.0
     */
    public boolean removeEntity(GameObject entity) {
        if (this.objects.contains(entity)) {//peut la supprimer
            Vector2 pos = this.objects.getVectorByObject(entity);
            this.objects.remove(pos);
            delete(entity, pos);
            return true;
        }
        return false;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    /**
     * Dessine la map
     *
     * @param batch       batch openGL de dessin
     * @param parentAlpha transparence du parent
     * @since 2.0
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        //Setup camera
        this.map.setView(this.camera);

        //render map
        this.map.render();

        //render borders
        if (this.showGrid)
            this.border.draw();
    }

    /**
     * Définit les bounds de la map
     *
     * @since 3.0
     */
    protected void setMapBounds() {
        float left = Gdx.graphics.getWidth() / 2f - this.getMapWidth() / 2;
        float right = Gdx.graphics.getWidth() / 2f + this.getMapWidth() / 2;
        float top = Gdx.graphics.getHeight() / 2f - this.getMapHeight() / 2;
        float bot = Gdx.graphics.getHeight() / 2f + this.getMapHeight() / 2;
        this.mapBounds = new Bounds(left, right, top, bot);
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

    public MapObjects getEntities(){
        return this.objects;
    }

    public String getName(){
        return this.name;
    }

    public OrthogonalTiledMapRenderer getMap() {
        return map;
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
