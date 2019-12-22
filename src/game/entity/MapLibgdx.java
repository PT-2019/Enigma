package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
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
import api.enums.Layer;
import editor.entity.EntitySerializable;
import editor.utils.Utility;
import game.ui.Border;
import game.utils.Bounds;
import game.utils.InputListener;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static api.MapsNameUtils.*;

/**
 * Map de la libgdx
 *
 * @version 3.0 14 décembre 2019
 * @since 2.0 5 décembre 2019
 */
public class MapLibgdx extends Group implements InputListener {

    /**
     * info principales
     */
    private final int tileWidth, tileHeight, width, height;

    private int mapWidth, mapHeight;
    /**
     * Dessinateur de la map
     */
    private OrthogonalTiledMapRenderer map;
    /**
     * Caméra de la map
     */
    private OrthographicCamera camera;
    /**
     * Bordure des cases de la map
     */
    private Border border;

    private Bounds mapBounds;

    /**
     * Crée une map depuis un fichier tmx
     *
     * @param path fichier .tmx
     */
    public MapLibgdx(@NotNull String path) {
        //load the map
        TiledMap tiledMap = new TmxMapLoader().load(path);
        this.map = new OrthogonalTiledMapRenderer(tiledMap, 1f);

        //save needed properties
        MapProperties properties = tiledMap.getProperties();
        this.tileWidth = properties.get(TILE_WIDTH_P.value, Integer.class);
        this.tileHeight = properties.get(TILE_HEIGHT_P.value, Integer.class);
        this.width = properties.get(WIDTH_P.value, Integer.class);
        this.height = properties.get(HEIGHT_P.value, Integer.class);

        this.border = new Border(this.width, this.height, this.tileHeight);

        this.border = new Border(this.width,
                (this.height * (int) this.map.getUnitScale()),
                this.tileHeight);

        this.mapWidth = width * tileWidth;
        this.mapHeight = height * tileHeight;

        MapLayer collision = this.map.getMap().getLayers().get(Layer.COLLISION.name());
        if (collision != null)
            collision.setVisible(false);

        //setup camera
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //this.camera.position.set(getMapWidth() / 2, getMapHeight() / 2, 0);
        this.camera.update();

        //bounds
        this.setMapBounds();
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
     */
    private static Vector2 posToIndex(float posX, float posY, MapLibgdx map) {
        Vector2 index = new Vector2();

		/*//convert coordinates to row and column
		posX = (posX / map.getUnitScale()) - map.mapBounds.left;
		posY = (posY / map.getUnitScale());

		float mapW = (map.mapBounds.right - map.mapBounds.left) / map.tileWidth;
		float mapH = (map.mapBounds.bot - map.mapBounds.top) / map.tileHeight;

		//and clamp to the map
		float column = MathUtils.clamp(Math.round(posX / map.getTileWidth()), 0, mapW);
		float row = MathUtils.clamp(Math.round(posY / map.getTileHeight()), 0, mapH);*/

        posX /= map.getUnitScale();
        posY /= map.getUnitScale();

        float column = MathUtils.clamp(Math.round(posX / map.getTileWidth()), 0, map.mapBounds.right);
        float row = MathUtils.clamp(Math.round(posY / map.getTileHeight()), 0, map.mapBounds.top);

        index.x = column;
        index.y = row;

        return index;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //update caméra
        //update map's camera from stage's camera
        Camera c = this.getStage().getCamera();
        this.camera.position.x = c.position.x;
        this.camera.position.y = c.position.y;
        this.camera.update();
        //update borders
        border.setProjectionMatrix(camera.combined);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        //Setup camera
        this.map.setView(this.camera);

        //render map
        this.map.render();

        //render borders
        this.border.draw();
    }

    @Override
    public boolean scrolled(int amount) {
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            if (amount == 1) {
                camera.zoom += 0.02;
            } else {
                camera.zoom -= 0.02;
            }
            return true;
        }
        return false;
    }

    @Deprecated
    private void updateMapBounds(float zoom) {
        float left = this.mapBounds.left, right = this.mapBounds.right;
        float top = this.mapBounds.top, bot = this.mapBounds.bot;

        left -= zoom * 27;
        right += zoom * 27;
        top -= zoom * 18;
        bot += zoom * 18;

        this.mapBounds = new Bounds(left, right, top, bot);

        //System.out.println(this.mapBounds);
    }

