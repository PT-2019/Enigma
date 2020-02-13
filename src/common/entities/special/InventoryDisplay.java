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
        p.addItem(new Key(1));
        this.showInventory(p);
    }

    public boolean showInventory(Container c){
        Table table = new Table();
        table.setFillParent(true);
        for(Item i : c.getItems()){
            System.out.println(i);
            Stack s = new Stack();
            s.add(new Image(
                  new TextureAtlas("assets/files/atlas/items.atlas").findRegion("key")
             ));
            table.add(s);
            table.add();
        }
        this.addActor(table);

        return true;
    }
}
