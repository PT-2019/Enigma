package common.entities.special;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import common.entities.Item;
import common.entities.consumable.Book;
import common.entities.consumable.Key;
import common.entities.players.Player;
import common.entities.types.Container;

import java.util.ArrayList;

public class InventoryDisplay extends Window {
    private final static int ACTOR_WIDTH = 80;
    private final static int ACTOR_HEIGHT = 80;
    private final static int MARGIN = 2;
    private ArrayList<Cell> cells;
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
        this.cells = new ArrayList<>();
        this.rowLength = 5;
        this.name = new Label("", this.getSkin());
        this.quantity = new Label("", this.getSkin());
        this.throwButton = new TextButton(THROW, this.getSkin());
        this.useButton = new TextButton(USE, this.getSkin());

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
        int j = 1;
        int bottomSpace = 20;
        int tablePad = 10;
        Table table = new Table(this.getSkin());
        table.setFillParent(true);
        table.setDebug(true);
        table.bottom();
        table.left();
        table.pad(tablePad);

        ArrayList<Item> items = c.getItems();

        if(c instanceof Player){
            Player p = (Player) c;
            //mains
            if(p.holdItemInLeftHand()){
                Stack stack = new Stack();
                Item i = p.getItemInLeftHand();
                Image img = new Image(
                        new TextureAtlas(i.getAtlasPath())
                                .findRegion(i.getAtlasRegionName())
                );
                stack.add(img);
                this.cells.add(table.add(stack).padBottom(bottomSpace));
            } else
                this.cells.add(table.add().padBottom(bottomSpace));

            for(int i = 2; i < this.rowLength; i++)
                table.add();

            if(p.holdItemInRightHand()){
                Stack stack = new Stack();
                Item i = p.getItemInRightHand();
                Image img = new Image(
                        new TextureAtlas(i.getAtlasPath())
                                .findRegion(i.getAtlasRegionName())
                );
                stack.add(img);
                this.cells.add(table.add(stack).padBottom(bottomSpace));
            } else
                this.cells.add(table.add().padBottom(bottomSpace));

            table.row();
        }

        //inventaire
        for(Item i : items){
            Stack stack = new Stack();

            if(i != null) {
                Image img = new Image(
                        new TextureAtlas(i.getAtlasPath())
                                .findRegion(i.getAtlasRegionName())
                );
                stack.add(img);
                this.cells.add(table.add(stack).pad(MARGIN));
            } else
                this.cells.add(table.add().pad(MARGIN));

            if((j % this.rowLength) == 0)
                table.row();
            j++;
        }

        for(Cell cell : this.cells)
            cell.width(ACTOR_WIDTH).height(ACTOR_HEIGHT);

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

    public void setSelectItem(Item i) {
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
