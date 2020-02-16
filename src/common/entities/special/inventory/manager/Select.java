package common.entities.special.inventory.manager;

import api.libgdx.utils.CheckEventType;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import common.entities.Item;
import common.entities.special.inventory.InventoryDisplay;

public class Select implements EventListener {
    private InventoryDisplay display;

    public Select(InventoryDisplay display){
        this.display = display;
    }

    @Override
    public boolean handle(Event event) {
        if(CheckEventType.isMouseClicked(event)) {
            System.out.println("----");
        }
        return false;
    }
}
