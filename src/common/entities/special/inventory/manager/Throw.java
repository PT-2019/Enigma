package common.entities.special.inventory.manager;

import api.libgdx.actor.GameActor;
import api.libgdx.utils.CheckEventType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import common.entities.GameObject;
import common.entities.Item;
import common.entities.special.inventory.ButtonInventory;
import common.entities.special.inventory.InventoryDisplay;
import common.map.GameMap;
import common.save.entities.serialization.EntityFactory;
import common.save.entities.serialization.EntitySerializable;
import data.Layer;
import game.EnigmaGame;

import java.util.Arrays;
import java.util.HashMap;

public class Throw implements EventListener {
    private InventoryDisplay display;

    public Throw(InventoryDisplay display){
        this.display = display;
    }

    @Override
    public boolean handle(Event event) {
        ButtonInventory inventory;
        Item item;
        if(CheckEventType.isMouseClicked(event)) {
            inventory = display.getSelectedItem();
            item = inventory.getItem();
            if(item != null) {
                //enlève de l'inventaire
                display.removeItem(item,inventory);

                //on pose l'objet par terre
                GameMap map = (GameMap) EnigmaGame.getCurrentScreen().getMap();
                map.addEntityToMap((GameActor) item);

                display.refreshInfo();
            }
        }
        return false;
    }
}
