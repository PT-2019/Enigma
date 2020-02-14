package common.entities.special;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import common.entities.Item;
import common.entities.consumable.Key;
import common.entities.players.Player;
import common.entities.types.Container;

public class InventoryDisplay extends Window {
    public final static int ROW_LENGTH = 5;

    public InventoryDisplay(String title, Skin skin) {
        super(title, skin);

        this.setSize(500,500);

        Player p = new Player(0);
        Key k = new Key(1);
        k.setAtlas("assets/files/atlas/items.atlas","key");
        p.addItem(k);

        this.showInventory(p);
    }

    public boolean showInventory(Container c){
        int j = 1;
        Table table = new Table();

        table.setFillParent(true);
        table.setDebug(true);
        table.pad(2);
        table.padLeft(3);
        table.padTop(20);

        int actorWidth = (int) (this.getWidth() / ROW_LENGTH);
        int actorHeight = (int) (this.getHeight() / ((Inventory.MAX_ITEMS / ROW_LENGTH) + 1));

        /*if(c instanceof Player){
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
                table.add(stack).pad(2).fill().expand().padBottom(20);
            } else
                table.add().pad(2).fill().expand().padBottom(20);

            if(p.holdItemInRightHand()){
                Stack stack = new Stack();
                Item i = p.getItemInRightHand();
                Image img = new Image(
                        new TextureAtlas(i.getAtlasPath())
                                .findRegion(i.getAtlasRegionName())
                );
                stack.add(img);
                table.add(stack).pad(2).fill().expand().padBottom(20);
            } else
                table.add().pad(2).fill().expand().padBottom(20);

            table.row();
        }*/

        //inventaire
        for(Item i : c.getItems()){
            Stack stack = new Stack();

            if(i != null) {
                Image img = new Image(
                        new TextureAtlas(i.getAtlasPath())
                                .findRegion(i.getAtlasRegionName())
                );
                stack.add(img);
                table.add(stack).pad(2).width(actorWidth).height(actorHeight);
            } else
                table.add().pad(2).width(actorWidth).height(actorHeight);

            if((j % ROW_LENGTH) == 0)
                table.row();
            j++;
        }

        this.addActor(table);

        return true;
    }
}
