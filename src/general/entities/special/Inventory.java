package general.entities.special;

import general.entities.Item;

import java.util.ArrayList;

/**
 * Inventaire d'un joueur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see general.entities.players.Player
 * @since 5.0
 */
public class Inventory {

    /***
     * Items de l'inventaire
     */
    private ArrayList<Item> items;

    /**
     * Maximum d'items stockables
     */
    public final static int MAX_ITEMS = 20;

    public Inventory(){
        this.items = new ArrayList<>();
    }

    /**
     * Ajouter un item
     * @param i Item
     */
    public void add(Item i){
        if(this.items.size() < MAX_ITEMS)
            this.items.add(i);
        else
            throw new IllegalStateException("Inventaire plein");
    }

    /**
     * Retirer un item
     * @param i Item
     */
    public void remove(Item i){
        this.items.remove(i);
    }

    /**
     * Obtenir tous les items
     * @return Liste des items
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Item> getItems(){
        return (ArrayList<Item>) this.items.clone();
    }

    /**
     * Obtenir un item via son index
     * @param index Index
     * @return Item
     */
    public Item getByIndex(int index){
        return this.items.get(index);
    }

    /**
     * Obtenir un item via son ID
     * @param id ID
     * @return Item, null si aucun item a cet ID
     */
    public Item getByID(int id){
        for(Item i : this.items){
            if(i.getID() == id)
                return i;
        }
        return null;
    }

    /**
     * Obtenir un item via son index puis le supprimer
     * @param index Index
     * @return Item
     */
    public Item getByIndexAndRemove(int index){
        Item i = this.getByIndex(index);
        this.remove(i);
        return i;
    }

    /**
     * Obtenir un item via son ID puis le supprimer
     * @param id ID
     * @return Item, null si aucun item a cet ID
     */
    public Item getByIDAndRemove(int id){
        Item i = this.getByID(id);
        this.remove(i);
        return i;
    }

    /**
     * Contient l'item
     * @param i Item
     * @return true si contient l'item, false sinon
     */
    public boolean contains(Item i){
        return this.items.contains(i);
    }
}
