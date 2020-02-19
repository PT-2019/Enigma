package common.entities.special.inventory;

import common.entities.Item;

public class InventoryItem {

    private Item item;

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
