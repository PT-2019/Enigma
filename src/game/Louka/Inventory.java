package game.Louka;

import api.entity.Item;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> items;

    public final static int MAX_ITEMS = 20;

    public Inventory(){
        this.items = new ArrayList<>();
    }

    public void add(Item i){
        if(this.items.size() < MAX_ITEMS)
            this.items.add(i);
        else
            throw new IllegalStateException("Inventaire plein");
    }

    public void remove(Item i){
        this.items.remove(i);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Item> getItems(){
        return (ArrayList<Item>) this.items.clone();
    }

    public Item getByIndex(int index){
        return this.items.get(index);
    }

    public Item getByID(int id){
        for(Item i : this.items){
            if(i.getID() == id)
                return i;
        }
        return null;
    }

    public Item getByIndexAndRemove(int index){
        Item i = this.getByIndex(index);
        this.remove(i);
        return i;
    }

    public Item getByIDAndRemove(int id){
        Item i = this.getByID(id);
        this.remove(i);
        return i;
    }

    public boolean contains(Item i){
        return this.items.contains(i);
    }
}
