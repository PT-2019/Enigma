package editor.map;

import editor.entities.interfaces.Entity;
import editor.datas.Layer;
import editor.entities.interfaces.IDInterface;

import java.util.HashMap;

/**
 * Une case de la map
 */
public class Case implements IDInterface {

	/** Taille basique d'une case **/
	private static final int WIDTH = 16, HEIGHT = 16;

	/** détermine s'il est possible de se rendre sur la case */
	private boolean walkable;

	/** Map, pour chaque niveau, on a possiblement une texture **/
	private HashMap<Layer, Entity> entities;

	/** taille de la case **/
	private int width, height;

	/**
	 * ID
	 */
	private int id;

	/**
	 * Crée une case
	 */
	public Case(){
		this.width = Case.WIDTH;
		this.height = Case.HEIGHT;
		this.walkable = false;
		this.entities = new HashMap<>();
	}

	/**
	 * Crée une case
	 * @param id ID
	 */
	public Case(int id){
		this.width = Case.WIDTH;
		this.height = Case.HEIGHT;
		this.walkable = false;
		this.entities = new HashMap<>();
		this.id = id;
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
	public void setEntity(Layer layer, Entity texture) {
		this.entities.put(layer,texture);
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

	public HashMap<Layer, Entity> getEntities() {
		return this.entities;
	}

	public Entity getEntity(Layer layer) {
		return this.entities.get(layer);
	}

	/**
	 * Obtenir l'ID
	 * @return L'ID, -1 si pas initialisé
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Définir l'ID
	 * @param id ID
	 */
	public void setID(int id) {
		this.id = id;
	}
}