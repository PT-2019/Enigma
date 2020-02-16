package common.entities.special.inventory.manager;

import api.libgdx.utils.CheckEventType;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import common.entities.Item;
import common.entities.special.inventory.InventoryDisplay;

public class Use implements EventListener {
    private InventoryDisplay display;

    public Use(InventoryDisplay display){
        this.display = display;
    }

    @Override
    public boolean handle(Event event) {
        if(CheckEventType.isMouseClicked(event)) {
            Item item = this.display.getSelectedItem();
            if(item != null){

            }
        }
        return false;
    }
}
