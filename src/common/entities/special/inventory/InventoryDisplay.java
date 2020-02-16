package common.entities.special.inventory;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import common.entities.Item;
import common.entities.consumable.Book;
import common.entities.consumable.Key;
import common.entities.players.Player;
import common.entities.special.inventory.manager.Select;
import common.entities.special.inventory.manager.Throw;
import common.entities.special.inventory.manager.Use;
import common.entities.types.Container;

import java.util.ArrayList;

public class InventoryDisplay extends Window {
    private final static int ACTOR_WIDTH = 80;
    private final static int ACTOR_HEIGHT = 80;
    private final static int MARGIN = 2;
    private Label name;
    private Label quantity;
    private TextButton throwButton;
    private TextButton useButton;
    private int rowLength;
    private Item selected;

    /**
     * Textes
     */
    private static final String QUANTITY = "Quantité";
    private static final String THROW = "Jeter";
    private static final String USE = "Utiliser";

    public InventoryDisplay(String title, Skin skin) {
        super(title, skin);
        this.rowLength = 5;
        this.name = new Label("", this.getSkin());
        this.quantity = new Label("", this.getSkin());
        this.throwButton = new TextButton(THROW, this.getSkin());
        this.useButton = new TextButton(USE, this.getSkin());

        this.throwButton.addListener(new Throw(this));
        this.useButton.addListener(new Use(this));

        Player p = new Player(0);
        Key k = new Key(1);
        Book b = new Book(2);
        k.setAtlas("assets/files/atlas/items.atlas","key");
        b.setAtlas("assets/files/atlas/items.atlas","redBook");
        p.addItem(k);
        p.setItemInRightHand(b);

        this.showInventory(p);
        this.refreshInfo();
    }

    public boolean showInventory(Container c){
        this.selected = null;
        int j = 1;
        int bottomSpace = 20;
        int tablePad = 10;
        ButtonGroup group = new ButtonGroup();
        Table table = new Table(this.getSkin());
        table.setFillParent(true);
        table.bottom();
        table.left();
        table.pad(tablePad);

        ArrayList<Item> items = c.getItems();

        if(c instanceof Player){
            Player p = (Player) c;
            Button buttonRight = new Button(this.getSkin());
            Button buttonLeft = new Button(this.getSkin());
            //mains
            if(p.holdItemInLeftHand()){
                Item i = p.getItemInLeftHand();
                Image img = new Image(
                        new TextureAtlas(i.getAtlasPath())
                                .findRegion(i.getAtlasRegionName())
                );
                buttonLeft.add(img).width(ACTOR_WIDTH).height(ACTOR_HEIGHT);
                buttonLeft.addListener(new Select(this));
                group.add(buttonLeft);
            }

            if(p.holdItemInRightHand()) {
                Item i = p.getItemInRightHand();
                Image img = new Image(
                        new TextureAtlas(i.getAtlasPath())
                                .findRegion(i.getAtlasRegionName())
                );
                buttonRight.add(img).width(ACTOR_WIDTH).height(ACTOR_HEIGHT);
                buttonRight.addListener(new Select(this));
                group.add(buttonRight);
            }

            table.add(buttonLeft).padBottom(bottomSpace).width(ACTOR_WIDTH).height(ACTOR_HEIGHT);
            for(int i = 2; i < this.rowLength; i++)
                table.add();
            table.add(buttonRight).padBottom(bottomSpace).width(ACTOR_WIDTH).height(ACTOR_HEIGHT);
            table.row();
        }

        //inventaire
        for(Item i : items){
            Button button = new Button(this.getSkin());

            if(i != null) {
                Image img = new Image(
                        new TextureAtlas(i.getAtlasPath())
                                .findRegion(i.getAtlasRegionName())
                );
                button.add(img).width(ACTOR_WIDTH).height(ACTOR_HEIGHT);
                button.addListener(new Select(this));
                group.add(button);
            }

            table.add(button).pad(MARGIN).width(ACTOR_WIDTH).height(ACTOR_HEIGHT);

            if((j % this.rowLength) == 0)
                table.row();
            j++;
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

    private void refreshInfo() {
        if(this.selected != null) {
            this.name.setText("");
            this.quantity.setText(QUANTITY + ": " + "");
            this.useButton.setDisabled(false);
            this.throwButton.setDisabled(false);
        } else {
            this.name.setText("");
            this.quantity.setText("");
            this.useButton.setDisabled(true);
            this.throwButton.setDisabled(true);
        }
    }

    protected void setSelectItem(Item i) {
        this.selected = i;
        this.refreshInfo();
    }

    public Item getSelectedItem() {
        return this.selected;
    }

    public int getRowLength() {
        return rowLength;
    }

    public void setRowLength(int rowLength) {
        this.rowLength = rowLength;
    }
}
