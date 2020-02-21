package common.entities.special.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import common.entities.Consumable;
import common.entities.Item;
import common.entities.consumable.Book;
import common.entities.consumable.Key;
import common.entities.players.Player;
import common.entities.special.inventory.manager.Select;
import common.entities.special.inventory.manager.Throw;
import common.entities.special.inventory.manager.Use;
import common.entities.types.Container;
import common.language.GameFields;
import common.language.GameLanguage;
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
    private Inventory container;

    public static final String SKIN_PATH = "assets/files/atlas/inventory.json";

    public static final String ATLAS_PATH = "assets/files/atlas/uiskin.atlas";

    /**
     * Textes
     */
    private static final String QUANTITY = "Quantité";
    private static final String THROW = "Jeter";
    private static final String USE = "Utiliser";

    public InventoryDisplay( Skin skin) {
        super("", skin);
        this.name = new Label("", this.getSkin());
        this.quantity = new Label("", this.getSkin());
        this.throwButton = new TextButton(THROW, this.getSkin());
        this.useButton = new TextButton(USE, this.getSkin());
        this.rowLength = 5;
        this.throwButton.addListener(new Throw(this));
        this.useButton.addListener(new Use(this));
        this.buttonInventory = new ButtonInventory[15];
        this.handInventory = new ButtonInventory[2];

        Player p = new Player(0);
        Key k = new Key(1);
        Key k2 = new Key(3);
        Book b = new Book(2);
        k.setAtlas("assets/files/atlas/items.atlas","key");
        b.setAtlas("assets/files/atlas/items.atlas","redBook");
        k2.setAtlas("assets/files/atlas/items.atlas","key");

        p.addItem(k);
        p.addItem(k2);
        p.setItemInRightHand(b);
        container = p.getInventory();

        this.showInventory(p);
        this.refreshInfo();
    }

    /**
     * Affiche l'inventaire
     * @param c
     * @return
     */
    public boolean showInventory(Container c){
        this.selected = null;
        int j = 1;
        int bottomSpace = 20;
        int tablePad = 10;
        int index = 0;
        Table table = new Table(this.getSkin());
        table.setFillParent(true);
        table.bottom();
        table.left();
        table.pad(tablePad);

        ArrayList<Item> items = c.getItems();

        if(c instanceof Player){
            Player p = (Player) c;
            ButtonInventory buttonRight = new ButtonInventory(this.getSkin());
            ButtonInventory buttonLeft = new ButtonInventory(this.getSkin());
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
            ButtonInventory button = new ButtonInventory(this.getSkin(),item);
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
        table.row();
        table.add(this.useButton).colspan(cosplan);

        if((this.rowLength % 2) != 0) {
            for(int i = (cosplan * 2); i < this.rowLength; i++)
                table.add();
        }
        table.add(this.throwButton).colspan(cosplan);
        this.addActor(table);
        this.setWidth(table.getPrefWidth());
        this.setHeight(table.getPrefWidth() + 15);

        return true;
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

                if(this.container.getNumber(this.selected.getItem()) == 0)
                    this.quantity.setText(QUANTITY + ": 1");
                else
                    this.quantity.setText(QUANTITY + ": " + this.container.getNumber(this.selected.getItem()));


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
     * Méthode qui permet de retirer un item d'un boutton inventaire
     * @param item
     */
    public void removeItem(Item item,ButtonInventory buttonInventory){
        //si la quantité n'est pas égale à zéro c'est qu'il en reste
        if (this.container.getNumber(item) == 0){
            selected = null;
            buttonInventory.setItem(null);
            buttonInventory.removeActor(buttonInventory.getImg());
        }
    }

    /**
     * Enlève un item des cases des mains
     */
    public void removeHandItem(ButtonInventory buttonInventory){
        selected = null;
        buttonInventory.setItem(null);
        buttonInventory.removeActor(buttonInventory.getImg());
    }

    public void setSelectItem(ButtonInventory i) {
        this.selected = i;
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

    public Inventory getContainer() {
        return container;
    }

    public ButtonInventory[] getButtonInventory(){
        return this.buttonInventory;
    }

    public ButtonInventory[] getHandInventory() {
        return handInventory;
    }
}
