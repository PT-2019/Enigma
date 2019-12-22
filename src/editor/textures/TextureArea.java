package editor.textures;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * La classe TextureArea permet de chargé des images contenant différentes
 * texture et de les décomposer pour ensuite instancier dse Textures.
 *
 * @version 2.1 28 novembre 2019
 * @see TextureProxy
 */
public class TextureArea {
    /**
     * L'image qui va être chargé sera stocké ici
     */
    private BufferedImage buffer;

    /**
     * Le minimum de la texture dans le fichier
     */
    private int min;

    /**
     * Le maximum de la texture dans le fichier
     */
    private int max;

    /**
     * Dimension d'une sous-texture
     */
    private int tileWidth, tileHeight;

    /**
     * Chemin de la texture
     */
    private String path;

    /**
     * Nombre de sous-textures sur une ligne de l'image
     */
    private int nbcol;

    /**
     * @param path       fichier de la texture
     * @param tileWidth  dimention d'une sous-texture
     * @param tileHeight dimention d'une sous-texture
     * @param nbcol      nombre de sous-texture par ligne
     * @param min        valeur min dans le fichier qui décrit la texture
     * @param max        valeur max dans le fichier qui décrit la texture
     */
    TextureArea(int tileWidth, int tileHeight, String path, int nbcol, int min, int max) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.path = path;
        this.nbcol = nbcol;
        this.max = max;
        this.min = min;
    }

    TextureArea(int tileWidth, int tileHeight, String path) {
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        this.path = path;
        this.nbcol = 0;
        this.min = 0;
        this.max = 0;
    }

    public Image getImage(int x, int y, int width, int height) {
        if (buffer == null) {
            this.load();
        }
        return buffer.getSubimage(x, y, width, height);
    }

    /**
     * Cette méthode va découper, une image charger en fonction du paramètre
     *
     * @param under_texture identifiant la sous-texture
     * @return Sous-Image décrite par under_texture
     * @throws IllegalStateException Fichier de l'image impossible à charger
     * @throws IllegalStateException Valeur trop élevée, soit les dimensions width et
     *                               height, soit les attributs col et row de la TextureType
     */
    public Image getImage(int under_texture) {
        int texture_value;

        if (under_texture < min || under_texture > max) {
            throw new IllegalStateException("under_texture is to low or to hight");
        }

        if (buffer == null) {
            this.load();
        }

        texture_value = under_texture - min;

        return buffer.getSubimage((texture_value % nbcol) * tileWidth, (texture_value / nbcol) * tileHeight, tileWidth, tileHeight);
    }

    public void load() {
        try {
            //open file
            FileInputStream input = new FileInputStream(new File(path));
            //read image
            buffer = ImageIO.read(input);
            //close file
            input.close();
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("File not found. Bad Path.");
        } catch (IOException e) {
            throw new IllegalStateException("Error reading image!");
        }
    }

    public String getPath() {
        return path;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int getNbcol() {
        return nbcol;
    }

    public int getWidth() {
        return buffer.getWidth();
    }

    public int getHeight() {
        return buffer.getHeight();
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }
}
