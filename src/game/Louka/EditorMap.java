package game.Louka;

import api.utils.Bounds;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class EditorMap extends AbstractMap {

    public EditorMap(String mapName, float unitScale) {
        super(mapName, unitScale);
    }

    @Override
    protected Bounds getMapBounds() {
        return mapBounds;
    }

    @Override
    protected Rectangle getMapSize() {
        Rectangle r = new Rectangle();
		/*
            Inverse le zoom, avant avec un zoom de 0.95 la map était plus grande et 1.05
            donnait une map plus petite

            zoom contient l'inverse : 1.05 contient une plus grande map, 0.95 une plus petite
         */
        float zoom = camera.zoom;
        if (zoom < 1) {
            zoom = 1 + (1 - camera.zoom);
        } else if (zoom > 1) {
            zoom = 1 + (1 - camera.zoom);
        }

        //mapSize according to zoom
        r.width = Math.round(this.getMapWidth() * zoom);
        r.height = Math.round(this.getMapHeight() * zoom);

        return r;
    }

    /**
     * Affiche la grille de la map
     *
     * @param show affiche la grille de la map
     */
    @Override
    public void showGrid(boolean show) {
        this.showGrid = show;
    }

    /**
     * Renvoi la hauteur de la map
     *
     * @return la hauteur de la map
     */
    @Override
    public float getMapHeight() {
        return mapHeight;
    }

    /**
     * Renvoi la largeur de la map
     *
     * @return la largeur de la map
     */
    @Override
    public float getMapWidth() {
        return mapWidth;
    }

    /**
     * Retourne le taux de distorsion de la taille d'un tile
     *
     * @return le taux de distorsion de la taille d'un tile
     */
    @Override
    public float getUnitScale() {
        return this.map.getUnitScale();
    }

    /**
     * Retourne la largeur d'un tile
     *
     * @return la largeur d'un tile
     */
    @Override
    public int getTileWidth() {
        return tileWidth;
    }

    /**
     * Retourne la hauteur d'un tile
     *
     * @return la hauteur d'un tile
     */
    @Override
    public int getTileHeight() {
        return tileHeight;
    }

    /**
     * Retourne la map tiled
     *
     * @return la map tiled
     */
    @Override
    public TiledMap getTiledMap() {
        return this.map.getMap();
    }
}
