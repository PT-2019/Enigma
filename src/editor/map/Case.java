package editor.map;

import editor.enums.Layer;
import editor.texture.Texture;

import java.util.HashMap;

/**
 * Une case de la map
 */
public class Case {

	/** Taille basique d'une case **/
	private static final int WIDTH = 16, HEIGHT = 16;

	/** détermine s'il est possible de se rendre sur la case */
	private boolean walkable;

	/** Map, pour chaque niveau, on a possiblement une texture **/
	private HashMap<Layer, Texture> textures;

	/** taille de la case **/
	private int width, height;

	/**
	 * Crée une case
	 */
	public Case(){
		this.width = Case.WIDTH;
		this.height = Case.HEIGHT;
		this.walkable = false;
		this.textures = new HashMap<>();
	}

	@Override
	public String toString() {
		return "o";//just to check if there is a case or not
	}

	/**
	 * Définit la texture associé au niveau
	 * @param layer le niveau
	 * @param texture la texture
	 */
	public void setTexture(Layer layer, Texture texture) {
		this.textures.put(layer,texture);
	}

	/**
	 * Return s'il est possible de se rendre sur la case
	 * @return true s'il est possible de se rendre sur la case sinon false
	 */
	public boolean isWalkable() { return this.walkable; }

	/**
	 * Définit s'il est possible de se rendre sur la case
	 * @param walkable true s'il est possible de se rendre sur la case sinon false
	 */
	public void setWalkable(boolean walkable) { this.walkable = walkable; }

	/**
	 * Retourne la largeur de la case
	 * @return la largeur de la case
	 */
	public int getWidth() {	return this.width; }

	/**
	 * Retourne la hauteur de la case
	 * @return la hauteur de la case
	 */
	public int getHeight() { return this.height; }

	public HashMap<Layer,Texture> getTextures() {
		return this.textures;
	}
}
