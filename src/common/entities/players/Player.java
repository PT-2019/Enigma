package common.entities.players;

import common.entities.Item;
import common.entities.special.Inventory;
import common.entities.types.AbstractLivingEntity;
import common.language.GameFields;
import common.language.GameLanguage;
import data.TypeEntity;

import java.util.EnumMap;

/**
 * Définie un personnage contrôlable : un joueur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see common.entities.Entity
 * @see common.entities.types.Living
 * @since 2.0
 */
public class Player extends AbstractLivingEntity {

	/**
	 * Points de vie maximaux du joueur
	 */
	private static final int MAX_PLAYER_PV = 100;

	/**
	 * Inventaire
	 */
	private Inventory inventory;

	/**
	 * Main
	 */
	private Item hand;

	public Player() {
		this(-1);
	}

	/**
	 * @param name Nom du joueur
	 */
	public Player(String name) {
		this(-1, name);
	}

	/**
	 * @param id ID
	 */
	public Player(int id) {
		this(id, null);
	}

	/**
	 * @param id   ID
	 * @param name Nom du joueur
	 */
	public Player(int id, String name) {
		super(id, name);
		this.pv = MAX_PLAYER_PV;
		this.inventory = new Inventory();
		this.hand = null;
	}

	// implements

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.PLAYER);
	}

	@Override
	public EnumMap<TypeEntity, Boolean> getImplements() {
		EnumMap<TypeEntity, Boolean> imp = TypeEntity.emptyMap();
		imp.put(TypeEntity.PLAYER, true);

		imp.put(TypeEntity.LIVING, true);
		imp.put(TypeEntity.CONTAINER, true);
		imp.put(TypeEntity.NEED_CONTAINER_MANAGER, true);
		return imp;
	}

	// inventory

	public Item getItemInHand() {
		return hand;
	}

	public void setItemInHand(Item hand) {
		this.hand = hand;
	}

	public boolean holdSomething() {
		return (this.hand != null);
	}

	public Inventory getInventory() {
		return inventory;
	}

	//toString

	@Override
	public String toString() {
		return "Player{" + "MAX_PLAYER_PV=" + MAX_PLAYER_PV + ", pv=" + pv + ", id=" + id +
				", tiles=" + tiles + ", bounds=" + bounds + '}';
	}
}