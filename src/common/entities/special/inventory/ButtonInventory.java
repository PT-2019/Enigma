package common.entities.special.inventory;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import common.entities.Consumable;
import common.entities.Item;

/**
 * Bouton qui représente une case de l'inventaire
 */
public class ButtonInventory extends ImageButton {

    private Item item;

    private Image img;

    public ButtonInventory(Skin skin,Item i){
        super(skin);
        this.item = i;
        refreshButton();
    }

    public ButtonInventory(Skin skin){
        super(skin);
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void refreshButton(){
        if(item != null){
            img = new Image(
                    new TextureAtlas(((Consumable)item).getAtlasPath())
                            .findRegion(((Consumable)item).getAtlasRegionName()));
            this.add(img).width(InventoryDisplay.ACTOR_WIDTH)
                    .height(InventoryDisplay.ACTOR_HEIGHT)
                    .padTop(InventoryDisplay.ACTOR_HEIGHT/2);
        }
    }

    public Image getImg() {
        return img;
    }
}