    private void setMapBounds() {
        float left = Gdx.graphics.getWidth() / 2f - this.getMapWidth() / 2;
        float right = Gdx.graphics.getWidth() / 2f + this.getMapWidth() / 2;
        float top = Gdx.graphics.getHeight() / 2f - this.getMapHeight() / 2;
        float bot = Gdx.graphics.getHeight() / 2f + this.getMapHeight() / 2;
        this.mapBounds = new Bounds(left, right, top, bot);
    }

    /**
     * Charge une entité dans la map depuis un point
     *
     * @param entity   entité a placer
     * @param location coordonnés x,y ou placer l'entité
     * @since 3.0 14 décembre 2019
     */
    @Deprecated
    public void load(EntitySerializable entity, Point location) {
        //on parcours toutes les niveaux de la map et on y ajoute les tiles de l'entité
        for (MapLayer mapLayer : this.map.getMap().getLayers()) {
            //c'est un layer de tiles ?
            if (!(mapLayer instanceof TiledMapTileLayer)) continue;

            TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;

            //récupère les tiles de l'entités pour ce niveau
            Array<Float> entities = entity.getTiles(Utility.stringToEnum(tileLayer.getName(), Layer.values()));

            //si pas de tiles a mettre sur ce layer, on passe au suivant
            if (entities == null) continue;

            //System.out.println(location+" "+camera.zoom);

            float maxW = this.mapBounds.right;//- entity.getWidth() * tileWidth;
            float maxH = this.mapBounds.bot;//- entity.getHeight() * tileHeight;

            //on regarde si l'entités est dans la map
            Vector2 start = null; //start contient le x, y ou on commence a placer les tiles
            if (location.x >= this.mapBounds.left && location.x <= maxW) {//Axe x
                if (location.y >= this.mapBounds.top && location.y <= maxH) {//Axe y
                    start = posToIndex(location.x, location.y, this);
                }
            }

            //si pas dans la map
            if (start == null) return;

            //calcul pour placer les tiles depuis x et y
            //sachant que y est inversé, on part de la dernière tile et on remonte
            //pas de problème pour x
            for (int i = (int) start.y - 1, index = 0; i >= (start.y - entity.getHeight()); i--) {
                for (int j = (int) start.x; j < start.x + entity.getWidth() && index < entities.size; j++, index++) {
                    MapLibgdxCell c = new MapLibgdxCell(tileLayer, i, j);
                    c.setTile(this.map.getMap().getTileSets().getTile(MathUtils.ceil(entities.get(index))));
                    c.setEntity(entity);
                    tileLayer.setCell(j, i, c);
                }
            }
        }
    }

    public float getMapHeight() {
        return mapHeight;
    }

    public float getMapWidth() {
        return mapWidth;
    }

    public float getUnitScale() {
        return this.map.getUnitScale();
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public boolean loadEntity(EntitySerializable entity, Vector2 pos) {
        float zoom = camera.zoom;
        if (zoom < 1) {
            zoom = 1 + (1 - camera.zoom);
        } else if (zoom > 1) {
            zoom = 1 + (1 - camera.zoom);
        }

        //map bounds
        Rectangle bounds = new Rectangle();
        bounds.setPosition((Gdx.graphics.getWidth() / 2f - camera.position.x) * 1,
                (Gdx.graphics.getHeight() / 2f - camera.position.y) * 1);
        bounds.setSize(this.getMapWidth() * zoom, this.getMapHeight() * zoom);
        this.mapBounds = new Bounds(bounds);

        System.out.println(mapBounds + " " + pos + " " + mapBounds.contains(pos));

        //si pas dans la map
        if (!mapBounds.contains(pos)) return false;

        pos.x -= this.mapBounds.left;//retire l'offset de l'espace
        pos.y -= this.mapBounds.bot;

        Vector2 start = posToIndex(pos.x, pos.y, this);

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
            for (int i = (int) start.y - 1, index = 0; i >= (start.y - entity.getHeight()); i--) {
                for (int j = (int) start.x; j < start.x + entity.getWidth() && index < entities.size; j++, index++) {
                    MapLibgdxCell c = new MapLibgdxCell(tileLayer, i, j);
                    c.setTile(this.map.getMap().getTileSets().getTile(MathUtils.ceil(entities.get(index))));
                    c.setEntity(entity);
                    tileLayer.setCell(j, i, c);
                }
            }
        }

        return true;
    }
}

