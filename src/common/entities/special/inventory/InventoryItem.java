package common.entities.special.inventory;

import common.entities.Item;

/**
 * Item et sa quantité utilisé dans l'inventaire
 */
public class InventoryItem {

    /**
     * Le type d'item utilisé
     */
    private Item item;

    /**
     * Nombre d'item posséder
     */
    private int number;

    public InventoryItem(Item item, int number){
        this.item = item;
        this.number = number;
    }

    public Item getItem() {
        return item;
    }

    public int getNumber() {
        return number;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void plusNumber() {
        number++;
    }

    public void lessNumber() {
        number--;
    }
}
