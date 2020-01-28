package game.Louka;

import api.utils.Bounds;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class GameMap extends AbstractMap {

    public GameMap(String mapName){
        super(mapName);
    }

    public GameMap(String mapName, MapObjects mo){
        super(mapName,mo);
    }

    @Override
    protected Bounds getMapBounds() {
        return null;
    }

    @Override
    protected Rectangle getMapSize() {
        return null;
    }

    /**
     * Affiche la grille de la map
     *
     * @param show affiche la grille de la map
     */
    @Override
    public void showGrid(boolean show) {

    }

    /**
     * Renvoi la hauteur de la map
     *
     * @return la hauteur de la map
     */
    @Override
    public float getMapHeight() {
        return 0;
    }

    /**
     * Renvoi la largeur de la map
     *
     * @return la largeur de la map
     */
    @Override
    public float getMapWidth() {
        return 0;
    }

    /**
     * Retourne le taux de distorsion de la taille d'un tile
     *
     * @return le taux de distorsion de la taille d'un tile
     */
    @Override
    public float getUnitScale() {
        return 0;
    }

    /**
     * Retourne la largeur d'un tile
     *
     * @return la largeur d'un tile
     */
    @Override
    public int getTileWidth() {
        return 0;
    }

    /**
     * Retourne la hauteur d'un tile
     *
     * @return la hauteur d'un tile
     */
    @Override
    public int getTileHeight() {
        return 0;
    }

    /**
     * Retourne la map tiled
     *
     * @return la map tiled
     */
    @Override
    public TiledMap getTiledMap() {
        return null;
    }
}
