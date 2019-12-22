package editor.entity;

import com.badlogic.gdx.utils.Array;
import api.enums.EntitiesCategories;
import api.enums.Layer;

import java.util.HashMap;

/**
 * Représentation d'une entité du fichier json
 *
 * @version 3.0 14 décembre 2019
 */
public class EntitySerializable {

    /**
     * chemin de la texture
     */
    private String path;
    /**
     * dimension
     */
    private int width, height;
    /**
     * les tiles (a convertir en int)
     */
    private HashMap<String, Array<Float>> tiles = new HashMap<>();
    /**
     * catégorie de l'entité
     */
    private EntitiesCategories category;
    /**
     * sa classe
     */
    private String className;

    public EntitySerializable() {
    }

    /**
     * Crée une copie d'une entité sérializable
     *
     * @param entity l'entité a copier
     */
    public EntitySerializable(EntitySerializable entity) {
        super();
        this.className = entity.className;
        this.path = entity.path;
        this.width = entity.width;
        this.height = entity.height;
        this.tiles = entity.tiles;
        this.category = entity.category;
    }

    public EntitiesCategories getCategory() {
        return this.category;
    }

    public String getPath() {
        return this.path;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Array<Float> getTiles(Layer layer) {
        return this.tiles.get(layer.name());
    }

    public String getClassName() {
        return this.className;
    }
}
