package api.entity.interfaces;

import api.enums.Layer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import editor.enigma.Enigma;
import editor.entity.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class AbstractItem implements Item { //peut extends GameActorTextured si besoin

	/**
	 * Enigmes données à l'objet
	 *
	 * @since 4.0
	 */
	protected ArrayList<Enigma> enigmas;

	/**
	 * ID
	 *
	 * @since 2.2
	 */
	protected int id;

	/**
	 * Dimensions de l'object
	 */
	protected Rectangle bounds;

	/**
	 * les tiles (a convertir en int)
	 */
	protected HashMap<Layer, Array<Float>> tiles;

	//constructeur par default
	public AbstractItem(){
		this(-1);
	}

	public AbstractItem(int id){
		this.enigmas = new ArrayList<Enigma>();
		this.id = id;
		this.tiles = new HashMap<>();
		this.bounds = new Rectangle();
	}

	//Enigmas

	@Override
	public void interactsWith(Player p) {
		for (Enigma e : this.enigmas) {
			if (!e.isKnown()) e.discovered();
			else e.verifyConditions(p);
		}
	}

	@Override
	public void addEnigma(Enigma e) {
		this.enigmas.add(e);
	}

	@Override
	public void removeEnigma(Enigma e) {
		this.enigmas.remove(e);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<Enigma> getAllEnigmas() {
		ArrayList<Enigma> e = (ArrayList<Enigma>) this.enigmas.clone();
		return e.iterator();
	}

	//id

	@Override
	public int getID() {
		return this.id;
	}

	@Override
	public void setID(int id) {
		this.id = id;
	}

	//new methods

	@Override
	public void setDimension(int width, int height) {
		this.bounds.setSize(width, height);
	}

	@Override
	public float getWidth() {
		return this.bounds.getWidth();
	}

	@Override
	public float getHeight() {
		return this.bounds.getHeight();
	}

	@Override
	public Vector2 getPosition() {
		return this.bounds.getPosition(new Vector2());
	}

	@Override
	public void setPosition(Vector2 pos) {
		this.bounds.setPosition(pos);
	}

	@Override
	public Array<Float> getTexture(Layer layer) {
		return this.tiles.get(layer);
	}

	@Override
	public void setTexture(Array<Float> texture, Layer layer) {
		this.tiles.put(layer, texture);
	}

	//toString

	@Override
	public String toLongString() {
		StringBuilder s = new StringBuilder(this.toString() + ":: enigmas = {");
		int size = this.enigmas.size() - 1;
		int i = 0;
		for (Enigma e : this.enigmas) {
			s.append(e.toLongString());
			if (i < size) s.append(", ");
			i++;
		}
		s.append("}]");
		return s.toString();
	}

	@Override
	public abstract String toString();
}
