package common.save.entities.serialization;

import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

/**
 * Représentation d'une entité du fichier json
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 13/02/2020
 * @since 6.0 13/02/2020
 */
public class ItemSerializableToJson extends EntitySerializable {

    private String atlas;
    private String key;

    public ItemSerializableToJson() {
        //TOUTES LES VARIABLES DOIVENT ETRE INSTANCIEES EN DEHORS A CAUSE DE LA LIBGDX
        //ET DE Json.fromJson qui fait des trucs bizarres avec une classe qui
        //peut mettre un float dans une variable de type int et qui lève une
        //NumberFormatException lorsque l'on passe ce int a une méthode qui attends
        //un int
    }

    /**
     * Une entité sérializable avec juste assez d'infos pour utiliser la fonction
     *
     * @param width     largeur
     * @param height    hauteur
     * @param className chemin classe entité
     */
    public ItemSerializableToJson(int width, int height, String className, HashMap<String, Array<Float>> tiles) {
        super(width, height, className, tiles);
        this.key = "";
        this.atlas = "";
    }

    /**
     * Crée une copie d'une entité sérializable
     *
     * @param entity l'entité a copier
     */
    private ItemSerializableToJson(ItemSerializableToJson entity) {
        super(entity);
        this.atlas = entity.atlas;
        this.key = entity.key;
    }

    /**
     * Retourne le chemin du json
     *
     * @return le chemin du json
     */
    public String getAtlas() {
        return atlas;
    }

    /**
     * Retourne le nom de l'entité dans le json
     *
     * @return le nom de l'entité dans le json
     */
    public String getKey() {
        return key;
    }

    @Override
    protected EntitySerializable duplicates() {
        return new ItemSerializableToJson(this);
    }

    @Override
    public String toString() {
        return "ItemSerializableToJson{ {" + super.toString()+ "} " +
                "json='" + atlas + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}