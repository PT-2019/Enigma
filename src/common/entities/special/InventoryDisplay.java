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
    private final static int ROW_LENGTH = 5;
    private final static int ACTOR_WIDTH = 100;
    private final static int ACTOR_HEIGHT = 100;
    private final static int MARGIN = 2;
    private ArrayList<Cell> cells;

    public InventoryDisplay(String title, Skin skin) {
        super(title, skin);
        this.cells = new ArrayList<>();

        Player p = new Player(0);
        Key k = new Key(1);
        Book b = new Book(2);
        k.setAtlas("assets/files/atlas/items.atlas","key");
        b.setAtlas("assets/files/atlas/items.atlas","redBook");
        p.addItem(k);
        p.setItemInRightHand(b);

        this.showInventory(p);
    }

    public boolean showInventory(Container c){
        int j = 1;
        int bottomSpace = 20;
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);

        ArrayList<Item> items = c.getItems();
        int rowNumber = items.size() / ROW_LENGTH;

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

            for(int i = 0; i < ROW_LENGTH - 2; i++)
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
            rowNumber++;
        }

        int windowWidth = (ROW_LENGTH * ACTOR_WIDTH) + (MARGIN * 10 * ROW_LENGTH);
        int windowHeight = (rowNumber * ACTOR_HEIGHT) + (MARGIN * 10 * rowNumber);
        this.setSize(windowWidth,windowHeight);

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

            if((j % ROW_LENGTH) == 0)
                table.row();
            j++;
        }

        for(Cell cell : this.cells)
            cell.width(ACTOR_WIDTH).height(ACTOR_HEIGHT);

        this.addActor(table);

        return true;
    }
}
