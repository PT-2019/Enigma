package common.entities.special;

import common.entities.Item;
import common.entities.consumable.Stackable;
import common.entities.players.Player;
import common.entities.special.inventory.InventoryItem;
import java.util.ArrayList;

    /**
     * Inventaire d'un joueur
     *
     * @author Jorys-Micke ALAÏS
     * @author Louka DOZ
     * @author Loic SENECAT
     * @author Quentin RAMSAMY-AGEORGES
     * @version 6.0
     * @see Player
     * @since 5.0
     */
public class Inventory {

	/**
	 * Maximum d'items stockables
	 */
	public final static int MAX_ITEMS = 15;
	/**
	 * Items de l'inventaire
	 */
	private InventoryItem[] items;

	@Deprecated
	private ArrayList<Item> item;


	public Inventory() {
		this.items = new InventoryItem[MAX_ITEMS];
	}

	/**
	 * Ajouter un item
	 *
	 * @param i Item
	 */
	public void add(Item i) {
		if (!this.isFull()) {
			for(int j = 0; j < this.items.length; j++){
				if(this.items[j] == null){
					this.items[j] = new InventoryItem(i,1);
					return;
				}else if(this.items[j].getItem().getClass().equals(i)
						&& i instanceof Stackable){
					//on a vérifier que l'item est bien stackable
					this.items[j].plusNumber();
					return;
				}
			}
		} else
			throw new IllegalStateException("Inventaire plein");
	}

	/**
	 * Retirer un item
	 *
	 * @param i Item
	 */
	public void remove(Item i) {
		int tmp = this.indexOf(i);

		if(tmp != -1) {
			//on enlève 1
			this.items[tmp].lessNumber();
			if (this.items[tmp].getNumber() <= 0)
				this.items[tmp] = null;
		}
	}

	/**
	 * Obtenir tous les items
	 *
	 * @return Liste des items
	 */
	public Item[] getItems() {
		Item[] items = new Item[MAX_ITEMS];
		int i = 0;
		for (InventoryItem item: this.items) {
			if (item != null){
				items[i] = item.getItem();
				i++;
			}
		}
		return items;
	}

	/**
	 * Obtenir un item via son index
	 *
	 * @param index Index
	 * @return Item
	 */
	public Item getByIndex(int index) {
		return this.items[index].getItem();
	}

	/**
	 * Obtenir un item via son ID
	 *
	 * @param id ID
	 * @return Item, null si aucun item a cet ID
	 */
	public Item getByID(int id) {
		for (InventoryItem i : this.items) {
			if (i.getItem().getID() == id)
				return i.getItem();
		}
		return null;
	}

	/**
	 * Obtenir un item via son index puis le supprimer
	 *
	 * @param index Index
	 * @return Item
	 */
	public Item getByIndexAndRemove(int index) {
		Item i = this.getByIndex(index);
		this.remove(i);
		return i;
	}

	/**
	 * Obtenir un item via son ID puis le supprimer
	 *
	 * @param id ID
	 * @return Item, null si aucun item a cet ID
	 */
	public Item getByIDAndRemove(int id) {
		Item i = this.getByID(id);
		this.remove(i);
		return i;
	}

	public int getNumber(Item i){
		for (InventoryItem items:
			 this.items) {
			if (items != null)
			if (items.getItem().equals(i)){
				return items.getNumber();
			}
		}
		return 0;
	}

	/**
	 * Contient l'item
	 *
	 * @param i Item
	 * @return true si contient l'item, false sinon
	 */
	public boolean contains(Item i) {
		return (this.indexOf(i) >= 0);
	}

	/**
	 * Retroune l'index d'un item
	 * @param i Item
	 * @return Son index
	 */
	public int indexOf(Item i){
		for(int j = 0; j < this.items.length; j++){
			if(this.items[j] != null && this.items[j].getItem().equals(i))
				return j;
		}
		return -1;
	}

	/**
	 * Interverti l'emplacement des 2 items
	 * @param index1 Item 1
	 * @param index2 Item 2
	 */
	public void swap(int index1, int index2){
		Item tmp = this.items[index1].getItem();
		this.items[index1].setItem(this.items[index2].getItem());
		this.items[index2].setItem(tmp);
	}

	/**
	 * L'inventaire est-il plein?
	 * @return true si l'inventaire est plein, false sinon
	 */
	public boolean isFull(){
		for(InventoryItem i : this.items){
			if(i == null)
				return false;
		}
		return true;
	}

	/**
	 * L'inventaire est-il vide?
	 * @return true si l'inventaire est vide, false sinon
	 */
	public boolean isEmpty(){
		for(InventoryItem i : this.items){
			if(i != null)
				return false;
		}
		return true;
	}
}