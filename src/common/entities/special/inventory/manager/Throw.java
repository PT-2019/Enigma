package common.entities.special.inventory.manager;

import api.libgdx.actor.GameActor;
import api.libgdx.utils.CheckEventType;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.utils.Array;
import common.entities.GameObject;
import common.entities.Item;
import common.entities.players.PlayerGame;
import common.entities.special.inventory.ButtonInventory;
import common.entities.special.inventory.InventoryDisplay;
import common.map.AbstractMap;
import common.map.GameMap;
import data.Direction;
import data.Layer;
import game.EnigmaGame;

import java.util.HashMap;

/**
 * Gère les évènements lorsque le joueur jette un item
 */
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

                for (GameActor game: map.getGameEntities()) {
                    if (game instanceof PlayerGame){
                        float posX,posY;
                        Direction direct = ((PlayerGame) game).getFacedDirection();
                        if (direct == Direction.BOTTOM){
                            posX = game.getX();
                            posY = game.getY() - 10;
                        }else if(direct == Direction.TOP){
                            posX = game.getX();
                            posY = game.getY() + 10;
                        }else if(direct == Direction.LEFT){
                            posX = game.getX() - 10;
                            posY = game.getY();
                        }else {
                            posX = game.getX() + 10;
                            posY = game.getY();
                        }
                        if(!(map.collision(game,posX,posY) && map.isWalkable(game,posX,posY))){
                            map.set(item, AbstractMap.posToIndex(posX,posY,map));
                        }else{
                            System.out.println("impossible de déposer l'objet");
                        }
                    }
                }
                display.refreshInfo();
            }
        }
        return false;
    }
}
