package common.entities.special.inventory.manager;

import api.libgdx.utils.CheckEventType;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import common.dialog.Dialog;
import common.dialog.EnigmaDialogPopup;
import common.entities.Item;
import common.entities.special.inventory.ButtonInventory;
import common.entities.special.inventory.InventoryDisplay;
import common.entities.types.Content;
import common.map.GameMap;
import game.EnigmaGame;
import game.screens.GameScreen;

public class Use implements EventListener {
    private InventoryDisplay display;

    public Use(InventoryDisplay display){
        this.display = display;
    }

    @Override
    public boolean handle(Event event) {
        if(CheckEventType.isMouseClicked(event)) {
            ButtonInventory button = this.display.getSelectedItem();
            if (button != null) {
                Item item = button.getItem();
                if (item != null) {
                    if (item instanceof Content){
                        EnigmaDialogPopup dialog = GameMap.getEnigmaDialog();
                        Dialog dia = new Dialog(((Content) item).getContent());
                        dialog.showDialog(dia, (GameMap) EnigmaGame.getCurrentScreen().getMap());
                    }
                }
            }
        }
        return false;
    }
}
