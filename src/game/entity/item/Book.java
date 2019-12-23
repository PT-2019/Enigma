package game.entity.item;

import api.entity.interfaces.Content;
import api.entity.interfaces.Item;
import api.enums.Layer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import editor.enigma.Enigma;
import editor.entity.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Un livre
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 4.0
 * @since 2.0
 */
public class Book implements Item, Content {

	/**
	 * Enigmes données à l'objet
	 * @since 2.0
	 */
	private ArrayList<Enigma> enigmas;

	/**
	 * Contenu de l'objet
	 * @since 2.0
	 */
	private String content;

	/**
	 * ID
	 * @since 2.2
	 */
	private int id;

	/**
	 * Dimensions du livre
	 */
	private Rectangle bounds;

	/**
	 * les tiles (a convertir en int)
	 */
	private HashMap<Layer, Array<Float>> tiles;

	/**
	 * Crée un livre
	 * @since 2.0
	 */
	public Book() {
		this(-1);
	}

	/**
	 * Crée un livre avec un id unique pour identifier ses énigmes
	 * @param id ID
	 * @since 2.2
	 */
	public Book(int id) {
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

	//content

	@Override
	public void addContent(String content) {
		this.content = content;
	}

	@Override
	public String getContent() {
		return this.content;
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
	public String toString() {
		return "Book{" + "enigmas=" + enigmas + ", content='" + content + '\'' + ", id=" + id + '}';
	}

	@Override
	public String toLongString() {
		StringBuilder s = new StringBuilder(this.toString()+":: enigmas = {");
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
}
