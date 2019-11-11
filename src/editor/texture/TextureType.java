package editor.texture;

/**
 * L'interface TextureType est utilisée pour permettre de charger des images avec
 * la class Texture. Cette interface montre quelles sont les données nécessaire pour
 * le bon chargement des textures.
 * @see Texture
 */
public interface TextureType{
    /**
     * @return la colonne de la sous-texture voulu
     */
	int getCol();

    /**
     * @return la ligne de la sous-texture voulu
     */
	int getRow();

    /**
     * On conseille de créer une variable final static pour représenter le chemin.
     * @return Le chemin de la texture à charger
     */
	String getPath();
}