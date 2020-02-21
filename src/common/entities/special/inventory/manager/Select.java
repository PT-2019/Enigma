package common.entities.special.inventory.manager;

import api.libgdx.utils.CheckEventType;
import api.libgdx.utils.LibgdxUtility;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.SnapshotArray;
import common.entities.Consumable;
import common.entities.Item;
import common.entities.consumable.Stackable;
import common.entities.special.inventory.ButtonInventory;
import common.entities.special.inventory.InventoryDisplay;

/**
 * lorsqu'on sélectionne un item
 */
public class Select extends ClickListener {
    private InventoryDisplay display;

    public Select(InventoryDisplay display){
        this.display = display;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        System.out.println("----");
        Actor target = event.getTarget();
        Item current;

        if (! (target instanceof ButtonInventory)){
            target = target.getParent();
        }
        //on prend l'item de la target
        current = ((ButtonInventory) target).getItem();

        display.setSelectItem((ButtonInventory) target);
        display.refreshInfo();
        System.out.println("selectted");

        if (current == null)
            return false;
        //si c'est un click droit alors c'est un déplacement d'item
        if(Input.Buttons.RIGHT == event.getButton()){
            //on regarde si l'objet fait partie des mains ou non du joueur
            if(display.getInventory().contains(current)){
                ButtonInventory[] hand = display.getHandInventory();

                if (hand[InventoryDisplay.RIGHT].getItem() == null && (!target.equals(hand[InventoryDisplay.LEFT]))){
                    hand[InventoryDisplay.RIGHT].setItem(current);
                    hand[InventoryDisplay.RIGHT].refreshButton();
                    //on enlève de l'inventaire
                    display.getInventory().remove(current);
                    display.removeItem(current,(ButtonInventory)target);
                }else if (hand[InventoryDisplay.LEFT].getItem() == null && (!target.equals(hand[InventoryDisplay.RIGHT]))){
                    //on met dans la main gauche l'objet
                    hand[InventoryDisplay.LEFT].setItem(current);
                    hand[InventoryDisplay.LEFT].refreshButton();
                    //on l'enlève de l'inventaire
                    display.getInventory().remove(current);
                    display.removeItem(current,(ButtonInventory)target);
                }/*else if(hand[InventoryDisplay.LEFT].getItem() != null || hand[InventoryDisplay.RIGHT].getItem() != null){
                    todo ce morceaux de code ne sert a rien actuellement car le modèle ne permet pas d'implémenter des items stackable
                    //pour que ce soit que quand on click sur les mains
                    if(target.equals(hand[InventoryDisplay.LEFT]) || target.equals(hand[InventoryDisplay.RIGHT])) {
                        //si on a un item qui est identique dans l'inventaire et qu'on le possède dans la main
                        ButtonInventory[] buttons = display.getButtonInventory();
                        for (ButtonInventory buttonInventory : buttons) {
                            if (buttonInventory.getItem().getClass() == current.getClass() && current instanceof Stackable) {
                                display.removeHandItem((ButtonInventory) target);
                                display.getInventory().add(current);
                                break;
                            }
                        }
                    }
                }*/
            }else{
                try{
                    ButtonInventory[] buttons = display.getButtonInventory();
                    //on regarde tout les boutons pour trouver une place pour notre item
                    for (ButtonInventory buttonInventory: buttons) {
                        if (buttonInventory.getItem() == null){
                            display.removeItem(current,(ButtonInventory)target);
                            display.getInventory().add(current);
                            buttonInventory.setItem(current);
                            buttonInventory.refreshButton();
                            break;
                        }
                    }
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }
            }
            display.refreshInfo();
        }
        return true;
    }
}
