package common.entities.types;

import api.libgdx.actor.GameActorTextured;
import api.utils.annotations.ConvenienceClass;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import common.entities.Entity;
import common.entities.players.Player;
import data.Layer;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 * Abstraction d'une entité
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 13/02/2020
 * @since 6.0 13/02/2020
 */
@ConvenienceClass
public abstract class AbstractLivingEntity extends GameActorTextured implements Entity, Living {

	/**
	 * Point de vie
	 */
	protected int pv;
	/**
	 * ID
	 */
	protected int id;
	/**
	 * les tiles (a convertir en int)
	 */
	protected HashMap<Layer, Array<Float>> tiles;
	/**
	 * Dimensions de l'object
	 */
	protected Rectangle bounds;

	/**
	 * Nom
	 */
	protected String name;
	/**
	 * json
	 */
	protected String json;
	/**
	 * clef
	 */
	protected String key;
	/**
	 * Entité temporaire
	 */
	private boolean temp;


	/**
	 * Abstraction d'une entité
	 */
	@SuppressWarnings("unused")
	public AbstractLivingEntity() {
		this(-1, null);
	}

	/**
	 * Abstraction d'une entité
	 *
	 * @param id   ID
	 * @param name Nom du joueur
	 */
	public AbstractLivingEntity(int id, @Nullable String name) {
		this.id = id;
		this.bounds = new Rectangle();
		this.tiles = new HashMap<>();
		this.name = name;
		this.temp = false;
	}

	//base

	@Override
	public void interactsWith(Player p) {
		throw new UnsupportedOperationException("node codé. InteractWidth " + this.getClass().getName());
	}

	@Override
	public int getHP() {
		return this.pv;
	}

	//json
	@Override
	public void setJson(String json, String key) {
		this.json = json;
		this.key = key;
		//si pas de nom, alors sa clef est son nom
		if (this.name == null || this.name.isEmpty() || this.name.isBlank()) this.name = this.key;
	}

	@Override
	public String getJson() {
		return this.json;
	}

	@Override
	public String getKey() {
		return this.key;
	}

	//size

	@Override
	public void setDimension(int width, int height) {
		this.bounds.setSize(width, height);
	}

	@Override
	public float getGameObjectWidth() {
		return this.bounds.getWidth();
	}

	@Override
	public float getGameObjectHeight() {
		return this.bounds.getHeight();
	}

	//pos

	@Override
	public Vector2 getGameObjectPosition() {
		return this.bounds.getPosition(new Vector2());
	}

	@Override
	public void setGameObjectPosition(Vector2 pos) {
		this.bounds.setPosition(pos);
	}

	//tiles

	@Override
	public Array<Float> getTiles(Layer layer) {
		return this.tiles.get(layer);
	}

	@Override
	public void setTiles(Array<Float> texture, Layer layer) {
		this.tiles.put(layer, texture);
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

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean isTemp() {
		return this.temp;
	}

	@Override
	public void setTemp(boolean temp) {
		this.temp = temp;
	}
}
