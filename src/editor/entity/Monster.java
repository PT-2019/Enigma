package editor.entity;

import api.entity.Entity;
import api.entity.actor.GameActorTextured;
import api.entity.types.Living;
import api.enums.Layer;
import api.enums.TypeEntity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import editor.utils.lang.GameLanguage;
import editor.utils.lang.fields.GameFields;

import java.util.EnumMap;
import java.util.HashMap;

/**
 * Un monstre
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class Monster extends GameActorTextured implements Entity, Living {

	/**
	 * Points de vie maximaux du joueur
	 */
	public final int MAX_MONSTER_PV = 10000;
	/**
	 * Point de vie du MONSTER
	 */
	private int pv;
	/**
	 * ID
	 */
	private int id;
	/**
	 * les tiles (a convertir en int)
	 */
	private HashMap<Layer, Array<Float>> tiles;
	/**
	 * Dimensions de l'object
	 */
	private Rectangle bounds;

	/**
	 * Nom du npc
	 */
	private String name;

	public Monster() {
		this(-1);
	}

	/**
	 * @param name Nom du npc
	 */
	public Monster(String name) {
		this(-1);
		this.name = name;
	}

	/**
	 * @param id ID
	 */
	public Monster(int id) {
		this.pv = MAX_MONSTER_PV;
		this.id = id;
		this.bounds = new Rectangle();
		this.tiles = new HashMap<>();
	}

	/**
	 * @param id   ID
	 * @param name Nom du joueur
	 */
	public Monster(int id, String name) {
		this.pv = MAX_MONSTER_PV;
		this.id = id;
		this.bounds = new Rectangle();
		this.tiles = new HashMap<>();
		this.name = name;
	}

	@Override
	public void interactsWith(Player p) {
		throw new UnsupportedOperationException("node codé. InteractWidth MONSTER");
	}

	@Override
	public int getHP() {
		return this.pv;
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

	@Override
	public EnumMap<TypeEntity, Boolean> getImplements() {
		EnumMap<TypeEntity, Boolean> imp = TypeEntity.emptyMap();
		imp.put(TypeEntity.MONSTER, true);

		imp.put(TypeEntity.LIVING, true);
		imp.put(TypeEntity.NEED_CONTAINER_MANAGER, true);
		return imp;
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

	//toString

	@Override
	public String toString() {
		return "Monster{" + "MAX_PLAYER_PV=" + MAX_MONSTER_PV + ", pv=" + pv + ", id=" + id +
				", tiles=" + tiles + ", bounds=" + bounds + '}';
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.MONSTER);
	}

}
