package common.entities.special.inventory;

import api.libgdx.utils.LibgdxUtility;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import common.entities.Consumable;
import common.entities.Item;
import common.entities.consumable.Book;
import common.entities.players.Player;
import common.entities.special.Inventory;
import common.entities.special.inventory.manager.Select;
import common.entities.special.inventory.manager.Throw;
import common.entities.special.inventory.manager.Use;
import common.entities.types.Container;
import common.language.GameFields;
import common.language.GameLanguage;
import common.map.GameMap;

import java.util.ArrayList;

/**
 * Vue de l'inventaire
 */
public class InventoryDisplay extends Window {
    /**
     * Taille des icônes de l'inventaire
     */
    public final static int ACTOR_WIDTH = 80;
    public final static int ACTOR_HEIGHT = 80;
    public final static int MARGIN = 2;

    /**
     * Stage du drag and drop
     */
    private final Stage dnd;

    private Label name;

    private Label quantity;

    private TextButton throwButton;

    private TextButton useButton;
    /**
     * Ces boutons représente les cases de l'inventaire
     */
    private ButtonInventory[] buttonInventory;
    /**
     * Ces boutons représente les mains du personnage
     */
    private ButtonInventory[] handInventory;
    /**
     * Main gauche
     */
    public static final int LEFT = 0;
    /**
     * Main droite
     */
    public static final int RIGHT = 1;
    /**
     * Nombre de colonne par ligne dans l'inventaire
     */
    private int rowLength;
    /**
     * Case de l'inventaire sélectionner
     */
    private ButtonInventory selected;
    /**
     * Modèle de l'inventaire
     */
    private Inventory inventory;

    private Container container;

    private static final String SKIN_PATH = "assets/files/atlas/inventory.json";

    private static final String ATLAS_PATH = "assets/files/atlas/uiskin.atlas";

    private Table table;

    /**
     * Textes
     */
    private static final String QUANTITY = "Quantité";
    private static final String THROW = "Jeter";
    private static final String USE = "Utiliser";

    public InventoryDisplay(Container c, Stage dnd) {
        super("", LibgdxUtility.loadSkin(SKIN_PATH, ATLAS_PATH));
        this.dnd = dnd;
        init();
        this.throwButton = new TextButton(THROW, this.getSkin());
        this.useButton = new TextButton(USE, this.getSkin());
        this.throwButton.addListener(new Throw(this));
        this.useButton.addListener(new Use(this));
        this.buttonInventory = new ButtonInventory[15];
        this.handInventory = new ButtonInventory[2];
        this.container = c;
        this.initInventory();
        this.setVisible(false);
        this.refreshInfo();
    }

    public InventoryDisplay(Stage dnd) {
        super("", LibgdxUtility.loadSkin(SKIN_PATH, ATLAS_PATH));
        this.dnd = dnd;
        this.init();
        this.setVisible(false);
    }

    private void init(){
        this.name = new Label("", this.getSkin());
        this.quantity = new Label("", this.getSkin());
        this.rowLength = 5;
        this.table = new Table(this.getSkin());
    }

    /**
     * Affiche l'inventaire
     * @return
     */
    private void initInventory(){
        this.selected = null;
        int j = 1;
        int bottomSpace = 20;
        int tablePad = 10;
        int index = 0;
        table.setFillParent(true);
        table.bottom();
        table.left();
        table.pad(tablePad);

        ArrayList<Item> items = container.getItems();

        if(container instanceof Player){
            this.inventory = ((Player) container).getInventory();
            Player p = (Player) container;
            ButtonInventory buttonRight = new ButtonInventory(this);
            ButtonInventory buttonLeft = new ButtonInventory(this);
            this.handInventory[RIGHT] = buttonRight;
            this.handInventory[LEFT] = buttonLeft;

            //mains
            if(p.holdItemInLeftHand()){
                Consumable i = (Consumable) p.getItemInLeftHand();
                buttonLeft.setItem(i);
                buttonLeft.refreshButton();
            }
            buttonLeft.addListener(new Select(this));

            if(p.holdItemInRightHand()) {
                Consumable i = (Consumable) p.getItemInRightHand();
                buttonRight.setItem(i);
                buttonRight.refreshButton();
            }
            buttonRight.addListener(new Select(this));

            table.add(buttonLeft).padBottom(bottomSpace).width(ACTOR_WIDTH).height(ACTOR_HEIGHT);
            for(int i = 2; i < this.rowLength; i++)
                table.add();
            table.add(buttonRight).padBottom(bottomSpace).width(ACTOR_WIDTH).height(ACTOR_HEIGHT);
            table.row();
        }

        //inventaire
        for(Item item : items){
            ButtonInventory button = new ButtonInventory(this, item);
            this.buttonInventory[index] = button;

            button.addListener(new Select(this));
            table.add(button).pad(MARGIN).width(ACTOR_WIDTH).height(ACTOR_HEIGHT);

            if((j % this.rowLength) == 0)
                table.row();
            j++;
            index++;
        }

        int cosplan = this.rowLength / 2;

        table.row();
        table.add(this.name).colspan(this.rowLength);
        table.row();
        table.add(this.quantity).colspan(this.rowLength);
        if(container instanceof Player) {
            table.row();
            table.add(this.useButton).colspan(cosplan);

            if ((this.rowLength % 2) != 0) {
                for (int i = (cosplan * 2); i < this.rowLength; i++)
                    table.add();
            }
            table.add(this.throwButton).colspan(cosplan);
        }

        this.addActor(table);
        this.setWidth(table.getPrefWidth());
        this.setHeight(table.getPrefWidth() + 15);
    }

