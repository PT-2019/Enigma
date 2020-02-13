package common.entities.special;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import common.entities.Item;
import common.entities.consumable.Key;
import common.entities.players.Player;
import common.entities.types.Container;

public class InventoryDisplay extends Window {
    public InventoryDisplay(String title, Skin skin) {
        super(title, skin);
        Player p = new Player(0);
        Key k = new Key(1);
        k.setAtlas("assets/files/atlas/items.atlas","key");
        p.addItem(k);
        this.showInventory(p);
    }

    public boolean showInventory(Container c){
        int j = 0;
        Table table = new Table();
        table.setFillParent(true);

        for(Item i : c.getItems()){
            if(i == null)
                table.add();
            else {
                Stack s = new Stack();
                s.add(new Image(
                        new TextureAtlas(i.getAtlasPath()).findRegion(i.getAtlasRegionName())
                ));
                table.add(s);
            }

            if(j > 0 && (Inventory.MAX_ITEMS % j) == 0)
                table.row();
            j++;
        }

        this.addActor(table);

        return true;
    }
}
