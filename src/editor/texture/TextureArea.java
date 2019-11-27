package editor.texture;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

/**
 * La classe TextureArea permet de chargé des images contenant différentes
 * texture et de les décomposer pour ensuite instancier dse Textures.
 * @see Texture
 * @see TextureProxy
 */
public class TextureArea{
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
	private int tile;

	private String path;

	/**
     * Nombre de sous-texture différente sur chaque ligne de l'image
     */
	private int nbcol;

	/**
	 * @param path fichier de la texture
	 * @param tile dimention d'une sous-texture
	 * @param nbcol nombre de sous-texture par ligne
     * @param min valeur min dans le fichier qui décrit la texture
     * @param max valeur max dans le fichier qui décrit la texture
	 */
	public TextureArea(int tile,String path,int nbcol,int min,int max) {
		buffer = null;
		this.tile = tile;
		this.path = path;
		this.nbcol = nbcol;
		this.max = max;
		this.min = min;
	}

    /**
     * Cette méthode va découper, une image charger en fonction du paramètre
     * @param under_texture
     * @throws IllegalStateException Fichier de l'image impossible à charger
     * @throws IllegalStateException Valeur trop élevé, soit les dimensions width et
     * height, soit les attributs col et row de la TextureType
     * @return Sous-Image décrite par la TextureType
     */
	public Image getImage(int under_texture) {
        int texture_value;

		if (under_texture < min  || under_texture > max) {
			throw new IllegalStateException("under_texture to low or to hight");
		}	

		if (buffer==null) {			
			ImageIcon image = new ImageIcon(path);

			if (image.getIconHeight() < 0 || image.getIconWidth() < 0) {
				throw new IllegalStateException("File not found. Bad Path.");
			}

			buffer = new  BufferedImage(image.getIconWidth(),image.getIconHeight(),BufferedImage.TYPE_3BYTE_BGR);

			Graphics g = buffer.getGraphics();

			g.drawImage(image.getImage(),0,0,new JLabel());
		}

        texture_value = under_texture - min;

		return buffer.getSubimage((texture_value%nbcol)*tile,(texture_value/nbcol)*tile,tile,tile);
	}

	/**
	 * Cette méthode static renvoi une sous-image en fonction des paramètres
	 * @param col
	 * @param row
	 * @return
	 */
	public static Image getImage(int col,int row,String path,int tileSize) {
		BufferedImage buffer;

		ImageIcon image = new ImageIcon(path);

		if (image.getIconHeight() < 0 || image.getIconWidth() < 0) {
			throw new IllegalStateException("File not found. Bad Path.");
		}

		buffer = new  BufferedImage(image.getIconWidth(),image.getIconHeight(),BufferedImage.TYPE_3BYTE_BGR);

		Graphics g = buffer.getGraphics();

		g.drawImage(image.getImage(),0,0,new JLabel());

		return buffer.getSubimage(row*tileSize,col*tileSize,tileSize,tileSize);
	}

	public int getMax(){
		return max;
	}

	public int getMin(){
		return min;
	}

	public String getPath(){
		return path;
	}
}