    /**
     * Lorsqu'on change de container il faut invoquer cette méthode pour que la vue
     * s'adapte à ce nouveau conteneur
     */
    public void changeView(){
        this.clear();
        ArrayList<Item> items = container.getItems();
        int sizeItems = items.size();
        int tablePad = 10;
        table = new Table(this.getSkin());
        table.setFillParent(true);
        table.bottom();
        table.left();
        table.pad(tablePad);

        //pour l'esthétisme
        if (sizeItems < 5){
            buttonInventory = new ButtonInventory[5];
            for (int i = 0; i < 5; i++) {
                if (sizeItems > i){
                    buttonInventory[i] = new ButtonInventory(this, items.get(i));
                }else{
                    buttonInventory[i] = new ButtonInventory(this);
                }
                table.add(buttonInventory[i]).pad(MARGIN).width(ACTOR_WIDTH).height(ACTOR_HEIGHT);
            }
        }else{
            buttonInventory = new ButtonInventory[sizeItems];
            for (int i = 0; i < sizeItems; i++) {
                buttonInventory[i] = new ButtonInventory(this, items.get(i));
                table.add(buttonInventory[i]).pad(MARGIN).width(ACTOR_WIDTH).height(ACTOR_HEIGHT);

                if((i % this.rowLength) == 0)
                    table.row();
            }
        }

        this.addActor(table);
        this.setWidth(table.getPrefWidth());
        this.setHeight(table.getPrefHeight() + 15);
    }

    /**
     * Permet de rafraichir l'inventaire
     */
    public void addItem(Item item){
        for (int i = 0; i < buttonInventory.length; i++) {
            if (buttonInventory[i].getItem() == null){
                buttonInventory[i].setItem(item);
                buttonInventory[i].refreshButton();
                break;
            }
        }
    }

    /**
     * Permet de refresh la vue en bas
     */
    public void refreshInfo() {
        if (this.selected != null) {
            if (this.selected.getItem() != null) {
                String msg;
                if (this.selected.getItem() instanceof Book)
                    msg = GameLanguage.gl.get(GameFields.BOOK);
                else
                    msg = GameLanguage.gl.get(GameFields.KEY);
                this.name.setText(msg);

                if(this.inventory.getNumber(this.selected.getItem()) == 0)
                    this.quantity.setText(QUANTITY + ": 1");
                else
                    this.quantity.setText(QUANTITY + ": " + this.inventory.getNumber(this.selected.getItem()));


                this.useButton.setDisabled(false);
                this.throwButton.setDisabled(false);
                return;
            }
        }
        this.name.setText("");
        this.quantity.setText("");
        this.useButton.setDisabled(true);
        this.throwButton.setDisabled(true);
    }

    /**
     * Méthode qui permet de retirer un item d'un bouton inventaire tout en vérifiant la quantité
     * @param item
     */
    public void removeItem(Item item, ButtonInventory buttonInventory){
        //si la quantité n'est pas égale à zéro c'est qu'il en reste
        selected = null;
        buttonInventory.setItem(null);
        buttonInventory.refreshButton();
    }

    /**
     * Enlève un item des cases des mains
     */
    public void removeHandItem(ButtonInventory buttonInventory){
        if(this.selected != null){
            this.selected.setSelected(false);
        }
        this.selected = null;
        buttonInventory.setItem(null);
        buttonInventory.removeActor(buttonInventory.getImg());
    }

    public void setSelectItem(ButtonInventory i) {
        if(this.selected != null){
            this.selected.setSelected(false);
        }
        this.selected = i;
        i.setSelected(true);
        this.refreshInfo();
    }

    public ButtonInventory getSelectedItem() {
        return this.selected;
    }

    public int getRowLength() {
        return rowLength;
    }

    public void setRowLength(int rowLength) {
        this.rowLength = rowLength;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ButtonInventory[] getButtonInventory(){
        return this.buttonInventory;
    }

    public ButtonInventory[] getHandInventory() {
        return handInventory;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Stage getDnd() {
        return dnd;
    }
}
